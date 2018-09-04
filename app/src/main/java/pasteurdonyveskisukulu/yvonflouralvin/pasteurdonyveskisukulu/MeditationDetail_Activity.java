package pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Lenovo on 8/16/2018.
 */

public class MeditationDetail_Activity extends AppCompatActivity {
    FloatingActionButton fab,fab_whtsapp,fab_fcbk;
    Animation Fab_extension, Fab_close, Fab_rotation, Fab_rotation_reverse;
    Boolean isOpen=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meditation_detail);
        final TextView details= (TextView)findViewById(R.id.detailTextView);
        final  TextView titre=(TextView)findViewById(R.id.titreTxt);
        final TextView date=(TextView)findViewById(R.id.date);
        final ImageView image= (ImageView) findViewById(R.id.img);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab_whtsapp=(FloatingActionButton)findViewById(R.id.fab_whatsapp);
        fab_fcbk=(FloatingActionButton)findViewById(R.id.fab_fbk);
        Fab_extension= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_extension);
        Fab_close=  AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        Fab_rotation=  AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_rotation);
        Fab_rotation_reverse= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_rotation_reverse);

        String value1 = super.getIntent().getExtras().getString("message");
        details.setText(value1);
        String value2=super.getIntent().getExtras().getString("titre");
        titre.setText(value2);
        String value3= super.getIntent().getExtras().getString("date");
        date.setText("posté le "+ value3);
        //Bitmap btmp = getIntent().getParcelableExtra("img");
      //  image.setImageBitmap(btmp);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOpen){
                    fab_whtsapp.startAnimation(Fab_close);
                    fab_fcbk.startAnimation(Fab_close);
                    fab.startAnimation(Fab_rotation_reverse);
                    fab_whtsapp.setClickable(false);
                    fab_fcbk.setClickable(false);
                    isOpen= false;
                }
                else {
                    fab_whtsapp.startAnimation(Fab_extension);
                    fab_fcbk.startAnimation(Fab_extension);
                    fab.startAnimation(Fab_rotation);
                    fab_whtsapp.setClickable(true);
                    fab_fcbk.setClickable(true);
                    isOpen= true;
                    fab_whtsapp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                            whatsappIntent.setType("text/plain");
                            whatsappIntent.setPackage("com.whatsapp");
                            whatsappIntent.putExtra(Intent.EXTRA_TEXT, details.getText());
                            try {
                                startActivity(whatsappIntent);
                            } catch (android.content.ActivityNotFoundException ex) {

                            }
                        }
                    });
                    fab_fcbk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent fcbkintent = new Intent(Intent.ACTION_SEND);
                            fcbkintent .setType("text/plain");
                            fcbkintent.setPackage("com.facebook.katana");
                            fcbkintent.putExtra(Intent.EXTRA_TEXT, details.getText());
                            try {
                                startActivity( fcbkintent);
                            } catch (android.content.ActivityNotFoundException ex) {
                               Toast.makeText(getApplicationContext(),"Facebook n'est pas installé sur votre appareil.",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }


            }
        });

    }
}

