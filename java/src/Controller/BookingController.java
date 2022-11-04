package Controller;

import Model.Booking.Booking;

import java.util.List;
import java.util.stream.Collectors;

public class BookingController {

    private List<Booking> bookings;

    public BookingController() {
        bookings = DatabaseController.getBookingFromDB();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        DatabaseController.updateBookingDB(bookings);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
        DatabaseController.updateBookingDB(bookings);
    }

    public void updateBooking(Booking booking) {
        bookings = bookings.stream().filter(bookingItem -> !bookingItem.getId().equals(booking.getId())).collect(Collectors.toList());
        bookings.add(booking);
        DatabaseController.updateBookingDB(bookings);
    }
}
