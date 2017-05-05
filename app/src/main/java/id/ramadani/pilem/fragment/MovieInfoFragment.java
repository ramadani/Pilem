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

import id.ramadani.pilem.R;
import id.ramadani.pilem.adapter.RecyclerAdapter;
import id.ramadani.pilem.adapter.viewholder.MovieInfoViewHolder;
import id.ramadani.pilem.model.ItemInfo;

/**
 * Created by dani on 5/5/17.
 */

public class MovieInfoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpInfo(view);
        setUpCompanies(view);
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
