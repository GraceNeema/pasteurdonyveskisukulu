package pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * Created by Lenovo on 8/16/2018.
 */

public class MeditationDetail extends AppCompatActivity {
    FloatingActionButton fab,fab_whtsapp,fab_fcbk;
    Animation Fab_extension, Fab_close, Fab_rotation, Fab_rotation_reverse;
    Boolean isOpen=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meditation_detail);
        final TextView details= (TextView)findViewById(R.id.detailTextView);
         fab=(FloatingActionButton)findViewById(R.id.fab);
         fab_whtsapp=(FloatingActionButton)findViewById(R.id.fab_whatsapp);
         fab_fcbk=(FloatingActionButton)findViewById(R.id.fab_fbk);
         Fab_extension= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_extension);
         Fab_close=  AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
         Fab_rotation=  AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_rotation);
         Fab_rotation_reverse= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_rotation_reverse);


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
                }


            }
        });

    }
}
