package com.example.veryness.main;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class Actor implements Parcelable {
    private String name;
    private transient int id;
    private Bitmap picture;
    private int width;
    private int time_appearance;
    private int time_disappearance;

    protected Actor(Parcel in) {
        Bundle bundle = in.readBundle(getClass().getClassLoader());
        assert bundle != null;
        name = bundle.getString("name");
        picture = bundle.getParcelable("picture");
        width = bundle.getInt("width");
        time_appearance = bundle.getInt("appearance");
        time_disappearance = bundle.getInt("disappearance");
    }

    public static final Creator<Actor> CREATOR = new Creator<Actor>() {
        @Override
        public Actor createFromParcel(Parcel in) {
            return new Actor(in);
        }

        @Override
        public Actor[] newArray(int size) {
            return new Actor[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Actor(String name, Bitmap picture, int time_appearance, int time_disappearance, int width) {
        this.name = name;
        this.picture = picture;
        this.time_appearance = time_appearance;
        this.time_disappearance = time_disappearance;
        this.width = width;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putParcelable("picture", picture);
        bundle.putInt("width", width);
        bundle.putInt("appearance", time_appearance);
        bundle.putInt("disappearance", time_disappearance);
    }
}