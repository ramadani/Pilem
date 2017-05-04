package id.ramadani.pilem.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by dani on 5/4/17.
 */

public abstract class RecyclerAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    private int mLayout;
    private Class<T> mModel;
    private List<T> mItems;
    private Class<VH> mViewHolder;

    public RecyclerAdapter(int layout, Class<T> model, List<T> items, Class<VH> viewHolder) {
        mLayout = layout;
        mModel = model;
        mItems = items;
        mViewHolder = viewHolder;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(mLayout, parent, false);

        try {
            Constructor<VH> constructor = mViewHolder.getConstructor(View.class);

            return constructor.newInstance(view);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        T model = mItems.get(position);
        bindView(holder, model, position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    abstract protected void bindView(VH holder, T model, int position);
}
