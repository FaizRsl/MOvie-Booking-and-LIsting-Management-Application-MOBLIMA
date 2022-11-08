import Controller.*;
import Controller.AdminController;
import Controller.CinemaController;
import Controller.MovieController;
import Controller.PriceController;
import view.MainMenuView;

import java.util.Scanner;

public class MOBLIMA {
    private static AdminController adminController = new AdminController();
    private static MovieController movieController = new MovieController();
    private static BookingController bookingController = new BookingController();
    private static CinemaController cinemaController = new CinemaController();
    private static PriceController priceController = new PriceController();
    private static MainMenuView mainMenuView = new MainMenuView();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mainMenuView.mainMenu(sc, adminController, movieController, cinemaController, bookingController, priceController);
    }
}

