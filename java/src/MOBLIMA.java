import Controller.*;
import Controller.AdminController;
import Controller.CinemaController;
import Controller.MovieController;
import view.MainMenuView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MOBLIMA {
    private static AdminController adminController = new AdminController();
    private static MovieController movieController = new MovieController();
    private static BookingController bookingController = new BookingController();
    private static CinemaController cinemaController = new CinemaController();
    private static MainMenuView mainMenuView = new MainMenuView();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        mainMenuView.mainMenu(br, adminController, movieController, cinemaController, bookingController);
    }
}

