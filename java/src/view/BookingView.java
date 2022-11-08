package view;

import java.util.List;

public class BookingView {
    public void displayTopFiveMovies(List<String> movieTitles, List<Double> salesPriceList) {
        System.out.println("Movie Title : Gross Ticket Sales");
        for (int i = 0; i < movieTitles.size();i++) {
            System.out.printf("%s: $%.1f \n",movieTitles.get(i),Math.round(salesPriceList.get(i) * 100.00) /100.00);
        }
    }
}
