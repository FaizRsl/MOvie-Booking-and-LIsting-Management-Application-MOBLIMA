package view;

import Controller.AdminController;
import Controller.BookingController;
import Controller.CinemaController;
import Controller.MovieController;
import Controller.PriceController;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Class MainMenuView, called in MOBLIMA to generate the main menu.
 */
public class MainMenuView {
    
    /** The customer menu, for generating the option menu for customers. */
    private CustomerView customerMenu = new CustomerView();
    
    /**
	 * Main menu.
	 *
	 * @param sc                the sc
	 * @param adminController   the admin controller
	 * @param movieController   the movie controller
	 * @param cinemaController  the cinema controller
	 * @param bookingController the booking controller
	 * @param priceController   the price controller
	 */
    public void mainMenu(Scanner sc, AdminController adminController, MovieController movieController, CinemaController cinemaController, BookingController bookingController, PriceController priceController)  {
        boolean loop = true;
        int count = 0;
        while (loop) {
            try {
                printMenu();
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        loop = adminController.printAdminMenu(sc, adminController, movieController, cinemaController, priceController);
                        break;
                    case 2:
                        loop = customerMenu.customerMenu(sc, movieController, cinemaController, bookingController,priceController);
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
            } catch (InputMismatchException e) {
                if (count > 3)
                    throw e;
                System.out.println("Unable to recognize your input. Please try again!");
                System.out.println("Expected Input: Integer");
                System.out.println("Input:" + e.getMessage());
                count++;
                sc.nextLine();
            }
        };
        sc.close();
    }
    
    /**
	 * Prints the menu upon running the MOBLIMA class.
	 * 
	 * @see MOBLIMA.java
	 */
    private void printMenu() {
        System.out.println("----------------Main Menu-----------------");
        System.out.println("------------------------------------------");
        System.out.println("1. Admin");
        System.out.println("2. Movie Goers");
        System.out.println("3. Exit");
        System.out.println("------------------------------------------");
        System.out.println("Please enter the choice for type of user:");
    }
}
