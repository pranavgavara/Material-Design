package com.pranav.materialdesigncardview;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

public class YouTubeMostPopularFragment extends Fragment implements YoutubeVideosLoadedListener, SwipeRefreshLayout.OnRefreshListener {


    private static final String POPULAR_VIDEOS ="popular_videos" ;
    private ImageLoader imageLoader;
    private ArrayList<Single_Row_YouTube_mostpopular_gson> youTube_list=new ArrayList<>();
    private Single_Row_YouTube_mostpopular_gson youtube_Video;
    private RecyclerView most_popular_list_recyclerView;
    private Adapter_mostpopularlist adapter_mostpopularlist;
    ArrayList<singleRowYouTube_normal> list_mostpopular=new ArrayList<>();
    TextView volleyErrorText;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static final int TABLE_NUMBER=0;
    private String URL="https://www.googleapis.com/youtube/v3/videos?part=snippet,id&chart=mostPopular&time=today&maxResults=50&type=video&key=AIzaSyBnwD7oP-j38RUdYTQuV0C3rcE4_MHXNac";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_youtube_mostpopular,container,false);
        most_popular_list_recyclerView= (RecyclerView) view.findViewById(R.id.youtube_mostPopular_list);
        volleyErrorText= (TextView) view.findViewById(R.id.textVolleyError);
        most_popular_list_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter_mostpopularlist=new Adapter_mostpopularlist(getContext());
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshvideos);
        swipeRefreshLayout.setOnRefreshListener(this);
        most_popular_list_recyclerView.setAdapter(adapter_mostpopularlist);
        if(savedInstanceState!=null){
            list_mostpopular=savedInstanceState.getParcelableArrayList(POPULAR_VIDEOS);
        }
        else{
            list_mostpopular=MyApplication.getYouTubeDatabase().getAllVideos(TABLE_NUMBER);
            if(list_mostpopular.isEmpty()){
                new TaskLoadYouTubeVideos(this,URL).execute();
            }
        }
        adapter_mostpopularlist.setYouTube_list(list_mostpopular);
        most_popular_list_recyclerView.addOnItemTouchListener(new RecycleTouchListener(getActivity(), most_popular_list_recyclerView, new RecycleTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent=new Intent(getActivity(),YouTubePlayerActivity.class);
                intent.putExtra("YouTube_Video",list_mostpopular.get(position));
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(POPULAR_VIDEOS,list_mostpopular);

    }

    public static YouTubeMostPopularFragment newInstance() {
        
        Bundle args = new Bundle();
        YouTubeMostPopularFragment fragment = new YouTubeMostPopularFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onYouTubeVideosLoaded(ArrayList<singleRowYouTube_normal> listVideos) {
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
        list_mostpopular=listVideos;
        adapter_mostpopularlist.setYouTube_list(listVideos);
    }

    @Override
    public void onRefresh() {

        new TaskLoadYouTubeVideos(this,URL).execute();
        Toast.makeText(getActivity(),"Refreshed video list",Toast.LENGTH_LONG).show();
    }

}
