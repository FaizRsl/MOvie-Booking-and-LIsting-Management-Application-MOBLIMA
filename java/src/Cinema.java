import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Cinema {

    private UUID id;
    private String name;
    private List<Movie> movieList;
    private Ticket ticket;
    private Seats seats;
    private int totalSales;

    public Cinema(){
    }

    public Cinema(List<Movie> movieList){
        this.movieList = movieList;
        this.seats = new Seats();
    }

    public Cinema(String name, List<Movie> movieList, Seats seats, int totalSales){
        this.name = name;
        this.movieList = movieList;
        this.seats = seats;
        this.totalSales = totalSales;
    }

    public Cinema(String name, Ticket ticket){
        this.name = name;
        this.ticket = ticket;
    }

    public UUID getId() {return id;}

    public void setId(UUID id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
    public Seats getSeats() {return seats;}

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public int getTotalSales() {return totalSales;}

    public void setTotalSales(int totalSales) {this.totalSales = totalSales;}

    public void findMovie(String movieName){
        Movie mov = null;
        for(int i = 0; i < movieList.size(); i++){
            mov = (Movie) movieList.get(i);
            if(movieName.toLowerCase().equals(mov.getTitle().toLowerCase())){
                System.out.println("Movie exist. Book yours now!");
                break;
            }
        }
    }

    public void listAllMovies(){
        Movie mov = null;
        System.out.println("Movie Title(s)");
        System.out.println("=================");
        for(int i = 0; i < movieList.size(); i++){
            mov = (Movie) movieList.get(i);
            System.out.println((i+1) + ") " + mov.getTitle());
        }
    }

    public void displayAllMovies(){
        Scanner sc = new Scanner(System.in);
        Movie mov = null;
        System.out.println("Movie Title(s)");
        System.out.println("=================");
        for(int i = 0; i < movieList.size(); i++){
            mov = (Movie) movieList.get(i);
            System.out.println((i+1) + ") " + mov.getTitle());
        }

        System.out.println("Select Movie Number to show more details: ");
        int showMore = sc.nextInt();
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
