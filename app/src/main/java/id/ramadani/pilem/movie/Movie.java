package id.ramadani.pilem.movie;

/**
 * Created by dani on 3/15/17.
 */

public class Movie {
    private String title;
    private String overview;
    private String releaseDate;
    private Double voteAverage;
    private String posterPath;

    public Movie(String title, String overview, String releaseDate, Double voteAverage) {
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
