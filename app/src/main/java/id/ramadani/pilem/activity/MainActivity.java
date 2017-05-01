package id.ramadani.pilem.activity;

import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import id.ramadani.pilem.R;
import id.ramadani.pilem.fragment.MovieDetailFragment;
import id.ramadani.pilem.fragment.MovieListFragment;
import id.ramadani.pilem.model.Movie;
import id.ramadani.pilem.presenter.NowPlayingMoviePresenter;
import id.ramadani.pilem.presenter.PopularMoviePresenter;
import id.ramadani.pilem.presenter.TopRatedMoviePresenter;
import id.ramadani.pilem.presenter.UpcomingMoviePresenter;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        MovieListFragment.OnItemSelectedListener {

    public static final String MOVIES_FRAGMENT_TAG = "MOVIES_FRAGMENT_TAG";

    private boolean mHasFragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment nowPlayingFragment = MovieListFragment.newInstance(new NowPlayingMoviePresenter(),
                R.string.nav_now_playing);
        setMovieFragment(nowPlayingFragment);
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

        onMovieNavigationItemSelected(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMovieItemSelected(Movie movie) {
        Toast.makeText(this, movie.getTitle(), Toast.LENGTH_SHORT).show();

        Fragment movieDetailFragment = MovieDetailFragment.newInstance(movie.getId(),
                movie.getTitle());
        setMovieFragment(movieDetailFragment);
    }

    private void onMovieNavigationItemSelected(int menuId) {
        if (menuId == R.id.nav_now_playing) {
            Fragment nowPlayingFragment = MovieListFragment.newInstance(
                    new NowPlayingMoviePresenter(), R.string.nav_now_playing);
            setMovieFragment(nowPlayingFragment);
        } else if (menuId == R.id.nav_upcoming) {
            Fragment upcomingFragment = MovieListFragment.newInstance(new UpcomingMoviePresenter(),
                    R.string.nav_upcoming);
            setMovieFragment(upcomingFragment);
        } else if (menuId == R.id.nav_top_rated) {
            Fragment topRatedFragment = MovieListFragment.newInstance(new TopRatedMoviePresenter(),
                    R.string.nav_top_rated);
            setMovieFragment(topRatedFragment);
        } else if (menuId == R.id.nav_popular) {
            Fragment popularFragment = MovieListFragment.newInstance(new PopularMoviePresenter(),
                    R.string.nav_popular);
            setMovieFragment(popularFragment);
        }
    }

    private void setMovieFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_movies, fragment, MOVIES_FRAGMENT_TAG);

        if (mHasFragment) {
            fragmentTransaction.addToBackStack(MOVIES_FRAGMENT_TAG);
        } else {
            mHasFragment = true;
        }

        fragmentTransaction.commit();
    }
}
