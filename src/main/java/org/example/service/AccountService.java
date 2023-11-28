package org.example.service;
import org.example.ConnectionSingletone;
import org.example.msg.AllGeneralMsg;
import org.example.repository.AccountRepository;
import org.example.repository.UserRepository;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountService {
    Scanner scanner;
    AccountRepository accountRepository = new AccountRepository(ConnectionSingletone.getConnection());
    UserRepository userRepository = new UserRepository(ConnectionSingletone.getConnection());
    public AccountService(Scanner scanner) throws SQLException {
        this.scanner = scanner;
    }
    public void replenisher (){
        System.out.println(AllGeneralMsg.TO_REFILL.getDescription() +"\n"
                         + AllGeneralMsg.ENTER_PASS.getDescription());
        String password = scanner.nextLine();
        int userID = userRepository.taker(password).getId();

        System.out.println(AllGeneralMsg.MONEY.getDescription() +"\n"
                           + accountRepository.taker(userID).getAmount() +"\n"
                           + AllGeneralMsg.TOP_UP.getDescription());

        while (!scanner.hasNextInt()) {
            System.out.println(AllGeneralMsg.INCORRECT.getDescription());
            scanner.next();
        }

        int deposit = scanner.nextInt() + accountRepository.taker(userID).getAmount();
        accountRepository.updater(deposit, userID);

        System.out.println(AllGeneralMsg.MONEY.getDescription() +"\n"
                           + accountRepository.taker(userID).getAmount());

    }
}
