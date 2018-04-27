package com.example.mohan.moviedb;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    ArrayList<listItem> listItems= new ArrayList<>();

    String url,movieTitle,movieDescription,moviePosterUri,posterUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        loadRecyclerView();


    }

    public void loadRecyclerView(){
        url = new Uri.Builder()
                .scheme("http")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("discover")
                .appendPath("movie")
                .appendQueryParameter("api_key",/* you api key */)
                .build()
                .toString();

        // https://developer.android.com/training/volley/request.html
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("response",response.toString());
                        try{
                            JSONArray movies = response.getJSONArray("results");

                            for(int i=0;i<movies.length();i++) {
                                JSONObject popularMovie = movies.getJSONObject(i);
                                movieTitle = popularMovie.getString("title");
                                movieDescription = popularMovie.getString("overview");
                                moviePosterUri = popularMovie.getString("poster_path");
                                moviePosterUri = moviePosterUri.substring(1);



                               posterUri = new Uri.Builder()
                                        .scheme("http")
                                        .authority("image.tmdb.org")
                                        .appendPath("t")
                                        .appendPath("p")
                                        .appendPath("w185")
                                        .appendPath(moviePosterUri).build().toString();
                          //      Log.e("posterUri", posterUri);

                                listItem n = new listItem(movieTitle,movieDescription,posterUri);
                                listItems.add(n);
                            }
                            adapter =new  myAdapter(listItems, getApplicationContext(), new myAdapter.onItemClickListener() {
                                @Override
                                public void onItemClick(listItem item) {
                                    Intent i = new Intent(getApplicationContext(),Activity2.class);
                                    i.putExtra("movieTitle",item.getMovieName());
                                    i.putExtra("movieDescription",item.getDescription());
                                    i.putExtra("posterUri", item.getMoviePoster());
                                    getApplicationContext().startActivity(i);
                                }
                            });
                             recyclerView.setAdapter(adapter);



                          //  movieName.setText(movieTitle);
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error-volley",error.getMessage());

                    }
                });

        // Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(jsObjRequest);

        AppController.getInstance().addToRequestQueue(jsObjRequest);


    }


}
