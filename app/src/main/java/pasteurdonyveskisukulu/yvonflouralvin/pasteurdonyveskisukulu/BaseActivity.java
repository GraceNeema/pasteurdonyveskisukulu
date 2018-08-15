package pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu;

import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

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
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.ActualiteAdapter;
import Fragments.Frag_Actualite;
import Fragments.Frag_Predication;
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

        displaySelectedScreen(R.id.accueil);

        if(getIntent().getExtras() != null){
            switch (getIntent().getExtras().getString("origin")){
                case "predication":
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Frag_Predication()).commit();
                    break;
            }
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Frag_Actualite()).commit();
        }


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

        displaySelectedScreen(item.getItemId());

        return true;
    }
    private void displaySelectedScreen(int itemId) {

        //création et initialisation de l'objet fragment
        Fragment fragment = null;


        switch (itemId) {
            case R.id.accueil:
                fragment = new Accueil_fragment();
                break;
            case R.id.meditation:
                fragment = new Meditation_fragment();
                break;
            case R.id.event:
              //event()
                break;
        }

        //remplacement des différents fragments
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
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
