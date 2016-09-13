package com.pranav.materialdesigncardview;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Pranav on 9/9/2016.
 */
public class Parser_searchYouTubeList {
    public static ArrayList<singleRowYouTube_normal> parseJson(JSONObject response) {
        ArrayList<singlerowYouTube_search_gson> list = new ArrayList<>();
        ArrayList<singleRowYouTube_normal> final_list = new ArrayList<>();
        int size=0;
        try {
            size =response.getJSONArray("items").length();
            Log.d("size", "parseJson: "+size);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();

        for (int i = 0; i <size ; i++) {
            singlerowYouTube_search_gson single_row_youTube = gson.fromJson(response.toString(), singlerowYouTube_search_gson.class);
            list.add(single_row_youTube);
//            Log.d("yo", "parseJson: "+single_row_youTube.getItems().get(i).getId());

            final_list.add(new singleRowYouTube_normal(single_row_youTube.getItems().get(i).getId().getVideoId(), single_row_youTube.getItems().get(i).getSnippet().getTitle(), single_row_youTube.getItems().get(i).getSnippet().getThumbnails().getDefaultX().getUrl()));

        }
        return final_list;
    }
}
