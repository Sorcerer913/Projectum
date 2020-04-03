package com.example.veryness;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.veryness.main.Cellular;

import java.util.ArrayList;

public class Surfaceanim extends SurfaceView implements SurfaceHolder.Callback{
    float x=0;
    float y=0;
    Bitmap picture;

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public Surfaceanim(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new com.example.veryness.Surfaceanim.drawThread(holder).start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x=getX();
        y=getY();
        return super.onTouchEvent(event);
    }

    class drawThread extends Thread {
        private SurfaceHolder surfaceHolder;
        private volatile boolean running = true;
        Paint paint1 = new Paint();
        Paint paint2 = new Paint();
        ArrayList<Bitmap> er=new ArrayList<Bitmap>();

        public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
        }


        public drawThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
        }


        public void requestStop() {
            running = false;
        }

        @Override
        public void run() {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                canvas.drawColor(Color.WHITE);
                if(picture!=null && er.size()!=0){
                    Rect rect=new Rect(0,picture.getHeight(),picture.getWidth(),0);
                    canvas.drawBitmap(picture,rect,new Rect((int)x-100,(int)y-100,(int)x+100,(int)y+100),paint1);
                    er.add(picture);
                    }}else{
                    er.add(picture);
                }
                getHolder().unlockCanvasAndPost(canvas);

            }
        }
    }



