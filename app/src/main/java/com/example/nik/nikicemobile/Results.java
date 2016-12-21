package com.example.nik.nikicemobile;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Results extends AppCompatActivity {
    public String Mov_Title;
    public String Mov_Year;
    public String Mov_Director;
    public String Mov_Plot;
    public String Mov_Actors;
    public String Mov_Img;

    public String MovieName;
    public String MovieYear;
    public String MovieDirector;
    public String MoviePlot;
    public String MovieActors;
    public String MovieImg;


    TextView title, date, director, plot, img, actors;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        Bundle extras = getIntent().getExtras();
        MovieName = extras.getString("MovieTitle");
        MovieYear = extras.getString("MovieYear");
        MovieDirector = extras.getString("MovieDirector");
        MoviePlot = extras.getString("MoviePlot");
        MovieActors = extras.getString("MovieActors");
        MovieImg = extras.getString("MoviePoster");

        //lv = (ListView)findViewById(R.id.listView);
        //List<String> movie_list = new ArrayList<String>(Arrays.asList(MovieName));

        title = (TextView)findViewById(R.id.title);
        date = (TextView)findViewById(R.id.date);
        director = (TextView)findViewById(R.id.director);
        plot = (TextView)findViewById(R.id.plot);
        actors = (TextView)findViewById(R.id.actors);
       // img = (TextView)findViewById(R.id.imgurl);

        title.setText(MovieName);
        date.setText(MovieYear);
        director.append(MovieDirector);
        plot.append(MoviePlot);
        actors.append(MovieActors);
       // img.setText(MovieImg);





        //Log.d("url",Mov_Img);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        if ((MovieImg.toString()).equals("N/A")){
            Picasso.with(this)
                    .load(R.drawable.profile_pc).resize(800, 900)
                    .into(imageView);

        }
        else {
            Picasso.with(this)
                    .load(MovieImg.toString()).resize(800, 900)
                    .into(imageView);

        }
    }
    /*
    public class MovieResults {
        public String Mov_Title;
        public String Mov_Year;
        public String Mov_Director;
        public String Mov_Plot;
        public String Mov_Actors;
*/


    public void parsejson(String json) throws JSONException {
        JSONObject movie_results = new JSONObject(json);
        Mov_Title = movie_results.getString("Title");
        Mov_Year = movie_results.getString("Year");
        Mov_Director = movie_results.getString("Director");
        Mov_Actors = movie_results.getString("Actors");
        Mov_Plot = movie_results.getString("Plot");
        Mov_Img = movie_results.getString("Poster");
    }


}
