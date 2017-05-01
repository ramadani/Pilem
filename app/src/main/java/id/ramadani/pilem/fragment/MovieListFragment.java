package id.ramadani.pilem.fragment;

import android.content.Context;
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
import id.ramadani.pilem.presenter.MoviePresenterContract;
import id.ramadani.pilem.util.EndlessRecyclerViewScrollListener;
import id.ramadani.pilem.util.MarginItemDecoration;
import id.ramadani.pilem.view.MovieView;

/**
 * Created by dani on 4/24/17.
 */

public class MovieListFragment extends Fragment implements MovieView {
    private static final String TAG = MovieListFragment.class.getSimpleName();
    private static final String MOVIE_PRESENTER_KEY = "MOVIE_PRESENTER_KEY";
    private static final String MOVIE_TITLE_KEY = "MOVIE_TITLE_KEY";
    private int mTitleId;
    private ProgressBar mProgressBar;
    private RecyclerView mRvMovies;
    private MoviesAdapter mMoviesAdapter;
    private List<Movie> mMovieList;
    private MoviePresenterContract mMoviePresenter;
    private EndlessRecyclerViewScrollListener mScrollListener;
    private OnItemSelectedListener mItemSelectedListener;

    public static MovieListFragment newInstance(MoviePresenterContract moviePresenterContract,
                                                int titleId) {
        Bundle args = new Bundle();
        args.putSerializable(MOVIE_PRESENTER_KEY, moviePresenterContract);
        args.putInt(MOVIE_TITLE_KEY, titleId);

        MovieListFragment fragment = new MovieListFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnItemSelectedListener) {
            mItemSelectedListener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement "
                    + OnItemSelectedListener.class.getName());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMovieList = new ArrayList<>();
        mMoviesAdapter = new MoviesAdapter(mMovieList);
        mMoviePresenter =
                (MoviePresenterContract) getArguments().getSerializable(MOVIE_PRESENTER_KEY);
        mMoviePresenter.setView(this);
        mTitleId = getArguments().getInt(MOVIE_TITLE_KEY, R.string.app_name);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_movies);
        mRvMovies = (RecyclerView) view.findViewById(R.id.rv_movies);

        int movieItemMargin = view.getResources().getDimensionPixelSize(R.dimen.item_padding);
        mRvMovies.addItemDecoration(new MarginItemDecoration(movieItemMargin));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRvMovies.setLayoutManager(layoutManager);
        mRvMovies.setAdapter(mMoviesAdapter);

        mMoviesAdapter.setOnItemClickListener(new MoviesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                mItemSelectedListener.onMovieSelected(movie);
            }
        });

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
    public void onResume() {
        super.onResume();
        getActivity().setTitle(mTitleId);
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

    public interface OnItemSelectedListener {
        void onMovieSelected(Movie movie);
    }
}
