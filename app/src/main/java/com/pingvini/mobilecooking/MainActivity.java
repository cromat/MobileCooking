package com.pingvini.mobilecooking;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
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

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipes = new ArrayList<Recipe>();

        // Initializing Parse connection
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                        .applicationId("pingvini")
                        .server("http://pingvini-mbcooking.rhcloud.com/parse/")
                        .build()
        );

        // Getting all recipe objects from Parse
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Recipes");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> recipeObjects, ParseException e) {
                if (e == null) {

//                    ParseObject.deleteAllInBackground(recipeObjects);

                    for (ParseObject recipeObject : recipeObjects) {
                        Recipe recipe = new Recipe();
                        recipe.setName(recipeObject.getString("name"));
                        recipe.setDescription(recipeObject.getString("description"));

//                        List<String> ingredients = Arrays.asList(recipeObject.getString("ingredients")
//                                .split(";"));

//                        recipe.setIngredients(ingredients);
                        //TODO Provjeriti kako se image sprema i dohvaca

                        recipe.setRating((float) recipeObject.getDouble("rating"));
                        recipe.setVotes(recipeObject.getInt("votes"));
                        recipe.setUserId(recipeObject.getInt("userId"));
                        recipe.setId(recipeObject.getString("id"));

                        recipes.add(recipe);
                    }
                } else {
                    // handle Parse Exception here
                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabRecipes = (TabLayout) findViewById(R.id.tab_recipes);
        tabRecipes.addTab(tabRecipes.newTab().setText("New"));
        tabRecipes.addTab(tabRecipes.newTab().setText("Popular"));
        tabRecipes.addTab(tabRecipes.newTab().setText("Your"));
        tabRecipes.setTabGravity(TabLayout.GRAVITY_FILL);

        tabRecipes.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
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
        mViewPager.setAdapter(fragmentRecipesAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
