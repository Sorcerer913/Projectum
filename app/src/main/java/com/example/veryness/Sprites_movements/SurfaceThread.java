package com.example.veryness.Sprites_movements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.jcodec.api.android.AndroidSequenceEncoder;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.Rational;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SurfaceThread extends Thread {
    final SurfaceHolder Holder;
    MySurfaceView view;
    List<Bitmap> frames;
    long Time;
    int key;
    boolean runBool = true;

    public SurfaceThread(SurfaceHolder holder, MySurfaceView view) { //constructor
        Holder = holder;
        this.key=0;
        frames=new ArrayList<>();
        this.view = view; //remember полученные переменные to local
        Time = System.currentTimeMillis();
    }

    @Override
    public void run() {
        while (runBool) {
            //super.run();
            Canvas canvas = null;
            long Time0 = System.currentTimeMillis();
            long Time_ = Time0 - Time;

            if (Time_ > 100) {
                Time = Time0;
                if(Holder!=null){
                    canvas = Holder.lockCanvas();
                    synchronized (Holder) {
                        if(canvas!=null){
                        this.view.draw(canvas);
                        if(key==1){
                            frames.add(getBitmapFromView(this.view));
                            frames.add(getBitmapFromView(this.view));
                            frames.add(getBitmapFromView(this.view));
                            frames.add(getBitmapFromView(this.view));
                            try {
                                sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        }

                        }
                    }

                    if (canvas != null) Holder.unlockCanvasAndPost(canvas);}
            }
        }


                // Generate the image, for Android use Bitmap
        // Finalize the encoding, i.e. clear the buffers, write the header, etc.



    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }
    public void setKey(int key){
        this.key=key;
    }

    public List<Bitmap> getFrames() {
        return frames;
    }
}