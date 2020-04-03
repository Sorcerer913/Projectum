package com.example.veryness;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.InputStream;

public class ObjectFragment extends Fragment implements View.OnClickListener {
    Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert container != null;
        button=container.findViewById(R.id.button);

        return inflater.inflate(R.layout.objfragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button firstobject=(Button)getView().findViewById(R.id.firstobject);
        firstobject.setOnClickListener(this);

    }
    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v){
        InputStream puter;
        Bitmap tr;
        switch (v.getId()){
            case R.id.firstobject:
                puter=getResources().openRawResource(R.drawable.download);
                tr= BitmapFactory.decodeStream(puter);
                MainFragment1 fragment1=(MainFragment1) getFragmentManager().findFragmentById(R.id.container);
                Surfaceanim viewer= (Surfaceanim) fragment1.getView();
                if(tr!=null){
                    viewer.setPicture(tr);}
                break;
        }

    }

}
