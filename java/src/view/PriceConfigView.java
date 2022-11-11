package view;

import java.util.ArrayList;

import javax.swing.plaf.synth.SynthScrollBarUI;

import Model.Pricing.PriceConfig;
import Model.Pricing.PublicHoliday;

public class PriceConfigView {
    public void printPriceConfigMenu(){
        System.out.println("----------------Price Config----------------");
        System.out.println("--------------------------------------------");
        System.out.println("1. Set Cinema Type Prices");
        System.out.println("2. Set Movie Type Prices");
        System.out.println("3. Set Discounts");
        System.out.println("4. Set GST");
        System.out.println("5. Set Holidays");
        System.out.println("--------------------------------------------");
        System.out.println("Please enter your choice:");
    }

    public void printCinemaPriceConfig(PriceConfig priceConfig){
        System.out.println("-----------Cinema Type Prices---------------");
        System.out.println("Base Ticket Price: " + priceConfig.getTicketBasePrice());
        System.out.println("Gold Ticket Price: " + priceConfig.getTicketGoldBasePrice());
        System.out.println("Platinum Ticket Price: " + priceConfig.getTicketPlatinumBasePrice());
        System.out.println("--------------------------------------------");
        System.out.println("1. Base");
        System.out.println("2. Gold");
        System.out.println("3. Platinum");
        System.out.println("--------------------------------------------");
        System.out.println("Please enter your choice:");
    }
    
    public void printMoviePriceConfig(PriceConfig priceConfig){
        System.out.println("-------------Movie Type Prices-------------");
        System.out.println("3D Ticket Price Increase: " + priceConfig.getThreeDMovieIncrease());
        System.out.println("BlockBuster Ticket Price increase: " + priceConfig.getBlockbusterIncrease());
        System.out.println("IMAX Ticket Price Increase: " + priceConfig.getIMAXIncrease());
        System.out.println("--------------------------------------------");
        System.out.println("1. 3D");
        System.out.println("2. Blockbuster");
        System.out.println("3. IMAX");
        System.out.println("--------------------------------------------");
        System.out.println("Please enter your choice:");
    }

    public void printDiscountConfig(PriceConfig priceConfig){
        System.out.println("--------------Set Discounts-----------------");
        System.out.println("Child Discount: " + priceConfig.getChildDiscount() + "%");
        System.out.println("Student Discount: " + priceConfig.getStudentDiscount() + "%");
        System.out.println("Senior Discount: " + priceConfig.getSeniorDiscount() + "%");
        System.out.println("--------------------------------------------");
        System.out.println("1. Child");
        System.out.println("2. Student");
        System.out.println("3. Senior");
        System.out.println("--------------------------------------------");
        System.out.println("Please enter your choice:");
    }

    public void printGSTconfig(PriceConfig priceConfig){
        System.out.println("-----------------Set GST--------------------");
        System.out.println("Current GST: " + priceConfig.getGstPercentageIncrease() + "%");
        System.out.println("--------------------------------------------");
        System.out.println("Set new percentage:");
    }

    public void printHolidaysConfig(){
        System.out.println("-------------Holiday Settings---------------");
        System.out.println("--------------------------------------------");
        System.out.println("1. Add holiday");
        System.out.println("2. Remove holiday");
        System.out.println("3. Change Holiday Price Increase");
        System.out.println("--------------------------------------------");
        System.out.println("Please enter your choice:");
    }

    public void printHolidays(ArrayList<PublicHoliday> publicHolidays){
        for(int i = 0; i < publicHolidays.size(); i++){
            System.out.println((i+1) + ". " + publicHolidays.get(i).getName() + "(" + publicHolidays.get(i).getDate() + ")");
        }

    }
}
