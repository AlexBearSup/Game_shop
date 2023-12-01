package org.example;
import org.example.repository.AccountRepositoryImpl;
import org.example.repository.GameRepositoryImpl;
import org.example.repository.PurchaseRepositoryImpl;
import org.example.repository.UserRepositoryImpl;
import org.example.service.AccountService;
import org.example.service.MenuService;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
public static void main(String[] args) throws SQLException {

        MenuService menuService = new MenuService();

        menuService.displayStartMenu();

    }
}