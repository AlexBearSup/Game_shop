package org.example.repository;
import java.sql.*;
public class PurchaseRepository implements org.example.repository.DAO.PurchaseRepository {

    Connection connection;

    private static final String save =
            """
                    INSERT INTO public.usergames(
                    user_ID, game_ID)
                    VALUES (?, ?)
            """;
    public PurchaseRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void adder (int userID, int gameID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, gameID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }








}
