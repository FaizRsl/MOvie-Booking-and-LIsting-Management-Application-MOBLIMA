package Model.Pricing;

import Controller.DatabaseController;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.ArrayList;

public class PriceConfig implements Serializable {

    private static double ticketBasePrice = 10;
    private static double ticketPlatinumBasePrice = 20;
    private static double ticketGoldBasePrice = 30;
    private static double weekendIncrease = 5;
    private static double publicHolidayIncrease = 7;
    private static double threeDMovieIncrease = 5;
    private static double blockbusterIncrease = 4;
    private static double IMAXIncrease = 6;
    private static double childDiscount = 0.5;
    private static double studentDiscount = 0.9;
    private static double seniorDiscount = 0.7;
    private static double gstPercentageIncrease = 0.07;
    private ArrayList<PublicHoliday> publicHolidays = new ArrayList<>();

    public PriceConfig(){
    }

    public ArrayList<PublicHoliday> getPublicHolidays() {
        return publicHolidays;
    }

    public void setPublicHolidays(ArrayList<PublicHoliday> publicHolidays) {
        this.publicHolidays = publicHolidays;
    }

    public double getTicketBasePrice() {
        return ticketBasePrice;
    }

    public void setTicketBasePrice(double ticketBasePrice) {
        PriceConfig.ticketBasePrice = ticketBasePrice;
    }

    public double getTicketPlatinumBasePrice() {
        return ticketPlatinumBasePrice;
    }

    public double getIMAXIncrease() {
        return IMAXIncrease;
    }

    public void setIMAXIncrease(double IMAXIncrease) {
        PriceConfig.IMAXIncrease = IMAXIncrease;
    }

    public void setTicketPlatinumBasePrice(double ticketPlatinumBasePrice) {
        PriceConfig.ticketPlatinumBasePrice = ticketPlatinumBasePrice;
    }

    public double getTicketGoldBasePrice() {
        return ticketGoldBasePrice;
    }

    public void setTicketGoldBasePrice(double ticketGoldBasePrice) {
        PriceConfig.ticketGoldBasePrice = ticketGoldBasePrice;
    }

    public double getWeekendIncrease() {
        return weekendIncrease;
    }

    public void setWeekendIncrease(double weekendIncrease) {
        PriceConfig.weekendIncrease = weekendIncrease;
    }

    public double getPublicHolidayIncrease() {
        return publicHolidayIncrease;
    }

    public void setPublicHolidayIncrease(double publicHolidayIncrease) {
        PriceConfig.publicHolidayIncrease = publicHolidayIncrease;
    }

    public double getThreeDMovieIncrease() {
        return threeDMovieIncrease;
    }

    public void setThreeDMovieIncrease(double threeDMovieIncrease) {
        PriceConfig.threeDMovieIncrease = threeDMovieIncrease;
    }

    public double getBlockbusterIncrease() {
        return blockbusterIncrease;
    }

    public void setBlockbusterIncrease(double blockbusterIncrease) {
        PriceConfig.blockbusterIncrease = blockbusterIncrease;
    }

    public double getChildDiscount() {
        return childDiscount;
    }

    public void setChildDiscount(double childDiscount) {
        PriceConfig.childDiscount = childDiscount;
    }

    public double getStudentDiscount() {
        return studentDiscount;
    }

    public void setStudentDiscount(double studentDiscount) {
        PriceConfig.studentDiscount = studentDiscount;
    }

    public double getSeniorDiscount() {
        return seniorDiscount;
    }

    public void setSeniorDiscount(double seniorDiscount) {
        PriceConfig.seniorDiscount = seniorDiscount;
    }

    public double getGstPercentageIncrease() {
        return gstPercentageIncrease;
    }

    public void setGstPercentageIncrease(double gstPercentageIncrease) {
        PriceConfig.gstPercentageIncrease = gstPercentageIncrease;
    }

}
