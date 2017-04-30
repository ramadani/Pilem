package id.ramadani.pilem.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dani on 3/18/17.
 */

public class Movie {
    private Integer id;
    private String title;
    private String overview;
    private Double voteAverage;
    private String posterPath;
    private String releaseDate;

    public Movie(String title, String overview, Double voteAverage, String posterPath) {
        this.title = title;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
    }

    public Integer getId() {
        return id;
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

    public String getReleaseDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = dateFormat.parse(releaseDate);

        return SimpleDateFormat.getDateInstance(SimpleDateFormat.FULL).format(date);
    }

    public String getPosterUrl() {
        return "https://image.tmdb.org/t/p/w185/" + this.posterPath;
    }
}
