package com.pranav.materialdesigncardview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Pranav on 8/29/2016.
 */
public class Adapter_mostpopularlist extends RecyclerView.Adapter<Adapter_mostpopularlist.ViewHolderMostPopular> {
    private LayoutInflater layoutInflater;
    private ArrayList<singleRowYouTube_normal> YouTube_list=new ArrayList<>();
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private int previous_position=0;


    public Adapter_mostpopularlist(Context context) {
        layoutInflater=layoutInflater.from(context);
        volleySingleton=VolleySingleton.getInstance();
        imageLoader= volleySingleton.getmImageLoader();
    }

    @Override
    public ViewHolderMostPopular onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.custom_row,parent,false);
        ViewHolderMostPopular viewHolderMostPopular=new ViewHolderMostPopular(view);
        return viewHolderMostPopular;
    }
    public void setYouTube_list(ArrayList<singleRowYouTube_normal> list){
        this.YouTube_list=list;
        notifyItemRangeChanged(0,YouTube_list.size());
    }
    public void clearData() {
        int size = this.YouTube_list.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.YouTube_list.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolderMostPopular holder, int position) {
        singleRowYouTube_normal currentVideo=YouTube_list.get(position);
        holder.textView.setText(currentVideo.getTitle());
        String URL_Thumbnail=currentVideo.getThumbnail();
        if(URL_Thumbnail!=null){
            imageLoader.get(URL_Thumbnail, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.imageView.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    holder.imageView.setImageResource(R.mipmap.ic_launcher);

                }
            });
        }
        if(position>previous_position){
            AnimateUtils.animate(holder,true);
        }else{
            AnimateUtils.animate(holder,false);

        }
        previous_position=position;
    }

    @Override
    public int getItemCount() {
        return YouTube_list.size();
    }

    static class ViewHolderMostPopular extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView imageView;

        public ViewHolderMostPopular(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.list_text);
            imageView= (ImageView) itemView.findViewById(R.id.list_icon);
        }
    }
}
