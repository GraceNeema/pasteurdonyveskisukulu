package pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu;

import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import Fragments.Frag_Actualite;
import Fragments.Frag_Predication;
import Fragments.frag_Meditation;
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
    }


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
                Log.i("BaseActivity_info", "Vous avez besoin de l'accueil!");
                new AsyncTask(){

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
                        return null;
                    }
                }.execute();
                fragment = new Frag_Actualite();
                break;
            case R.id.meditation:
                new AsyncTask(){

                    @Override
                    protected Object doInBackground(Object[] objects) {
                        String data = HttpRequest.submit(
                                url,
                                "POST",
                                new String[]{
                                        "target"
                                },
                                new String[]{
                                        "get_meditation"
                                }
                        );
                        return null;
                    }
                }.execute();
                fragment = new frag_Meditation();
                break;
            case R.id.predication:
                new AsyncTask(){

                    @Override
                    protected Object doInBackground(Object[] objects) {
                        String data = HttpRequest.submit(
                                url,
                                "POST",
                                new String[]{
                                        "target"
                                },
                                new String[]{
                                        "get_meditation"
                                }
                        );
                        return null;
                    }
                }.execute();
                fragment = new Frag_Predication();
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
