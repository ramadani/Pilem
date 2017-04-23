package id.ramadani.pilem.activity;

import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import id.ramadani.pilem.R;
import id.ramadani.pilem.adapter.MoviesAdapter;
import id.ramadani.pilem.model.Movie;
import id.ramadani.pilem.presenter.MoviePresenter;
import id.ramadani.pilem.util.DividerItemDecoration;
import id.ramadani.pilem.util.EndlessRecyclerViewScrollListener;
import id.ramadani.pilem.util.MarginItemDecoration;
import id.ramadani.pilem.view.MovieView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MovieView {

    MoviePresenter moviePresenter;
    RecyclerView rvMovies;
    MoviesAdapter moviesAdapter;
    ArrayList<Movie> movies;

    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialization();

        movies = new ArrayList<Movie>();
        moviesAdapter = new MoviesAdapter(this, movies);

        rvMovies = (RecyclerView) findViewById(R.id.rv_movies);

        // set margin peritem
        int movieItemMargin = getResources().getDimensionPixelSize(R.dimen.item_pading);
        rvMovies.addItemDecoration(new MarginItemDecoration(movieItemMargin));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvMovies.setLayoutManager(layoutManager);
        rvMovies.setAdapter(moviesAdapter);

        moviePresenter = new MoviePresenter(this);

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                moviePresenter.loadTopRated(page);
            }
        };

        rvMovies.addOnScrollListener(scrollListener);

        scrollListener.setCurrentPage(1);
        moviePresenter.loadTopRated(1);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_upcoming:
                break;
            case R.id.nav_top_rated:
                break;
            case R.id.nav_popular:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void pushToMovieList(List<Movie> movies) {
        for (Movie movie: movies) {
            this.movies.add(movie);
        }

        moviesAdapter.notifyDataSetChanged();
    }

    private void initialization() {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}
