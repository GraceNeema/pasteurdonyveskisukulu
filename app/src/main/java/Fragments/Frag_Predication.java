package Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.ActualiteAdapter;
import Adapter.PredicationAdapter;
import Model.Actualite;
import Model.Predication;
import Tool.Application;
import Tool.HttpRequest;
import pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu.R;

/**
 * Created by YvonFlourAlvin on 14/08/2018.
 */

public class Frag_Predication extends Fragment implements Application{
    public RecyclerView recycler ;
    public Context context;
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
        return inflater.inflate(R.layout.frag_predication, container, false);

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
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = view.getContext();
        recycler = (RecyclerView)view.findViewById(R.id.recycler);
        load_data(view.getContext());

    }

    protected void load_data(final Context context){
        new AsyncTask(){
            ArrayList<Predication> list_predication = new ArrayList<>();
            @Override
            protected Object doInBackground(Object[] objects) {
                String data = HttpRequest.submit(
                        url,
                        "POST",
                        new String[]{
                                "target"
                        },
                        new String[]{
                                "get_predication"
                        }
                );

                try{
                    JSONArray jsonArray = new JSONArray(data);
                    if(jsonArray.length() != 0){
                        for(int i =0; i<jsonArray.length();i++){
                            Predication predication =  new Predication();
                            JSONObject json_ob = jsonArray.getJSONObject(i);
                            predication.setIdpredication(json_ob.getInt("idpredication"));
                            predication.setTitre(json_ob.getString("titre"));
                            predication.setMessage(json_ob.getString("contenu"));
                            predication.setImageRef(json_ob.getString("image"));
                            predication.setOrateur(json_ob.getString("orateur"));
                            predication.setDate(json_ob.getString("date"));

                            list_predication.add(predication);
                        }
                    }
                    return list_predication;
                }catch (Exception e){
                    Log.e("Frag_Predication_info",e.getMessage());
                }
                return null;
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            protected void onPostExecute(Object o) {
                if(o != null){
                    ArrayList<Predication> preds = (ArrayList<Predication>)o;
                    recycler.setLayoutManager(new LinearLayoutManager(context));
                    recycler.setAdapter(new PredicationAdapter(preds, context));

                }else{

                }
                super.onPostExecute(o);
            }
        }.execute();

    }


}
