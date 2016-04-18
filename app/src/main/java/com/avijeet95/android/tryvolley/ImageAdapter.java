package com.avijeet95.android.tryvolley;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

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

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = (point.x)/2;
        int layoutW, layoutH;
        layoutW = width-15;
        layoutH = (int)(1.5 * (width-15));

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(     Context.LAYOUT_INFLATER_SERVICE );
        View v = inflater.inflate(R.layout.grid_block, parent, false);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(layoutW, layoutH );

//        final ImageView imageView = new ImageView(context);
        final NetworkImageView networkImageView = (NetworkImageView) v.findViewById(R.id.imageView);

        String url = movieList.get(position).getPosterUrl();
        networkImageView.setLayoutParams(params);
        networkImageView.setScaleType(NetworkImageView.ScaleType.FIT_CENTER);
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    // Set the URL of the image that should be loaded into this view, and
    // specify the ImageLoader that will be used to make the request.

        networkImageView.setImageUrl(url, imageLoader);
        imageLoader.get(url, new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MyApp", "Image Load Error: " + error.getMessage());
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    // load image into imageview
                    networkImageView.setImageBitmap(response.getBitmap());
                }
            }
        });


        return v;
    }
}
