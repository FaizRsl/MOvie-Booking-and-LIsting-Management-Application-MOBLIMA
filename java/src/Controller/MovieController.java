package Controller;

import Model.Movie.*;
import view.MovieView;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MovieController {
    private List<Movie> movies;

    private MovieView movieView;

    public MovieController() {
        movieView = new MovieView();
        movies = DatabaseController.getMovieFromDB();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void createMovie(BufferedReader br) {
        String title;
        String director;
        String synopsis;
        double rating;
        ArrayList<Review> reviews = new ArrayList<Review>();
        ArrayList<String> casts = new ArrayList<String>();
        MovieDetails movieDetails;
        MovieStatus movieStatus = null;
        MovieCensorship movieCensorship = null;
        Genre genre = null;

        Scanner sc = new Scanner(System.in);


        System.out.println("Movie Title: ");
        title = sc.nextLine();
        System.out.println("Director: ");
        director = sc.nextLine();
        System.out.println("Synopsis: ");
        synopsis = sc.nextLine();

        System.out.println("Cast Members: (eg: robert, alex, sam) -> use , to add more cast members");
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

        System.out.println("Genre: ");
        System.out.println("1) Action");
        System.out.println("2) Romance");
        System.out.println("3) Horror");
        System.out.println("4) Adventure");
        System.out.println("5) Mystery");
        loop = true;

        while(loop){

            choice = sc.nextInt();
            if(choice > 6 && choice < 0){
                System.out.println("Please input a valid number!");
                continue;
            }
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
            loop = false;
        }

        movieDetails = new MovieDetails(movieStatus,movieCensorship,genre);

        Movie mov = new Movie(title, movieDetails);
        mov.setRating(rating);
        mov.setDirector(director);
        mov.setSynopsis(synopsis);
        mov.setReviews(reviews);
        mov.setCasts(casts);

        addMovie(mov);
        DatabaseController.updateMovieDB(movies);
    }

    public void removeMovieByTitle(String movieTitle) {
        Movie movie = getMovieByTitle(movieTitle);
        removeMovie(movie);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
        DatabaseController.updateMovieDB(movies);
    }

    public void removeMovieByStatus(){
        Scanner sc = new Scanner(System.in);
        displayAllMovie();
        System.out.println("What movie is to be removed?");
        int remove = sc.nextInt();
        sc.nextLine();

        movies.get(remove).getMovieDetails().setMovieStatus(MovieStatus.ENDED);
        DatabaseController.updateMovieDB(movies);
    }

    public void updateMovieDetails(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Which movie to update?");
        displayAllMovie();
        int update = sc.nextInt();

        Movie movie = movies.get(update-1);


        boolean loop = true;
        int choice;
        while(loop){
            movieView.displayMovieDetails(update, movie );
            movieView.displayUpdateOptions();
            choice = sc.nextInt();
            sc.nextLine();
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
                        movieView.displayUpdateCastOptions();
                        int castChoice = sc.nextInt();
                        sc.nextLine();
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
                                sc.nextLine();
                                break;
                            case 3:

                                System.out.println("Select which cast member to update: ");
                                for(int i = 0; i < castList.size(); i++){
                                    System.out.println(i+1 + ") " + castList.get(i));
                                }
                                int castIndex = sc.nextInt()-1;
                                sc.nextLine();
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
                    movieView.displayUpdateAgeRatingOptions();
                    int censorChoice = sc.nextInt();
                    sc.nextLine();
                    switch(censorChoice){
                        case 1:
                            movie.getMovieDetails().setMovieCensorship(MovieCensorship.G);
                            break;
                        case 2:
                            movie.getMovieDetails().setMovieCensorship(MovieCensorship.PG);
                            break;
                        case 3:
                            movie.getMovieDetails().setMovieCensorship(MovieCensorship.PG13);
                            break;
                        case 4:
                            movie.getMovieDetails().setMovieCensorship(MovieCensorship.NC16);
                            break;
                        case 5:
                            movie.getMovieDetails().setMovieCensorship(MovieCensorship.M18);
                            break;
                        case 6:
                            movie.getMovieDetails().setMovieCensorship(MovieCensorship.R21);
                            break;
                    }

                    break;
                case 5:
                    System.out.println("Input new synopsis: ");
                    movie.setSynopsis(sc.nextLine());
                    break;
                case 6:
                    movieView.displayUpdateMovieStatusOptions();
                    int statusChoice = sc.nextInt();
                    sc.nextLine();
                    switch(statusChoice){
                        case 1:
                            movie.getMovieDetails().setMovieStatus(MovieStatus.COMINGSOON);
                            break;
                        case 2:
                            movie.getMovieDetails().setMovieStatus(MovieStatus.PREVIEW);
                            break;
                        case 3:
                            movie.getMovieDetails().setMovieStatus(MovieStatus.NOWSHOWING);
                            break;
                        case 4:
                            movie.getMovieDetails().setMovieStatus(MovieStatus.ENDED);
                            break;

                    }
                    break;

            }

            System.out.println("Continue Changes?");
            System.out.println("1) Yes");
            System.out.println("2) No");
            if(sc.nextInt() != 1){
                loop = false;
            }
        }
        updateMovieDetails(movie);

    }

    public void updateMovieDetails(Movie movie) {
        movies.remove(movie);
        movies.add(movie);
        DatabaseController.updateMovieDB(movies);
    }


    public Movie getMovieByTitle(String movieTitle) {
        return movies.stream().filter(movie -> movie.getTitle().equalsIgnoreCase(movieTitle)).findFirst().orElse(null);
    }

    public void findMovieByTitle(String movieTitle) {
        Movie movie = getMovieByTitle(movieTitle);
        movieView.findMovieByTitle(movie);
    }
    public int displayCurrentShowingMovie() {
        return displayAccordingtoMovieStatus(MovieStatus.NOWSHOWING);
    }
    public int displayPreviewMovie() {
        return displayAccordingtoMovieStatus(MovieStatus.PREVIEW);
    }

    public int displayComingSoonMovie() {
        return displayAccordingtoMovieStatus(MovieStatus.COMINGSOON);
    }

    public int displayEndedMovies() {
        return displayAccordingtoMovieStatus(MovieStatus.ENDED);
    }

    public void displayAllMovie() {
        //ArrayList<Movie> showingMovies = getNonEndedMovies();
        movieView.displayMovies(movies);
    }

    public void showDetails(int choice) {
        //ArrayList<Movie> showMovies = getNonEndedMovies();
        if (choice <= movies.size()) {
         movieView.displayMovieDetails(choice,movies.get(choice-1));
        }
    }

    public void showReviewsByMovie(int choice){
        ArrayList<Movie> showMovies = getCurrentShowing();
        if (choice <= showMovies.size()) {
            movieView.displayReviewsByMovies(choice,showMovies.get(choice-1));
        }
    }

    public void addReviews(String name, double rating, String reviewContent, int movieChoice, String status){
        Review review = new Review(name, rating, reviewContent);
        ArrayList<Movie> showMovies = getCurrentShowing();
        Movie movie = getMovieByStatusAndIndex(status, movieChoice);
        System.out.println(movie.getTitle());
        movie.getReviews().add(review);
        double overallRating = Math.round(getOverallRating(movie) * 100.0)/100.0;
        movie.setRating(overallRating);
        System.out.println(movie.getReviews().size());
        updateMovieDetails(movie);
    }

    public double getOverallRating(Movie movie){
        double total = 0;
        int count = 0;
        for(int i=0; i<movie.getReviews().size(); i++){
            total += movie.getReviews().get(i).getRating();
            count++;
        }
        return (total/count);
    }

    public void getTopFiveMovieRating(){
        class MovieComparator implements Comparator<Movie>{

            public int compare(Movie s1, Movie s2) {
                if (s1.getRating() < s2.getRating())
                    return 1;
                else if (s1.getRating() > s2.getRating())
                    return -1;
                return 0;
            }
        }

        PriorityQueue<Movie> movieList = new PriorityQueue<Movie>(movies.size(), new MovieComparator());

        for(int i=0; i<movies.size(); i++){
            movieList.add(movies.get(i));
        }

        for(int i=0; i<5; i++){
            Movie movie = movieList.poll();
            System.out.print(movie.getTitle() + ": ");
            System.out.println(movie.getRating());
        }
    }

    public Movie getMovieByStatusAndIndex(String movieStatus, int movieIndex){
        Movie movie = new Movie();
        if(movieStatus.toLowerCase().equals("nowshowing")){
            ArrayList<Movie> showMovies = getCurrentShowing();
            for(int i=0; i<showMovies.size(); i++){
                if((i+1) == movieIndex){
                    movie = showMovies.get(i);
                }
            }
        }
        else if(movieStatus.toLowerCase().equals("preview")){
            ArrayList<Movie> showMovies = getPreviewShowing();
            for(int i=0; i<showMovies.size(); i++){
                if((i+1) == movieIndex){
                    movie = showMovies.get(i);
                }
            }
        }
        return movie;
    }

    public void showDetailsComingSoon(int choice) {
        ArrayList<Movie> showMovies = getComingSoon();
        if (choice <= showMovies.size()) {
            movieView.displayMovieDetails(choice,showMovies.get(choice-1));
        }
    }
    public void showDetailsCurrentShowing(int choice) {
        ArrayList<Movie> showMovies = getCurrentShowing();
        if (choice <= showMovies.size()) {
            movieView.displayMovieDetails(choice,showMovies.get(choice-1));
        }
    }

    public void showDetailsPreviewShowing(int choice) {
        ArrayList<Movie> showMovies = getPreviewShowing();
        if (choice <= showMovies.size()) {
            movieView.displayMovieDetails(choice,showMovies.get(choice-1));
        }
    }

    public void searchMovie(BufferedReader br) throws IOException{
        System.out.println("Please enter the name of the Movie:");
        String movieTitle = br.readLine();
        Movie mov = getMovieByTitle(movieTitle);
        if(mov == null){
            System.out.println("Movies doesn't exist in our database");
        }
        else{
            System.out.println(mov.getTitle() + " (" + mov.getMovieDetails().getMovieStatus() + ")");
        }
    }

    public void showDetailsEndedShowing(int choice) {
        ArrayList<Movie> showMovies = getEndedShowing();
        if (choice <= showMovies.size()) {
            movieView.displayMovieDetails(choice,showMovies.get(choice-1));
        }
    }

    private int displayAccordingtoMovieStatus(MovieStatus movieStatus) {
        ArrayList<Movie> currentShowing = movies.stream().filter(movie -> movie.getMovieDetails().getMovieStatus() == movieStatus).collect(Collectors.toCollection(ArrayList::new));
        movieView.displayMovies(currentShowing);
        return currentShowing.size();
    }

    private ArrayList<Movie> getNonEndedMovies() {
        return movies.stream().filter(movie -> movie.getMovieDetails().getMovieStatus() != MovieStatus.ENDED).collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Movie> getComingSoon() {
        return movies.stream().filter(movie -> movie.getMovieDetails().getMovieStatus() == MovieStatus.COMINGSOON).collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Movie> getCurrentShowing() {
        return movies.stream().filter(movie -> movie.getMovieDetails().getMovieStatus() == MovieStatus.NOWSHOWING).collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Movie> getEndedShowing() {
        return movies.stream().filter(movie -> movie.getMovieDetails().getMovieStatus() == MovieStatus.ENDED).collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Movie> getPreviewShowing() {
        return movies.stream().filter(movie -> movie.getMovieDetails().getMovieStatus() == MovieStatus.PREVIEW).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Movie> getMoviesFromDB() {
        movies = DatabaseController.getMovieFromDB();
        return movies;
    }

    private void addMovie(Movie movie) {
        movies.add(movie);
    }
}
