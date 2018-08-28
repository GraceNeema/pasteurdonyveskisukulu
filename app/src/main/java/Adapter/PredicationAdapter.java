package Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import Model.Predication;
import Tool.Application;
import pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu.PredicationDetail_Activity;
import pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu.R;

/**
 * Created by YvonFlourAlvin on 27/06/2018.
 */

public class PredicationAdapter extends RecyclerView.Adapter<PredicationAdapter.Holder> implements Application{

    ArrayList<Predication> predications = null;
    Context context;

    public PredicationAdapter(ArrayList<Predication> predications, Context _context) {
        this.context = _context;
        this.predications = predications;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.predication_container, null);
        return new Holder(view);
    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(predications.get(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return predications.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        RelativeLayout relativeLayout;
        TextView titre, date;
        public Holder(View itemView) {
            super(itemView);
            titre = itemView.findViewById(R.id.titre);
            date = itemView.findViewById(R.id.date);
            relativeLayout = itemView.findViewById(R.id.predication);
        }

        public void bind(final Predication actu){
            titre.setText(actu.getTitre());
            date.setText(actu.getDate());
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PredicationDetail_Activity.class);
                    intent.putExtra("idpredication", actu.getIdpredication());
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }
            });
        }
    }
}
