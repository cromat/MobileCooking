package com.pingvini.mobilecooking.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.pingvini.mobilecooking.R;
import com.pingvini.mobilecooking.model.Recipe;

import java.util.List;

/**
 * Created by mat on 28.04.16..
 */
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{

    private static List<Recipe> recipes;

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder rvh, int i) {
        rvh.rating.setRating(recipes.get(i).getRating());
        rvh.image.setImageBitmap(recipes.get(i).getImage());
        rvh.textName.setText(recipes.get(i).getName());
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textName;
        public ImageView image;
        public RatingBar rating;
        public Context context;

        public RecipeViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);

            textName = (TextView)view.findViewById(R.id.text_item_name);
            image = (ImageView)view.findViewById(R.id.image_item_recipe);
            rating = (RatingBar)view.findViewById(R.id.rating_item_recipe);
            context = itemView.getContext();
        }

        @Override
        public void onClick(View v) {

        }

    }

}
