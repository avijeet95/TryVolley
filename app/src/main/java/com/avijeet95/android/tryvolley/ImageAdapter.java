package com.avijeet95.android.tryvolley;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by avijeet on 17/04/16.
 */

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private List<Movie> movieList;
    public Integer[] images;
    public ImageAdapter(Context c, List<Movie> movieList){
        this.context = c;
        this.movieList = movieList;
    }
    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
