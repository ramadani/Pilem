package id.ramadani.pilem.viewholders;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import id.ramadani.pilem.R;

/**
 * Created by dani on 3/18/17.
 */

public class MovieViewHolder extends ViewHolder {
    public TextView title;
    public TextView overview;

    public MovieViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.tv_movie_title);
        overview = (TextView) itemView.findViewById(R.id.tv_movie_overview);
    }
}
