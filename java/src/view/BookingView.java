package view;

import Model.Booking.Booking;
import Model.Movie.Movie;

import java.util.ArrayList;
import java.util.List;

public class BookingView {

    public List<Booking> findBookingByUsername(List<Booking> bookings, String username) {
        List<Booking> bookingList = new ArrayList<>();
        for(int i=(bookings.size()-1); i>=0; i--){
            if(bookings.get(i).getBuyerName().toLowerCase().equals(username))
                bookingList.add(bookings.get(i));
        }
        return bookingList;
    }




}
