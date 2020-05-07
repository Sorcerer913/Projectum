package com.example.veryness.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.veryness.R;
import com.example.veryness.workingfragments.AddingFragment;
import com.example.veryness.workingfragments.MainFragment;
import com.example.veryness.workingfragments.ObjectFragment;
import com.example.veryness.workingfragments.RecordingFragment;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

/* Hi rhere
It is me, you classmate
Do you remember me......shit
Ok,then,we have some problens
I have adde a few buttond in fragments
Your goal is to make this buttons work
they must activate your fragment SurfaceView when you press on them
good luck!! */

    Button button1;
    Button button2;
    Button button3;
    public static int SPLASH_TIME_OUT=2000;
    private List<Actor> items;
    private ActionBar actionBar;
    public RecordingFragment recfragment=RecordingFragment.newInstance();
    public AddingFragment funfragment=AddingFragment.newInstance();
    public ObjectFragment objectFragment=ObjectFragment.newInstance();
    public MainFragment fragment=MainFragment.newInstance();

    //record zone
    private static final int REQUEST_PERMISSIONS = 10;
    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commitNow();
       // if(savedInstanceState == null){
            //buildFragment(0);
       // }else{
            //TODO: savedInstanceState
       // }
        items=new ArrayList<>();
        actionBar=getSupportActionBar();
        button1 = findViewById(R.id.objects);
        button2 = findViewById(R.id.addings);
        button3 = findViewById(R.id.records);
        button1.setOnClickListener(myOnClickListener);


        button2.setOnClickListener(new View.OnClickListener() {
            int j=0;
            @Override
            public void onClick(View v) {
                if(j%2==0){
                    if(findViewById(R.id.fragmentContainer)!=null){
                        removeFragment();
                    }
                    addFragment("fun");
                    j++;
                }else{
                        removeFragment();
                        j++;

                }

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            int d;
            @Override
            public void onClick(View v) {
                if(d%2==0){
                    if(findViewById(R.id.fragmentContainer)!=null){
                        removeFragment();
                    }
                    addFragment("rec");
                    d++;
                }else{
                        removeFragment();
                        d++;
                }
            }
        });

        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.RECORD_AUDIO)+
                ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                + ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)
                !=
        PackageManager.PERMISSION_GRANTED){
            //When permission not granted
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)||
                    ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.RECORD_AUDIO)||
                    ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)
            ){
                //create AlertDialog
                AlertDialog.Builder builder= new AlertDialog.Builder(
                        MainActivity.this
                );
                builder.setTitle("Дайте,пожалуйста,разрешения");
                builder.setMessage("Приложению нужен доступ к памяти и к микрофону");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{
                                        Manifest.permission.RECORD_AUDIO,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.READ_EXTERNAL_STORAGE
                                },
                                REQUEST_CODE);
                    }
                });
                builder.setNegativeButton("Cancel",null);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }else{
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{
                                Manifest.permission.RECORD_AUDIO,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        },
                        REQUEST_CODE);

            }

        }else{
            //When permissions are already granted
            Toast.makeText(getApplicationContext(),"Permissions are alredy granted..",Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CODE){
            if((grantResults.length>0)&&(grantResults[0]+grantResults[1]+grantResults[2]==PackageManager.PERMISSION_GRANTED)){
                //permissions are granted
                Toast.makeText(getApplicationContext(),"Permissions GRANTED..",Toast.LENGTH_SHORT).show();
            }else{
               // permissions are denied
                Toast.makeText(getApplicationContext(),"Permissions DENIED..",Toast.LENGTH_SHORT).show();;

            }
        }
    }

    public void addFragment(String s){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        switch (s){
            case("obj"):
                fragmentTransaction.add(R.id.fragmentContainer,objectFragment);
                break;

            case("rec"):
                recfragment.setMainFragment(fragment,actionBar);
                fragmentTransaction.add(R.id.fragmentContainer,recfragment);
                break;

            case("fun"):
                items.clear();
                items.addAll(fragment.getItems());
                funfragment.setItems(items);
                fragmentTransaction.add(R.id.fragmentContainer,funfragment);
                break;

        }
        fragmentTransaction.commit();
    }

    public void setMainFragment(MainFragment mainFragment){
        this.fragment=mainFragment;
    }
    public void removeFragment(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment fragment=fragmentManager.findFragmentById(R.id.fragmentContainer);
        if(fragment!=null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
        }
    }

    View.OnClickListener myOnClickListener = new View.OnClickListener() {
        int i=0;
        @Override
        public void onClick(View v) {
            if(i%2==0){
                if(findViewById(R.id.fragmentContainer)!=null){
                    removeFragment();

                }
                addFragment("obj");
                i++;
            }else{
                if(findViewById(R.id.fragmentContainer)!=null){
                    removeFragment();
                    i++;
                }
            }



        }
    };


}
