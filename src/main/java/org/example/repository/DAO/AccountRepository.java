package org.example.repository.DAO;

import org.example.model.Account;

public interface AccountRepository {
    Account taker (int userID);
    void updater (int deposit, int userID);
    void adder (Account account );

}
