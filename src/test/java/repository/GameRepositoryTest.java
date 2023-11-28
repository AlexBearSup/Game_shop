package repository;

import connetct.H2Connector;
import org.example.model.Game;
import org.example.repository.GameRepisitory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class GameRepositoryTest {

    Connection connection;
@Before
    public void init () throws SQLException, IOException, ClassNotFoundException {
        connection = H2Connector.get();
    }


    @Test
    public void testPullerAll(){
        GameRepisitory gameRepisitory = new GameRepisitory(connection);

        List<Game> allGames = gameRepisitory.pullerAll();
        assertNotNull(allGames);

        assertFalse(allGames.isEmpty());
        assertTrue(allGames.size() > 0);
    }


}


