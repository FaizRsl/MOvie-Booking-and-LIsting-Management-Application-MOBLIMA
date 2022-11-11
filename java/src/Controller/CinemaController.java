package Controller;

import Model.Cinema.Cinema;
import Model.Cinema.Cineplex;
import Model.Cinema.Showtime;
import Model.Movie.Movie;
import Model.Movie.MovieStatus;
import Model.Movie.MovieType;
import view.CinemaView;
import view.MovieView;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class CinemaController {

    private DatabaseController databaseController = DatabaseController.getInstance();

    private List<Cineplex> cineplexes;

    private CinemaView cinemaView;

    public CinemaController() {
        cinemaView = new CinemaView();
        cineplexes = databaseController.getCineplexFromDB();
    }

    public List<Cineplex> getAllCineplex(){
        return cineplexes;
    }

    public int displayAllCineplex(){
        return cinemaView.displayCineplex(cineplexes);
    }

    public int displayShowtimeByDate(String date, String movieTitle) {
        return cinemaView.displayShowtimes(cineplexes, movieTitle, date);
    }
    public int displayAllCineplexShowtimes(String movieName){
        return cinemaView.displayShowtimes(cineplexes, movieName);
    }

    public int displayShowtimeByCineplex(int choice, String movieName){
        return cinemaView.displayShowtimeByCinema(cineplexes.get(choice), movieName);
    }

    public Showtime getCineplexAllShowtimes(int showtimeChoice, String movieName){
        return cinemaView.getShowtime(cineplexes,showtimeChoice,movieName);
    }

    public void displayShowtimeInfo(Showtime showtime){
        cinemaView.displayShowtime(showtime);
    }

    public Showtime getShowtimeByCineplex(int cineplexChoice, int showtimeChoice, String movieName){
        return cinemaView.getShowtimeByCineplex(cineplexes.get(cineplexChoice), showtimeChoice, movieName);
    }
    public Showtime getShowtimeByDate(int choice, String date, String movieTitle){
        return cinemaView.getShowtime(cineplexes, choice,  movieTitle, date);
    }

    public void addNewShowtime(Scanner sc,MovieController movieController) throws InputMismatchException {
        Cinema cinema = getCinemaFromCineplex(sc);
        if (cinema == null)
            return;
        addShowtimeToCineplex(cinema, sc, movieController);
        databaseController.updateCineplexDB(cineplexes);
    }

    private void addShowtimeToCineplex(Cinema cinema,Scanner sc, MovieController movieController) throws InputMismatchException {
        ArrayList<Showtime> showtimeList = cinema.getShowtimes();
        List<Movie> availableMovieList = movieController.getCurrentlyAvailableMovies();

        System.out.println("Creating Showtime: ");
        System.out.println("==================");
        movieController.displayMovieFromMovieList(availableMovieList);
        System.out.println("Select Movie: ");
        int movieChoice = sc.nextInt();
        if (movieChoice > availableMovieList.size()) {
            System.out.println("Input is out of range. Please enter within the range!");
            return;
        }
        Movie mov = availableMovieList.get(movieChoice-1);

        System.out.println("Type of Movie: ");
        cinemaView.displayMovieTypes();
        int movieType = sc.nextInt();
        MovieType type = null;
        type = getMovieType(movieType, type);
        LocalDateTime showDate = createLocalDateTime(sc);
        Showtime showtime = new Showtime(showDate, mov, cinema, type);
        cinema.addShowTime(showtime);
        
        cinemaView.displayCinemaShowtime(showtimeList);

    }

    public LocalDateTime createLocalDateTime(Scanner sc) {
        int hour = 0;
        while (true) {
            System.out.println("What time (hour) [in 24 hour format]: ");
            cinemaView.displayTimeHours();
            hour = sc.nextInt();
            if (hour > 1 && hour < 8) {
                System.out.println("Unable to put showtime during these hours!");
                continue;
            }
            break;
        }
        System.out.println("What time (minutes):" );
        cinemaView.displayTimeMinutes();
        int minute = (sc.nextInt()-1) * 15;

        int year, month = 0;
        year = LocalDateTime.now().getYear();

        boolean loop = true;

        while(loop){
            System.out.println("Input month (as a number): ");
            month = sc.nextInt();
            if(month <= 12){
                loop = false;
            } else {
                System.out.println("Invalid month");
            }

        }

        if(month < LocalDateTime.now().getMonthValue()){
            year++;
        }


        YearMonth yearMonth = YearMonth.of(year, month);
        int lengthOfMonth = yearMonth.lengthOfMonth();
        int day = 0;
        loop = true;
        while(loop){
            System.out.println("Input day: ");
            day = sc.nextInt();
            if(day >= 1 || day <= lengthOfMonth){
                loop = false;
            }
        }
        return LocalDateTime.of(year, month, day, hour, minute);
    }

    public void updateShowtime(Scanner sc,MovieController movieController) throws InputMismatchException {
        displayAllCineplex();
        Cinema cinema = getCinemaFromCineplex(sc);
        List<Movie> availableMovieList = movieController.getCurrentlyAvailableMovies();
        cinemaView.displayUpdateShowtimeOptions();
        int choice = sc.nextInt();

        cinemaView.displayCinemaShowtime(cinema.getShowtimes());
        System.out.println("Input showtime index to update");
        int index = sc.nextInt();
        index--;
        switch (choice) {
            case 1:
                movieController.displayMovieFromMovieList(availableMovieList);
                System.out.println("Select Movie: ");
                int movieChoice = sc.nextInt();
                Movie mov = availableMovieList.get(movieChoice - 1);
                cinema.getShowtimes().get(index).setMovie(mov);
                break;
            case 2:
                System.out.println("Type of Movie: ");
                cinemaView.displayMovieTypes();
                int movieType = sc.nextInt();
                MovieType type = null;
                type = getMovieType(movieType, type);
                cinema.getShowtimes().get(index).setMovieType(type);
                break;
            case 3:
                LocalDateTime date = createLocalDateTime(sc);
                cinema.getShowtimes().get(index).setDateTime(date);
                break;
            case 4:
                displayAllCineplex();
                System.out.println("New Cinema Location");
                System.out.println("====================");
                System.out.println("Select New Cineplex: ");
                Showtime showtime = cinema.getShowtimes().get(index);
                Cinema tmp = getCinemaFromCineplex(sc);
                if (tmp == null)
                    break;
                tmp.getShowtimes().add(showtime);
                cinema.getShowtimes().remove(showtime);
                break;
        }

        databaseController.updateCineplexDB(cineplexes);
        }

    public void removeShowtime(Scanner sc) throws InputMismatchException{
        Cinema cinema = getCinemaFromCineplex(sc);
        if (cinema == null)
            return;
        cinemaView.displayCinemaShowtime(cinema.getShowtimes());
        System.out.println("Input showtime index to remove");
        int index = sc.nextInt();
        cinema.getShowtimes().remove(index - 1);
        databaseController.updateCineplexDB(cineplexes);
    }

    private Cinema getCinemaFromCineplex(Scanner sc){
        displayAllCineplex();
        System.out.println("Select Cineplex: ");
        int cineplexChoice = selectCineplex(sc);
        if (cineplexChoice >= cineplexes.size()) {
            System.out.println("Input is out of range. Please enter within the range!");
            return null;
        }
        System.out.println("Select Cinema: ");
        int cinemaChoice = selectCinema(sc, cineplexChoice);
        if (cinemaChoice >= cineplexes.get(cineplexChoice).getCinemas().size()) {
            System.out.println("Input is out of range. Please enter within the range!");
            return null;
        }
        return cineplexes.get(cineplexChoice).getCinemas().get(cinemaChoice);
    }
    private int selectCineplex(Scanner sc) throws InputMismatchException {
        int cineplexChoice = sc.nextInt();
        return --cineplexChoice;
    }

    private int selectCinema(Scanner sc,int cineplexChoice) throws InputMismatchException {
        cinemaView.displayCinemas(cineplexes.get(cineplexChoice));
        int cinemaChoice = sc.nextInt();
        return --cinemaChoice;
    }
    private MovieType getMovieType(int movieType, MovieType type) {
        switch(movieType){
            case 1:
                type = MovieType.NORMAL;
                break;
            case 2:
                type = MovieType.THREED;
                break;
            case 3:
                type = MovieType.IMAX;
                break;
            case 4:
                type = MovieType.BLOCKBUSTER;
                break;
        }
        return type;
    }
}
