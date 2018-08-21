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
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

//import Model.Actualité;
import Adapter.PredicationAdapter;
import Model.Actualite;
import Model.Meditation;
import Model.Predication;
import Tool.Application;
import Tool.BackgroundTask;
import Tool.HttpRequest;
import pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu.MeditationDetail;
import pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu.R;

import static Tool.Application.url;

/**
 * Created by Lenovo on 8/14/2018.
 */

public class frag_Meditation extends Fragment implements Application {
    // ArrayList<Meditation> listitems = new ArrayList<>();
    RecyclerView MyRecyclerView;
    public Context context;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.meditation_fragment, container, false);
        //getActivity().setTitle("Méditation");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = view.getContext();
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);
        load_data(view.getContext());


    }

    protected void load_data(final Context context) {
        new AsyncTask() {
            ArrayList<Meditation> list_meditation = new ArrayList<>();

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

                try {
                    String data_tmp = data.substring(data.indexOf("["), data.indexOf("]") + 1);
                    JSONArray jsonArray = new JSONArray(data_tmp);
                    // JSONArray jsonArray = new JSONArray(data);
                    if (jsonArray.length() != 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Meditation meditation = new Meditation();
                            JSONObject json_ob = jsonArray.getJSONObject(i);
                            // meditation.set(json_ob.getInt("idmeditation"));
                            meditation.setTitre(json_ob.getString("titre"));
                            meditation.setSoustitre(json_ob.getString("soustitre"));
                            meditation.setMessage(json_ob.getString("message"));
                            meditation.setImageReSource(json_ob.getString("image_ref"));
                            //  predication.setOrateur(json_ob.getString("orateur"));
                            meditation.setDate(json_ob.getString("date"));

                            list_meditation.add(meditation);
                        }
                    }
                    return list_meditation;
                } catch (Exception e) {
                    //   Log.e("Frag_Predication_info",e.getMessage());
                }
                return null;
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            protected void onPostExecute(Object o) {
                if (o != null) {
                    ArrayList<Meditation> meds = (ArrayList<Meditation>) o;
                    MyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    MyRecyclerView.setAdapter(new YourListAdapter(meds));

                } else {

                }
                super.onPostExecute(o);
            }
        }.execute();

    }

    public class YourListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements  Application{

        private static final int LAYOUT_ONE= 0;
        private static final int LAYOUT_TWO= 1;
        private ArrayList<Meditation> list;



        public YourListAdapter ( ArrayList<Meditation> list) {
            this.list=list;

        }


        @Override
        public int getItemViewType(int position)
        {
            if(position%2==0)       //  position paire
                return LAYOUT_ONE;
            else                   //  position impaire
                return LAYOUT_TWO;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view =null;
            RecyclerView.ViewHolder viewHolder = null;

            if(viewType==LAYOUT_ONE)
            {
                view = LayoutInflater.from(context).inflate(R.layout.recyclerview_items, parent,false);
                viewHolder = new ViewHolderOne(view);
            }
            else
            {
                view = LayoutInflater.from(context).inflate(R.layout.accueil_item_2, parent,false);
                viewHolder= new ViewHolderTwo(view);
            }

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

            if(holder.getItemViewType()== LAYOUT_ONE)
            {
                final ViewHolderOne vh =(ViewHolderOne)holder ;
                // list.get(position);
                vh.titreTextView.setText(list.get(position).getTitre());
                vh.messageTextView.setText(list.get(position).getMessage()+"\nSoustitre: "+list.get(position).getSoustitre());
                vh.coverImageView.setVisibility(View.INVISIBLE);
                new AsyncTask() {

                    @Override
                    protected Object doInBackground(Object[] objects) {
                        Bitmap bitmap = HttpRequest.donwload_bitmap(url +list.get(position).getImageResource());
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
                            vh.coverImageView.setVisibility(View.VISIBLE);
                            img.setImageBitmap(bitmap);
                        }
                        super.onPostExecute(o);
                    }
                }.execute(new WeakReference<ImageView>( vh.coverImageView));
            }

            else {

                final ViewHolderTwo vaultItemHolder = (ViewHolderTwo) holder;
                list.get(position);
                vaultItemHolder.titre.setText(list.get(position).getMessage());
                vaultItemHolder.soustitre.setText(list.get(position).getTitre());
                vaultItemHolder.img.setVisibility(View.INVISIBLE);
                new AsyncTask() {

                    @Override
                    protected Object doInBackground(Object[] objects) {
                        Bitmap bitmap = HttpRequest.donwload_bitmap(url + list.get(position).getImageResource());
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
                            vaultItemHolder.img.setVisibility(View.VISIBLE);
                            img.setImageBitmap(bitmap);
                        }
                        super.onPostExecute(o);
                    }
                }.execute(new WeakReference<ImageView>( vaultItemHolder.img));
            }


        }

    }

    //****************  VIEW HOLDER 1 ******************//

    public class ViewHolderOne extends RecyclerView.ViewHolder {

        public TextView titreTextView, messageTextView;
        public ImageView coverImageView;
        public Button lire;


        public ViewHolderOne(View itemView) {
            super(itemView);
            titreTextView = (TextView) itemView.findViewById(R.id.titreTextView);
            messageTextView = (TextView) itemView.findViewById(R.id.messageTextView);
            coverImageView = (ImageView) itemView.findViewById(R.id.coverImageView);
            lire = (Button) itemView.findViewById(R.id.btnlire);
            lire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MeditationDetail.class);
                    view.getContext().startActivity(intent);
                }
            });






    }
    }


    //****************  VIEW HOLDER 2 ******************//

    public class ViewHolderTwo extends RecyclerView.ViewHolder{
        View mView;
        public TextView titre;
        public TextView soustitre;
        public  ImageView img;
        public ViewHolderTwo(View itemView) {
            super(itemView);
            mView=itemView;
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MeditationDetail.class);
                    view.getContext().startActivity(intent);
                }
            });
            titre = (TextView)itemView.findViewById(R.id.txt1);
            img=(ImageView)itemView.findViewById(R.id.img1);
            soustitre=(TextView)itemView.findViewById(R.id.soustitre);


        }
    }
}
