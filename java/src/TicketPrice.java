public class TicketPrice {

    private String ticketType;
    private double price;

    public TicketPrice(){

    }

    public TicketPrice(String ticketType){
        this.ticketType = ticketType;
        this.price = getPrice(ticketType);
    }


    public double getPrice(String ticketType) {
        if(ticketType.toLowerCase().equals("senior"))
            return 5;
        else if(ticketType.toLowerCase().equals("student"))
            return 5.60;
        else if(ticketType.toLowerCase().equals("weekdays"))
            return 5.70;
        else if(ticketType.toLowerCase().equals("weekends"))
            return 5.80;
        else if(ticketType.toLowerCase().equals("ph"))
            return 5.90;

        System.out.println("Can't be found. Please try again.");
        return 0; //if return 0 reloop the getprice again
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
