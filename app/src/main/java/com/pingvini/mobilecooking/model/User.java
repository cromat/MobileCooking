package com.pingvini.mobilecooking.model;

import android.media.Image;

import java.util.Date;

/**
 * Created by mat on 28.04.16..
 */
public class User {

    private String username;
    private String email;
    private Date dateReg;
    private Image image;

    public User(String username, String email, Image image, Date dateReg) {
        this.username = username;
        this.email = email;
        this.image = image;
        this.dateReg = dateReg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
