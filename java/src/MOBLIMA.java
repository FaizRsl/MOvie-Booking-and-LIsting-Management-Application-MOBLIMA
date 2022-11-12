import Controller.*;
import Controller.AdminController;
import Controller.CinemaController;
import Controller.MovieController;
import Controller.PriceController;
import view.MainMenuView;

import java.util.Scanner;

/**
 * The Class MOBLIMA, main class and primary access point for users.
 */

public class MOBLIMA {
	
	/** The admin controller. */
	
    private static AdminController adminController = new AdminController();
    
    /** The movie controller. */
    
    private static MovieController movieController = new MovieController();
    
    /** The booking controller. */
    
    private static BookingController bookingController = new BookingController();
    
    /** The cinema controller. */
    
    private static CinemaController cinemaController = new CinemaController();
    
    /** The price controller. */
    
    private static PriceController priceController = new PriceController();
    
    /** Generating the new menu. */
    
    private static MainMenuView mainMenuView = new MainMenuView();
    
    /**
     * Main function that gives us the IO menu.
     *
     * @param args the user input
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mainMenuView.mainMenu(sc, adminController, movieController, cinemaController, bookingController, priceController);
    }
}
