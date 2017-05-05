package id.ramadani.pilem.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.ramadani.pilem.R;
import id.ramadani.pilem.adapter.RecyclerAdapter;
import id.ramadani.pilem.adapter.viewholder.MovieInfoViewHolder;
import id.ramadani.pilem.model.ItemInfo;
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
        tabs.addTab(tabs.newTab().setText("Info"));
        tabs.addTab(tabs.newTab().setText("Cast"));
        tabs.addTab(tabs.newTab().setText("Crew"));

        setUpBackdrop(view);
        setUpInfo(view);
        setUpCompanies(view);
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

    private void setUpInfo(View view) {
        RecyclerView rvInfo = (RecyclerView) view.findViewById(R.id.rv_movie_detail_info);
        ArrayList<ItemInfo> infoItems = new ArrayList<>();
        String overviewValStr = view.getResources().getString(R.string.lorem_ipsum_long);

        infoItems.add(new ItemInfo("Overview", overviewValStr));
        infoItems.add(new ItemInfo("Tagline", "Divided We Fall"));
        infoItems.add(new ItemInfo("Status", "Releases"));
        infoItems.add(new ItemInfo("Budget", "250.000.000"));
        infoItems.add(new ItemInfo("Revenue", "1.153.304.495"));
        infoItems.add(new ItemInfo("Homepage", "http://marvel.com/captainamericapremiere"));

        RecyclerAdapter infoAdapter = new RecyclerAdapter<ItemInfo, MovieInfoViewHolder>(
                R.layout.item_movie_info, ItemInfo.class, infoItems, MovieInfoViewHolder.class) {
            @Override
            protected void bindView(MovieInfoViewHolder holder, ItemInfo model, int position) {
                holder.getLabel().setText(model.getLabel());
                holder.getValue().setText(model.getValue());
            }
        };

        rvInfo.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvInfo.setAdapter(infoAdapter);
        rvInfo.addItemDecoration(new MovieInfoViewHolder.ItemDecoration());
    }

    private void setUpCompanies(View view) {
        RecyclerView rvCompany = (RecyclerView) view.findViewById(R.id.rv_movie_detail_company);
        ArrayList<ItemInfo> companies = new ArrayList<>();

        companies.add(new ItemInfo("Company", "Studio Babelsberg"));
        companies.add(new ItemInfo("Company", "Marvel Studios"));
        companies.add(new ItemInfo("Company", "Walt Disney Studios Motion Pictures"));
        companies.add(new ItemInfo("Company", "Studio Babelsberg"));
        companies.add(new ItemInfo("Company", "Marvel Studios"));
        companies.add(new ItemInfo("Company", "Walt Disney Studios Motion Pictures"));

        RecyclerAdapter companiesAdapter = new RecyclerAdapter<ItemInfo, MovieInfoViewHolder>(
                R.layout.item_movie_info, ItemInfo.class, companies, MovieInfoViewHolder.class) {
            @Override
            protected void bindView(MovieInfoViewHolder holder, ItemInfo model, int position) {
                holder.getLabel().setVisibility(View.GONE);
                holder.getValue().setText(model.getValue());
            }
        };

        rvCompany.setAdapter(companiesAdapter);
        rvCompany.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvCompany.addItemDecoration(new MovieInfoViewHolder.ItemDecoration());
    }
}
