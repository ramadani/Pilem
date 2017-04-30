package id.ramadani.pilem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.ramadani.pilem.R;
import id.ramadani.pilem.model.Movie;
import id.ramadani.pilem.adapter.viewholder.MovieViewHolder;

/**
 * Created by dani on 3/18/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private final List<Movie> mMovies;
    private final OnItemClickListener mListener;

    public MoviesAdapter(List<Movie> movies, OnItemClickListener listener) {
        mMovies = movies;
        mListener = listener;
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
        Movie movie = mMovies.get(position);
        viewHolder.bind(movie, mListener);
    }

    @Override
    public int getItemCount() {
        return this.mMovies.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }
}
