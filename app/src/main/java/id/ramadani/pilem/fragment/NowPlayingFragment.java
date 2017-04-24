package id.ramadani.pilem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import id.ramadani.pilem.R;
import id.ramadani.pilem.adapter.MoviesAdapter;
import id.ramadani.pilem.model.Movie;
import id.ramadani.pilem.presenter.MoviePresenter;
import id.ramadani.pilem.util.EndlessRecyclerViewScrollListener;
import id.ramadani.pilem.util.MarginItemDecoration;
import id.ramadani.pilem.view.MovieView;

/**
 * Created by dani on 4/24/17.
 */

public class NowPlayingFragment extends Fragment implements MovieView {

    private ProgressBar mProgressBar;
    private RecyclerView mRvMovies;
    private MoviesAdapter mMoviesAdapter;
    private List<Movie> mMovieList;
    private MoviePresenter mMoviePresenter;
    private EndlessRecyclerViewScrollListener mScrollListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMovieList = new ArrayList<>();
        mMoviesAdapter = new MoviesAdapter(getActivity(), mMovieList);
        mMoviePresenter = new MoviePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_now_playing, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_movies);
        mRvMovies = (RecyclerView) view.findViewById(R.id.rv_movies);

        int movieItemMargin = view.getResources().getDimensionPixelSize(R.dimen.item_pading);
        mRvMovies.addItemDecoration(new MarginItemDecoration(movieItemMargin));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRvMovies.setLayoutManager(layoutManager);
        mRvMovies.setAdapter(mMoviesAdapter);

        mScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mMoviePresenter.load(page);
            }
        };

        mRvMovies.addOnScrollListener(mScrollListener);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mScrollListener.setCurrentPage(1);
        mMoviePresenter.load(1);
    }

    @Override
    public void pushToMovieList(List<Movie> movies) {
        mMovieList.addAll(movies);
        mMoviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(ProgressBar.GONE);
    }
}
