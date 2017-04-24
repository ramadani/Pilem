package id.ramadani.pilem.presenter;

import java.util.ArrayList;

import id.ramadani.pilem.api.response.MovieCollectionResponse;
import id.ramadani.pilem.model.Movie;
import id.ramadani.pilem.view.MovieView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dani on 4/24/17.
 */

public class TopRatedMoviePresenter extends MoviePresenter implements MoviePresenterContract {

    @Override
    public void setView(MovieView view) {
        this.view = view;
    }

    @Override
    public void load(int page) {
        final ArrayList<Movie> movies = new ArrayList<>();

        view.showProgress();
        mService.topRatedList(page).enqueue(new Callback<MovieCollectionResponse>() {
            @Override
            public void onResponse(Call<MovieCollectionResponse> call,
                                   Response<MovieCollectionResponse> response) {
                MovieCollectionResponse collectionResponse = response.body();

                for (Movie movie: collectionResponse.getMovies()) {
                    movies.add(movie);
                }

                view.pushToMovieList(movies);
                view.hideProgress();
            }

            @Override
            public void onFailure(Call<MovieCollectionResponse> call, Throwable t) {
                view.hideProgress();
            }
        });
    }
}
