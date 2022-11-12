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

/**
 * The Class CinemaController.
 */

public class CinemaController {

    private DatabaseController databaseController = DatabaseController.getInstance();
    
    /** List of Cineplex objects. */

    private List<Cineplex> cineplexes;
    
    /** The cinema view. 
     * @see CinemaView#CinemaView()
     * */

    private CinemaView cinemaView;
    
    /**
     * Instantiates a new cinema controller.
     */

    public CinemaController() {
        cinemaView = new CinemaView();
        cineplexes = databaseController.getCineplexFromDB();
    }
    
    /**
     * Gets all cineplexes
     *
     * @return a list containing of Cineplex objects, representing all Cineplexes
     */

    public List<Cineplex> getAllCineplex(){
        return cineplexes;
    }
    
    /**
     * Display all cineplexes.
     * 
     */

    public int displayAllCineplex(){
        return cinemaView.displayCineplex(cineplexes);
    }

    public int displayShowtimeByDate(String date, String movieTitle) {
        return cinemaView.displayShowtimes(cineplexes, movieTitle, date);
    }
    
    /**
     * Display all cineplex showtimes.
     *
     * @param movieName the movie name
     */
    public int displayAllCineplexShowtimes(String movieName){
        return cinemaView.displayShowtimes(cineplexes, movieName);
    }
    
    /**
     * Display showtime by cineplex.
     *
     * @param choice the choice
     * @param movieName the movie name
     */

    public int displayShowtimeByCineplex(int choice, String movieName){
        return cinemaView.displayShowtimeByCinema(cineplexes.get(choice), movieName);
    }
    
    /**
     * Gets the cineplex all showtimes.
     *
     * @param showtimeChoice the showtime choice
     * @param movieName the movie name
     * @return the cineplex all showtimes
     */

    public Showtime getCineplexAllShowtimes(int showtimeChoice, String movieName){
        return cinemaView.getShowtime(cineplexes,showtimeChoice,movieName);
    }

    /**
     * Display showtime info.
     *
     * @param showtime the showtime
     */
    
    public void displayShowtimeInfo(Showtime showtime){
        cinemaView.displayShowtime(showtime);
    }
    
    /**
     * Gets the showtime by cineplex.
     *
     * @param cineplexChoice the cineplex choice
     * @param showtimeChoice the showtime choice
     * @param movieName the movie name
     * @return the showtime by cineplex
     */

    public Showtime getShowtimeByCineplex(int cineplexChoice, int showtimeChoice, String movieName){
        return cinemaView.getShowtimeByCineplex(cineplexes.get(cineplexChoice), showtimeChoice, movieName);
    }
    
    /**
     * Gets the showtime by date.
     *
     * @param choice the choice
     * @param date the date
     * @param movieTitle the movie title
     * @return the showtime by date
     */
    
    public Showtime getShowtimeByDate(int choice, String date, String movieTitle){
        return cinemaView.getShowtime(cineplexes, choice,  movieTitle, date);
    }
    
    /**
     * Adds the new showtime.
     */

    public void addNewShowtime(Scanner sc,MovieController movieController) throws InputMismatchException {
        Cinema cinema = getCinemaFromCineplex(sc);
        if (cinema == null)
            return;
        addShowtimeToCineplex(cinema, sc, movieController);
        databaseController.updateCineplexDB(cineplexes);
    }
    
    /**
     * Adds the showtime to cineplex.
     *
     * @param cinema the cinema
     */

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
        
        boolean loop = true;

        while(loop){
            if(checkShowtimes(showtimeList, showDate)){
                loop = false;
                Showtime showtime = new Showtime(showDate, mov, cinema, type);
                cinema.addShowTime(showtime);
            } else {
                System.out.println("Invalid date!");
                System.out.println("Try again?");
                System.out.println("1) Yes");
                System.out.println("2) No");
                if(sc.nextInt() == 2){
                    loop = false;
                } else {
                    showDate = createLocalDateTime(sc);
                }
                
            }
        }
        
        
        cinemaView.displayCinemaShowtime(showtimeList);

    }

    public boolean checkShowtimes(ArrayList<Showtime> showtimeList, LocalDateTime showDate){
        ArrayList<Showtime> sameDates = new ArrayList<Showtime>();
        Showtime temp = null;
        for(int i = 0; i < showtimeList.size(); i++){
            temp = showtimeList.get(i);
            if(temp.getDateTime().getMonthValue() == showDate.getMonthValue() && temp.getDateTime().getDayOfMonth() == showDate.getDayOfMonth()){
                sameDates.add(temp);
            }
        }
    
        System.out.println(showDate.getHour() + ":" + showDate.getMinute());
        
        for(int i = 0; i < sameDates.size(); i++){
            
            temp = sameDates.get(i);

            if(showDate.getHour() == temp.getDateTime().getHour()){ return false;}

            if(showDate.getHour() > temp.getDateTime().getHour()){ //when new showtime is later than current
                if(showDate.getHour() - temp.getDateTime().getHour() < 2){ //no gap for new showtime later than current
                    return false;
                } else if (showDate.getHour() - temp.getDateTime().getHour() == 2 && showDate.getMinute() - temp.getDateTime().getMinute() != 0){ //not full 2 hours
                    return false;
                }
            } else if(showDate.getHour() < temp.getDateTime().getHour()){ //when new showtime is earlier than current
                if(temp.getDateTime().getHour() - showDate.getHour() < 2){ //no gap for new showtime earlier than current
                    return false;
                } else if (temp.getDateTime().getHour() - showDate.getHour() == 2 && showDate.getMinute() - temp.getDateTime().getMinute() != 0){ //not full 2 hours
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Creates the local date time.
     *
     * @return the local date time
     */

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
        System.out.println("Hour: " + hour);

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
    
    /**
     * Update showtime.
     */

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
    
    /**
     * Removes the showtime.
     */

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
