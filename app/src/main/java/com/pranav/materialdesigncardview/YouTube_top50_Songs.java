package com.pranav.materialdesigncardview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pranav on 9/11/2016.
 */
public class YouTube_top50_Songs extends Fragment implements SwipeRefreshLayout.OnRefreshListener, Callback<JsonObject>,YoutubeVideosLoadedListener {

    private SwipeRefreshLayout swipeRefreshLayout_top50;
    private static final String TOP_SONGS ="top_songs" ;
    private RecyclerView youTube_top50_songs;
    private Adapter_YouTubeLists adapter_top50;
    public final String URL="http://ws.audioscrobbler.com";
    String track_name;
    String artist_name;
    private ArrayList<String> Search_Query_list;
    private ArrayList<singleRowYouTube_normal> list_top50;
    private static final int TABLE_NUMBER=1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_youtube_top50,container,false);
        swipeRefreshLayout_top50= (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshvideostop50);
        youTube_top50_songs= (RecyclerView) view.findViewById(R.id.youtube_top50);
        youTube_top50_songs.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter_top50=new Adapter_YouTubeLists(getContext());
        swipeRefreshLayout_top50.setOnRefreshListener(this);
        youTube_top50_songs.setAdapter(adapter_top50);
        list_top50=new ArrayList<>();
        Search_Query_list=new ArrayList<>();
        if(savedInstanceState!=null){
            list_top50=savedInstanceState.getParcelableArrayList(TOP_SONGS);
        }
        else{
            list_top50=MyApplication.getYouTubeDatabase().getAllVideos(TABLE_NUMBER);
            if(list_top50.isEmpty()){
                downloadData_Retrofit();
            }
        }
        adapter_top50.setYouTube_list(list_top50);

        youTube_top50_songs.addOnItemTouchListener(new RecycleTouchListener(getActivity(), youTube_top50_songs, new RecycleTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent=new Intent(getActivity(),YouTubePlayerActivity.class);
                intent.putExtra("YouTube_Video",list_top50.get(position));
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        return view;
    }
    public void downloadData_Retrofit(){
        Gson gson=new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        LastfmAPIInterface lastfmAPIInterface=retrofit.create(LastfmAPIInterface.class);
        Call<JsonObject> call=lastfmAPIInterface.loadTracks("chart.gettoptracks");
        call.enqueue(this);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(TOP_SONGS,list_top50);
    }

    public static YouTube_top50_Songs newInstance() {

        Bundle args = new Bundle();
        YouTube_top50_Songs fragment = new YouTube_top50_Songs();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onRefresh() {
        list_top50=new ArrayList<>();
        downloadData_Retrofit();
        Toast.makeText(getActivity(),"Refreshed video list",Toast.LENGTH_LONG).show();
    }


    @Override
    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//        System.out.println("Hello"+response.body().toString());

        JsonObject JsonTracks=response.body().getAsJsonObject("tracks");
        JsonArray jsonArray=JsonTracks.getAsJsonArray("track");
        for (JsonElement type:jsonArray){
            JsonObject ind= type.getAsJsonObject();
            System.out.println("Hello1"+type.toString());
            track_name= String.valueOf(ind.get("name"));
//            System.out.println("hi"+track_name);
            artist_name= String.valueOf(ind.getAsJsonObject("artist").get("name"));
            String Search_Query=track_name+" "+artist_name;
//            System.out.println("hi"+Search_Query);
            Search_Query_list.add(Search_Query.replaceAll(" ","%20"));
        }
        getFromYouTube(Search_Query_list);
    }

    private void getFromYouTube(ArrayList<String> search_query_list) {
        for(int i=0;i<search_query_list.size();i++){
            Log.d("query", "getFromYouTube: "+search_query_list.get(i));
            String YoutubeAPIURL="https://www.googleapis.com/youtube/v3/search?part=snippet,id&q="+search_query_list.get(i)+"&type=video&key=AIzaSyBnwD7oP-j38RUdYTQuV0C3rcE4_MHXNac";
            new TaskLoadYouTubeVideos(this,YoutubeAPIURL).execute();
        }

    }

    @Override
    public void onFailure(Call<JsonObject> call, Throwable t) {
        Toast.makeText(getContext(),"Nope"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        Log.d("hi", "onFailure: "+t.getMessage());

    }

    @Override
    public void onYouTubeVideosLoaded(ArrayList<singleRowYouTube_normal> listVideos) {


        if(listVideos.get(0)!=null){
            list_top50.add(listVideos.get(0));
        }

        if(list_top50.size()==50){
            setadapter(list_top50);
            if(swipeRefreshLayout_top50.isRefreshing()){
                swipeRefreshLayout_top50.setRefreshing(false);
            }
        }
    }

    private void setadapter(ArrayList<singleRowYouTube_normal> list) {
        MyApplication.getYouTubeDatabase().insertData(list,true,TABLE_NUMBER);
        adapter_top50.setYouTube_list(list);
    }
}
