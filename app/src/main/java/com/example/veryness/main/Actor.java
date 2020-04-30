package com.example.veryness.main;

import android.graphics.Bitmap;

public class Actor{
   private String name;
   private int id;
   private Bitmap picture;
   private int time_appearance;
   private int time_disappearance;

   public Actor(String name, Bitmap picture, int time_appearance, int time_disappearance) {
       this.name = name;
       this.picture = picture;
       this.time_appearance = time_appearance;
       this.time_disappearance = time_disappearance;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public int getTime_appearance() {
        return time_appearance;
    }

    public void setTime_appearance(int time_appearance) {
        this.time_appearance = time_appearance;
    }

    public int getTime_disappearance() {
        return time_disappearance;
    }

    public void setTime_disappearance(int time_disappearance) {
        this.time_disappearance = time_disappearance;
    }
}
