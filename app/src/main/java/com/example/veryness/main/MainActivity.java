package com.example.veryness.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.veryness.R;
import com.example.veryness.workingfragments.AddingFragment;
import com.example.veryness.workingfragments.MainFragment;
import com.example.veryness.workingfragments.ObjectFragment;
import com.example.veryness.workingfragments.RecordingFragment;

import java.util.ArrayList;
import java.util.List;

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
    public RecordingFragment recfragment=RecordingFragment.newInstance();
    public AddingFragment funfragment=AddingFragment.newInstance();
    public ObjectFragment objectFragment=ObjectFragment.newInstance();
    public MainFragment fragment=MainFragment.newInstance();
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

    }
    public void addFragment(String s){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        switch (s){
            case("obj"):
                fragmentTransaction.add(R.id.fragmentContainer,objectFragment);
                break;

            case("rec"):
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
            /*switch (v.getId()){
                case R.id.objects:
                    buildFragment(0);
                    break;
                case R.id.addings:
                    buildFragment(1);
                    break;
                case R.id.records:
                    buildFragment(2);
                    break;
            } */



        }
    };


    private void buildFragment(int fragmentNum){
        switch (fragmentNum){
            case 0:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, MainFragment.newInstance())
                        .commitNow();
                break;
//            case 1:   //TODO: make fragment     ("CharacterChoosingFragment")
//                break;
//            case 2:   //TODO: make fragment     ("ExportFragment")
//                break;
//            //TODO: make other fragments
        }
    }

}
