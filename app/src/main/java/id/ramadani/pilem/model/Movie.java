package id.ramadani.pilem.model;

/**
 * Created by dani on 3/18/17.
 */

public class Movie {
    private String title;
    private String overview;
    private Double voteAverage;

    public Movie(String title, String overview, Double voteAverage) {
        this.title = title;
        this.overview = overview;
        this.voteAverage = voteAverage;
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
}
