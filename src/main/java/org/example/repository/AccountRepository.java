package org.example.repository;
import org.example.msg.AllGeneralMsg;
import org.example.model.Account;
import java.sql.*;

public class AccountRepository implements org.example.repository.DAO.AccountRepository {
    private final Connection connection;
    private static final String update =
            """
                    UPDATE public.accounts
                    SET amount=?
                    WHERE  user_id = ?;
                                        
            """;
    private static final String select =
            """
                  SELECT * FROM accounts 
                  WHERE user_id = ?
            """;
    private static final String save =
            """
                    INSERT INTO public.accounts(
                    amount, type, user_id)
                    VALUES (?, ?, ?)
            """;
    public AccountRepository(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void updater (int deposit, int userID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setInt(1, deposit);
            preparedStatement.setInt(2, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating account: " + e.getMessage());
        }
    }
    @Override
    public Account taker(int userID)  {
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return Account.builder()
                        .id(resultSet.getInt("id"))
                        .amount(resultSet.getInt("amount"))
                        .type(resultSet.getString("type"))
                        .build();
            }
        } catch (SQLException ex) {
            System.err.println(AllGeneralMsg.USER_NF.getDescription() + ex.getMessage());
        }
        return null;
    }
    @Override
    public void adder (Account account ) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(save, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, 0);
                preparedStatement.setString(2, account.getType());
                preparedStatement.setInt(3, account.getUserID());
                preparedStatement.executeUpdate();
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                generatedKeys.next();
                account.setId(generatedKeys.getInt(1));
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
