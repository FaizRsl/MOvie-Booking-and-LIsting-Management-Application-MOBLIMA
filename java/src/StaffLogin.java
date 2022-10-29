

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class StaffLogin {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        String username = "";
        String password = "";
        Scanner sc = new Scanner(System.in);

        SerializeDB userDB = new SerializeDB("UserDB.dat");
        System.out.print("Username: ");
        username = sc.next();
        System.out.print("Password: ");
        password = sc.next();

        List<User> list = userDB.getObjectsList();
        boolean success = checkLogin(list, username,password);
    
        if(success) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Login Failed!");
            return;
        }

        SerializeDB movieDB = new SerializeDB("Movie.dat");
        List<Movie> movieList = movieDB.getObjectsList();
        while(success) {
            System.out.println("1) System Settings");
            System.out.println("2) Display All Movies");
            System.out.println("3) Enter new movie");
            System.out.println("4) Remove movie");
            System.out.println("8) Create New User (Testing creating row feature) ");
            System.out.println("9) Quit");
            System.out.println("Next Choice: ");
            int input = sc.nextInt();
            switch(input) {
                case 1:
                    System.out.println("Coming soon");
                    break;
                case 2:
                    if(movieDB.getFileExists()){
                        displayAllMovies(movieList);
                    } else {
                        System.out.println("No movies in Movie Database!");
                    }
                    break;
                case 3:
                    if(movieDB == null){

                        movieDB = new SerializeDB("Movie.dat");
                        if(movieDB.getFileExists()){
                            movieList = movieDB.getObjectsList();
                        } else {
                            movieList = new ArrayList<Movie>();
                        }

                    }
                    enterNewMovie(movieList);
                    break;
                case 4:
                    System.out.println("Input movie number to remove: ");
                    System.out.println("To cancel, type 0");
                    int remove = sc.nextInt();
                    if(remove == 0) break;
                    movieList.remove(remove-1);
                    break;
                case 8:
                    System.out.println("Enter Username: ");
                    username = sc.next();
                    System.out.println("Enter Password ");
                    password = sc.next();
                    User user = new User(username,password);
                    list.add(user);
                    userDB.writeToDB(list);
                    break;
                default:
                    success = false;
                    break;
            }
        }
        movieDB.writeToDB(movieList);
    }

    public static Boolean checkLogin(List<User> list,String username, String password) {


        try {

            for(int i = 0; i < list.size(); i++) {
                User user = (User)list.get(i);
                if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return true;
                }
            }

        } catch (Exception e) {
            System.out.println( "Exception >> " + e.getMessage());
        }
        return false;
    }

    public static void enterNewMovie(List<Movie> movieList){
        String title;
        String director;
        String synopsis;
        String cast;
        Rating rating;
        Ticket ticket;
        boolean seats;
        int totalSales;

        MovieDetails movieDetails = null;
        String movieRatings, movieType, status;
        Scanner sc = new Scanner(System.in);



        System.out.println("Movie Title: ");
        title = sc.nextLine();
        System.out.println("Director: ");
        director = sc.nextLine();
        System.out.println("Synopsis: ");
        synopsis = sc.nextLine();
        System.out.println("Cast: ");
        cast = sc.nextLine();
        System.out.println("Movie Rating (G, PG, PG13, M16, R): ");
        movieRatings = sc.nextLine();
        System.out.println("Movie Type(Horror, Romance, Action, Blockbuster, Comedy): ");
        movieType = sc.nextLine();
        System.out.println("Movie Status (Coming Soon, Preview, Now Showing, End Of Showing): ");
        status = sc.nextLine();

        movieDetails = new MovieDetails(movieRatings, movieType, status);

        Movie mov = new Movie(title, director, synopsis, cast, movieDetails);
        
        movieList.add(mov);
        

    }

    public static void displayAllMovies(List<Movie> movieList){
        Scanner sc = new Scanner(System.in);
        Movie mov = null;
        System.out.println("Movie Title(s)");
        System.out.println("=================");
        for(int i = 0; i < movieList.size(); i++){
            mov = (Movie) movieList.get(i);
            System.out.println((i+1) + ") " + mov.getTitle());
            
        }
        
        while(true){
            System.out.println("Select Movie Number to show more details: ");
            System.out.println("Type 0 to quit");
            int showMore = sc.nextInt();
            if(showMore == 0) break;
            try{
                mov = (Movie) movieList.get(showMore-1);
                MovieDetails movDetails = mov.getMovieDetails();
                System.out.println((showMore) + ") Movie Title: " + mov.getTitle());
                System.out.println("===========================================");
                System.out.println("Director " + mov.getDirector() + "\n");
                System.out.println("Cast: " + mov.getCast() + "\n");
                System.out.println("Age Rating: " + movDetails.getMovieRating() + "\n");
                System.out.println("Synopsis: " + mov.getSynopsis() + "\n");
                System.out.println("Genre: " + movDetails.getMovieType() + "\n");
                System.out.println("Movie Status: " + movDetails.getStatus() + "\n");
            } catch(IndexOutOfBoundsException e){
                System.out.println("No such movie number!");
                System.out.println("Please enter a movie number between 1 and " + (movieList.size()));

            }
            
        }
    }
}
