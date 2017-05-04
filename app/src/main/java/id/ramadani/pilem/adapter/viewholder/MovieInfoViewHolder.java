package id.ramadani.pilem.adapter.viewholder;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import id.ramadani.pilem.R;
import id.ramadani.pilem.model.ItemInfo;

/**
 * Created by dani on 5/4/17.
 */

public class MovieInfoViewHolder extends ViewHolder {
    private TextView label;
    private TextView value;

    public MovieInfoViewHolder(View itemView) {
        super(itemView);

        label = (TextView) itemView.findViewById(R.id.tv_movie_detail_info_label);
        value = (TextView) itemView.findViewById(R.id.tv_movie_detail_info_value);
    }

    public void bind(ItemInfo info) {
        label.setText(info.getLabel());
        value.setText(info.getValue());
    }

    public static class ItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = 0;
            } else {
                int margin = view.getResources().getDimensionPixelSize(R.dimen.item_padding);
                outRect.top = margin;
            }
        }
    }
}
