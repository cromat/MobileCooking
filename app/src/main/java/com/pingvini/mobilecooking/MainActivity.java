package com.pingvini.mobilecooking;

import android.app.ActionBar;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.pingvini.mobilecooking.adapter.FragmentRecipesAdapter;
import com.pingvini.mobilecooking.model.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<Recipe> recipes;
    private FragmentRecipesAdapter fragmentRecipesAdapter;
    private ActionBar actionBar;
    private ViewPager mViewPager;
    private TabLayout tabRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipes = new ArrayList<Recipe>();

        try {
            // Initializing Parse connection
            Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                    .applicationId("pingvini")
                    .server("http://pingvini-mbcooking.rhcloud.com/parse/")
                    .build()
            );
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabRecipes = (TabLayout) findViewById(R.id.tab_recipes);
        tabRecipes.addTab(tabRecipes.newTab().setText("New"));
        tabRecipes.addTab(tabRecipes.newTab().setText("Popular"));
        tabRecipes.addTab(tabRecipes.newTab().setText("Your"));
        tabRecipes.setTabGravity(TabLayout.GRAVITY_FILL);


        tabRecipes.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.select();
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        fragmentRecipesAdapter = new FragmentRecipesAdapter(getSupportFragmentManager(), tabRecipes.getTabCount());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager_recipes);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position){
                super.onPageSelected(position);
                TabLayout.Tab tab = tabRecipes.getTabAt(position);
                tab.select();
            }
        };
        mViewPager.setAdapter(fragmentRecipesAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(getApplicationContext() ,LoginActivity.class));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id){
            case R.id.action_settings:
                return true;
            case R.id.action_add_recipe:
                startActivity(new Intent(getApplicationContext(), RecipeAddActivity.class));
                return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
