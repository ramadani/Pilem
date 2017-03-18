package id.ramadani.pilem.models;

/**
 * Created by dani on 3/18/17.
 */

public class Movie {
    private String mTitle;
    private String mOverview;
    private Double mVoteAverage;

    public Movie(String title, String overview, Double voteAverage) {
        this.mTitle = title;
        this.mOverview = overview;
        this.mVoteAverage = voteAverage;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getOverview() {
        return this.mOverview;
    }

    public Double getVoteAverage() {
        return this.mVoteAverage;
    }
}
