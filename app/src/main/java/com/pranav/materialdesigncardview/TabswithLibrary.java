package com.pranav.materialdesigncardview;

import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class TabswithLibrary extends AppCompatActivity implements MaterialTabListener {
    Toolbar toolbar;
    MaterialTabHost materialTabHost;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabswith_library);
        toolbar = (Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        materialTabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                materialTabHost.setSelectedNavigationItem(position);
            }
        });
        for (int i = 0; i < adapter.getCount(); i++) {
            materialTabHost.addTab(
                    materialTabHost.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(this)
            );

        }
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    class MyPagerAdapter extends FragmentPagerAdapter {

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

            return tabtext[position];

        }

        @Override
        public int getCount() {
            return tabtext.length;
        }
    }

}
