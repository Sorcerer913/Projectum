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
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.veryness.R;
import com.example.veryness.main.Actor;
import com.example.veryness.workingfragments.AddingFragment;
import com.example.veryness.workingfragments.MainFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static android.os.Looper.prepare;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    Resources resources;
    Bitmap img, sprite_image; SurfaceThread thread;
    Paint paint;
    MainFragment fragment;
    AddingFragment addingFragment;
    float currentx=0, currenty=0, stepx=0, stepy=0, touchx, touchy;
    float width, height;
    boolean touchevent=false;
    boolean mTimerRunning;
    CountDownTimer countDownTimer;

    int count=0;
    int columna;
    int touch_count=0;
    int start_state;
    private static final long  START_TIME_IN_MILES=300000;
    private long  mTimeleftinmills;
    int rowa;
    int st=0;
    boolean touchable=true;
    LinkedList <LinkedList> coders=new LinkedList<LinkedList>();
    LinkedList<CountDownTimer> timers=new LinkedList<>();
    LinkedList<Integer> counts=new LinkedList<Integer>();
    //Sprites sprite;
    ArrayList<Sprites> sprite = new ArrayList<Sprites>();
    int povt=0;

    @SuppressLint("WrongViewCast")
    public MySurfaceView(Context context,MainFragment fragment) {
        super(context);
        getHolder().addCallback(this);
        this.fragment=fragment;
        resources = getResources();
        start_state=0;

        img = BitmapFactory.decodeResource(resources, R.drawable.m);
        //sprite_image = BitmapFactory.decodeResource(resources, R.drawable.sprites);
        setFocusable(true);
        //sprite = new Sprites (this, sprite_image, currentx, currenty);
        //sprite.add(0, new Sprites (this, sprite_image, currentx, currenty));
    }

        public void setFragment(MainFragment fragment){
        this.fragment=fragment;
        }

    public SurfaceThread getThread() {
        return thread;
    }

    public void setThread(SurfaceThread thread) {
        this.thread = thread;
    }
    public void setStart_state(int state){
        this.start_state=state;
    }
    public void setAddingFragment(AddingFragment addingFragment){
                this.addingFragment=addingFragment;
    }
    public void setSprite_image(Bitmap sprite_image, int columns, int rows) {
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
        if(touchable) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                touchevent = true;
                touchx = event.getX();
                touchy = event.getY();
                j = sprite.size();
                if (touch_count == 0) {
                    createTimer();
                    Log.v("CREATED","true");
                }
                if (sprite_image != null) {
                    // for (j=0; j<sprite.size(); j++){
                    if ((j != 0) && touch_count % 2 != 0) {
                        if (sprite.get(j - 1) != null) {
                            sprite.get(j - 1).setTarget(touchx, touchy);//}
                        }
                    }
                    if (touch_count % 2 == 0) {
                        addItem(new Actor("actor", Bitmap.createBitmap(sprite_image, 0, 0, sprite_image.getWidth() / columna, sprite_image.getHeight() / rowa), 0, 300, Objects.requireNonNull(fragment.getView()).getWidth()));
                        sprite.add(j, new Sprites(this, sprite_image, touchx, touchy, columna, rowa, touchx, touchy));
                        addingFragment.setItems(fragment.getItems());
                        touch_count++;
                    } else {
                        touch_count++;
                    }
                }

            /*
            stepx = (touchx - currentx)/width*100;// расчет скоростей
            // (float) Math.sqrt((touchy-currenty)*(touchy-currenty)+(touchx-currentx)*(touchx-currentx));
            stepy = (touchy - currenty)/height*100;//(float) Math.sqrt((touchy-currenty)*(touchy-currenty)+(touchx-currentx)*(touchx-currentx));
        */
            }

            return true;
        }else{
            return true;
        }
    }

    public void isTouchable(boolean touchable){
        this.touchable=touchable;
    }
    @Override
    public void draw(Canvas canvas) {

        super.draw(canvas);
        width = getWidth();
        height = getHeight();
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

        for (int i=0; i<sprite.size(); i++) {
            if(coders.size()!=0){
            if(addingFragment.getItems().size()==sprite.size()) {
                    if ((addingFragment.getItems().get(i) != null) && (addingFragment.getItems().size() != 0)) {
                        sprite.get(i).timeappearance = addingFragment.getItems().get(i).getTime_appearance();
                        sprite.get(i).timedisappearance = addingFragment.getItems().get(i).getTime_disappearance();
                        if (sprite.get(i) != null) {
                            if ((sprite.get(i).timeappearance <=(int)coders.getLast().getLast()) && (sprite.get(i).timedisappearance >=(int)coders.getLast().getLast())){
                                if (start_state == 1) {
                                    sprite.get(i).startOption(sprite.get(i));
                                }
                                sprite.get(i).draw(canvas);
                                Log.v("Time_APPEarance", String.valueOf(sprite.get(i).timeappearance));
                                Log.v("Time_DISAPPEarance", String.valueOf(sprite.get(i).timedisappearance));
                                Log.v("TIMER", String.valueOf((int)coders.getLast().getLast()));
                            } //direction 3 вверх 0 вниз 2вправо 1 влево
                        }
                    }
            }else{
                addingFragment.getItems().add(null);
            }
        if((int)coders.getLast().getLast()==300){
           // counts.clear();
            povt++;
            countDownTimer.onFinish();
            mTimeleftinmills=START_TIME_IN_MILES;
        }
 }}
        start_state=0;
    }

    public  long getStartTimeInMiles() {
        return START_TIME_IN_MILES;
    }


    public long getmTimeleftinmills() {
        return mTimeleftinmills;
    }

    public void setmTimeleftinmills(long mTimeleftinmills) {
        this.mTimeleftinmills = mTimeleftinmills;
    }

    public void createTimer( ){

        //coders.add(povt,crot);
            countDownTimer=new CountDownTimer(mTimeleftinmills,1000) {
            int counter;
            LinkedList<Integer>crot=new LinkedList<>();
            int cr=povt;
           @Override
            public void onTick(long millisUntilFinished) {
               mTimeleftinmills=millisUntilFinished;
               counter=(int)(START_TIME_IN_MILES-mTimeleftinmills)/1000;
               crot.add(counter);
               if(!coders.contains(crot)){
                   coders.addLast(crot);
               }
               // counts.add(counter);
               if(counter==(START_TIME_IN_MILES)/1000-2){
                   onFinish();
               }}
            @Override
            public void onFinish() {
                mTimeleftinmills=START_TIME_IN_MILES;
                mTimerRunning=false;
                povt++;
                st=0;
                try{
                    createTimer();
                }catch (Exception er){
                    er.printStackTrace();
                }

            }
        }.start();
       ;
        mTimerRunning=true;
    }
    public void finishTimer( ){
        povt++;
        countDownTimer.onFinish();
        mTimeleftinmills=START_TIME_IN_MILES;
      //counts= new LinkedList<Integer>();
    }

    public class Sprites {
        MySurfaceView surfaceview;
        Bitmap image;
        float currentx, currenty, targetx, targety, speedx = 0, speedy = 0;
        int columns, rows , width_1, height_1;
        float Startspeedx,Startspeedy;
        float startX,startY;
        int timeappearance,timedisappearance;
        Paint paint= new Paint();
        int currentFrame=0, direction=0;
        int cadrx, cadry;


        public Sprites (MySurfaceView surfaceview, Bitmap image, float x, float y,int columns,int rows,float startX,float startY) {
            this.surfaceview = surfaceview;
            this.image = image;
            currentx = x;
            currenty = y;
            this.columns=columns;
            this.rows=rows;
            this.startX=startX;
            this.startY=startY;
            width_1 = image.getWidth() / columns;
            height_1 = image.getHeight() / rows;
        }

        public void startOption(Sprites sprite){
            sprite.currentx=sprite.startX;
            sprite.currenty=sprite.startY;
            sprite.speedx=sprite.Startspeedx;
            sprite.speedy=sprite.Startspeedy;
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
            Startspeedx=(targetx - startX) / surfaceview.getWidth() * 50;
            Startspeedy=(targety - startY) / surfaceview.getWidth() * 50;
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