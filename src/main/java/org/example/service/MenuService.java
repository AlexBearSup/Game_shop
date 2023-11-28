package org.example.service;
import org.example.ConnectionSingletone;
import org.example.msg.AllGeneralMsg;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuService {
    Scanner scanner = new Scanner(System.in);
    UserService userService = new UserService(scanner);
    GameService gameService = new GameService(scanner);
    AccountService accountService = new AccountService(scanner);
    public MenuService() throws SQLException {
    }
    public void displayAccMenu() throws SQLException {
        int selection;
        do {
            System.out.println(AllGeneralMsg.SEPARATION.getDescription());
            accountMenu();
            System.out.println(AllGeneralMsg.SELECT.getDescription());
            selection = scanner.nextInt();
            scanner.nextLine();
            switch (selection) {
                case 1 -> gameService.displayAll();
                case 2 -> accountService.replenisher();
                case 3 -> gameService.buyer();
                case 4 -> displayStartMenu();
                case 5 -> {
                    System.out.println(AllGeneralMsg.EXIT_APP.getDescription());
                    System.exit(0);
                }
            }
        } while (selection != 5);
        scanner.close();
        ConnectionSingletone.getConnection().close();
    }

    public void displayStartMenu() throws SQLException {
        int choice;
        do {
            startMenu();
            System.out.println(AllGeneralMsg.SELECT.getDescription());
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    if(!userService.loginer()){
                        continue;
                    }
                    displayAccMenu();
                }
                case 2 -> {
                    userService.creator();
                    displayAccMenu();
                }
                case 3 -> {
                    System.out.println(AllGeneralMsg.EXIT_APP.getDescription());
                    System.exit(0);
                }
                default -> System.out.println(AllGeneralMsg.INCORRECT.getDescription());
            }
        } while (choice != 3);
        scanner.close();
        ConnectionSingletone.getConnection().close();
    }


    public void startMenu(){
        System.out.println(AllGeneralMsg.HEADER_STARTMENU.getDescription()
                + "\nPlease enter in your account or create new"
                + "\n1." + AllGeneralMsg.LOGIN.getDescription()
                + "\n2." + AllGeneralMsg.CREATE_USER.getDescription()
                + "\n3." + AllGeneralMsg.EXIT.getDescription());
    }

    public void accountMenu(){
        System.out.println(AllGeneralMsg.ACCOUNT_MENU.getDescription()
                + "\n1." + AllGeneralMsg.VIEW_GAMES.getDescription()
                + "\n2." + AllGeneralMsg.DEPOSIT.getDescription()
                + "\n3." + AllGeneralMsg.BUY_GAME.getDescription()
                + "\n4." + AllGeneralMsg.TO_HOME.getDescription()
                + "\n5." + AllGeneralMsg.EXIT.getDescription());
    }


}
