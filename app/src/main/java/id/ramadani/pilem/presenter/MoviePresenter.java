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

public class MoviePresenter {
    private final MovieView view;

    public MoviePresenter(MovieView view) {
        this.view = view;
    }

    public void loadTopRated(int page) {
        final ArrayList<Movie> movies = new ArrayList<Movie>();

        TmdbService service = ApiBuilder.call();

        service.topRatedList(page).enqueue(new Callback<MovieCollectionResponse>() {
            @Override
            public void onResponse(Call<MovieCollectionResponse> call,
                                   Response<MovieCollectionResponse> response) {
                MovieCollectionResponse collectionResponse = response.body();

                for (Movie movie: collectionResponse.getMovies()) {
                    movies.add(movie);
                }

                view.movieList(movies);
            }

            @Override
            public void onFailure(Call<MovieCollectionResponse> call, Throwable t) {

            }
        });
    }
}
