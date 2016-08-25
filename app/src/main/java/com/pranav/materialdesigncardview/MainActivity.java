package com.pranav.materialdesigncardview;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import tabs.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    Navigation_Fragment navigation_fragment;
    private ViewPager mPager;
    private SlidingTabLayout mtabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);
        toolbar= (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigation_fragment= (Navigation_Fragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        navigation_fragment.setUp(R.id.fragment,(DrawerLayout)findViewById(R.id.drawer),toolbar);

        mPager= (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mtabs= (SlidingTabLayout) findViewById(R.id.tabs);
        mtabs.setDistributeEvenly(true);
        mtabs.setCustomTabView(R.layout.custom_tabs_view,R.id.tabText);
//        mtabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
//            @Override
//            public int getIndicatorColor(int position) {
//                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
//                    return getResources().getColor(R.color.colorAccent,null);
//                }
//                return 0;
//            }
//        });
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            mtabs.setBackgroundColor(getColor(R.color.colorPrimary));
            mtabs.setSelectedIndicatorColors(getColor(R.color.colorAccent));
        }

        mtabs.setViewPager(mPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.recycler_class){
            Intent intent = new Intent(this, RecyclerViewActivity.class);
            startActivity(intent);
        }
        if(id==R.id.LibraryTabs){
            Intent intent = new Intent(this, TabswithLibrary.class);
            startActivity(intent);
        }
        return true;
    }

    public void gotoSecond(View view) {
        Intent intent = new Intent(this, RecyclerViewActivity.class);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for gingerbread and newer versions
            getWindow().setExitTransition(new Explode());
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }else{
            startActivity(intent);
        }
    }

    class MyPagerAdapter extends FragmentPagerAdapter{
//        String[] tabs;
        int[] images={R.raw.applause50,R.raw.hide48,R.raw.pacmanfilled};
        String[] tabtext=getResources().getStringArray(R.array.tabs);

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
//            tabs=getResources().getStringArray(R.array.tabs);

        }

        @Override
        public Fragment getItem(int position) {
            MyFragment myFragment=MyFragment.getInstance(position);
            return myFragment;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                Drawable drawable=getResources().getDrawable(images[position],null);
                drawable.setBounds(140,50,240,150);
                ImageSpan imageSpan=new ImageSpan(drawable);
                SpannableString spannableString=new SpannableString(" ");
                spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return spannableString;
            }
            else{
                return tabtext[position];
            }
//            return tabs;
        }

        @Override
        public int getCount() {
            return tabtext.length/2;
        }
    }
}

