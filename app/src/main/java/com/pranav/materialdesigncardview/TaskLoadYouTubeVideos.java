package com.pranav.materialdesigncardview;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

/**
 * Created by Pranav on 9/7/2016.
 */
public class TaskLoadYouTubeVideos extends AsyncTask<Void,Void,ArrayList<singleRowYouTube_normal>> {
    private YoutubeVideosLoadedListener myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private String URL;
    public TaskLoadYouTubeVideos(YoutubeVideosLoadedListener myComponent,String Url) {
        this.myComponent=myComponent;
        volleySingleton=VolleySingleton.getInstance();
        requestQueue=volleySingleton.getmRequestQueue();
        this.URL=Url;
    }

    @Override
    protected ArrayList<singleRowYouTube_normal> doInBackground(Void... voids) {
        ArrayList<singleRowYouTube_normal> listVideos=VideoUtils.loadYouTubeVideos(requestQueue,URL);
        return listVideos;

    }

    @Override
    protected void onPostExecute(ArrayList<singleRowYouTube_normal> single_row_youTubes) {
        super.onPostExecute(single_row_youTubes);
        if(myComponent!=null){
            myComponent.onYouTubeVideosLoaded(single_row_youTubes);
        }
    }
}
