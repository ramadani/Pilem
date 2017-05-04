package id.ramadani.pilem.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import id.ramadani.pilem.R;
import id.ramadani.pilem.util.GrayscaleTransformation;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = view.getContext();

        TabLayout tabs = (TabLayout) view.findViewById(R.id.tab_movie_detail);
        tabs.addTab(tabs.newTab().setText("Info"));
        tabs.addTab(tabs.newTab().setText("Cast"));
        tabs.addTab(tabs.newTab().setText("Crew"));

        ImageView imgBackdrop = (ImageView) view.findViewById(R.id.iv_movie_backdrop);
        View viewOverlay = view.findViewById(R.id.view_backdrop_overlay);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_movie_detail_title);

        Picasso.with(context)
                .load(R.mipmap.civil_war_backdrop)
                .transform(new GrayscaleTransformation(Picasso.with(context)))
                .into(imgBackdrop);

        imgBackdrop.setContentDescription(mTitle);
        viewOverlay.setAlpha((float) 0.8);
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
