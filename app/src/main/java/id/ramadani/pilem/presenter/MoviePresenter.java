package id.ramadani.pilem.presenter;

import java.util.ArrayList;

import id.ramadani.pilem.api.ApiBuilder;
import id.ramadani.pilem.api.TmdbService;
import id.ramadani.pilem.api.response.MovieCollectionResponse;
import id.ramadani.pilem.model.Movie;
import id.ramadani.pilem.view.MovieView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dani on 3/19/17.
 */

public abstract class MoviePresenter {
    protected MovieView view;
    protected TmdbService mService;

    public MoviePresenter() {
        mService = ApiBuilder.call();
    }

    public static class MovieListCallback implements Callback<MovieCollectionResponse> {
        private MovieView mMovieView;

        public MovieListCallback(MovieView movieView) {
            mMovieView = movieView;
        }

        @Override
        public void onResponse(Call<MovieCollectionResponse> call,
                               Response<MovieCollectionResponse> response) {

            final ArrayList<Movie> movies = new ArrayList<>();

            MovieCollectionResponse collectionResponse = response.body();

            for (Movie movie: collectionResponse.getMovies()) {
                movies.add(movie);
            }

            mMovieView.pushToMovieList(movies);
            mMovieView.hideProgress();
        }

        @Override
        public void onFailure(Call<MovieCollectionResponse> call, Throwable t) {
            mMovieView.hideProgress();
        }
    }
}
