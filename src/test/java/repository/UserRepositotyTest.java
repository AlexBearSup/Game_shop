package repository;

import connetct.H2Connector;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserRepositotyTest {

    Connection connection;

    @Before
    public void init () throws SQLException, IOException, ClassNotFoundException {
        connection = H2Connector.get();
    }

    @Test
    public void testAdder(){
        UserRepository userRepository = new UserRepository(connection);
        User testUser = User.builder()
                .name("Alex")
                .nickname("Alexis")
                .password("123")
                .birthday(LocalDate.of(2000, 1, 1))
                .build();
        userRepository.adder(testUser);
        assertEquals(1,testUser.getId());
    }
    @Test
    public void testTaker() {
        UserRepository userRepository = new UserRepository(connection);
        User testUser = User.builder()
                .name("Alex")
                .nickname("Alexis")
                .password("123")
                .birthday(LocalDate.of(2000, 1, 1))
                .build();
        userRepository.adder(testUser);
        User getUser = userRepository.taker(testUser.getNickname(), testUser.getPassword());
        assertNotNull(getUser);
        assertEquals(testUser.getName(), getUser.getName());
        assertEquals(testUser.getNickname(), getUser.getNickname());
        assertEquals(testUser.getBirthday(), getUser.getBirthday());
        assertEquals(testUser.getPassword(), getUser.getPassword());
    }

}
