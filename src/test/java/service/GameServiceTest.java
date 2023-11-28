package service;

import connetct.H2Connector;
import org.example.service.GameService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class GameServiceTest {
    Connection connection;

    @Before
    public void init () throws SQLException, IOException, ClassNotFoundException {
        connection = H2Connector.get();
    }

    @Test
    public void testDisplayAll() throws SQLException {
          Scanner scanner = new Scanner(System.in);
          ByteArrayOutputStream outContent = new ByteArrayOutputStream();
          System.setOut(new PrintStream(outContent));
          GameService gameService = new GameService(scanner);

          gameService.displayAll();

          Assert.assertTrue(outContent.toString().length() > 0);

    }

}
