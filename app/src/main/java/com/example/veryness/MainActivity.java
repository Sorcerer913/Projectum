package com.example.veryness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.veryness.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            buildFragment(0);
        }else{
            //TODO: savedInstanceState
        }

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button1.setOnClickListener(myOnClickListener);

    }

    View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button1:
                    buildFragment(0);
                    break;
                case R.id.button2:
                    buildFragment(1);
                    break;
                case R.id.button3:
                    buildFragment(2);
                    break;
            }
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
