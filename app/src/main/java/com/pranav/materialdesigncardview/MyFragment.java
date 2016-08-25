package com.pranav.materialdesigncardview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Pranav on 8/25/2016.
 */
public class MyFragment extends Fragment {
    private TextView textView;
    private int num;
    public static MyFragment getInstance(int position){
        MyFragment myFragment=new MyFragment();
        Bundle args=new Bundle();
        args.putInt("position",position+1);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout= inflater.inflate(R.layout.fragment_my,container,false);
        textView= (TextView) layout.findViewById(R.id.position);
        if(getArguments()!=null){
            textView.setText("I am yo number "+getArguments().getInt("position"));
        }

        return layout;
    }
}
