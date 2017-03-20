package id.ramadani.pilem.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by dani on 3/20/17.
 */

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener{
    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private int visibleThreshold = 5;

    // The current offset index of data you have loaded
    private int currentPage = 0;

    // The total number of items in the dataset after the last load
    private int previousTotalItemCount = 0;

    // True if we are still waiting for the last set of data to load.
    private boolean isLoading = true;

    // Sets the starting page index
    private int startingPageIndex = 0;

    RecyclerView.LayoutManager layoutManager;

    public EndlessRecyclerViewScrollListener(LinearLayoutManager linearLayoutManager) {
        this.layoutManager = linearLayoutManager;
    }

    public int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;

        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }

        return maxSize;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int lastVisibleItemPosition = 0;
        int totalItemCount = layoutManager.getItemCount();

        if (layoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                    .findLastVisibleItemPosition();
        }

        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (isLoading && (totalItemCount > previousTotalItemCount)) {
            isLoading = false;
            previousTotalItemCount = totalItemCount;
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        if (!isLoading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++;
            isLoading = true;
        }
    }

    public void resetState() {
        this.currentPage = this.startingPageIndex;
        this.previousTotalItemCount = 0;
        this.isLoading = true;
    }

    // Defines the process for actually loading more data based on page
    public abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);
}
