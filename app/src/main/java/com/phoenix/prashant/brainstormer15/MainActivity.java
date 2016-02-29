package com.phoenix.prashant.brainstormer15;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MainActivity extends FragmentActivity{

    private ResideMenu resideMenu;
    private MainActivity mContext;
    private ActionBarDrawerToggle drawerToggle;
    private static Toolbar toolbar;
    String titles[]= {"Home","Rules","Event Details","Gallery","About","Sponsors","Registration","Quiz"};
    int icon [] ={R.drawable.ic_home,R.drawable.ic_rules,R.drawable.ic_invitation,R.drawable.ic_gallery,R.drawable.ic_about,R.drawable.ic_sponsors,R.drawable.ic_registration,R.drawable.ic_faq};
    Button btmaps;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mContext = this;
        toolbar = (Toolbar) findViewById(R.id.layout_top);
        btmaps= (Button) findViewById(R.id.btmaps);

        setUpMenu();
        if( savedInstanceState == null )
//            changeFragment(new Home());
        fragchange(0);
        btmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("prashant.phoenix.MAP"));
            }
        });
     /*   setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");*/
//        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg2));


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_search) {
            Toast.makeText(this, "Hey!!!", Toast.LENGTH_SHORT).show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }


    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.wallpaperblur);
        resideMenu.attachToActivity(this);
//        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip. 
        resideMenu.setScaleValue(0.6f);




        // create menu items;

        for (int i= 0; i<titles.length;i++){
            ResideMenuItem item = new ResideMenuItem(this,icon[i],titles[i]);
            final int finalI = i;
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragchange(finalI);
                }
            });
            resideMenu.addMenuItem(item,ResideMenu.DIRECTION_LEFT);
        }
        // You can disable a direction by setting ->
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
    }
/*
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }*/



    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }


    void fragchange(int position){
        Fragment fragment;
        CustomTitle(titles[position]);
        if (position==0) {
            fragment = new Home();
            CustomTitle("Brainstormer 15");

        }
        else if (position==1)
            fragment = new RulesFragment();
        else if (position==2)
            fragment= new DetailsFragment();
        else if (position==3)
            fragment = new GalleryFragment();
        else if (position==4)
            fragment = new AboutFragment();
        else if (position==5)
            fragment= new SponsorFragment();
        else if (position==6)
            fragment = new RegistrationFragment();
        else if (position==7) {

            fragment= new QuizFragment();


        }

        else  {
            fragment = new ProfileFragment();

        }
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, fragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
        if (getResideMenu().isOpened())
        resideMenu.closeMenu();


    }

    private void CustomTitle(String ti) {
        TextView tv;
        tv = (TextView) findViewById(R.id.title);
        tv.setText(ti);
    }


    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (resideMenu.isOpened())
            resideMenu.closeMenu();
        else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please press back again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    public void hideBar(){
    toolbar = (Toolbar) findViewById(R.id.layout_top);
    if (toolbar!=null)
//    toolbar.setVisibility(View.INVISIBLE);
    toolbar.setBackgroundColor(Color.parseColor("#000000"));

}

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }

    public void changefrag(){
        Fragment fragment;
        fragment = new ProfileFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, fragment)
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();


    }
}
