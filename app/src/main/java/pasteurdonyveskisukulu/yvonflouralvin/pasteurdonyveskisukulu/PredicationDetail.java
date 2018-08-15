package pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import Adapter.PredicationAdapter;
import Model.Predication;
import Tool.Application;
import Tool.HttpRequest;

public class PredicationDetail extends AppCompatActivity implements Application {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predication);

        if (getIntent().getExtras() != null) {
            int i = getIntent().getExtras().getInt("idpredication");
            load_data(PredicationDetail.this, i);
        }
    }


    protected void load_data(final Context context, final int idpredication) {
        new AsyncTask() {


            @Override
            protected Object doInBackground(Object[] objects) {
                String data = HttpRequest.submit(
                        url,
                        "POST",
                        new String[]{
                                "target",
                                "idpredication"
                        },
                        new String[]{
                                "get_predication",
                                String.valueOf(idpredication)
                        }
                );

                try {
                    Predication predication = new Predication();
                    JSONObject json_ob = new JSONObject(data);
                    predication.setIdpredication(json_ob.getInt("idpredication"));
                    predication.setTitre(json_ob.getString("titre"));
                    predication.setMessage(json_ob.getString("contenu"));
                    predication.setImageRef(json_ob.getString("image"));
                    predication.setOrateur(json_ob.getString("orateur"));
                    predication.setDate(json_ob.getString("date"));
                    return predication;
                } catch (Exception e) {
                    Log.e("Frag_Predication_info", e.getMessage());
                }
                return null;
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            protected void onPostExecute(final Object o) {
                if (o != null) {
                    ((TextView)findViewById(R.id.titre)).setText(((Predication)o).getTitre());
                    ((TextView)findViewById(R.id.orateur)).setText("Orateur : "+((Predication)o).getOrateur());
                    ((TextView)findViewById(R.id.date)).setText(((Predication)o).getDate());
                    ((TextView)findViewById(R.id.contenu)).setText(((Predication)o).getMessage());
                    new AsyncTask(){

                        @Override
                        protected Object doInBackground(Object[] objects) {
                            Bitmap bitmap = HttpRequest.donwload_bitmap(url+(((Predication)o).getImageRef()));
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
                                //load_image.setVisibility(View.INVISIBLE);
                                ((ImageView)findViewById(R.id.image)).setVisibility(View.VISIBLE);
                                img.setImageBitmap(bitmap);
                            }
                            super.onPostExecute(o);
                        }
                    }.execute(new WeakReference<ImageView>(((ImageView)findViewById(R.id.image))));
                } else {

                }
                super.onPostExecute(o);
            }
        }.execute();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PredicationDetail.this, Accueil.class);
        intent.putExtra("origin", "predication");
        startActivity(intent);
        finish();
    }
}
