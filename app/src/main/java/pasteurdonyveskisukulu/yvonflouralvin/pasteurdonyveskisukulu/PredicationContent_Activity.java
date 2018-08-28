package pasteurdonyveskisukulu.yvonflouralvin.pasteurdonyveskisukulu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by Lenovo on 8/27/2018.
 */

public class PredicationContent_Activity extends AppCompatActivity {
    public static final String API_KEY = "AIzaSyCHjUWly6VP4Yn-UEAGQbJpohhM20h6lKU";
    public static final String VIDEO_ID = "qEHE8Ie-leU&t=5s";

  /*  @Override
  /*  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.predication_container);
/** Initializing YouTube Player View **/
     /*   YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);
        youTubePlayerView.initialize(API_KEY, this);
  /*  }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
/** add listeners to YouTubePlayer instance **/
    /*    player.setPlayerStateChangeListener(playerStateChangeListener);
      /*  player.setPlaybackEventListener(playbackEventListener);
/** Start buffering **/
      /*  if (!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onBuffering(boolean arg0) {
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onPlaying() {
        }

        @Override
        public void onSeekTo(int arg0) {
        }

        @Override
        public void onStopped() {
        }
    };
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onAdStarted() {
        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {
        }

        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String arg0) {
        }
    };
    */
}
