package com.example.nik.nikicemobile;

/**
 * Created by Nik on 2016-11-22.
 */
public class Movies {

    private String image;
    private String name;
    private String year;
    private String plot;
    private String director;
    private String actors;

    public Movies(String name, String year, String image) {
        this.image = image;
        this.name = name;
        this.year = year;
        //this.plot = plot;
        //this.director = director;
       // this.actors = actors;

    }

    public String getImage() {return image;}
    public void setImage(String image) {this.image = image;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getYear() {return year;}
    public void setYear(String year) {this.year = year;}

    //public String getDirector() {return director;}
    //public void setDirector(String director) {this.director = director;}

    //public String getPlot() {return plot;}
   // public void setPlot(String plot) {this.plot = plot;}

   // public String getActors() {return actors;}
   // public void setActors(String actors) {this.actors = actors;}
}
