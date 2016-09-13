package com.pranav.materialdesigncardview;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Pranav on 9/7/2016.
 */
public class Parser_mostpopularList {
    public static ArrayList<singleRowYouTube_normal> parseJson(JSONObject response) {
        ArrayList<Single_Row_YouTube_mostpopular_gson> list = new ArrayList<>();
        ArrayList<singleRowYouTube_normal> final_list=new ArrayList<>();
        int size=0;

        try {
            size=response.getJSONArray("items").length();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();

        for (int i = 0; i < size; i++) {
            Single_Row_YouTube_mostpopular_gson single_row_youTube = gson.fromJson(response.toString(), Single_Row_YouTube_mostpopular_gson.class);
            list.add(single_row_youTube);
//            Log.d("yo", "parseJson: "+single_row_youTube.getItems().get(i).getId());

            final_list.add(new singleRowYouTube_normal(single_row_youTube.getItems().get(i).getId(),single_row_youTube.getItems().get(i).getSnippet().getTitle(),single_row_youTube.getItems().get(i).getSnippet().getThumbnails().getDefaultX().getUrl()));
        }

//        JSONArray videoArray= null;
//        if(response!=null&&response.length()!=0){
//            try {
//                videoArray = response.getJSONArray("items");
//                for(int i=0;i<50;i++){
//                    JSONObject eachVideoobject=videoArray.getJSONObject(i);
//
////                    String id=eachVideoobject.getJSONObject("id").getString("videoId");
//                    String id=eachVideoobject.getString("id");
//                    String videoTitle=eachVideoobject.getJSONObject("snippet").getString("title");
//                    String thumbnails=eachVideoobject.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("default").getString("url");
////
//                    list.add(new Single_Row_YouTube_mostpopular_gson(thumbnails,videoTitle,id));
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
        return final_list;
    }
}
