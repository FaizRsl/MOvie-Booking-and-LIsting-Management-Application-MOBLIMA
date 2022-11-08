package view;

import Controller.AdminController;
import Controller.BookingController;
import Controller.CinemaController;
import Controller.MovieController;
import Controller.PriceController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class MainMenuView {
    private CustomerView customerMenu = new CustomerView();
    public void mainMenu(Scanner sc, AdminController adminController, MovieController movieController, CinemaController cinemaController, BookingController bookingController, PriceController priceController)  {
        boolean loop = true;
        do {
            printMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    loop = adminController.printAdminMenu(sc, adminController, movieController, cinemaController, priceController);
                    break;
                case 2:
                    loop = customerMenu.customerMenu(sc, movieController, cinemaController, bookingController);
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    System.out.println("Goodbye!");
                    loop = false;
                    break;
                default:
                    System.out.println("Please enter a valid choice!");
                    break;
            }
        } while(loop);
        sc.close();
    }
    private static void printMenu() {
        System.out.println("----------------Main Menu-----------------");
        System.out.println("------------------------------------------");
        System.out.println("1. Admin");
        System.out.println("2. Movie Goers");
        System.out.println("3. Exit");
        System.out.println("------------------------------------------");
        System.out.println("Please enter the choice for type of user:");
    }
}
