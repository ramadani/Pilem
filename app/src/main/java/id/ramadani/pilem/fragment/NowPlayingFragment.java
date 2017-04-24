package id.ramadani.pilem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.ramadani.pilem.R;
import id.ramadani.pilem.adapter.MoviesAdapter;
import id.ramadani.pilem.model.Movie;
import id.ramadani.pilem.presenter.MoviePresenter;
import id.ramadani.pilem.util.MarginItemDecoration;
import id.ramadani.pilem.view.MovieView;

/**
 * Created by dani on 4/24/17.
 */

public class NowPlayingFragment extends Fragment implements MovieView {

    RecyclerView rvMovies;
    MoviesAdapter moviesAdapter;
    List<Movie> movies;
    MoviePresenter moviePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movies = new ArrayList<>();
        moviesAdapter = new MoviesAdapter(getActivity(), movies);
        moviePresenter = new MoviePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_now_playing, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        rvMovies = (RecyclerView) view.findViewById(R.id.rv_movies);

        int movieItemMargin = view.getResources().getDimensionPixelSize(R.dimen.item_pading);
        rvMovies.addItemDecoration(new MarginItemDecoration(movieItemMargin));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvMovies.setLayoutManager(layoutManager);
        rvMovies.setAdapter(moviesAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        moviePresenter.load(1);
    }

    @Override
    public void pushToMovieList(List<Movie> movies) {
        this.movies.addAll(movies);
        moviesAdapter.notifyDataSetChanged();
    }
}
