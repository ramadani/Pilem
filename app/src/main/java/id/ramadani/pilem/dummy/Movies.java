package id.ramadani.pilem.dummy;

import java.util.ArrayList;

import id.ramadani.pilem.model.Movie;

/**
 * Created by dani on 3/18/17.
 */

public class Movies {
    public static ArrayList<Movie> createMovieList(int total) {
        ArrayList<Movie> movies = new ArrayList<Movie>();

        for (int i = 1; i <= total; i++) {
            String title = "Title of Movie " + i;
            String overview = "Lorem Ipsum is simply dummy text of the printing and" +
                    " typesetting industry";
            Double voteAvg = 8.4;

            Movie movie = new Movie(title, overview, voteAvg);

            movies.add(movie);
        }

        return movies;
    }
}
