package com.example.veryness.workingfragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.veryness.Sprites_movements.MySurfaceView;
import com.example.veryness.main.Actor;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private static Context context;
    private MainFragment fragment;
    MySurfaceView mySurfaceView;
    private List<Actor> items = new ArrayList<>();

    private void setFragment(MainFragment fragment) {
        this.fragment = fragment;
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        fragment.setFragment(fragment);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       // Surfaceanim screen=new Surfaceanim(super.getContext());
        super.onCreate(savedInstanceState);
        try{
            boolean j=mySurfaceView.isActivated();
        }catch (NullPointerException er){
            mySurfaceView=new MySurfaceView(super.getContext(),fragment);
        }
        return mySurfaceView;
        //return inflater.inflate(R.layout.main_fragment, container, false);
    }

    public MySurfaceView getMySurfaceView() {
        return mySurfaceView;
    }

    public void setItems(List<Actor> items) {
        this.items = items;
    }

    public void setMySurfaceView(MySurfaceView mySurfaceView) {
        this.mySurfaceView=mySurfaceView;
    }

    public void addItem(Actor item){
        items.add(item);
    }

    public List<Actor> getItems() {
        return items;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //m
        // TODO: Use the ViewModel
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}


