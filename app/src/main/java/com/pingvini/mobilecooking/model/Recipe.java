package com.pingvini.mobilecooking.model;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.List;

/**
 * Created by mat on 28.04.16..
 */
public class Recipe {

    private String id;
    private String name;
    private String description;
    private List<String> ingredients;
    private Bitmap image;
    private float rating;
    private int votes;
    private int userId;

    public Recipe(String name, String description, List<String> ingredients, Bitmap image, float rating, int votes, int userId) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.rating = rating;
        this.votes = votes;
        this.userId = userId;
        this.ingredients = ingredients;
    }

    public Recipe(String id, String name, String description, List<String> ingredients, Bitmap image, float rating, int votes, int userId) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.rating = rating;
        this.votes = votes;
        this.userId = userId;
        this.ingredients = ingredients;
        this.id = id;
    }

    public Recipe(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<String> getIngrediens() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
