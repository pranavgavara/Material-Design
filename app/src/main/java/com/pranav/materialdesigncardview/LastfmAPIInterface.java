package com.pranav.materialdesigncardview;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Pranav on 9/11/2016.
 */
public interface LastfmAPIInterface {
//    method=chart.gettoptracks&api_key=0faa615bcf8f233867a09c8b6226d8c4&format=json
    @GET("/2.0?api_key=0faa615bcf8f233867a09c8b6226d8c4&format=json")
    Call<JsonObject> loadTracks(@Query("method") String method);
}
