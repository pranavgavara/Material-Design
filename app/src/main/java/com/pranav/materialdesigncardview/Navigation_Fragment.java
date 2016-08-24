package com.pranav.materialdesigncardview;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Navigation_Fragment extends Fragment {

    public ActionBarDrawerToggle mDrawertoggle;
    public static final String KEY="user_learned_key";
    private DrawerLayout mDrawerLayout;
    public static final String File_Name="test_pref";
    private View containerView;
    private RecyclerView recyclerView;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private Adapter_recycle adapter;


    public Navigation_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer=Boolean.valueOf(readFromSavedPreferences(getActivity(),KEY,"false"));
        if(savedInstanceState!=null){
            mFromSavedInstanceState=true;
        }


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout=inflater.inflate(R.layout.fragment_navigation_, container, false);
        recyclerView= (RecyclerView) layout.findViewById(R.id.recyclerList_drawer);
        adapter=new Adapter_recycle(getActivity(),getData());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return layout;
    }


    public void setUp(int FragmentID, DrawerLayout drawerLayout, final Toolbar toolbar) {
        mDrawerLayout=drawerLayout;
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.colorPrimaryDark,null));
//        }
        containerView=getActivity().findViewById(FragmentID);
        mDrawertoggle=new ActionBarDrawerToggle(getActivity(),mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer=true;
                    saveToSavedPreferences(getActivity(),KEY,mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if(slideOffset<0.6){
                    toolbar.setAlpha(1-slideOffset);
                }
            }
        };
        if(!mUserLearnedDrawer && !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.addDrawerListener(mDrawertoggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawertoggle.syncState();
            }
        });
    }

    public static List<Single_Row> getData(){
        List<Single_Row> data=new ArrayList<>();
        int[] imageArray={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
        String[] Data={"Data1","Data2","Data3","Data4"};
        for(int i=0;i<Data.length;i++){
            Single_Row current=new Single_Row(imageArray[i],Data[i]);
            data.add(current);
        }
        return data;
    }



    public static void saveToSavedPreferences(Context context, String preferenceName, String preferenceValue){
        SharedPreferences sharedPref=context.getSharedPreferences(File_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPref.edit();
        editor.putString(preferenceName,preferenceValue);
        editor.apply();

    }
    public static String readFromSavedPreferences(Context context,String preferenceName,String defaultValue){
        SharedPreferences sharedPref=context.getSharedPreferences(File_Name, Context.MODE_PRIVATE);
        return sharedPref.getString(preferenceName,defaultValue);
    }

}