package id.ramadani.pilem.api;

import id.ramadani.pilem.api.response.MovieCollectionResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dani on 3/19/17.
 */

public interface TmdbService {
    @GET("movie/now_playing")
    Call<MovieCollectionResponse> nowPlaying(@Query("page") int page);

    @GET("movie/latest")
    Call<MovieCollectionResponse> latest(@Query("page") int page);

    @GET("movie/upcoming")
    Call<MovieCollectionResponse> upcoming(@Query("page") int page);

    @GET("movie/popular")
    Call<MovieCollectionResponse> popular(@Query("page") int page);

    @GET("movie/top_rated")
    Call<MovieCollectionResponse> topRated(@Query("page") int page);
}
