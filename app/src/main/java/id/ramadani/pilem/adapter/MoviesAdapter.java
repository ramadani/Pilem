package id.ramadani.pilem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.ramadani.pilem.R;
import id.ramadani.pilem.model.Movie;
import id.ramadani.pilem.adapter.viewholder.MovieViewHolder;

/**
 * Created by dani on 3/18/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private Context mContext;
    private List<Movie> mMovies;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.mContext = context;
        this.mMovies = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View movieView = inflater.inflate(R.layout.item_movie, parent, false);

        return new MovieViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder viewHolder, int position) {
        Movie movie = this.mMovies.get(position);

        viewHolder.title.setText(movie.getTitle());
        viewHolder.overview.setText(movie.getOverview());
        viewHolder.voteAvg.setText(String.valueOf(movie.getVoteAverage()));

        Picasso.with(getContext()).load(movie.getPosterUrl()).into(viewHolder.poster);
    }

    @Override
    public int getItemCount() {
        return this.mMovies.size();
    }

    private Context getContext() {
        return this.mContext;
    }
}
