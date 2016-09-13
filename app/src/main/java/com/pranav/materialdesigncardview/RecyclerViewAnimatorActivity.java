package com.pranav.materialdesigncardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

public class RecyclerViewAnimatorActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private EditText editText;
    private Adapter_item_animations mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_animator);
        toolbar= (Toolbar) findViewById(R.id.app_bar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText= (EditText) findViewById(R.id.input_text);

        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerViewanimations);
        mAdapter=new Adapter_item_animations(this);
//        DefaultItemAnimator animator=new DefaultItemAnimator();
//        animator.setAddDuration(1000);
//        animator.setRemoveDuration(1000);
        ScaleInAnimator animator=new ScaleInAnimator();
        animator.setAddDuration(2000);
        animator.setRemoveDuration(2000);
        mRecyclerView.setItemAnimator(animator);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);





    }

    public void add_item(View view) {
        if(editText.getText()!=null){
            String text=editText.getText().toString();
            if(text!=null && text.trim().length()>0){
                mAdapter.addItem(text);
            }
        }
        editText.setText("");

    }
}
