package com.pranav.materialdesigncardview;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;


import tabs.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {
    private static final int JOB_ID = 100;
    Toolbar toolbar;
    Navigation_Fragment navigation_fragment;
    private ViewPager mPager;
    private SlidingTabLayout mtabs;
    private static final int YOUTUBE_MOST_POPULAR=0;
    private static final int YOUTUBE_TOP_FIFTY=1;
    private static final int YOUTUBE_SEARCH=2;
    private JobScheduler mJobScheduler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);
        toolbar= (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        YoYo.with(Techniques.BounceInDown)
//                .duration(3000)
//                .playOn(toolbar);

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


        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageResource(R.mipmap.ic_launcher);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setBackgroundDrawable(R.drawable.button_action_dark)
                .build();

//        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
//        SubActionButton button1 = itemBuilder.setContentView(icon).build();
//        SubActionButton button2 = itemBuilder.setContentView(icon).build();
//
//        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
//                .addSubActionView(button1)
//                .addSubActionView(button2)
//                .attachTo(actionButton)
//                .build();
//        mJobScheduler= (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                constructJob();
//            }
//        },30000);



    }
    public void onDrawerItemClicked(int position){
        mPager.setCurrentItem(position-1);

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id=item.getItemId();
//        if(id==R.id.recycler_class){
//            Intent intent = new Intent(this, FakeActivity.class);
//            startActivity(intent);
//        }
//        if(id==R.id.LibraryTabs){
//            Intent intent = new Intent(this, TabswithLibrary.class);
//            startActivity(intent);
//        }
//        if(id==R.id.cardView){
//            Intent intent = new Intent(this, CardViewActivity.class);
//            startActivity(intent);
//        }
//        return true;
//    }



    class MyPagerAdapter extends FragmentStatePagerAdapter{
//        String[] tabs;
//        int[] images={R.raw.applause50,R.raw.hide48,R.raw.pacmanfilled};
        String[] tabtext=getResources().getStringArray(R.array.YouTube);

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
//            tabs=getResources().getStringArray(R.array.tabs);

        }

        @Override
        public Fragment getItem(int position) {

//            MyFragment_i_am_yo_blank myFragment=MyFragment_i_am_yo_blank.getInstance(position);
            Fragment fragment=null;
            switch (position){
                case YOUTUBE_MOST_POPULAR:
                    fragment= YouTubeMostPopularFragment.newInstance();
                    break;
                case YOUTUBE_TOP_FIFTY:
                    fragment=YouTube_top50_Songs.newInstance();
                    break;
                case YOUTUBE_SEARCH:
                    fragment=YouTubeSearchFragment.newInstance();
                    break;

            }
            return fragment;
        }


//        @Override
//        public CharSequence getPageTitle(int position) {
//            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
//                Drawable drawable=getResources().getDrawable(images[position],null);
//                drawable.setBounds(140,50,240,150);
//                ImageSpan imageSpan=new ImageSpan(drawable);
//                SpannableString spannableString=new SpannableString(" ");
//                spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                return spannableString;
//            }
//            else{
//                return tabtext[position];
//            }
////            return tabs;
//        }


        @Override
        public CharSequence getPageTitle(int position) {
            return tabtext[position];
        }

        @Override
        public int getCount() {
            return tabtext.length;
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void constructJob(){
        JobInfo.Builder builder=new JobInfo.Builder(JOB_ID,new ComponentName(this,JobSchedulerService.class));
//        PersistableBundle persistableBundle=new PersistableBundle();

        builder.setPeriodic(1080000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true);
        mJobScheduler.schedule(builder.build());
    }
}

