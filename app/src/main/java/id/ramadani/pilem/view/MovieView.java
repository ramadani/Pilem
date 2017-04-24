package id.ramadani.pilem.view;

import java.util.List;

import id.ramadani.pilem.model.Movie;

/**
 * Created by dani on 3/19/17.
 */

public interface MovieView {
    void pushToMovieList(List<Movie> movies);

    void showProgress();

    void hideProgress();
}
