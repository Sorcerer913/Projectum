package com.example.veryness.workingfragments;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.veryness.R;
import com.example.veryness.Sprites_movements.MySurfaceView;

import java.io.InputStream;

public class ObjectFragment extends Fragment implements View.OnClickListener {
    private static String TAG = "Picture";
    Button button;
    Bitmap mpy;
    Bitmap bir;
    Bitmap fen;
    Drawable d;
    Drawable e;
    Drawable f;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert container != null;
        button = container.findViewById(R.id.button);
         mpy=BitmapFactory.decodeResource(getResources(),R.drawable.sprites);
         bir=BitmapFactory.decodeResource(getResources(),R.drawable.birds);
         fen=BitmapFactory.decodeResource(getResources(),R.drawable.spreee);
         e=new BitmapDrawable(getResources(),Bitmap.createBitmap(bir,0,0,bir.getWidth()/5,bir.getHeight()/3));
         d = new BitmapDrawable(getResources(),Bitmap.createBitmap(mpy,0,0,mpy.getWidth()/3,mpy.getHeight()/4) );
         f=new BitmapDrawable(getResources(),Bitmap.createBitmap(fen,0,0,fen.getWidth()/4,fen.getHeight()/4));
            return inflater.inflate(R.layout.objfragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button firstobject = (Button) getView().findViewById(R.id.firstobject);
        Button secondobject=(Button) getView().findViewById(R.id.secondobject);
        Button thirdobject=(Button) getView().findViewById(R.id.thirdobject);
        secondobject.setBackground(e);
        firstobject.setBackground(d);
        thirdobject.setBackground(f);
        firstobject.setOnClickListener(this);
        secondobject.setOnClickListener(this);
        thirdobject.setOnClickListener(this);

    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        InputStream puter;
        MainFragment fragment1 = (MainFragment) getFragmentManager().findFragmentById(R.id.container);
        MySurfaceView viewer= (MySurfaceView) fragment1.getView();
       // Bitmap tr;
        switch (v.getId()) {
            case R.id.firstobject:
                Log.v(TAG, "onClick");
                assert viewer != null;
                viewer.setSprite_image(mpy,3,4);
                break;
            case R.id.secondobject:
                Log.v(TAG, "onClick");
                assert viewer != null;
                viewer.setSprite_image(bir,5,3);
                break;
            case R.id.thirdobject:
                Log.v(TAG, "onClick");
                assert viewer != null;
                viewer.setSprite_image(fen,4,4);
                break;
        }

    }
}
