public class TicketPrice {

//    enum TicketType {
//        SENIOR,
//        STUDENT,
//        WEEKDAYS,
//        WEEKENDS,
//        PH
//    }
//
//    enum MovieType {
//        HORROR,
//        ROMANCE,
//        ACTION
//    }

    private String ticketType;
    private float price;

    private String movieType;


    public TicketPrice(String ticketPrice, String movieType, float price){
        this.ticketType = ticketPrice;
        this.movieType = movieType;
        this.price = price;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }


}
