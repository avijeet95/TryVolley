package com.avijeet95.android.tryvolley;

/**
 * Created by avijeet on 17/04/16.
 */
public class Movie {
    private int id;
    private String title ;
    private String overview ;
    private String poster_path;
    private String backdrop_path;
    private String release_date;
    private String vote_average;

    public Movie(){

    }
    public Movie(int id,String title ,String overview, String poster_path, String backdrop_path, String release_date, String vote_average){
        this.id=id;
        this.title=title;
        this.overview=overview;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
    }
    public int getId() { return id; }
    public String getTitle(){
        return title;
    }

    public String getOverview(){
        return overview;
    }

    public String getBackdropUrl(){
        return backdrop_path;
    }
    public String getPosterUrl(){
        return poster_path;
    }
    public String getReleaseDate() { return release_date; }
    public String getVoteAverage() { return vote_average ;}
}
