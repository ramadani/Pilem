package id.ramadani.pilem.movie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import id.ramadani.pilem.R;

/**
 * Created by dani on 3/15/17.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {

    public static class ViewHolder {
        TextView title;
    }

    public MovieAdapter(@NonNull Context context, @NonNull ArrayList<Movie> movies) {
        super(context, 0, movies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        Movie movie = getItem(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie,
                    parent, false);

            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_movie_title);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(movie.getTitle());

        return convertView;
    }
}
