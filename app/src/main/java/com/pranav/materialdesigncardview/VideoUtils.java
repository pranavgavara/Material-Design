package com.pranav.materialdesigncardview;

import com.android.volley.RequestQueue;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Pranav on 9/7/2016.
 */
public class VideoUtils {
    public static ArrayList<singleRowYouTube_normal> loadYouTubeVideos(RequestQueue requestQueue,String URL){
        ArrayList<singleRowYouTube_normal> listVideos=new ArrayList<>();
        JSONObject response=Requestor.sendJSONRequest(requestQueue,URL);
        if(!URL.contains("search")){
            listVideos= Parser_mostpopularList.parseJson(response);
            MyApplication.getYouTubeDatabase().insertData(listVideos,true,0);
        }else{
            listVideos= Parser_searchYouTubeList.parseJson(response);
        }

        return listVideos;
    }
}
