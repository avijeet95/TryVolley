package com.avijeet95.android.tryvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String url = "http://api.themoviedb.org/3/discover/movie?";
    public List<Movie> movieList = new ArrayList<Movie>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageLoader imageLoader = new ImageLoader(AppController.getInstance().getRequestQueue(), new LruBitmapCache(
                LruBitmapCache.getDefaultLruCacheSize()));
        final GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this,movieList));
        //Volley Response
        String requestUrl = url + "api_key=" + Tags.API_KEY + "&sort_by=popularity.desc";
        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                requestUrl
                , null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
//                        Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                        parseJson(response);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Didnt Work",Toast.LENGTH_SHORT).show();
            }
        });


        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }
    //Converts Json Reponse to useful Java Objects
    void parseJson(JSONObject response){

        try {
            JSONArray results = response.getJSONArray("results");

            for(int i = 0 ; i < results.length();i++){
                JSONObject currentMovie = results.getJSONObject(i);
                int id = currentMovie.getInt("id");
                String title = currentMovie.getString("title");
                String overview = currentMovie.getString("overview");
                String poster_path = "http://image.tmdb.org/t/p/original/" + currentMovie.getString("poster_path");
                String backdrop_path = "http://image.tmdb.org/t/p/original/" + currentMovie.getString("backdrop_path");
                String release_date = currentMovie.getString("release_date");
                String vote_average = currentMovie.getString("vote_average");

                Movie m = new Movie(id,title,overview,poster_path,backdrop_path,release_date,vote_average);
//                Toast.makeText(getApplicationContext(),m.title.toString(),Toast.LENGTH_SHORT).show();
                movieList.add(m);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
