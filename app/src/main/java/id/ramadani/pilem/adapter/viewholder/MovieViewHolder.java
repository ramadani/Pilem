package id.ramadani.pilem.adapter.viewholder;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;

import id.ramadani.pilem.R;
import id.ramadani.pilem.adapter.MoviesAdapter;
import id.ramadani.pilem.model.Movie;

/**
 * Created by dani on 3/18/17.
 */

public class MovieViewHolder extends ViewHolder {
    private TextView title;
    private TextView overview;
    private TextView voteAvg;
    private TextView releaseDate;
    private ImageView poster;

    public MovieViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.tv_movie_title);
        overview = (TextView) itemView.findViewById(R.id.tv_movie_overview);
        voteAvg = (TextView) itemView.findViewById(R.id.tv_movie_vote_avg);
        releaseDate = (TextView) itemView.findViewById(R.id.tv_movie_release_date);
        poster = (ImageView) itemView.findViewById(R.id.iv_movie_poster);
    }

    public void bind(final Movie movie, final MoviesAdapter.OnItemClickListener listener) {
        Picasso.with(itemView.getContext()).load(movie.getPosterUrl()).into(poster);

        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        poster.setContentDescription(movie.getTitle());

        if (movie.getVoteAverage() > 0) {
            voteAvg.setText(String.valueOf(movie.getVoteAverage()));
        } else {
            voteAvg.setVisibility(View.GONE);
        }

        try {
            releaseDate.setText(movie.getReleaseDate());
        } catch (ParseException e) {
            releaseDate.setVisibility(View.GONE);
            Log.e("RELEASE_DATE", "Error Release Date", e);
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(movie);
            }
        });
    }
}
