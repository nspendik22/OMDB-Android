package com.example.nik.nikicemobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

public class Nik_IceMobile extends AppCompatActivity {
    EditText movie_title;
    Button search;
    TextView title;
    private ProgressDialog pDialog;
    String json;
    Results result;
    String TitleResult;
    ArrayList<Movies> movielist;
    JSONArray movieinfo = null;
    private static String url;
    // private static final String TAG_INFO = "";
    private static final String TAG_TITLE = "Title";
    private static final String TAG_YEAR = "Year";
    private static final String TAG_DIRECTOR = "Director";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nik__ice_mobile);


        //movie_title = (EditText) findViewById(R.id.editText);
        search = (Button) findViewById(R.id.button);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movie_title = (EditText) findViewById(R.id.editText);

              String url = "http://www.omdbapi.com/?t="+movie_title.getText().toString();
                url = url.replaceAll(" ", "%20");
                DownloadJSON(url);

            }
        });



    }





    public void displayMovieStats(){
        result = new Results();
        try {
            result.parsejson(this.json);
            Intent intent = new Intent(getBaseContext(),Results.class);
            intent.putExtra("MovieTitle",result.Mov_Title);
            intent.putExtra("MovieYear",result.Mov_Year);
            intent.putExtra("MovieDirector",result.Mov_Director);
            intent.putExtra("MoviePlot",result.Mov_Plot);
            intent.putExtra("MovieActors", result.Mov_Actors);
            intent.putExtra("MoviePoster", result.Mov_Img);
            startActivity(intent);
            //title.setText(result.Mov_Title);
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
    public void DownloadJSON(String url){
        AsyncTask<String,String,String> task = new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                String response = "";
                try{
                    URL url = new URL(params[0]);

                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    BufferedReader
                            reader = new
                            BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String line = "";
                    while((line = reader.readLine()) != null){
                        response += line + "\n";
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                return response;
            }

            @Override
            protected void onPostExecute(String result) {
                Nik_IceMobile.this.json = result;
                displayMovieStats();
            }
        };

        task.execute(url);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nik__ice_mobile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /******************************************************


    private class GetMovie extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            //pDialog = new ProgressDialog(Nik_IceMobile.this);
            //pDialog.setMessage(getString(R.string.wait));
            //pDialog.setCancelable(false);
            //pDialog.show();
        }

        @Override
        protected String doInBackground(Void... arg0) {

            DownloadJSON jsonString = new DownloadJSON(url);
            return jsonString.getJSON();

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            json = result;
          //  if (pDialog.isShowing()) { pDialog.dismiss(); }

        }

    } //end of GetLogin
     */
}
