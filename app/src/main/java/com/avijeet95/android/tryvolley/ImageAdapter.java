package com.avijeet95.android.tryvolley;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

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

        final ImageView imageView = new ImageView(context);
        String url = movieList.get(position).getPosterUrl();


// Retrieves an image specified by the URL, displays it in the UI.
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        imageView.setImageResource(R.mipmap.ic_launcher);
                    }
                });
// Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(request);
        return imageView;
    }
}
