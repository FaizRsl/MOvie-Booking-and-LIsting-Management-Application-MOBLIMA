package Model.Cinema;

import Model.Pricing.PriceConfig;

import java.io.Serializable;

public enum CinemaClass implements Serializable {
    NORMAL,
    GOLD,
    PLATINUM;

    public double getPrice(){
        switch(this){
            case NORMAL:
                return PriceConfig.getInstance().getTicketBasePrice();
            case GOLD:
                return PriceConfig.getInstance().getTicketGoldBasePrice();
            case PLATINUM:
                return PriceConfig.getInstance().getTicketPlatinumBasePrice();
            default:
                return PriceConfig.getInstance().getTicketBasePrice();
        }
    }

}
