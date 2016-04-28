package com.pingvini.mobilecooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TabHost;
import android.widget.TextView;

import com.pingvini.mobilecooking.model.Recipe;

public class RecipeDetailsActivity extends AppCompatActivity {

    //Properties
    Recipe recipe;

    TabHost tabHost;
    ImageView image;
    TextView txtName;
    RatingBar ratingbar;
    TextView txtDescription;
    TextView txtIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        InitControls();
        GetRecipe();
        SetUpControls(recipe);

    }

    private void InitControls() {
        tabHost = (TabHost) findViewById(R.id.tabHostRecipeDetails);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("description");
        tabSpec.setContent(R.id.tabRecipeDescription);
        tabSpec.setIndicator("Description");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("ingredients");
        tabSpec.setContent(R.id.tabRecipeIngredients);
        tabSpec.setIndicator("Ingredients");
        tabHost.addTab(tabSpec);

        image = (ImageView)findViewById(R.id.recipe_details_image);
        txtName = (TextView)findViewById(R.id.recipe_details_name);
        ratingbar = (RatingBar)findViewById(R.id.recipe_details_ratingBar);
        txtDescription = (TextView)findViewById(R.id.recipe_details_description);
        txtIngredients = (TextView)findViewById(R.id.recipe_details_ingredients);
    }

    private void GetRecipe() {
        Intent i = getIntent();
        recipe = (Recipe)i.getSerializableExtra("recipe");
    }

    private void SetUpControls(Recipe recipe) {
        image.setImageBitmap(recipe.getImage());
        txtName.setText(recipe.getName());
        ratingbar.setRating(recipe.getRating());
        txtDescription.setText(recipe.getDescription());
        //txtIngredients.setText(recipe.getIngrediens());
    }
}
