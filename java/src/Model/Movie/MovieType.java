package Model.Movie;

import Model.Pricing.PriceConfig;

import java.io.Serializable;

public class MovieType implements Serializable {

    private String movieTypeName;

    MovieType(String movieTypeName){this.movieTypeName = movieTypeName;}

    public String getMovieTypeName(){return movieTypeName;}

    public double getPriceIncrease(){
        switch(this.movieTypeName){
            case "normal":
                return 0;
            case "threed":
                return PriceConfig.getInstance().getThreeDMovieIncrease();
            case "blockbuster":
                return PriceConfig.getInstance().getBlockbusterIncrease();
            default:
                return 0;
        }
    }
}
