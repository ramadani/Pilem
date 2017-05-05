package id.ramadani.pilem.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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

        TabLayout tabs = (TabLayout) view.findViewById(R.id.tab_movie_detail);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.vp_movie_detail);

        setUpBackdrop(view);
        setUpViewPager(viewPager);
        tabs.setupWithViewPager(viewPager);
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

    private void setUpBackdrop(View view) {
        Context context = view.getContext();

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

    private void setUpViewPager(ViewPager viewPager) {
        PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager());
        pagerAdapter.addFragment(new MovieInfoFragment(), "Info");
        pagerAdapter.addFragment(new MovieInfoFragment(), "Cast");
        pagerAdapter.addFragment(new MovieInfoFragment(), "Crew");

        viewPager.setAdapter(pagerAdapter);
    }

    private static class PagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
    }
}
