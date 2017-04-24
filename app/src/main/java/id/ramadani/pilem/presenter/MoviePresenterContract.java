package id.ramadani.pilem.presenter;

import java.io.Serializable;

import id.ramadani.pilem.view.MovieView;

/**
 * Created by dani on 4/24/17.
 */

public interface MoviePresenterContract extends Serializable {
    void setView(MovieView view);

    void load(int page);
}
