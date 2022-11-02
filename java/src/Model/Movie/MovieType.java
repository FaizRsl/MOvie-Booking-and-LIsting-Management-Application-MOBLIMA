package Model.Movie;

import Model.Pricing.PriceConfig;

import java.io.Serializable;

public enum MovieType implements Serializable {
    NORMAL,
    THREED,
    IMAX,
    BLOCKBUSTER;

    public double getPriceIncrease(){
        switch(this){
            case NORMAL:
                return 0;
            case THREED:
                return PriceConfig.getInstance().getThreeDMovieIncrease();
            case BLOCKBUSTER:
                return PriceConfig.getInstance().getBlockbusterIncrease();
            default:
                return 0;
        }
    }
}
