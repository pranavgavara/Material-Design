package com.pranav.materialdesigncardview;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Pranav on 9/7/2016.
 */
public class Requestor {
    public static JSONObject sendJSONRequest(RequestQueue requestQueue,String URL){
        JSONObject response=null;
        RequestFuture<JSONObject> requestfuture=RequestFuture.newFuture();
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, URL, null,requestfuture,requestfuture);



//                    new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
////                Toast.makeText(getActivity(),"Response"+response.toString(),Toast.LENGTH_LONG).show();
////                    volleyErrorText.setVisibility(View.GONE);
////                    youTube_list=parseJson(response);
////                    adapter_mostpopularlist.setYouTube_list(youTube_list);
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
////                    volleyErrorText.setVisibility(View.VISIBLE);
////                    if(error instanceof TimeoutError || error instanceof NoConnectionError){
////                        volleyErrorText.setText("Connection could not be established");
////                    }else if(error instanceof AuthFailureError){
////                        volleyErrorText.setText("Authentication Failure");
////
////                    }else if(error instanceof ServerError){
////                        volleyErrorText.setText("Server Error");
////
////                    }else if(error instanceof NetworkError){
////                        volleyErrorText.setText("Network Error");
////
////                    }else if(error instanceof ParseError){
////                        volleyErrorText.setText("Parse Error");
////
////                    }
//                }
//            });
        requestQueue.add(request);
        try {
            response=requestfuture.get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return response;
    }
}
