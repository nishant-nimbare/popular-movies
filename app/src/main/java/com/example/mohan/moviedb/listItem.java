package com.example.mohan.moviedb;


/**
 * Created by Mohan on 02-17-2018.
 */

public class listItem {
   String moviePoster,description,movieName;


    public listItem(String movieName, String description,String moviePoster) {
       this.description=description;
       this.movieName=movieName;
       this.moviePoster=moviePoster;

    }

    public String getDescription() {

        return description;
    }

    public String getMovieName() {

        return movieName;
    }

    public String getMoviePoster() {

        return moviePoster;
    }


}
