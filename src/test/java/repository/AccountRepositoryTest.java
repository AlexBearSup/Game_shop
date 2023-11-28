package repository;

import connetct.H2Connector;
import org.example.model.Account;
import org.example.model.User;
import org.example.repository.AccountRepository;
import org.example.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class AccountRepositoryTest {
    Connection connection;

    @Before
    public void init () throws SQLException, IOException, ClassNotFoundException {
        connection = H2Connector.get();
    }

    @Test
    public void adderTest(){
        AccountRepository accountRepository = new AccountRepository(connection);
        UserRepository userRepository = new UserRepository(connection);
        User testUser = User.builder()
                .name("Last")
                .nickname("on")
                .password("123")
                .birthday(LocalDate.of(1000, 1, 1))
                .build();
        userRepository.adder(testUser);
        Account account = Account.builder()
                .amount(100)
                .type("VISA")
                .userID(testUser.getId())
                .build();
        accountRepository.adder(account);
        assertEquals(account.getType(),"VISA");
        assertEquals(account.getAmount(),100);
    }
    @Test
    public void takerTest(){
        AccountRepository accountRepository = new AccountRepository(connection);
        assertEquals(50,accountRepository.taker(1).getAmount());
        assertEquals("Mastercard",accountRepository.taker(4).getType());

    }



}



















