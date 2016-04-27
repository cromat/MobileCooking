package com.pingvini.mobilecooking.model;

import android.media.Image;

import java.util.List;

/**
 * Created by mat on 28.04.16..
 */
public class Recipe {

    private String name;
    private String description;
    private List<String> ingredients;
    private Image image;
    private double rating;
    private int votes;
    private int userId;

    public Recipe(String name, String description, List<String> ingredients, Image image, double rating, int votes, int userId) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.rating = rating;
        this.votes = votes;
        this.userId = userId;
        this.ingredients = ingredients;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
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

    public void setUserId(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
