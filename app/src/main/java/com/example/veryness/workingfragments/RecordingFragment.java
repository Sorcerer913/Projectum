package com.example.veryness.workingfragments;

import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.veryness.R;
import com.example.veryness.main.VideoFragment;

public class RecordingFragment extends Fragment {
   private ImageButton recordvideo;
   private RecordingFragment fragment;
   private ImageButton res;
   private MainFragment mainFragment;
   private ActionBar actionBar;
   private VideoFragment videoFragment=VideoFragment.newInstance();

    public static RecordingFragment newInstance() {
        RecordingFragment fragment = new RecordingFragment();
        fragment.setFragment(fragment);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void setMainFragment(MainFragment fragment, ActionBar actionBar){this.mainFragment=fragment;
    this.actionBar=actionBar;
    }


    public void  setFragment(RecordingFragment fragment){
        this.fragment = fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert container != null;
       // button=container.findViewById(R.id.button);
        return inflater.inflate(R.layout.recfragment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public VideoFragment getVideoFragment() {
        return videoFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recordvideo=(ImageButton)view.findViewById(R.id.recordvideo);
        res=(ImageButton)view.findViewById(R.id.restart);
        recordvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                videoFragment.setFragmentofVideo(mainFragment,actionBar);
                mainFragment.getMySurfaceView().isTouchable(false);
                videoFragment.setFragment(videoFragment);
                getFragmentManager().beginTransaction().add(R.id.videorecording,videoFragment).commit();

            }
        });
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainFragment.getMySurfaceView().setStart_state(1);
                mainFragment.getMySurfaceView().finishTimer();
            }
        });


    }


}
