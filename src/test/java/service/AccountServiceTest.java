package service;

import connetct.H2Connector;
import org.example.model.Account;
import org.example.model.User;
import org.example.repository.AccountRepository;
import org.example.repository.UserRepository;
import org.example.service.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountServiceTest {
    Connection connection;

    @Before
    public void init () throws SQLException, IOException, ClassNotFoundException {
        connection = H2Connector.get();

    }

    @Test
    public void testReplenisher() throws SQLException {
        String input = "123\n50\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        AccountService accountService = new AccountService(scanner);
        AccountRepository accountRepository = new AccountRepository(connection);
        UserRepository userRepository = new UserRepository(connection);
        accountService.replenisher();

        User testUser = userRepository.taker("123");
        Account testAccount = accountRepository.taker(testUser.getId());

        
        Assert.assertNotNull(testUser);
        Assert.assertNotNull(testAccount);

        Assert.assertEquals(50, testAccount.getAmount());
    }

}
