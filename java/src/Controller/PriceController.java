package Controller;

import Model.Cinema.CinemaClass;
import Model.Movie.MovieType;
import Model.Pricing.PriceConfig;
import Model.Pricing.PublicHoliday;
import Model.Ticket.Ticket;
import view.PriceConfigView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PriceController {
    //factors to consider: age(student/senior), cinema class(gold/platinum), day of the week(ph/weekends/weekdays), type of movie(3D/blockbuster)
    private static PriceConfigView priceConfigMenu = new PriceConfigView();

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
            if(ph.getDate().equals(date)){
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

    public static void setPrices(){
        priceConfigMenu.printPriceConfigMenu();

        PriceConfig priceConfig = PriceConfig.getInstance();
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        switch(choice){
            case 1: //Cinema Type Prices
                configCinemaTypePrice(priceConfig);
                break;
            case 2: //Movie Type Prices
                configMovieTypePrice(priceConfig);
                break;
            case 3: //Discounts
                configDiscounts(priceConfig);
                break;
            case 4: // GST
                configGST(priceConfig);
                break;
            case 5: // Holidays
                configHolidays(priceConfig);
                break;
        }
    }

    public static void configCinemaTypePrice(PriceConfig priceConfig){
        priceConfigMenu.printCinemaPriceConfig();
        Scanner sc = new Scanner(System.in);
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
    }

    public static void configMovieTypePrice(PriceConfig priceConfig){
        priceConfigMenu.printMoviePriceConfig();
        Scanner sc = new Scanner(System.in);
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
    }

    public static void configDiscounts(PriceConfig priceConfig){
        priceConfigMenu.printDiscountConfig();
        Scanner sc = new Scanner(System.in);

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
    }

    public static void configGST(PriceConfig priceConfig){
        priceConfigMenu.printGSTconfig();
        Scanner sc = new Scanner(System.in);
        double gst = sc.nextDouble();
        priceConfig.setGstPercentageIncrease(gst);
        
    }

    public static void configHolidays(PriceConfig priceConfig){
        priceConfigMenu.printHolidaysConfig();
        Scanner sc = new Scanner(System.in);

        int choice = sc.nextInt();

        ArrayList<PublicHoliday> publicHolidays = priceConfig.getPublicHolidays();

 
        switch(choice){
            case 1:
                publicHolidays.add(addHoliday());
                break;
            case 2:
                priceConfigMenu.printHolidays();
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
    }

    public static PublicHoliday addHoliday(){
        PublicHoliday ph = null;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Holiday Name:");
        String holidayName = sc.nextLine();
        int year, month = 0;
        year = LocalDate.now().getYear();

        boolean loop = true;

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

        if(month < LocalDate.now().getMonthValue()){
            year++;
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
}
