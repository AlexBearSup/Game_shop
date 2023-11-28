package org.example.repository;
import org.example.model.User;
import java.sql.*;
public class UserRepository implements org.example.repository.DAO.UserRepository {
    private final Connection connection;
    private static final String save =
            """
                    INSERT INTO public.users(
                    name, nickname, birthday, password)
                    VALUES (?, ?, ?, ?)
            """;
    private static final String select =
            """
                  SELECT * FROM users 
                  WHERE nickname = ? 
                  AND password = ?  
            """;
    private static final String selectByPass =
            """
                  SELECT * FROM users 
                  WHERE password = ?
            """;
    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void adder (User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(save, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getNickname());
            preparedStatement.setDate(3, Date.valueOf(user.getBirthday()));
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public User taker (String nickname, String password)  {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, nickname);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .nickname(resultSet.getString("nickname"))
                        .birthday(resultSet.getDate("birthday").toLocalDate())
                        .password(resultSet.getString("password"))
                        .build();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    @Override
    public User taker (String password)  {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectByPass);
            preparedStatement.setString(1, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .nickname(resultSet.getString("nickname"))
                        .birthday(resultSet.getDate("birthday").toLocalDate())
                        .password(resultSet.getString("password"))
                        .build();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
}
