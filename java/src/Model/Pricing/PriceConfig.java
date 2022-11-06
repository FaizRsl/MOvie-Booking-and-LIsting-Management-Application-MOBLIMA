package Model.Pricing;

import Controller.DatabaseController;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.ArrayList;

public class PriceConfig implements Serializable {

    private double ticketBasePrice = 10;

    private double ticketPlatinumBasePrice = 20;

    private double ticketGoldBasePrice = 30;

    private double weekendIncrease = 5;

    private double publicHolidayIncrease = 7;

    private double threeDMovieIncrease = 5;

    private double blockbusterIncrease = 4;

    private double IMAXIncrease = 6;

    private double childDiscount = 0.5;

    private double studentDiscount = 0.9;

    private double seniorDiscount = 0.7;

    private double gstPercentageIncrease = 0.07;

    private ArrayList<PublicHoliday> publicHolidays = new ArrayList<>();

    private static PriceConfig instance = null;

    public PriceConfig(){
    }

    public static PriceConfig getInstance(){
        DatabaseController databaseController = DatabaseController.getInstance();
        if(instance == null){
            if(databaseController.retrievePriceConfig() == null){
                instance = new PriceConfig();
            }
            else{
                instance = databaseController.retrievePriceConfig();
            }

        }
        return instance;
    }


    public ArrayList<PublicHoliday> getPublicHolidays() {
        return publicHolidays;
    }

    public void setPublicHolidays(ArrayList<PublicHoliday> publicHolidays){
        DatabaseController databaseController = DatabaseController.getInstance();
        this.publicHolidays = publicHolidays;
        databaseController.savePriceConfig(this);
    }

    public double getTicketBasePrice() {
        return ticketBasePrice;
    }

    public void setTicketBasePrice(double ticketBasePrice) {
        DatabaseController databaseController = DatabaseController.getInstance();
        this.ticketBasePrice = ticketBasePrice;
        databaseController.savePriceConfig(this);
    }

    public double getTicketPlatinumBasePrice() {
        return ticketPlatinumBasePrice;
    }

    public double getIMAXIncrease() {
        return IMAXIncrease;
    }

    public void setIMAXIncrease(double IMAXIncrease) {
        DatabaseController databaseController = DatabaseController.getInstance();
        this.IMAXIncrease = IMAXIncrease;
        databaseController.savePriceConfig(this);
    }

    public void setTicketPlatinumBasePrice(double ticketPlatinumBasePrice) {
        DatabaseController databaseController = DatabaseController.getInstance();
        this.ticketPlatinumBasePrice = ticketPlatinumBasePrice;
        databaseController.savePriceConfig(this);
    }

    public double getTicketGoldBasePrice() {
        return ticketGoldBasePrice;
    }

    public void setTicketGoldBasePrice(double ticketGoldBasePrice) {
        DatabaseController databaseController = DatabaseController.getInstance();
        this.ticketGoldBasePrice = ticketGoldBasePrice;
        databaseController.savePriceConfig(this);
    }

    public double getWeekendIncrease() {
        return weekendIncrease;
    }

    public void setWeekendIncrease(double weekendIncrease) {
        DatabaseController databaseController = DatabaseController.getInstance();
        this.weekendIncrease = weekendIncrease;
        databaseController.savePriceConfig(this);
    }

    public double getPublicHolidayIncrease() {
        return publicHolidayIncrease;
    }

    public void setPublicHolidayIncrease(double publicHolidayIncrease) {
        DatabaseController databaseController = DatabaseController.getInstance();
        this.publicHolidayIncrease = publicHolidayIncrease;
        databaseController.savePriceConfig(this);
    }

    public double getThreeDMovieIncrease() {
        return threeDMovieIncrease;
    }

    public void setThreeDMovieIncrease(double threeDMovieIncrease) {
        DatabaseController databaseController = DatabaseController.getInstance();
        this.threeDMovieIncrease = threeDMovieIncrease;
        databaseController.savePriceConfig(this);
    }

    public double getBlockbusterIncrease() {
        return blockbusterIncrease;
    }

    public void setBlockbusterIncrease(double blockbusterIncrease) {
        DatabaseController databaseController = DatabaseController.getInstance();
        this.blockbusterIncrease = blockbusterIncrease;
        databaseController.savePriceConfig(this);
    }

    public double getChildDiscount() {
        return childDiscount;
    }

    public void setChildDiscount(double childDiscount) {
        DatabaseController databaseController = DatabaseController.getInstance();
        this.childDiscount = childDiscount;
        databaseController.savePriceConfig(this);
    }

    public double getStudentDiscount() {
        return studentDiscount;
    }

    public void setStudentDiscount(double studentDiscount) {
        DatabaseController databaseController = DatabaseController.getInstance();
        this.studentDiscount = studentDiscount;
        databaseController.savePriceConfig(this);
    }

    public double getSeniorDiscount() {
        return seniorDiscount;
    }

    public void setSeniorDiscount(double seniorDiscount) {
        DatabaseController databaseController = DatabaseController.getInstance();
        this.seniorDiscount = seniorDiscount;
        databaseController.savePriceConfig(this);
    }

    public double getGstPercentageIncrease() {
        return gstPercentageIncrease;
    }

    public void setGstPercentageIncrease(double gstPercentageIncrease) {
        DatabaseController databaseController = DatabaseController.getInstance();
        this.gstPercentageIncrease = gstPercentageIncrease;
        databaseController.savePriceConfig(this);
    }

}
