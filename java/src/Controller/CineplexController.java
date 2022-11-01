package Controller;

import Model.Cinema.Cineplex;
import Model.Movie.Movie;
import view.CineplexView;

import java.io.BufferedReader;
import java.util.ArrayList;

public class CineplexController {

    private ArrayList<Cineplex> cineplexes;

    private CineplexView cineplexView;

    private BufferedReader br;

    public CineplexController(BufferedReader br) {
        this.br = br;
    }

    public void serializeDataWithTXT() {

    }

    public void populateCineplexesWithMovies(ArrayList<Movie> movies) {
    }
}
