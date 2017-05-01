package id.ramadani.pilem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ramadani.pilem.R;

/**
 * Created by dani on 5/1/17.
 */

public class MovieDetailFragment extends Fragment {
    private static final String MOVIE_ID_ARG = "MOVIE_ID_ARG";
    private static final String MOVIE_TITLE_ARG = "MOVIE_TITLE_ARG";

    private String mTitle;

    public static MovieDetailFragment newInstance(int id, String title) {
        Bundle args = new Bundle();
        args.putInt(MOVIE_ID_ARG, id);
        args.putString(MOVIE_TITLE_ARG, title);

        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        movieDetailFragment.setArguments(args);

        return movieDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTitle = getArguments().getString(MOVIE_TITLE_ARG);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(mTitle);
    }
}
