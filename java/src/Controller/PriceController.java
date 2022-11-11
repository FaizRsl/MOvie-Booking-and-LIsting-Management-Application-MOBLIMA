package Controller;

import Model.Cinema.CinemaClass;
import Model.Movie.MovieType;
import Model.Pricing.PriceConfig;
import Model.Pricing.PublicHoliday;
import Model.Ticket.AdultTicket;
import Model.Ticket.SeniorTicket;
import Model.Ticket.Ticket;
import view.PriceConfigView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PriceController {
    //factors to consider: age(student/senior), cinema class(gold/platinum), day of the week(ph/weekends/weekdays), type of movie(3D/blockbuster)
    private DatabaseController databaseController = DatabaseController.getInstance();

    private PriceConfigView priceConfigMenu;

    private PriceConfig priceConfig;

    public PriceController() {
        priceConfig = databaseController.retrievePriceConfig();
        priceConfigMenu = new PriceConfigView();
    }

    public double GSTCalculation(double price){
        return (price + price * priceConfig.getGstPercentageIncrease());
    }

    public void setPrices(Scanner sc){
        priceConfigMenu.printPriceConfigMenu();

        int choice = sc.nextInt();
        sc.nextLine();
        switch(choice){
            case 1: //Cinema Type Prices
                configCinemaTypePrice(priceConfig,sc);
                break;
            case 2: //Movie Type Prices
                configMovieTypePrice(priceConfig,sc);
                break;
            case 3: //Discounts
                configDiscounts(priceConfig,sc);
                break;
            case 4: // GST
                configGST(priceConfig,sc);
                break;
            case 5: // Holidays
                configHolidays(priceConfig,sc);
                break;
        }
    }

    public void configCinemaTypePrice(PriceConfig priceConfig,Scanner sc) throws InputMismatchException {
        priceConfigMenu.printCinemaPriceConfig(priceConfig);
        int choice = sc.nextInt();
        double price = 0;
        sc.nextLine();
        System.out.println("Input new cinema type price increase:");
        price = sc.nextDouble();
        switch(choice){
            case 1:
                priceConfig.setTicketBasePrice(price);
                break;
            case 2:
                priceConfig.setTicketGoldBasePrice(price);
                break;
            case 3:
                priceConfig.setTicketPlatinumBasePrice(price);
                break;
        }
        databaseController.savePriceConfig(priceConfig);
    }

    public void configMovieTypePrice(PriceConfig priceConfig, Scanner sc) throws InputMismatchException{
        priceConfigMenu.printMoviePriceConfig(priceConfig);
        int choice = sc.nextInt();
        sc.nextLine();
        double price = 0;

        System.out.println("Input new movie type price increase:");
        price = sc.nextDouble();
        switch(choice){
            case 1:
                priceConfig.setThreeDMovieIncrease(price);
                break;
            case 2:
                priceConfig.setBlockbusterIncrease(price);
                break;
            case 3:
                priceConfig.setIMAXIncrease(price);
                break;
        }
        databaseController.savePriceConfig(priceConfig);
    }

    public void configDiscounts(PriceConfig priceConfig, Scanner sc) throws InputMismatchException{
        priceConfigMenu.printDiscountConfig(priceConfig);
        int choice = sc.nextInt();

        double discount = 0;

        System.out.println("Change Discount:");
        discount = sc.nextDouble();
        sc.nextLine();
        switch(choice){
            case 1:
                priceConfig.setChildDiscount(discount);
                break;
            case 2:
                priceConfig.setStudentDiscount(discount);
                break;
            case 3:
                priceConfig.setSeniorDiscount(discount);
                break;
        }
        databaseController.savePriceConfig(priceConfig);
    }

    public void configGST(PriceConfig priceConfig, Scanner sc) throws InputMismatchException{
        priceConfigMenu.printGSTconfig(priceConfig);
        double gst = sc.nextDouble();
        priceConfig.setGstPercentageIncrease(gst);
        databaseController.savePriceConfig(priceConfig);
    }

    public void configHolidays(PriceConfig priceConfig,Scanner sc) throws InputMismatchException{
        priceConfigMenu.printHolidaysConfig();
        int choice = sc.nextInt();

        ArrayList<PublicHoliday> publicHolidays = priceConfig.getPublicHolidays();


        switch(choice){
            case 1:
                publicHolidays.add(addHoliday(sc));
                break;
            case 2:
                priceConfigMenu.printHolidays(publicHolidays);
                System.out.println("Input holiday index to remove: ");
                publicHolidays.remove(sc.nextInt()-1);
                sc.nextLine();
                break;
            case 3:
                System.out.println("Current Holiday Price Increase: " + priceConfig.getPublicHolidayIncrease());
                System.out.println("New Public Holiday Price Increase: ");
                priceConfig.setPublicHolidayIncrease(sc.nextDouble());
                sc.nextLine();
                break;
        }

        priceConfig.setPublicHolidays(publicHolidays);
        databaseController.savePriceConfig(priceConfig);
    }

    private PublicHoliday addHoliday(Scanner sc){
        PublicHoliday ph = null;

        System.out.println("Holiday Name:");
        String holidayName = sc.nextLine();
        int year = 0, month = 0;

        boolean loop = true;

        while(loop){
            System.out.println("Input Year: ");
            year = sc.nextInt();
            if(year < LocalDate.now().getYear()){
                System.out.println("Invalid year");
            } else {
                loop = false;
            }
        }
        loop = true;

        while(loop){
            System.out.println("Input month (as a number): ");
            month = sc.nextInt();
            sc.nextLine();
            if(month <= 12 || month >= 1){
                loop = false;
            } else {
                System.out.println("Invalid month");
            }

        }

        YearMonth yearMonth = YearMonth.of(year, month);
        int lengthOfMonth = yearMonth.lengthOfMonth();
        int day = 0;
        loop = true;
        while(loop){
            System.out.println("Input day: ");
            day = sc.nextInt();
            sc.nextLine();
            if(day >= 1 || day <= lengthOfMonth){
                loop = false;
            }
        }

        LocalDate date = LocalDate.of(year, month, day);

        ph = new PublicHoliday(holidayName, date);

        return ph;

    }

    public double calculateTicketPrice(Ticket ticket){
        double price = 0;

        price = cinemaClassCalculation(ticket.getShowtime().getCinema().getCinemaClass());

        price = dayOfWeekPHCalculation(price, ticket.getShowtime().getDateTime().toLocalDate());

        price = movieTypeCalculation(price, ticket.getShowtime().getMovieType());

        price = ageCalculation(price, ticket);

        return price;
    }

    private double cinemaClassCalculation(CinemaClass cinemaClass){
        return getPriceIncrease(cinemaClass);
    }

    private double dayOfWeekPHCalculation(double price, LocalDate date){
        PublicHoliday ph;
        for(int i=0; i< priceConfig.getPublicHolidays().size(); i++){
            ph = priceConfig.getPublicHolidays().get(i);
            if(ph.getDate().equals(date)){
                return price + priceConfig.getPublicHolidayIncrease();
            }
        }

        if((date.getDayOfWeek() == DayOfWeek.SATURDAY) || (date.getDayOfWeek() == DayOfWeek.SUNDAY)){
            return price + priceConfig.getWeekendIncrease();
        }

        return price; //weekdays
    }

    private double movieTypeCalculation(double price, MovieType movieType){
        return price + getPriceIncrease(movieType);
    }

    private double ageCalculation(double price, Ticket ticket){
        int ticketType = ticket.getTicketType();
        double discount = 1;
        switch (ticketType) {
            case 2:
                discount = priceConfig.getStudentDiscount();
                break;
            case 3:
                discount = priceConfig.getChildDiscount();
                break;
            case 4:
                discount = priceConfig.getSeniorDiscount();
                break;
            default:
                break;
        }
        return price * discount;
    }

    private double getPriceIncrease(MovieType type){
        switch(type){
            case THREED:
                return priceConfig.getThreeDMovieIncrease();
            case IMAX:
                return priceConfig.getIMAXIncrease();
            case BLOCKBUSTER:
                return priceConfig.getBlockbusterIncrease();
            default:
                return 0;
        }
    }

    private double getPriceIncrease(CinemaClass cinemaClass) {
        switch(cinemaClass){
            case GOLD:
                return priceConfig.getTicketGoldBasePrice();
            case PLATINUM:
                return priceConfig.getTicketPlatinumBasePrice();
            default:
                return priceConfig.getTicketBasePrice();
        }
    }
}
