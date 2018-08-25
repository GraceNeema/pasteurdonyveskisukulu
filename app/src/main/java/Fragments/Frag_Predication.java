package Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import Tool.HttpRequest;
import de.hdodenhof.circleimageview.CircleImageView;
import pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu.MeditationContent;
import pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu.MeditationDetail;
import pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu.R;

import static Tool.Application.url;

/**
 * Created by YvonFlourAlvin on 14/08/2018.
 */

public class Frag_Predication extends Fragment {

    RecyclerView MyRecyclerView;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_predication, container, false);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_pred);
        YourListAdapter adapter = new YourListAdapter(this.getContext(),new int[]{R.drawable.video2, R.drawable.speaker, R.drawable.read}, new String[]{"Video","Audio","Texte"});
        MyRecyclerView.setAdapter(adapter);
        MyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



    }



    public class YourListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {


        public Context context;
        private String[] themes;
        private int[] images;


        public YourListAdapter(Context context, int [] images , String[] themes) {
            this.context=context;
            this.images=images;
            this.themes=themes;

        }


        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_predication,parent ,false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final Holder vh =(Holder) holder ;
            vh.titre.setText(themes[position]);
            vh.imageView.setImageResource(images[position]);
        }



        /**
         * Returns the total number of items in the data set held by the adapter.
         *
         * @return The total number of items in this adapter.
         */
        @Override
        public int getItemCount() {
            return (images.length & themes.length);
        }

        class Holder extends RecyclerView.ViewHolder {

            TextView titre;
            CircleImageView imageView;

            @SuppressLint("WrongViewCast")
            public Holder(View itemView) {
                super(itemView);
                titre = itemView.findViewById(R.id.txt);

                imageView = itemView.findViewById(R.id.imgView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                    }
                });

            }


        }
    }

}
