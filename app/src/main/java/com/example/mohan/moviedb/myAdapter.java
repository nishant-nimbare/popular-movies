package com.example.mohan.moviedb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mohan on 02-17-2018.
 */

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {

    listItem movie;
    ArrayList<listItem> movieList;
    Context context;
    onItemClickListener listener;

    public interface onItemClickListener{
        public void onItemClick(listItem item);
    }

    public myAdapter (ArrayList<listItem> movieList , Context context,onItemClickListener listener) {
        this.movieList = movieList;
        this.context = context;
        this.listener=listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout,parent,false);

        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       movie = movieList.get(position);
       holder.bind(movie,listener);
       /*   holder.movieName.setText(movie.getMovieName());
        Picasso.with(context)
                .load(movie.getMoviePoster())
                .into(holder.moviePoster);

        itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                listener.onItemClick(movie);
            }
        });*/

       /* holder.gridLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,Activity2.class);
                i.putExtra("movieTitle",movie.getMovieName());
                i.putExtra("movieDescription",movie.getDescription());
                i.putExtra("posterUri", movie.getMoviePoster());
                context.startActivity(i);
                Toast.makeText(context,"you clicked"+movie.getMovieName(),Toast.LENGTH_SHORT).show();

            }
        });*/


    }

    @Override
    public int getItemCount() {

        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
       TextView movieName;
       ImageView moviePoster;
      GridLayout gridLayout;


        public MyViewHolder(View itemView) {
            super(itemView);

           moviePoster=(ImageView) itemView.findViewById(R.id.small_poster);
            movieName =(TextView) itemView.findViewById(R.id.small_title);
           gridLayout=(GridLayout) itemView.findViewById(R.id.Grid_layout);
        }

        public void bind(final listItem movie,final onItemClickListener listener){
            movieName.setText(movie.getMovieName());
            Picasso.with(context)
                    .load(movie.getMoviePoster())
                    .into(moviePoster);

            itemView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    listener.onItemClick(movie);
                }
            });

        }
    }
}
