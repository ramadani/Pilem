package id.ramadani.pilem.presenter;

import id.ramadani.pilem.api.ApiBuilder;
import id.ramadani.pilem.api.TmdbService;
import id.ramadani.pilem.view.MovieView;

/**
 * Created by dani on 3/19/17.
 */

public abstract class MoviePresenter {
    protected MovieView view;
    protected TmdbService mService;

    public MoviePresenter() {
        mService = ApiBuilder.call();
    }
}
