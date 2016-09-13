package com.pranav.materialdesigncardview;

import android.content.Context;
import android.support.v7.widget.CardView;
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
public class Adapter_recycle extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Single_Row_Navigation> data = Collections.EMPTY_LIST;
    private LayoutInflater inflater;
    private Context context;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;


    public Adapter_recycle(Context context, List<Single_Row_Navigation> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = inflater.inflate(R.layout.drawer_header, parent, false);
            HeaderHolder holder = new HeaderHolder(view);

            return holder;

        } else {


            View view = inflater.inflate(R.layout.custom_row, parent, false);
            itemHolder holder = new itemHolder(view);

            return holder;
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof HeaderHolder){


        }else {
            itemHolder itemHolder= (Adapter_recycle.itemHolder) holder;
            Single_Row_Navigation current = data.get(position-1);
            itemHolder.textView.setText(current.title);
            itemHolder.imageView.setImageResource(current.iconID);
        }
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                delete(position);
//            }
//        });


    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return TYPE_HEADER;

        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return data.size()+1;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }


    static class itemHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textView;
        ImageView imageView;

        public itemHolder(View itemView) {
            super(itemView);
//            cardView = (CardView) itemView.findViewById(R.id.cardview);
            textView = (TextView) itemView.findViewById(R.id.list_text);
            imageView = (ImageView) itemView.findViewById(R.id.list_icon);
        }


    }
    static class HeaderHolder extends RecyclerView.ViewHolder {

        public HeaderHolder(View itemView) {
            super(itemView);
        }


    }

}


