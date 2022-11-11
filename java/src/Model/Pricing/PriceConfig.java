package Model.Pricing;

import Controller.DatabaseController;



import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Class PriceConfig, where all prices and price effects of relevant factors are defined.
 */
public class PriceConfig implements Serializable {
	
    /** The ticket base price. */
    private static double ticketBasePrice = 10;

    /** The platinum ticket base price. */
    private static double ticketPlatinumBasePrice = 20;

    /** The gold ticket base price. */
    private static double ticketGoldBasePrice = 30;

    /** The price increase for weekend showings. */
    private static double weekendIncrease = 5;

    /** The price increase for public holiday showings. */
    private static double publicHolidayIncrease = 7;

    /** The price increase for 3D movie showings. */
    private static double threeDMovieIncrease = 5;

    /** The price increase for blockbuster showings. */
    private static double blockbusterIncrease = 4;

    /** The price increase for IMAX showings. */
    private static double IMAXIncrease = 6;

    /** The price discount for children moviegoers. */
    private static double childDiscount = 0.5;

    /** The price discount for student moviegoers. */
    private static double studentDiscount = 0.9;

    /** The price discount for senior moviegoers. */
    private static double seniorDiscount = 0.7;

    /** The percentage increase in price for GST. */
    private static double gstPercentageIncrease = 0.07;

    /** ArrayList of all public holidays. */
    private ArrayList<PublicHoliday> publicHolidays = new ArrayList<>();

    /**
     * Instantiates a new price config.
     */
    public PriceConfig(){
    }
    
    /**
     * Gets the public holidays.
     *
     * @return the public holidays
     */
    public ArrayList<PublicHoliday> getPublicHolidays() {
        return publicHolidays;
    }
    
    /**
     * Sets the public holidays.
     *
     * @param publicHolidays the new public holidays
     */
    public void setPublicHolidays(ArrayList<PublicHoliday> publicHolidays) {
        this.publicHolidays = publicHolidays;
    }
    
    /**
     * Gets the ticket base price.
     *
     * @return the ticket base price
     */
    public double getTicketBasePrice() {
        return ticketBasePrice;
    }
    
    /**
     * Sets the ticket base price.
     *
     * @param ticketBasePrice the new ticket base price
     */
    public void setTicketBasePrice(double ticketBasePrice) {
        PriceConfig.ticketBasePrice = ticketBasePrice;
    }

    /**
     * Gets the ticket platinum base price.
     *
     * @return the ticket platinum base price
     */
    public double getTicketPlatinumBasePrice() {
        return ticketPlatinumBasePrice;
    }
    /**
     * Gets the IMAX increase.
     *
     * @return the IMAX increase
     */
    public double getIMAXIncrease() {
        return IMAXIncrease;
    }
    /**
     * Sets the IMAX increase.
     *
     * @param IMAXIncrease the new IMAX increase
     */
    public void setIMAXIncrease(double IMAXIncrease) {
        PriceConfig.IMAXIncrease = IMAXIncrease;
    }
    /**
     * Sets the ticket platinum base price.
     *
     * @param ticketPlatinumBasePrice the new platinum ticket base price
     */
    public void setTicketPlatinumBasePrice(double ticketPlatinumBasePrice) {
        PriceConfig.ticketPlatinumBasePrice = ticketPlatinumBasePrice;
    }
    /**
     * Gets the gold ticket base price.
     *
     * @return the gold ticket base price
     */
    public double getTicketGoldBasePrice() {
        return ticketGoldBasePrice;
    }
    /**
     * Sets the gold ticket base price.
     *
     * @param ticketGoldBasePrice the new gold ticket base price
     */
    public void setTicketGoldBasePrice(double ticketGoldBasePrice) {
        PriceConfig.ticketGoldBasePrice = ticketGoldBasePrice;
    }
    /**
     * Gets the weekend price increase.
     *
     * @return the weekend price increase
     */
    public double getWeekendIncrease() {
        return weekendIncrease;
    }
    /**
     * Sets the weekend price increase.
     *
     * @param weekendIncrease the new weekend price increase
     */
    public void setWeekendIncrease(double weekendIncrease) {
        PriceConfig.weekendIncrease = weekendIncrease;
    }
    /**
     * Gets the public holiday price increase.
     *
     * @return the public holiday price increase
     */
    public double getPublicHolidayIncrease() {
        return publicHolidayIncrease;
    }
    /**
     * Sets the public holiday price increase.
     *
     * @param publicHolidayIncrease the new public holiday price increase, type double
     */
    public void setPublicHolidayIncrease(double publicHolidayIncrease) {
        PriceConfig.publicHolidayIncrease = publicHolidayIncrease;
    }
    /**
     * Gets the 3D movie increase.
     *
     * @return the 3D movie increase
     */
    public double getThreeDMovieIncrease() {
        return threeDMovieIncrease;
    }
    /**
     * Sets the 3D movie increase.
     *
     * @param threeDMovieIncrease the new 3D movie increase
     */
    public void setThreeDMovieIncrease(double threeDMovieIncrease) {
        PriceConfig.threeDMovieIncrease = threeDMovieIncrease;
    }
    /**
     * Gets the blockbuster increase.
     *
     * @return the blockbuster increase
     */
    public double getBlockbusterIncrease() {
        return blockbusterIncrease;
    }
    /**
     * Sets the blockbuster increase.
     *
     * @param blockbusterIncrease the new blockbuster increase
     */
    public void setBlockbusterIncrease(double blockbusterIncrease) {
        PriceConfig.blockbusterIncrease = blockbusterIncrease;
    }
    /**
     * Gets the child discount.
     *
     * @return the child discount
     */
    public double getChildDiscount() {
        return childDiscount;
    }
    /**
     * Sets the child discount.
     *
     * @param childDiscount the new child discount
     */
    public void setChildDiscount(double childDiscount) {
        PriceConfig.childDiscount = childDiscount;
    }
    /**
     * Gets the student discount.
     *
     * @return the student discount
     */
    public double getStudentDiscount() {
        return studentDiscount;
    }
    /**
     * Sets the student discount.
     *
     * @param studentDiscount the new student discount
     */
    public void setStudentDiscount(double studentDiscount) {
        PriceConfig.studentDiscount = studentDiscount;
    }
    /**
     * Gets the senior discount.
     *
     * @return the senior discount
     */
    public double getSeniorDiscount() {
        return seniorDiscount;
    }
    /**
     * Sets the senior discount.
     *
     * @param seniorDiscount the new senior discount
     */
    public void setSeniorDiscount(double seniorDiscount) {
        PriceConfig.seniorDiscount = seniorDiscount;
    }
    /**
     * Gets the gst percentage increase.
     *
     * @return the gst percentage increase
     */
    public double getGstPercentageIncrease() {
        return gstPercentageIncrease;
    }
    /**
     * Sets the gst percentage increase.
     *
     * @param gstPercentageIncrease the new gst percentage increase
     */
    public void setGstPercentageIncrease(double gstPercentageIncrease) {
        PriceConfig.gstPercentageIncrease = gstPercentageIncrease;
    }

}
