package com.example.veryness.Sprites_movements;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.veryness.R;
import com.example.veryness.main.Actor;
import com.example.veryness.workingfragments.MainFragment;

import java.util.ArrayList;
import java.util.List;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    Resources resources;
    Bitmap img, sprite_image; SurfaceThread thread;
    Paint paint;
    MainFragment fragment;
    float currentx=0, currenty=0, stepx=0, stepy=0, touchx, touchy;
    float width, height;
    boolean touchevent=false;
    int columna;
    int rowa;
    //Sprites sprite;
    ArrayList<Sprites> sprite = new ArrayList<Sprites>();

    @SuppressLint("WrongViewCast")
    public MySurfaceView(Context context,MainFragment fragment) {
        super(context);
        getHolder().addCallback(this);
        this.fragment=fragment;
        resources = getResources();
        img = BitmapFactory.decodeResource(resources, R.drawable.m);
        //sprite_image = BitmapFactory.decodeResource(resources, R.drawable.sprites);
        setFocusable(true);
        //sprite = new Sprites (this, sprite_image, currentx, currenty);
        //sprite.add(0, new Sprites (this, sprite_image, currentx, currenty));
    }


    public void setSprite_image(Bitmap sprite_image,int columns,int rows) {
        this.sprite_image = sprite_image;
        this.columna=columns;
        this.rowa=rows;
    }

    public ArrayList<Sprites> getSprite() {
        return sprite;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new SurfaceThread(getHolder(), this);
        thread.start();
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }
    void checkWall(){
        if (currentx <=0 || currentx+img.getWidth()>=width) stepx=-stepx;
        if (currenty <=0 || currenty+img.getHeight()>=height) stepy=-stepy;
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry  = true; thread.runBool = false;
        while (retry){
            try {
                thread.join(); retry=false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) { //информация о касании event
        int j;
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            touchevent = true;
            touchx = event.getX();
            touchy = event.getY();
            j=sprite.size();
            if(sprite_image!=null){
           // for (j=0; j<sprite.size(); j++){
                if (j != 0) {
                    if(sprite.get(j-1)!=null){
                        sprite.get(j-1).setTarget(touchx, touchy);//}
                    }
                }
            if(j%2==0){
                sprite.add(j, new Sprites(this, sprite_image, touchx, touchy,columna,rowa));
            addItem(new Actor("actor",  Bitmap.createBitmap(sprite_image, 0, 0, sprite_image.getWidth() / columna, sprite_image.getHeight() / rowa),0,100));}
            else{sprite.add(null);}
            }

            /*
            stepx = (touchx - currentx)/width*100;// расчет скоростей
            // (float) Math.sqrt((touchy-currenty)*(touchy-currenty)+(touchx-currentx)*(touchx-currentx));
            stepy = (touchy - currenty)/height*100;//(float) Math.sqrt((touchy-currenty)*(touchy-currenty)+(touchx-currentx)*(touchx-currentx));
        */
        }

        return true;
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        width = canvas.getWidth();
        height = canvas.getHeight();
        canvas.drawARGB(255, 255, 255, 255);
        /*if (!touchevent){
            currentx = width/2 - img.getWidth()/2;
            currenty = height/2 - img.getHeight()/2;}
        else{
            currentx += stepx;
            currenty += stepy;
        }
        canvas.drawBitmap(img, currentx, currenty, paint);
        checkWall();*/

        for (int i=0; i<sprite.size(); i++){
            if(sprite.get(i)!=null){
            sprite.get(i).draw(canvas);  } //direction 3 вверх 0 вниз 2вправо 1 влево
        }
    }

    public class Sprites {
        MySurfaceView surfaceview;
        Bitmap image;
        float currentx, currenty, targetx, targety, speedx = 0, speedy = 0;
        int columns, rows , width_1, height_1;
        Paint paint= new Paint();
        int currentFrame=0, direction=0;
        int cadrx, cadry;


        public Sprites (MySurfaceView surfaceview, Bitmap image, float x, float y,int columns,int rows) {
            this.surfaceview = surfaceview;
            this.image = image;
            currentx = x;
            currenty = y;
            this.columns=columns;
            this.rows=rows;
            width_1 = image.getWidth() / columns;
            height_1 = image.getHeight() / rows;
        }

        void draw(Canvas canvas) {
            currentx += speedx;
            currenty += speedy;
            checkWall();
            currentFrame = ++currentFrame % columns;
            cadrx = currentFrame * width_1;
            cadry = direction * height_1;
            Rect src = new Rect(cadrx, cadry, cadrx+width_1, cadry+height_1);
           // Rect dst = new Rect((int) currentx, (int) currenty, (int) currentx + width_1, (int) currenty + height_1);
            Rect dst = new Rect((int) currentx, (int) currenty, (int) currentx + 250, (int) currenty + 250);
            canvas.drawBitmap(image, src, dst, paint);
        }

        public void setTarget(float targetx, float targety) {
            this.targetx = targetx;
            this.targety = targety;
            speedx = (targetx - currentx) / surfaceview.getWidth() * 50;
            speedy = (targety - currenty) / surfaceview.getHeight() * 50;
            if (speedy/speedx>1 ) {direction=3;}
            else {if (speedy/speedx<1 & speedy/speedx>-1) direction=0;
            else {
                if (speedy/speedx<-1){direction=1;}
            }}
        }

        void checkWall(){
            if (currentx <=0 || currentx+width_1>=surfaceview.getWidth()) speedx=-speedx;
            if (currenty <=0 || currenty+height_1>=surfaceview.getHeight()) speedy=-speedy;
        }
    }

    public void addItem(Actor item) {
        fragment.addItem(item);
    }


}