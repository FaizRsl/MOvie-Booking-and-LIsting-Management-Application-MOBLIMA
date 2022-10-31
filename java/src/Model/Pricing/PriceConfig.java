package Model.Pricing;

import Controller.DatabaseController;

import java.util.ArrayList;

public class PriceConfig {

    private double ticketBasePrice = 10;

    private double ticketPlatinumBasePrice = 20;

    private double ticketGoldBasePrice = 30;

    private double weekendIncrease = 5;

    private double publicHolidayIncrease = 7;

    private double threeDMovieIncrease = 5;

    private double blockbusterIncrease = 4;

    private double childDiscount = 0.5;

    private double studentDiscount = 0.9;

    private double seniorDiscount = 0.7;

    private double gstPercentageIncrease = 0.07;

    private ArrayList<PublicHoliday> publicHolidays = new ArrayList<>();

    private static PriceConfig instance = null;

    public PriceConfig(){

    }

    public static PriceConfig getInstance(){
        if(instance == null){
            if(DatabaseController.retrievePriceConfig() == null){
                instance = new PriceConfig();
            }
            else{
                instance = DatabaseController.retrievePriceConfig();
            }

        }
        return instance;
    }


    public ArrayList<PublicHoliday> getPublicHolidays() {
        return publicHolidays;
    }

    public void setPublicHolidays(ArrayList<PublicHoliday> publicHolidays){
        this.publicHolidays = publicHolidays;
        DatabaseController.savePriceConfig(this);
    }

    public double getTicketBasePrice() {
        return ticketBasePrice;
    }

    public void setTicketBasePrice(double ticketBasePrice) {
        this.ticketBasePrice = ticketBasePrice;
        DatabaseController.savePriceConfig(this);
    }

    public double getTicketPlatinumBasePrice() {
        return ticketPlatinumBasePrice;
    }

    public void setTicketPlatinumBasePrice(double ticketPlatinumBasePrice) {
        this.ticketPlatinumBasePrice = ticketPlatinumBasePrice;
        DatabaseController.savePriceConfig(this);
    }

    public double getTicketGoldBasePrice() {
        return ticketGoldBasePrice;
    }

    public void setTicketGoldBasePrice(double ticketGoldBasePrice) {
        this.ticketGoldBasePrice = ticketGoldBasePrice;
        DatabaseController.savePriceConfig(this);
    }

    public double getWeekendIncrease() {
        return weekendIncrease;
    }

    public void setWeekendIncrease(double weekendIncrease) {
        this.weekendIncrease = weekendIncrease;
        DatabaseController.savePriceConfig(this);
    }

    public double getPublicHolidayIncrease() {
        return publicHolidayIncrease;
    }

    public void setPublicHolidayIncrease(double publicHolidayIncrease) {
        this.publicHolidayIncrease = publicHolidayIncrease;
        DatabaseController.savePriceConfig(this);
    }

    public double getThreeDMovieIncrease() {
        return threeDMovieIncrease;
    }

    public void setThreeDMovieIncrease(double threeDMovieIncrease) {
        this.threeDMovieIncrease = threeDMovieIncrease;
        DatabaseController.savePriceConfig(this);
    }

    public double getBlockbusterIncrease() {
        return blockbusterIncrease;
    }

    public void setBlockbusterIncrease(double blockbusterIncrease) {
        this.blockbusterIncrease = blockbusterIncrease;
        DatabaseController.savePriceConfig(this);
    }

    public double getChildDiscount() {
        return childDiscount;
    }

    public void setChildDiscount(double childDiscount) {
        this.childDiscount = childDiscount;
        DatabaseController.savePriceConfig(this);
    }

    public double getStudentDiscount() {
        return studentDiscount;
    }

    public void setStudentDiscount(double studentDiscount) {
        this.studentDiscount = studentDiscount;
        DatabaseController.savePriceConfig(this);
    }

    public double getSeniorDiscount() {
        return seniorDiscount;
    }

    public void setSeniorDiscount(double seniorDiscount) {
        this.seniorDiscount = seniorDiscount;
        DatabaseController.savePriceConfig(this);
    }

    public double getGstPercentageIncrease() {
        return gstPercentageIncrease;
    }

    public void setGstPercentageIncrease(double gstPercentageIncrease) {
        this.gstPercentageIncrease = gstPercentageIncrease;
        DatabaseController.savePriceConfig(this);
    }

}
