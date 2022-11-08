package view;

import Model.Movie.*;

import java.io.BufferedReader;
import java.io.IOException;
import Model.Movie.Movie;
import Model.Movie.MovieCensorship;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MovieView {

    //movie function
    public void displayMovies(List<Movie> movieList) {
        System.out.println("Movie Title(s)");
        System.out.println("========================");
        for (int i = 0;i < movieList.size();i++) {
            System.out.printf("%d) %s ( %s ) \n",i+1,movieList.get(i).getTitle(),movieList.get(i).getMovieDetails().getMovieStatus().getMovieStatusName());
        }
        System.out.println("========================");
    }
    public void displayMovieDetails(int index,Movie movie) {
        System.out.printf("%d)\n%s",index,movie.toString());
    }

    public void displayReviewsByMovies(Movie movie){
        System.out.printf("Movie Title: %s \n",movie.getTitle());
        if(movie.getReviews().size() == 0){
            System.out.println("No reviews for this current movie.");
            return;
        }
        for(int i=0; i< movie.getReviews().size(); i++){
            System.out.println("Review " + (i+1) + ":");
            System.out.println("Reviewer Name: " + movie.getReviews().get(i).getReviewerName());
            System.out.println("Rating: " + movie.getReviews().get(i).getRating());
            System.out.println("Review Content: " + movie.getReviews().get(i).getReviewContent());
            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.println();
        }
    }
    public void displayUpdateOptions(){
        System.out.println("What would you like to update?");
        System.out.println("1) Movie Title");
        System.out.println("2) Director");
        System.out.println("3) Cast");
        System.out.println("4) Age Rating");
        System.out.println("5) Synopsis");
        System.out.println("6) Movie Status");

    }
    public void displayUpdateCastOptions(){
        System.out.println("1) Add Cast Member");
        System.out.println("2) Remove Cast Member");
        System.out.println("3) Change Cast Member's Name");
        System.out.println("4) Quit");
    }
    public void displayUpdateAgeRatingOptions(){
        System.out.println("New Age Rating: ");
        System.out.println("1) G");
        System.out.println("2) PG");
        System.out.println("3) PG13");
        System.out.println("4) NC16");
        System.out.println("5) M18");
        System.out.println("6) R21");

    }
    public void displayUpdateMovieStatusOptions(){
        System.out.println("New Movie Status: ");
        System.out.println("1) Coming Soon");
        System.out.println("2) Preview");
        System.out.println("3) Now Showing");
        System.out.println("4) Ending");
    }

    public void displayMovieWithReviews(List<Movie> movies) {
        System.out.println("Movie Title: Rating");
        movies.forEach(movie -> {
            System.out.printf("%s: %.1f",movie.getTitle(),movie.getRating());
        });
    }

    public Movie displayUpdateMovie(Scanner sc, List<Movie> movies) {
        System.out.println("Which movie to update?");
        displayMovies(movies);
        int update = sc.nextInt();

        Movie movie = movies.get(update-1);
        boolean loop = true;
        int choice;
        while(loop){
            displayMovieDetails(update, movie );
            displayUpdateOptions();
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Input new movie title: ");
                    movie.setTitle(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Input new director(s): ");
                    movie.setDirector(sc.nextLine());
                    break;
                case 3:
                    boolean castLoop = true;
                    while(castLoop){
                        displayUpdateCastOptions();
                        int castChoice = sc.nextInt();
                        ArrayList<String> castList = movie.getCasts();
                        switch(castChoice){
                            case 1:
                                System.out.println("Cast Name: ");
                                castList.add(sc.nextLine());
                                break;
                            case 2:
                                System.out.println("Select which cast member to remove: ");
                                for(int i = 0; i < castList.size(); i++){
                                    System.out.println(i+1 + ") " + castList.get(i));
                                }
                                castList.remove(sc.nextInt()-1);
                                break;
                            case 3:
                                System.out.println("Select which cast member to update: ");
                                for(int i = 0; i < castList.size(); i++){
                                    System.out.println(i+1 + ") " + castList.get(i));
                                }
                                int castIndex = sc.nextInt()-1;
                                System.out.println("New Cast Name: ");
                                castList.set(castIndex, sc.nextLine());
                                break;
                            case 4:
                                castLoop = false;
                                break;
                        }
                    }
                    break;
                case 4:
                    displayUpdateAgeRatingOptions();
                    int censorChoice = sc.nextInt();
                    movie.getMovieDetails().setMovieCensorship(setCensorship(censorChoice));
                    break;
                case 5:
                    System.out.println("Input new synopsis: ");
                    movie.setSynopsis(sc.nextLine());
                    break;
                case 6:
                    displayUpdateMovieStatusOptions();
                    int statusChoice = sc.nextInt();
                    movie.getMovieDetails().setMovieStatus(setMovieStatus(statusChoice));
                    break;

            }
            System.out.println("Continue Changes?");
            System.out.println("1) Yes");
            System.out.println("2) No");
            if(sc.nextInt()!= 1)
                loop = false;
        }
        return movie;
    }

    public Movie displayCreateMovie(Scanner sc) {

        ArrayList<Review> reviews = new ArrayList<Review>();
        ArrayList<String> casts = new ArrayList<String>();
        MovieDetails movieDetails;
        MovieStatus movieStatus = null;
        MovieCensorship movieCensorship = null;
        Genre genre = null;

        System.out.println("Movie Title: ");
        String title = sc.nextLine();
        System.out.println("Director: ");
        String director = sc.nextLine();
        System.out.println("Synopsis: ");
        String synopsis = sc.nextLine();

        System.out.println("Cast Members: (eg: robert, alex, sam) -> use , to add more cast members");
        String cast = sc.nextLine();
        List<String> tempList = Arrays.asList(cast.split(","));
        for (int i = 0; i < tempList.size(); i++){
            casts.add(i,tempList.get(i).trim());
        }
        System.out.println("Rating: ");
        double rating = sc.nextDouble();

        System.out.println("Movie Status: ");
        System.out.println("1) Coming Soon");
        System.out.println("2) Preview");
        System.out.println("3) Now Showing");
        System.out.println("4) Ended");
        int choice;
        movieStatus = (MovieStatus) infiniteLoop(4,sc);

        System.out.println("Movie Censorship: ");
        System.out.println("1) G");
        System.out.println("2) PG");
        System.out.println("3) PG13");
        System.out.println("4) NC16");
        System.out.println("5) M18");
        System.out.println("6) R21");

        movieCensorship = (MovieCensorship) infiniteLoop(6,sc);
        System.out.println("Genre: ");
        System.out.println("1) Action");
        System.out.println("2) Romance");
        System.out.println("3) Horror");
        System.out.println("4) Adventure");
        System.out.println("5) Mystery");
        genre = (Genre) infiniteLoop(5,sc);

        movieDetails = new MovieDetails(movieStatus,movieCensorship,genre);

        Movie mov = new Movie(title, movieDetails);
        mov.setRating(rating);
        mov.setDirector(director);
        mov.setSynopsis(synopsis);
        mov.setReviews(reviews);
        mov.setCasts(casts);
        return mov;
    }

    private Object infiniteLoop(int index, Scanner sc) {
        boolean loop = true;
        int choice = 0;
        Object obj = null;
        while (loop) {
                choice = sc.nextInt();
                if (choice < index) {
                    System.out.println("Please enter a valid number!");
                    continue;
                }
            switch (index) {
                case 4:
                    obj = setMovieStatus(choice);
                    break;
                case 5:
                    obj = setGenre(choice);
                    break;
                case 6:
                    obj = setCensorship(choice);
                    break;
                default:
                    break;
            }
            loop = false;
        }
        return obj;
    }
    private MovieStatus setMovieStatus(int choice) {
        MovieStatus movieStatus = null;
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
                break;
        }
        return movieStatus;
    }
    private Genre setGenre(int choice) {
        Genre genre = null;
        switch(choice){
            case 1:
                genre = Genre.ACTION;
                break;
            case 2:
                genre = Genre.ROMANCE;
                break;
            case 3:
                genre = Genre.HORROR;
                break;
            case 4:
                genre = Genre.ADVENTURE;
                break;
            case 5:
                genre = Genre.MYSTERY;
                break;
            default:
                break;
        }
        return genre;
    }
    private MovieCensorship setCensorship(int choice) {
        MovieCensorship movieCensorship = null;
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
        return movieCensorship;
    }
}
