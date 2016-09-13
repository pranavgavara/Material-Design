package com.pranav.materialdesigncardview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Pranav on 8/29/2016.
 */
public class YouTubeSearchFragment extends Fragment implements View.OnClickListener,YoutubeVideosLoadedListener {
    EditText editText;
    String apiUrl;
    String YoutubeAPIURL;
    Button searchYT;
    private RecyclerView search_list_recyclerView;
    private Adapter_mostpopularlist adapter_searchList;
    ArrayList<singleRowYouTube_normal> list;
    Button clearYT;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_you_tube_search,container,false);
        editText= (EditText) view.findViewById(R.id.SearchYouTube);
        apiUrl=editText.getText().toString();
        search_list_recyclerView= (RecyclerView) view.findViewById(R.id.YouTube_SearchList);
        search_list_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter_searchList=new Adapter_mostpopularlist(getContext());
        search_list_recyclerView.setAdapter(adapter_searchList);
        searchYT= (Button) view.findViewById(R.id.SearchRT);
        clearYT=(Button) view.findViewById(R.id.Clear_List);
        searchYT.setOnClickListener(this);
        clearYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter_searchList.clearData();
                editText.setText("");
            }
        });
        search_list_recyclerView.addOnItemTouchListener(new RecycleTouchListener(getActivity(), search_list_recyclerView, new RecycleTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent=new Intent(getActivity(),YouTubePlayerActivity.class);
                intent.putExtra("YouTube_Video",list.get(position));
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return view;
    }


    public static YouTubeSearchFragment newInstance() {
        
        Bundle args = new Bundle();
        YouTubeSearchFragment fragment = new YouTubeSearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View view) {
        String search=editText.getText().toString().replaceAll(" ","%20");
        YoutubeAPIURL="https://www.googleapis.com/youtube/v3/search?part=snippet,id&q="+search+"&maxResults=50&type=video&key=AIzaSyBnwD7oP-j38RUdYTQuV0C3rcE4_MHXNac";
        new TaskLoadYouTubeVideos(this,YoutubeAPIURL).execute();
//        RequestQueue requestQueue= VolleySingleton.getInstance().getmRequestQueue();
//        StringRequest request=new StringRequest(Request.Method.GET, YoutubeAPIURL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(getActivity(),"Response received"+response,Toast.LENGTH_LONG).show();
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getActivity(),"Error received"+error.getMessage(),Toast.LENGTH_LONG).show();
//
//            }
//        });
//        requestQueue.add(request);
        editText.setText("");
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    @Override
    public void onYouTubeVideosLoaded(ArrayList<singleRowYouTube_normal> listVideos) {
        adapter_searchList.setYouTube_list(listVideos);
        list=listVideos;

    }
}
