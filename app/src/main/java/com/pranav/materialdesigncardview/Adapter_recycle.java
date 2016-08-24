package com.pranav.materialdesigncardview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Pranav on 8/23/2016.
 */
public class Adapter_recycle extends RecyclerView.Adapter<myViewHolder> {
    List<Single_Row> data= Collections.EMPTY_LIST;
    private LayoutInflater inflater;
    public Adapter_recycle(Context context,List<Single_Row> data){
        inflater=LayoutInflater.from(context);
        this.data=data;
    }


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.custom_row,parent,false);
        myViewHolder holder=new myViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        Single_Row current=data.get(position);
        holder.textView.setText(current.title);
        holder.imageView.setImageResource(current.iconID);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class myViewHolder extends RecyclerView.ViewHolder{
    TextView textView;
    ImageView imageView;

    public myViewHolder(View itemView) {
            super(itemView);
        textView= (TextView) itemView.findViewById(R.id.list_text);
        imageView= (ImageView) itemView.findViewById(R.id.list_icon);
    }
}

