package com.pranav.materialdesigncardview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class YouTubePlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    TextView finalvideoTitle;
    YouTubePlayerView youTubePlayerView;
    String API_Key="AIzaSyBnwD7oP-j38RUdYTQuV0C3rcE4_MHXNac";
    String Video_ID;
    private RecyclerView SuggestedVideo_list;
    private Adapter_YouTubeLists adapter_suggestedVideos;
    private ArrayList<singleRowYouTube_normal> list_suggestedVideos=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_player);
        finalvideoTitle= (TextView) findViewById(R.id.videotitlefinal);
        youTubePlayerView= (YouTubePlayerView) findViewById(R.id.youtube_player);
        singleRowYouTube_normal Results=getIntent().getParcelableExtra("YouTube_Video");
        youTubePlayerView.initialize(API_Key,this);
        finalvideoTitle.setText(Results.getTitle());
        Video_ID=Results.getId();
        SuggestedVideo_list= (RecyclerView) findViewById(R.id.suggestedVideos);
        adapter_suggestedVideos=new Adapter_YouTubeLists(this);
        SuggestedVideo_list.setLayoutManager(new LinearLayoutManager(this));
        SuggestedVideo_list.setAdapter(adapter_suggestedVideos);
        
        adapter_suggestedVideos.setYouTube_list(list_suggestedVideos);


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
/** Start buffering **/
        if (!b) {
            youTubePlayer.cueVideo(Video_ID);
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
        public void onError(YouTubePlayer.ErrorReason arg0) {
        }
        @Override
        public void onLoaded(String arg0) {
        }
        @Override
        public void onLoading() {
        }
        @Override
        public void onVideoEnded() {
        }
        @Override
        public void onVideoStarted() {
        }
    };

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        Toast.makeText(YouTubePlayerActivity.this, "Failed to Initialize", Toast.LENGTH_SHORT).show();

    }
}
