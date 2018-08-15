package pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu;

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

import java.util.ArrayList;

//import Model.Actualité;
import Model.Meditation;
import Tool.BackgroundTask;

/**
 * Created by Lenovo on 8/14/2018.
 */

public class Meditation_fragment extends Fragment {
    ArrayList<Meditation> listitems = new ArrayList<>();
    RecyclerView MyRecyclerView;
    String Wonders[] = {"Christ at the center", "Christ the Redeemer"};
    int Images[] = {R.drawable.header_2, R.drawable.header_3};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          initializeList();
        getActivity().setTitle("Méditation");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.meditation_fragment, container, false);
         MyRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);
       //BackgroundTask backgroundTask = new BackgroundTask();
       // backgroundTask.execute();
        MyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        BackgroundTask backgroundTask=new BackgroundTask();
        backgroundTask.execute();
        if (listitems.size() > 0 & MyRecyclerView != null) {
            MyRecyclerView.setAdapter(new MyAdapter(listitems));
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<Meditation> list;

        public MyAdapter(ArrayList<Meditation> Data) {
            list = Data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_items, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            holder.titleTextView.setText(list.get(position).getCardName());
            holder.coverImageView.setImageResource(list.get(position).getImageResourceId());
            holder.shareImageView.setTag(list.get(position).getImageResourceId());
         //   holder.likeImageView.setTag(R.drawable.ic_like);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public ImageView coverImageView;

        public ImageView shareImageView;

        public MyViewHolder(View v) {
            super(v);
            titleTextView = (TextView) v.findViewById(R.id.titleTextView);
            coverImageView = (ImageView) v.findViewById(R.id.coverImageView);

            shareImageView = (ImageView) v.findViewById(R.id.shareImageView);

        }
    }
    public void initializeList() {
        listitems.clear();

        for (int i = 0; i < 2; i++) {


            Meditation item = new Meditation();
            item.setTitre(Wonders[i]);
            item.setImageRef(Images[i]);
            item.setIsturned(0);
            listitems.add(item);

        }
    }
}



