package org.example.repository.DAO;

import org.example.model.User;

public interface UserRepository {
    void adder (User user);
    User taker (String nickname, String password);
    User taker (String password);


}
