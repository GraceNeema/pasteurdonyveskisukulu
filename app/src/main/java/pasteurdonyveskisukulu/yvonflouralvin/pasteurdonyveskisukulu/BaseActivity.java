package pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu;

import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.AccueilAdapter;
import Model.Actualite;
import Tool.Application;
import Tool.HttpRequest;

/**
 * Created by YvonFlourAlvin on 26/06/2018.
 */

public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Application {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        accueil();
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.accueil, menu);
        return true;
    }
    */
/*
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
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.accueil) {
            // Handle the camera action
            accueil();
        } else if (id == R.id.meditation) {
            //meditation();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.event) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void accueil(){
        new AsyncTask(){
            ArrayList<Actualite> actualites = new ArrayList<>();
            @Override
            protected Object doInBackground(Object[] objects) {
                String data = HttpRequest.submit(
                        url,
                        "POST",
                        new String[]{
                                "target"
                        },
                        new String[]{
                                "get_actu"
                        }
                );

                try{
                    JSONArray jsonArray = new JSONArray(data);
                    if(jsonArray.length() != 0){
                        for(int i =0; i<jsonArray.length();i++){
                            Actualite actualite =  new Actualite();
                            JSONObject json_ob = jsonArray.getJSONObject(i);
                            actualite.setIdactualite(json_ob.getInt("idactualite"));
                            actualite.setTitre(json_ob.getString("titre"));
                            actualite.setMessage(json_ob.getString("message"));
                            actualite.setImageRef(json_ob.getString("image_ref"));
                            actualite.setDate(json_ob.getString("date"));
                            actualites.add(actualite);
                        }
                    }
                    return actualites;
                }catch (Exception e){
                    Log.e("BaseActivity_info",e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                if(o != null){
                    Log.e("BaseActivity_info","actualité trouve");
                    ArrayList<Actualite> actualites1 = (ArrayList<Actualite>)o;
                    ((RecyclerView)findViewById(R.id.recycler_actus)).setLayoutManager(new LinearLayoutManager(BaseActivity.this));
                    ((RecyclerView)findViewById(R.id.recycler_actus)).setAdapter(new AccueilAdapter(actualites1));
                }else{
                    Log.e("BaseActivity_info","Aucune actualité trouve");
                }
                super.onPostExecute(o);
            }
        }.execute();

    }
    protected void meditation(){
        ((RelativeLayout)findViewById(R.id.container)).removeAllViews();
        View v = LayoutInflater.from(BaseActivity.this).inflate(R.layout.container_meditation, null);
        ((RelativeLayout)findViewById(R.id.container)).addView(v);
    }

    public boolean canConnecte(){
        return (((ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo() !=null && ((ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo().isConnected());
    }
}
