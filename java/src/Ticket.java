import java.util.UUID;

public class Ticket {

    private UUID id;
    private double price;
    private int seatNumber;
    private String movieTitle;
    private TicketPrice ticketPrice;

    public Ticket(int seatNumber, String movieTitle, String ticketType){
        this.id = UUID.randomUUID();
        ticketPrice = new TicketPrice();
        this.price = ticketPrice.getPrice(ticketType);
        this.seatNumber = seatNumber;
        this.movieTitle = movieTitle;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getPrice(String ticketType) {
        return ticketPrice.getPrice(ticketType);
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public TicketPrice getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(TicketPrice ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

}
