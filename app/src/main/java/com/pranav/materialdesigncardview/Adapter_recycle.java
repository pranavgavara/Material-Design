package com.pranav.materialdesigncardview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by Pranav on 8/23/2016.
 */
public class Adapter_recycle extends RecyclerView.Adapter<myViewHolder>  {
    List<Single_Row> data = Collections.EMPTY_LIST;
    private LayoutInflater inflater;
    private Context context;


    public Adapter_recycle(Context context, List<Single_Row> data) {
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }



    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        myViewHolder holder = new myViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {
        Single_Row current = data.get(position);
        holder.textView.setText(current.title);
        holder.imageView.setImageResource(current.iconID);
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                delete(position);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,getItemCount());
    }

}

class myViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textView;
        ImageView imageView;

        public myViewHolder(View itemView) {
            super(itemView);
//            cardView = (CardView) itemView.findViewById(R.id.cardview);
            textView = (TextView) itemView.findViewById(R.id.list_text);
            imageView = (ImageView) itemView.findViewById(R.id.list_icon);
        }



}


