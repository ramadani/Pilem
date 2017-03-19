package id.ramadani.pilem.presenter;

import java.util.ArrayList;

import id.ramadani.pilem.model.Movie;
import id.ramadani.pilem.view.MovieView;

/**
 * Created by dani on 3/19/17.
 */

public class MoviePresenter {
    private final MovieView view;

    public MoviePresenter(MovieView view) {
        this.view = view;
    }

    public void list() {
        ArrayList<Movie> movies = new ArrayList<Movie>();

        for (int i = 1; i <= 10; i++) {
            String title = "Title of Movie " + i;
            String overview = "Lorem Ipsum is simply dummy text of the printing and" +
                    " typesetting industry";
            Double voteAvg = 8.4;

            Movie movie = new Movie(title, overview, voteAvg);

            movies.add(movie);
        }

        this.view.movieList(movies);
    }
}
