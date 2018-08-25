package Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import Model.Actualité;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import Model.Theme;
import Tool.Application;
import Tool.HttpRequest;
import pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu.MeditationContent;
import pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu.R;

import static Tool.Application.url;

/**
 * Created by Lenovo on 8/14/2018.
 */

public class frag_Meditation extends Fragment implements Application {

    RecyclerView MyRecyclerView;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.meditaton_frag, container, false);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_med);
        load_data(view.getContext());



    }

    protected void load_data(final Context context) {
        new AsyncTask() {
            ArrayList<Theme> list_theme = new ArrayList<>();

            @Override
            protected Object doInBackground(Object[] objects) {
                String data = HttpRequest.submit(
                        url,
                        "POST",
                        new String[]{
                                "target"
                        },
                        new String[]{
                                "get_theme"
                        }
                );

                try {
                    String data_tmp = data.substring(data.indexOf("["), data.indexOf("]") + 1);
                    JSONArray jsonArray = new JSONArray(data_tmp);
                    // JSONArray jsonArray = new JSONArray(data);
                    if (jsonArray.length() != 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                           Theme theme = new Theme();
                            JSONObject json_ob = jsonArray.getJSONObject(i);
                            // meditation.set(json_ob.getInt("idmeditation"));
                            theme.setTitre(json_ob.getString("titre"));
                            //meditation.setSoustitre(json_ob.getString("soustitre"));

                            theme.setImageref(json_ob.getString("image_ref"));
                            //  predication.setOrateur(json_ob.getString("orateur"));

                            list_theme.add(theme);
                        }
                    }
                    return list_theme;
                } catch (Exception e) {
                    // Log.e("Frag_Predication_info","Line 84 : "+e.getMessage());
                }
                return null;
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            protected void onPostExecute(Object o) {
                if (o != null) {
                    ArrayList<Theme> themes = (ArrayList<Theme>) o;
                    MyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    MyRecyclerView.setAdapter(new YourListAdapter(themes,context));

                } else {

                }
                super.onPostExecute(o);
            }
        }.execute();

    }

    public class YourListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Application {


        public Context context;

        ArrayList<Theme>theme;


        public YourListAdapter(ArrayList<Theme>theme,Context context) {


            this.theme=theme;
            this.context=context;
        }


        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_meditation,parent ,false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            final Holder vh =(Holder) holder ;
            vh.titre.setText(theme.get(position).getTitre());
            vh.imageView.setVisibility(View.INVISIBLE);
            new AsyncTask() {

                @Override
                protected Object doInBackground(Object[] objects) {
                    Bitmap bitmap = HttpRequest.donwload_bitmap(url +theme.get(position).getImageref());
                    ArrayList<Object> objects1 = new ArrayList<>();
                    objects1.add(bitmap);
                    objects1.add(objects[0]);
                    return objects1;
                }

                @Override
                protected void onPostExecute(Object o) {
                    ArrayList<Object> objects = (ArrayList<Object>) o;
                    if (objects.get(0) != null) {
                        Bitmap bitmap = (Bitmap) objects.get(0);
                        WeakReference weak = (WeakReference) objects.get(1);
                        ImageView img = (ImageView) weak.get();
                        // load_image.setVisibility(View.INVISIBLE);
                        vh.imageView.setVisibility(View.VISIBLE);
                        img.setImageBitmap(bitmap);
                    }
                    super.onPostExecute(o);
                }
            }.execute(new WeakReference<ImageView>( vh.imageView));
        }



        /**
         * Returns the total number of items in the data set held by the adapter.
         *
         * @return The total number of items in this adapter.
         */
        @Override
        public int getItemCount() {
            return theme.size();
        }

        class Holder extends RecyclerView.ViewHolder implements Application {

            TextView titre;
            ImageView imageView;

            public Holder(View itemView) {
                super(itemView);
                titre = itemView.findViewById(R.id.text);

                imageView = itemView.findViewById(R.id.imageView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent i= new Intent(getActivity(), MeditationContent.class);
                        startActivity(i);
                    }
                });

            }


        }
    }




}

