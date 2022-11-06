package Controller;

import Model.Cinema.Cinema;
import Model.Cinema.Cineplex;
import Model.Cinema.Showtime;
import Model.Movie.Movie;
import Model.Movie.MovieStatus;
import Model.Movie.MovieType;
import view.CinemaView;
import view.MovieView;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class CinemaController {

    private DatabaseController databaseController = DatabaseController.getInstance();

    private List<Cineplex> cineplexes;

    private CinemaView cinemaView;
    private MovieView movieView;

    public CinemaController() {
        movieView = new MovieView();
        cinemaView = new CinemaView();
        cineplexes = databaseController.getCineplexFromDB();
    }

    public List<Cineplex> getAllCineplex(){
        return cineplexes;
    }

    public void displayAllCineplex(){
        cinemaView.displayCineplex(cineplexes);
    }

    public void displayAllCineplexShowtimes(String movieName){
        cinemaView.displayShowtimes(cineplexes, movieName);
    }

    public void displayShowtimeByDate(String date, String movieTitle){
        cinemaView.displayShowtimes(cineplexes,movieTitle,date);
    }

    public void displayShowtimeByCineplex(int choice, String movieName){
        cinemaView.displayShowtimeByCinema(cineplexes.get(choice-1), movieName);
    }

    public Showtime getCineplexAllShowtimes(int showtimeChoice, String movieName){
        return cinemaView.getShowtime(cineplexes,showtimeChoice,movieName);
    }

    public void displayShowtimeInfo(Showtime showtime){
        cinemaView.displayShowtime(showtime);
    }

    public Showtime getShowtimeByCineplex(int cineplexChoice, int showtimeChoice, String movieName){
        return cinemaView.getShowtimeByCineplex(cineplexes.get(cineplexChoice-1), showtimeChoice, movieName);
    }
    public Showtime getShowtimeByDate(int choice, String date, String movieTitle){
        return cinemaView.getShowtime(cineplexes, choice,  movieTitle, date);
    }

    public Cinema getCinemaFromCineplex(){
        displayAllCineplex();
        System.out.println("Select Cineplex: ");
        Scanner sc = new Scanner(System.in);
        int cineplexChoice = sc.nextInt();
        sc.nextLine();
        System.out.println("Select cinema: ");
        cinemaView.displayCinemas(cineplexes.get(cineplexChoice));
        int choice = sc.nextInt();
        sc.nextLine();
        return cineplexes.get(cineplexChoice).getCinemas().get(choice);
    }
    public void addNewShowtime(BufferedReader br) throws IOException {
        addShowtimeToCineplex(getCinemaFromCineplex(),br);
        databaseController.updateCineplexDB(cineplexes);
    }

    public void addShowtimeToCineplex(Cinema cinema,BufferedReader br) throws IOException {
        ArrayList<Showtime> showtimeList = cinema.getShowtimes();
        List<Movie> movieList = databaseController.getMovieFromDB();
        List<Movie> availableMovieList;
        availableMovieList = getAvailableMovies(movieList);

        System.out.println("Creating Showtime: ");
        System.out.println("==================");
        movieView.displayMovies(availableMovieList);
        System.out.println("Select Movie: ");
        int movieChoice = Integer.parseInt(br.readLine());
        Movie mov = availableMovieList.get(movieChoice-1);

        System.out.println("Type of Movie: ");
        cinemaView.displayMovieTypes();
        int movieType = Integer.parseInt(br.readLine());
        MovieType type = null;
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
        
        LocalDateTime showDate = createLocalDateTime(br);
        Showtime showtime = new Showtime(showDate, mov, cinema, type);
        cinema.addShowTime(showtime);
        
        cinemaView.displayCinemaShowtime(showtimeList);
    }

    public List<Movie> getAvailableMovies(List<Movie> movieList){
        List<Movie> availableMovies = new ArrayList<Movie>();
        for(int i = 0; i < movieList.size(); i++){
            if(movieList.get(i).getMovieDetails().getMovieStatus() == MovieStatus.PREVIEW || movieList.get(i).getMovieDetails().getMovieStatus() == MovieStatus.NOWSHOWING){
                availableMovies.add(movieList.get(i));
            }
        }
        
        return availableMovies;
    }

    public LocalDateTime createLocalDateTime(BufferedReader br) throws IOException{
        System.out.println("What time (hour): ");
        cinemaView.displayTimeHours();
        int hour = Integer.parseInt(br.readLine());

        System.out.println("What time (minutes):" );
        cinemaView.displayTimeMinutes();
        int minute = (Integer.parseInt(br.readLine())-1) * 15;

        System.out.println("AM/PM: ");
        System.out.println("1) AM");
        System.out.println("2) PM");
        if(Integer.parseInt(br.readLine()) == 2){ //pm
            if(hour < 12){
                hour += 12;
            }
        } else { //am
            if(hour == 12){
                hour = 0;
            }
        }
        
        int year, month = 0;
        year = LocalDateTime.now().getYear();

        boolean loop = true;

        while(loop){
            System.out.println("Input month (as a number): ");
            month = Integer.parseInt(br.readLine());;
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
            day = Integer.parseInt(br.readLine());;
            if(day >= 1 || day <= lengthOfMonth){
                loop = false;
            }
        }
        
        return LocalDateTime.of(year, month, day, hour, minute);
    }

    public void updateShowtime(BufferedReader br) throws IOException{
        displayAllCineplex();
        System.out.println("Select Cineplex: ");
        int cineplexChoice = Integer.parseInt(br.readLine());
        cineplexChoice--;
        System.out.println("Select cinema: ");
        cinemaView.displayCinemas(cineplexes.get(cineplexChoice));
        int cinemaChoice = Integer.parseInt(br.readLine());
        cinemaChoice--;

        List<Movie> availableMovieList = getAvailableMovies(databaseController.getMovieFromDB());
        cinemaView.displayUpdateShowtimeOptions();
        int choice = Integer.parseInt(br.readLine());

        cinemaView.displayCinemaShowtime(cineplexes.get(cineplexChoice).getCinemas().get(cinemaChoice).getShowtimes());
        System.out.println("Input showtime index to update");
        int index = Integer.parseInt(br.readLine());
        index--;
        switch(choice){
            case 1:
                movieView.displayMovies(availableMovieList);
                System.out.println("Select Movie: ");
                int movieChoice = Integer.parseInt(br.readLine());
                Movie mov = availableMovieList.get(movieChoice-1);
                cineplexes.get(cineplexChoice).getCinemas().get(cinemaChoice).getShowtimes().get(index).setMovie(mov);
                break;
            case 2:
                System.out.println("Type of Movie: ");
                cinemaView.displayMovieTypes();
                int movieType = Integer.parseInt(br.readLine());
                MovieType type = null;
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
                cineplexes.get(cineplexChoice).getCinemas().get(cinemaChoice).getShowtimes().get(index).setMovieType(type);
                break;
            case 3:
                LocalDateTime date = createLocalDateTime(br);
                cineplexes.get(cineplexChoice).getCinemas().get(cinemaChoice).getShowtimes().get(index).setDateTime(date);
                break;
            case 4:
                displayAllCineplex();
                System.out.println("New Cinema Location");
                System.out.println("====================");
                System.out.println("Select New Cineplex: ");
                int newCineplexChoice = Integer.parseInt(br.readLine());
                newCineplexChoice--;
                System.out.println("Select New Cinema: ");
                cinemaView.displayCinemas(cineplexes.get(cineplexChoice));
                int newCinemaChoice = Integer.parseInt(br.readLine());
                newCinemaChoice--;
                Showtime showtime = cineplexes.get(cineplexChoice).getCinemas().get(cinemaChoice).getShowtimes().get(index);
                cineplexes.get(newCineplexChoice).getCinemas().get(newCinemaChoice).getShowtimes().add(showtime);  
                
                cineplexes.get(cineplexChoice).getCinemas().get(cinemaChoice).getShowtimes().remove(showtime);

                break;
        }

        databaseController.updateCineplexDB(cineplexes);    }

    public void removeShowtime(BufferedReader br) throws IOException {
        displayAllCineplex();
        System.out.println("Select Cineplex: ");
        int cineplexChoice = Integer.parseInt(br.readLine());
        cineplexChoice--;
        System.out.println("Select cinema: ");
        cinemaView.displayCinemas(cineplexes.get(cineplexChoice));
        int choice = Integer.parseInt(br.readLine());
        choice--;

        cinemaView.displayCinemaShowtime(cineplexes.get(cineplexChoice).getCinemas().get(choice).getShowtimes());
        System.out.println("Input showtime index to remove");
        int index = Integer.parseInt(br.readLine());
        cineplexes.get(cineplexChoice).getCinemas().get(choice).getShowtimes().remove(index-1);
        databaseController.updateCineplexDB(cineplexes);
        
    }

}
