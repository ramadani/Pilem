package id.ramadani.pilem.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import id.ramadani.pilem.model.Movie;

/**
 * Created by dani on 3/19/17.
 */

public class MovieCollectionResponse {
    int page;
    int totalResults;
    int totalPages;

    @SerializedName("results")
    List<Movie> movies;

    public MovieCollectionResponse() {
        movies = new ArrayList<Movie>();
    }

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
