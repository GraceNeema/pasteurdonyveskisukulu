package Adapter;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import Model.Actualite;
import Tool.Application;
import Tool.HttpRequest;
import pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu.R;

/**
 * Created by YvonFlourAlvin on 27/06/2018.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.Holder> implements Application{

    ArrayList<Actualite> actualites = null;

    public EventAdapter(ArrayList<Actualite> actualites) {
        this.actualites = actualites;
        Log.i("AccueilAdapter_info", "j'entre dans l'ActualiteAdapter");
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.accueil_item_3, null);
        return new Holder(view);
    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(actualites.get(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return actualites.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView titre, message, date;
        ImageView imageView;
        ProgressBar load_image;
        public Holder(View itemView) {
            super(itemView);
            titre = itemView.findViewById(R.id.titre);
            message = itemView.findViewById(R.id.message);
            date = itemView.findViewById(R.id.date);
            imageView  = itemView.findViewById(R.id.image);
            load_image = itemView.findViewById(R.id.load_image);
        }

        public void bind(final Actualite actu){
            Log.i("AccueilAdapter_info", "j'entre dans le bind");
            titre.setText(actu.getTitre());
            message.setText(actu.getMessage());
            date.setText(actu.getDate());
            imageView.setVisibility(View.INVISIBLE);
            new AsyncTask(){

                @Override
                protected Object doInBackground(Object[] objects) {
                    Bitmap bitmap = HttpRequest.donwload_bitmap(url+actu.getImageRef());
                    ArrayList<Object> objects1 = new ArrayList<>();
                    objects1.add(bitmap);
                    objects1.add(objects[0]);
                    return  objects1;
                }

                @Override
                protected void onPostExecute(Object o) {
                    ArrayList<Object> objects = (ArrayList<Object>)o;
                    if( objects.get(0) != null){
                        Bitmap bitmap = (Bitmap)objects.get(0);
                        WeakReference weak = (WeakReference)objects.get(1);
                        ImageView img = (ImageView)weak.get();
                        load_image.setVisibility(View.INVISIBLE);
                        imageView.setVisibility(View.VISIBLE);
                        img.setImageBitmap(bitmap);
                    }
                    super.onPostExecute(o);
                }
            }.execute(new WeakReference<ImageView>(imageView));
        }
    }
}
