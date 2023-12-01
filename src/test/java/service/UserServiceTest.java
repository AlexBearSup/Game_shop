package service;

import org.example.service.UserService;
import org.junit.Test;

import java.io.*;
import java.sql.SQLException;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class UserServiceTest {

    @Test
    public void testLoginer () throws SQLException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "alexis\n123\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService(scanner);
        userService.loginer();

        assertTrue(outContent.toString().contains("User is not found !"));


    }
}
