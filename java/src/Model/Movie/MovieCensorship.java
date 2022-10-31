package Model.Movie;

import java.io.Serializable;

public enum MovieCensorship implements Serializable {

    G("G"),
    PG("PG"),
    PG13("PG13"),
    NC16("NC16"),
    M18("M18"),
    R21("R21");
    private String censorshipName;

    MovieCensorship(String censorshipName) {this.censorshipName = censorshipName;}

    public String getCensorshipName() { return censorshipName; }
}
