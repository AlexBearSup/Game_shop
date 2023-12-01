package org.example.service;
import org.example.ConnectionSingletone;
import org.example.msg.AllGeneralMsg;
import org.example.model.Game;
import org.example.repository.AccountRepository;
import org.example.repository.GameRepisitory;
import org.example.repository.PurchaseRepository;
import org.example.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class GameService {
    GameRepisitory gameRepisitory = new GameRepisitory(ConnectionSingletone.getConnection());
    UserRepository userRepository = new UserRepository(ConnectionSingletone.getConnection());
    AccountRepository accountRepository = new AccountRepository(ConnectionSingletone.getConnection());
    PurchaseRepository purchaseRepository = new PurchaseRepository(ConnectionSingletone.getConnection());
    Scanner scanner;
    public GameService(Scanner scanner) throws SQLException {
        this.scanner = scanner;
    }
    public void displayAll(){
        System.out.println(AllGeneralMsg.LIST_GAMES.getDescription());
        gameRepisitory.pullerAll()
                .forEach(g -> System.out.println(g.getId() + ". "
                        + g.getName() + " - "
                        + g.getCost() + "$ - "
                        + g.getRating() + " points - "
                        + g.getDescription()));
    }
    public void buyer(){
        System.out.println(AllGeneralMsg.GAME_BUYING.getDescription());
        List<Game> all = gameRepisitory.pullerAll();
                all.forEach(game -> System.out.println(
                "ID = " + game.getId() + ". " + game.getName() + " -  cost: "
                        + game.getCost() + "$"));
        System.out.println(AllGeneralMsg.SEPARATION.getDescription() + "\n"
                            + AllGeneralMsg.SELECT.getDescription());
        while (!scanner.hasNextInt()) {
            System.out.println(AllGeneralMsg.INCORRECT.getDescription());
            scanner.next();
        }
        int choice = scanner.nextInt();
        if (choice >= 1 && choice <= all.size()){
            Game selected = all.get(choice - 1);
            System.out.println("ID = " + selected.getId() + ". "+ selected.getName() + " -  cost: "
                    + selected.getCost() + "$");
        System.out.println(AllGeneralMsg.ENTER_PASS.getDescription());
        scanner.nextLine();
        String password = scanner.nextLine();
        int userID = userRepository.taker(password).getId();
        System.out.println(AllGeneralMsg.MONEY.getDescription() + "\n"
                        + accountRepository.taker(userID).getAmount());
            if (accountRepository.taker(userID).getAmount() < selected.getCost()){
                System.out.println(AllGeneralMsg.NO_MONEY.getDescription());
            } else {
                int newBalance = accountRepository.taker(userID).getAmount() - selected.getCost();
                accountRepository.updater(newBalance, userID);
                purchaseRepository.adder(userID, selected.getId());
                System.out.println( AllGeneralMsg.PURCHASE_OK.getDescription() + "\n"
                        + AllGeneralMsg.MONEY.getDescription() + newBalance);
            }
        } else {
            System.out.println(AllGeneralMsg.INCORRECT.getDescription());
        }
    }
}

