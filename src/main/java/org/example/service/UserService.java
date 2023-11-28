package org.example.service;
import org.example.ConnectionSingletone;
import org.example.msg.AllGeneralMsg;
import org.example.model.Account;
import org.example.model.User;
import org.example.repository.AccountRepository;
import org.example.repository.UserRepository;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserService {
    Scanner scanner;
    UserRepository userRepository = new UserRepository(ConnectionSingletone.getConnection());
    AccountRepository accountRepository = new AccountRepository(ConnectionSingletone.getConnection());
    public UserService(Scanner scanner) throws SQLException {
        this.scanner = scanner;
    }
    public boolean loginer (){
        System.out.println(AllGeneralMsg.ENTER_LOGIN.getDescription());
        String nickname = scanner.nextLine();
        System.out.println(AllGeneralMsg.ENTER_PASS.getDescription());
        String password = scanner.nextLine();
        if (userRepository.taker(nickname, password) == null){
            System.out.println(AllGeneralMsg.USER_NF.getDescription());
            return false;
        } else {
            System.out.println(AllGeneralMsg.GREETINGS.getDescription() + "\n"
                                + AllGeneralMsg.SEPARATION.getDescription() + "\n"
                                + userRepository.taker(nickname, password).getName());
            return true;
        }
    }
    public void creator (){
        System.out.println(AllGeneralMsg.CREATE_USER_PARAMS.getDescription() + "\n"
                            + AllGeneralMsg.ENTER_NAME.getDescription());
        String newName = scanner.nextLine();
        System.out.println(AllGeneralMsg.ENTER_LOGIN.getDescription());
        String newNickName = scanner.nextLine();
        LocalDate newBirthDate = null;
        while (newBirthDate == null) {
            System.out.println(AllGeneralMsg.ENTER_BIRTHDATE.getDescription());
            try {
                newBirthDate = LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println(AllGeneralMsg.INCORRECT.getDescription());
            }
        }
        System.out.println(AllGeneralMsg.ENTER_PASS.getDescription());
        String newPassword = scanner.nextLine();

        String newTypeCard;
        do {
            System.out.println(AllGeneralMsg.CARDTYPE.getDescription());
            newTypeCard = scanner.nextLine().toUpperCase();
        } while (!newTypeCard.equals("VISA") && !newTypeCard.equals("MASTERCARD"));
            User newUser = User.builder()
                            .name(newName)
                            .nickname(newNickName)
                            .birthday(newBirthDate)
                            .password(newPassword)
                            .build();
            userRepository.adder(newUser);
            Account newAccount = Account.builder()
                            .type(newTypeCard)
                            .amount(0)
                            .userID(newUser.getId())
                            .build();
            accountRepository.adder(newAccount);
        System.out.println(AllGeneralMsg.USER_ADD.getDescription());
    }
}
