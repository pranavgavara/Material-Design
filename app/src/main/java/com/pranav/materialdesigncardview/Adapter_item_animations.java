package com.pranav.materialdesigncardview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pranav on 9/8/2016.
 */
public class Adapter_item_animations extends RecyclerView.Adapter<Adapter_item_animations.ViewHolderItemAnimator>{
    private ArrayList<String> mListData =new ArrayList<>();
    private LayoutInflater mLayoutInflater;

    public Adapter_item_animations(Context context){
        mLayoutInflater=LayoutInflater.from(context);
    }


    @Override
    public ViewHolderItemAnimator onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mLayoutInflater.inflate(R.layout.custom_row_item_animator,parent,false);
        ViewHolderItemAnimator viewHolderItemAnimator=new ViewHolderItemAnimator(view);
        return viewHolderItemAnimator;
    }

    @Override
    public void onBindViewHolder(ViewHolderItemAnimator holder, final int position) {
        final String data=mListData.get(position);
        holder.textView.setText(data);
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(data);
            }
        });

    }

    public void removeItem(int position) {
        mListData.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(String item){
        mListData.add(item);
        notifyItemInserted(mListData.size());

    }

    public void removeItem(String item) {
        int remove_position=mListData.indexOf(item);
        if(remove_position!=-1){
            mListData.remove(remove_position);
            notifyItemRemoved(remove_position);
        }

    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    static class ViewHolderItemAnimator extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageButton imageButton;

        public ViewHolderItemAnimator(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.animator_text);
            imageButton= (ImageButton) itemView.findViewById(R.id.button_delete);
        }
    }
}
