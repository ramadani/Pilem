package id.ramadani.pilem.adapter.viewholder;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import id.ramadani.pilem.R;

/**
 * Created by dani on 3/18/17.
 */

public class MovieViewHolder extends ViewHolder {
    public TextView title;
    public TextView overview;
    public TextView voteAvg;
    public TextView releaseDate;
    public ImageView poster;

    public MovieViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.tv_movie_title);
        overview = (TextView) itemView.findViewById(R.id.tv_movie_overview);
        voteAvg = (TextView) itemView.findViewById(R.id.tv_movie_vote_avg);
        releaseDate = (TextView) itemView.findViewById(R.id.tv_movie_release_date);
        poster = (ImageView) itemView.findViewById(R.id.iv_movie_poster);
    }
}
