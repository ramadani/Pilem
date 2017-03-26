package id.ramadani.pilem.model;

/**
 * Created by dani on 3/18/17.
 */

public class Movie {
    private String title;
    private String overview;
    private Double voteAverage;
    private String posterPath;

    public Movie(String title, String overview, Double voteAverage, String posterPath) {
        this.title = title;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getPosterUrl() {
        return "https://image.tmdb.org/t/p/w185/" + this.posterPath;
    }
}
