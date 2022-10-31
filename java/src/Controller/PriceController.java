package Controller;

import Model.Cinema.CinemaClass;
import Model.Movie.MovieType;
import Model.Pricing.PriceConfig;
import Model.Pricing.PublicHoliday;
import Model.Ticket.Ticket;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class PriceController {
    //factors to consider: age(student/senior), cinema class(gold/platinum), day of the week(ph/weekends/weekdays), type of movie(3D/blockbuster)

    public static double calculateTicketPrice(Ticket ticket){
        double price = 0;

        price = cinemaClassCalculation(ticket.getShowtime().getCinema().getCinemaClass());

        price = dayOfWeekPHCalculation(price, ticket.getShowtime().getDateTime().toLocalDate());

        price = movieTypeCalculation(price, ticket.getShowtime().getMovieType());

        price = ageCalculation(price, ticket);

        return price;
    }

    private static double cinemaClassCalculation(CinemaClass cinemaClass){
        return cinemaClass.getPrice();
    }

    private static double dayOfWeekPHCalculation(double price, LocalDate date){
        PublicHoliday ph;
        for(int i=0; i< PriceConfig.getInstance().getPublicHolidays().size(); i++){
            ph = PriceConfig.getInstance().getPublicHolidays().get(i);
            if(ph.getDate().isEqual(date)){
                return price + PriceConfig.getInstance().getPublicHolidayIncrease();
            }
        }

        if((date.getDayOfWeek() == DayOfWeek.SATURDAY) || (date.getDayOfWeek() == DayOfWeek.SUNDAY)){
            return price + PriceConfig.getInstance().getWeekendIncrease();
        }

        return price; //weekdays
    }

    private static double movieTypeCalculation(double price, MovieType movieType){
        return price + movieType.getPriceIncrease();
    }

    private static double ageCalculation(double price, Ticket ticket){
        return price * ticket.getDiscount();
    }


    public static double GSTCalculation(double price){
        return (price + price * PriceConfig.getInstance().getGstPercentageIncrease());
    }
}
