package com.example.mohan.moviedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Activity2 extends AppCompatActivity {
    ImageView bigPoster ;
    TextView bigTitle,description,desTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        bigPoster=(ImageView)findViewById(R.id.big_poster);
        bigTitle=(TextView)findViewById(R.id.big_title);
        description=(TextView)findViewById(R.id.description);
        desTitle=(TextView)findViewById(R.id.des_title);

        Intent intent = getIntent();
        String movieTitle=intent.getStringExtra("movieTitle");
        String overview = intent.getStringExtra("movieDescription");
        String posterUri= intent.getStringExtra("posterUri");

        Toast.makeText(getApplicationContext(),"you clicked"+movieTitle,Toast.LENGTH_LONG).show();

        Picasso.with(getApplicationContext()).load(posterUri)
                .into(bigPoster);

        bigTitle.setText(movieTitle);
        description.setText(overview);
        desTitle.setText("DESCRIPTION");

    }
}
