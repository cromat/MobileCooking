package com.pingvini.mobilecooking.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.pingvini.mobilecooking.R;
import com.pingvini.mobilecooking.adapter.RecipeAdapter;
import com.pingvini.mobilecooking.enums.RecipeTypeEnum;
import com.pingvini.mobilecooking.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Cvitas on 30.4.2016..
 */
public class RecipesFragment extends Fragment {

    protected static List<Recipe> recipes;
    protected static RecipeAdapter recipeAdapter;
    protected RecyclerView recyclerRecipesView;
    protected RecyclerView.LayoutManager layoutRecipes;
    protected ViewPager mViewPager;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static RecipesFragment newInstance(int sectionNumber, RecipeTypeEnum recipeType) {

        RecipesFragment fragment = new RecipesFragment();
        Bundle args = new Bundle();

        InitRecipesData(recipeType);
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipes, container, false);
        recipes = new ArrayList<Recipe>();

        recyclerRecipesView = (RecyclerView) rootView.findViewById(R.id.recycler_recipes);
        layoutRecipes = new LinearLayoutManager(getActivity());
        recyclerRecipesView.setLayoutManager(layoutRecipes);
        recipeAdapter = new RecipeAdapter(recipes);
        recyclerRecipesView.setAdapter(recipeAdapter);
        recipeAdapter.notifyItemInserted(recipes.size());

        return rootView;
    }


    public static void InitRecipesData(RecipeTypeEnum recipeType) {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Recipes");

        switch (recipeType)
        {
            case New:
                query.addDescendingOrder("createdAt");
                break;
            case Popular:
                query.addDescendingOrder("votes");
                break;
            case Own:
                query.whereEqualTo("userId", ParseUser.getCurrentUser().getObjectId());
                break;
        }

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> recipeObjects, ParseException e) {
                if (e == null) {

//                    ParseObject.deleteAllInBackground(recipeObjects);

                    for (ParseObject recipeObject : recipeObjects) {
                        Recipe recipe = new Recipe();
                        recipe.setName(recipeObject.getString("name"));
                        recipe.setDescription(recipeObject.getString("description"));

//                        List<String> ingredients = Arrays.asList(recipeObject.getString("ingredients")
//                                .split(","));

//                        recipe.setIngredients(ingredients);
                        //TODO Provjeriti kako se image sprema i dohvaca

                        recipe.setRating((float) recipeObject.getDouble("rating"));
                        recipe.setVotes(recipeObject.getInt("votes"));
                        recipe.setUserId(recipeObject.getInt("userId"));
                        recipe.setId(recipeObject.getString("id"));
                        //recipe.setDateCreated(recipeObject.getDate("createdAt"));

                        recipes.add(recipe);
                        recipeAdapter.notifyItemInserted(recipes.size());
                    }
                } else {
                    // handle Parse Exception here
                }
            }
        });
    }


}
