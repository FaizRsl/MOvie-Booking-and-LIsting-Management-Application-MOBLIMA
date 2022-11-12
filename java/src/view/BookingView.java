package view;

import Model.Booking.Booking;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
/**
 * The Class BookingView, for displaying bookings for a user in a readable format.
 */
public class BookingView {
	
	/**
	 * Displays top five movies. Prints data of movie titles and corresponding gross ticket sales respectively.
	 * 
	 * @param List<String> movieTitles
	 * @param List<Double> salesPriceList
	 * 
	 * 
	 */
    public void displayTopFiveMovies(List<String> movieTitles, List<Double> salesPriceList) {
        System.out.println("Movie Title : Gross Ticket Sales");
        System.out.println("--------------------------------------------");
        for (int i = 0; i < movieTitles.size();i++) {
            System.out.printf("%s: $%.1f \n",movieTitles.get(i),Math.round(salesPriceList.get(i) * 100.00) /100.00);
        }
    }
	/**
	 * Displays booking history of a user.
	 * 
	 * @param Scanner sc
	 * @param List<Booking> bookingList
	 * 
	 */
    public void displayBookingHistory(Scanner sc,List<Booking> bookingList) {
        int count = 0;
        if(bookingList.size() == 0)
            System.out.println("There is no booking for this user.");
        else
            while(true){
                System.out.println("You have " + bookingList.size() + " bookings.");
                System.out.println("Please select which bookings you wish to view (1-" +bookingList.size()+ "):");
                int choice = 0;
                try {
                    choice = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    if (count > 3)
                        throw e;
                    System.out.println("Unable to recognize your input. Please try again!");
                    System.out.println("Expected Input: Integer");
                    System.out.println("Input:" + e);
                    count++;
                    sc.nextLine();
                }
                if((choice-1) < 0 || choice > bookingList.size()){
                    System.out.println("Invalid input. Please try again");
                }
                else{
                    System.out.println(bookingList.get(choice-1).toString());
                    break;
                }
            }
    }
}
