package Tool;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu.R;

/**
 * Created by Lenovo on 8/14/2018.
 */

public class BackgroundTask extends AsyncTask<Void,Void,Void> {

 Context context;
 Activity activity;
 RecyclerView recyclerView;
 RecyclerView.Adapter adapter;
 RecyclerView.LayoutManager layoutManager;
 String json_string="http://192.168.43.1/pasteurdonyveskisukulu-master/database.class.php";

    @Override
    protected void onPreExecute() {
       super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
       try{
           URL url=new URL(json_string);
           HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
           InputStream inputStream=httpURLConnection.getInputStream();
           BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
           StringBuilder stringBuilder=new StringBuilder();
           String line;
           while ((line=bufferedReader.readLine())!=null){
               stringBuilder.append(line+"\n");
           }
           httpURLConnection.disconnect();;
           String json_string=stringBuilder.toString().trim();
           Log.d("JSON STRING",json_string);

       }
       catch (MalformedURLException e)
       {
           e.printStackTrace();
       }
       catch (IOException e){
           e.printStackTrace();
       }
       return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
