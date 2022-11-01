import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Model.Movie.Movie;
import Model.Movie.MovieCensorship;
import Model.Movie.MovieDetails;
import Model.Movie.MovieStatus;
import Model.User.SerializeDB;
import Model.User.User;
import Model.Movie.Review;

public class DatabaseManager {
    
    String filePath = new File("").getAbsolutePath() + "\\Database\\";
    String movieFile = filePath + "MovieDB.dat";
    String userFile = filePath + "UserDB.dat";
    public DatabaseManager(){
    }

    public void addUser(User user){
        SerializeDB userDB = new SerializeDB(userFile);
        List<User> userList = userDB.getObjectsList();

        if(userList == null){
            userList = new ArrayList<User>();
        }
        userList.add(user);

        userDB.writeToDB(userList);
        
    }

    public boolean authorizeUser(String username, String password){
        SerializeDB userDB = new SerializeDB(userFile);
        List<User> list = userDB.getObjectsList();

        try {
            for(int i = 0; i < list.size(); i++) {
                User user = list.get(i);
                if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println( "Exception >> " + e.getMessage());
        }
        return false;
    }

    public void viewAllMovies(){
        SerializeDB movieDB = new SerializeDB(movieFile);
        List<Movie> movList = movieDB.getObjectsList();

        for(int i = 0; i < movList.size(); i++){
            System.out.println("Title: " + movList.get(i).getTitle());
            System.out.println("Director: " + movList.get(i).getDirector());
            System.out.println("Cast: " + movList.get(i).getCasts());
            System.out.println("Synopsis: " + movList.get(i).getSynopsis());
        }
    }
    public void addMovieInput(){
        String title;
        String director;
        String synopsis;
        double rating;
        ArrayList<Review> reviews = new ArrayList<Review>();
        ArrayList<String> casts = new ArrayList<String>();
        MovieDetails movieDetails;
        MovieStatus movieStatus = null;
        MovieCensorship movieCensorship = null;

        Scanner sc = new Scanner(System.in);


        System.out.println("Movie Title: ");
        title = sc.nextLine();
        System.out.println("Director: ");
        director = sc.nextLine();
        System.out.println("Synopsis: ");
        synopsis = sc.nextLine();
        
        System.out.println("Cast Members:");
        String cast = sc.nextLine();
        List<String> tempList = Arrays.asList(cast.split(","));
        for (int i = 0; i < tempList.size(); i++){
            casts.add(i,tempList.get(i).trim());
        }
        System.out.println("Rating: ");
        rating = sc.nextDouble();

        System.out.println("Movie Status: ");
        System.out.println("1) Coming Soon");
        System.out.println("2) Preview");
        System.out.println("3) Now Showing");
        System.out.println("4) Ended");
        int choice;
        boolean loop = true;
        while(loop){

            choice = sc.nextInt();
            if(choice > 4 && choice < 0){
                System.out.println("Please input a valid number!");
                continue;
            }
            switch(choice){
                case 1:
                    movieStatus = MovieStatus.COMINGSOON;
                    break;
                case 2:
                    movieStatus = MovieStatus.PREVIEW;
                    break;
                case 3:
                    movieStatus = MovieStatus.NOWSHOWING;
                    break;
                case 4:
                    movieStatus = MovieStatus.ENDED;
                    break;
                default:
            }
            loop = false;
        }

        System.out.println("Movie Censorship: ");
        System.out.println("1) G");
        System.out.println("2) PG");
        System.out.println("3) PG13");
        System.out.println("4) NC16");
        System.out.println("5) M18");
        System.out.println("6) R21");


        loop = true;
        while(loop){

            choice = sc.nextInt();
            if(choice > 6 && choice < 0){
                System.out.println("Please input a valid number!");
                continue;
            }
            switch(choice){
                case 1:
                    movieCensorship = MovieCensorship.G;
                    break;
                case 2:
                    movieCensorship = MovieCensorship.PG;
                    break;
                case 3:
                    movieCensorship = MovieCensorship.PG13;
                    break;
                case 4:
                    movieCensorship = MovieCensorship.NC16;
                    break;
                case 5:
                    movieCensorship = MovieCensorship.M18;
                    break;
                case 6:
                    movieCensorship = MovieCensorship.R21;
                    break;
                default:
            }
            loop = false;
        }

        movieDetails = new MovieDetails(movieStatus,movieCensorship);
        
        Movie mov = new Movie(title, movieDetails);
        mov.setRating(rating);
        mov.setDirector(director);
        mov.setSynopsis(synopsis);
        mov.setReviews(reviews);
        mov.setCasts(casts);
        addMovie(mov);
    }


    private void addMovie(Movie mov){

        SerializeDB movieDB = new SerializeDB(movieFile);
        List<Movie> movieList = movieDB.getObjectsList();

        if(movieList == null){
            movieList = new ArrayList<Movie>();
        }
        movieList.add(mov);

        movieDB.writeToDB(movieList);
    }

    public void removeMovie(int index){

        SerializeDB movieDB = new SerializeDB(movieFile);
        List<Movie> movieList = movieDB.getObjectsList();

        movieList.remove(index);

        movieDB.writeToDB(movieList);
    }

    
}
