public class TicketPrice {

    enum TicketType {
        SENIOR,
        STUDENT,
        WEEKDAYS,
        WEEKENDS,
        PH
    }

    enum MovieType {
        HORROR,
        ROMANCE,
        ACTION
    }

    private TicketType ticketType;
    private float price;

    private MovieType movieType;


    public TicketPrice(){

    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }


}
