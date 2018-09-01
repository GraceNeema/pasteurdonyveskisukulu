package Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import Model.Pred;
import Tool.Application;
import Tool.HttpRequest;
import pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu.R;

/**
 * Created by YvonFlourAlvin on 14/08/2018.
 */

public class Frag_Predication extends Fragment implements Application{

    RecyclerView MyRecyclerView;
    public static final String API_KEY = "AIzaSyCHjUWly6VP4Yn-UEAGQbJpohhM20h6lKU"; // Id généré par console.developers.google.com permettant d'inculure l"API youtube
    public Context context;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_predication, container, false);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_pred);
        load_data(view.getContext());

    }


    protected void load_data(final Context context) {
        new AsyncTask() {
            ArrayList<Pred> list_pred = new ArrayList<>();

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

                try {
                    String data_tmp = data.substring(data.indexOf("["), data.indexOf("]") + 1);
                    JSONArray jsonArray = new JSONArray(data_tmp);
                    // JSONArray jsonArray = new JSONArray(data);
                    if (jsonArray.length() != 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Pred pred = new Pred();
                            JSONObject json_ob = jsonArray.getJSONObject(i);
                            // meditation.set(json_ob.getInt("idmeditation"));
                            pred.setUrl(json_ob.getString("url"));
                            //meditation.setSoustitre(json_ob.getString("soustitre"));

                            pred.setTexte(json_ob.getString("texte"));
                            //  predication.setOrateur(json_ob.getString("orateur"));
                            pred.setTexte_entier(json_ob.getString("texte_entier"));
                            pred.setTheme(json_ob.getString("theme"));

                            list_pred.add(pred);
                        }
                    }
                    return list_pred;
                } catch (Exception e) {
                    // Log.e("Frag_Predication_info","Line 84 : "+e.getMessage());
                }
                return null;
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            protected void onPostExecute(Object o) {
                if (o != null) {
                    ArrayList<Pred> predication = (ArrayList<Pred>) o;
                    MyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    MyRecyclerView.setAdapter(new MyAdapter(predication, context));


                } else {

                }
                super.onPostExecute(o);
            }
        }.execute();

    }

    public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements  Application {


        public Context context;

        ArrayList<Pred>predications;


        public MyAdapter(ArrayList<Pred>predications,Context context) {


            this.predications=predications;
            this.context=context;
        }


        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.predication_container,parent ,false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            final Holder vh = (Holder) holder;
            vh.text.setText(predications.get(position).getTexte());
            vh.theme.setText(predications.get(position).getTheme());
            //vh.texte_entier.setText(predications.get(position).getTexte_entier());
            vh.texte_entier.setText(Html.fromHtml(predications.get(position).getTexte_entier()));
          //  vh.video.initialize(predications.get(position).getUrl(), this);
            final YouTubeThumbnailLoader.OnThumbnailLoadedListener  onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener(){
                @Override
                public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

                }

                @Override
                public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                    youTubeThumbnailView.setVisibility(View.VISIBLE);
                    vh.relativeLayout.setVisibility(View.VISIBLE);
                }
            };

            vh.video.initialize(API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

                    youTubeThumbnailLoader.setVideo(predications.get(position).getUrl());
                    youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
                }

                @Override
                public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                    //write something for failure
                }
            });

        }

        @Override
        public int getItemCount () {
            return predications.size();
        }

        class Holder extends RecyclerView.ViewHolder implements Application {

            TextView text;
            YouTubeThumbnailView video;
            RelativeLayout relativeLayout;
            ImageView playButton;
            TextView theme,lire,showless;
            TextView texte_entier;


            public Holder(View itemView) {
                super(itemView);
                video = itemView.findViewById(R.id.youtube_thumbnail);
                text=itemView.findViewById(R.id.texte);
                theme=itemView.findViewById(R.id.theme);
                lire=itemView.findViewById(R.id.lire);
                texte_entier=itemView.findViewById(R.id.texte_entier);
                showless=itemView.findViewById(R.id.showless);
                relativeLayout=itemView.findViewById(R.id.relativeLayout);
                playButton=(ImageView)itemView.findViewById(R.id.btnYoutube_player);

               playButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Ceci permet d'appeller la fenetre youtube après 100milli-secondes
                        Intent intent = YouTubeStandalonePlayer.createVideoIntent((getActivity()) , API_KEY, String.valueOf(predications.get(getLayoutPosition()).getUrl()),100,true,true);

                        getActivity().startActivity(intent);
                    }
                });

               lire.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {


                       lire.setVisibility(View.GONE);

                       texte_entier.setVisibility(View.VISIBLE);
                       showless.setVisibility(View.VISIBLE);



                   }
               });
               showless.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       showless.setVisibility(View.GONE);
                       texte_entier.setVisibility(View.GONE);
                       lire.setVisibility(View.VISIBLE);
                   }
               });
            }


        }
    }


}
