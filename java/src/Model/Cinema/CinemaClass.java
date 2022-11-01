package Model.Cinema;

import Model.Pricing.PriceConfig;

import java.io.Serializable;

public class CinemaClass implements Serializable {

    private String className;

    CinemaClass(String className){
        this.className = className;
    }

    public String getClassName() {return className;}

    public double getPrice(){
        switch(this.className){
            case "normal":
                return PriceConfig.getInstance().getTicketBasePrice();
            case "gold":
                return PriceConfig.getInstance().getTicketGoldBasePrice();
            case "platinum":
                return PriceConfig.getInstance().getTicketPlatinumBasePrice();
            default:
                return PriceConfig.getInstance().getTicketBasePrice();
        }
    }

}
