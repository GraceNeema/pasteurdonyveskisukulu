package Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.ActualiteAdapter;
import Model.Actualite;
import Tool.Application;
import Tool.HttpRequest;
import pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu.BaseActivity;
import pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu.R;

/**
 * Created by YvonFlourAlvin on 14/08/2018.
 */

public class Frag_Actualite extends Fragment implements Application{
    public RecyclerView actualites ;
    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_actualite, container, false);

    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        actualites = (RecyclerView)view.findViewById(R.id.actualites);
        Log.i("Frag_Actualite_info", "Je suis dans le fragment actualite !");
        accueil(view.getContext());


    }

    protected void accueil(final Context context){
        new AsyncTask(){
            ArrayList<Actualite> list_actualites = new ArrayList<>();
            @Override
            protected Object doInBackground(Object[] objects) {
                Log.i("Frag_Actualite_info", "Je suis dans accueil doInBackground !");
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
                    String data_tmp = data.substring(data.indexOf("["), data.indexOf("]")+1);
                    JSONArray jsonArray = new JSONArray(data_tmp);
                    if(jsonArray.length() != 0){
                        for(int i =0; i<jsonArray.length();i++){
                            Actualite actualite =  new Actualite();
                            JSONObject json_ob = jsonArray.getJSONObject(i);
                            actualite.setIdactualite(json_ob.getInt("idactualite"));
                            actualite.setTitre(json_ob.getString("titre"));
                            actualite.setMessage(json_ob.getString("message"));
                            actualite.setImageRef(json_ob.getString("image_ref"));
                            actualite.setDate(json_ob.getString("date"));
                            list_actualites.add(actualite);
                        }
                    }
                    return list_actualites;
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
                    actualites.setLayoutManager(new LinearLayoutManager(context));
                    actualites.setAdapter(new ActualiteAdapter(actualites1));
                }else{
                    Log.e("BaseActivity_info","Aucune actualité trouve");
                }
                super.onPostExecute(o);
            }
        }.execute();

    }


}
