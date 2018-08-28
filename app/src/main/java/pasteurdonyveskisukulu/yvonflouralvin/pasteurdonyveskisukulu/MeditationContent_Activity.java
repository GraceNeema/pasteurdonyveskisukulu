package pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import Model.Meditation;
import Tool.Application;
import Tool.HttpRequest;

/**
 * Created by Lenovo on 8/22/2018.
 */

public class MeditationContent_Activity extends AppCompatActivity implements Application{
            RecyclerView  MyRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.meditation_content);


        MyRecyclerView = (RecyclerView) findViewById(R.id.cardView);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setTitle(getString(R.string.app_name));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        load_data(MeditationContent_Activity.this);
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
                   // Log.e("Frag_Predication_info","Line 84 : "+e.getMessage());
                }
                return null;
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            protected void onPostExecute(Object o) {
                if (o != null) {
                    ArrayList<Meditation> meds = (ArrayList<Meditation>) o;
                    MyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    MyRecyclerView.setAdapter(new YourListAdapter(meds,context));

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
        public Context context;



        public YourListAdapter ( ArrayList<Meditation> list, Context context) {
            this.list=list;
            this.context=context;

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
                vh.sousTitre.setText(list.get(position).getSoustitre());
                vh.messageTextView.setText(list.get(position).getMessage());
                vh.date.setText(list.get(position).getDate());
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
                vaultItemHolder.titre.setText(list.get(position).getTitre());
                vaultItemHolder.soustitre.setText(list.get(position).getSoustitre());
                vaultItemHolder.messagetxt.setText(list.get(position).getMessage());
                vaultItemHolder.datetxt.setText(list.get(position).getDate());
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

        public TextView titreTextView, sousTitre, messageTextView, date;
        public ImageView coverImageView;



        public ViewHolderOne(View itemView) {
            super(itemView);
            titreTextView = (TextView) itemView.findViewById(R.id.titreTextView);
            sousTitre = (TextView) itemView.findViewById(R.id.soustitreTextView);
            messageTextView=(TextView)itemView.findViewById(R.id.message);
            date=(TextView)itemView.findViewById(R.id.date);
            coverImageView = (ImageView) itemView.findViewById(R.id.coverImageView);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MeditationDetail_Activity.class);
                    String value1=messageTextView.getText().toString();
                    String value2=titreTextView.getText().toString();
                    String value3=date.getText().toString();
                  //  Bitmap bitmap = ((BitmapDrawable) coverImageView.getDrawable()).getBitmap();
                  //  intent.putExtra("img",bitmap);
                    intent.putExtra("date",value3);
                    intent.putExtra("titre",value2);
                    intent.putExtra("message",value1);
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
        public TextView messagetxt, datetxt;
        public  ImageView img;
        public ViewHolderTwo(View itemView) {
            super(itemView);
            mView=itemView;
            titre = (TextView)itemView.findViewById(R.id.txt1);
            img=(ImageView)itemView.findViewById(R.id.img1);
            soustitre=(TextView)itemView.findViewById(R.id.soustitre);
            messagetxt=(TextView)itemView.findViewById(R.id.msgtxt);
            datetxt=(TextView)itemView.findViewById(R.id.datetxt);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MeditationDetail_Activity.class);
                    String value1=messagetxt.getText().toString();
                    String value2=titre.getText().toString();
                    String value3=datetxt.getText().toString();

                //    Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
                  //  intent.putExtra("img",bitmap);
                    intent.putExtra("date",value3);
                    intent.putExtra("titre",value2);
                    intent.putExtra("message",value1);
                    view.getContext().startActivity(intent);


                }
            });


        }
    }
}