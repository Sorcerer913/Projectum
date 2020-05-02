package com.example.veryness.workingfragments;

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

public class RecordingFragment extends Fragment {
   private ImageButton recordvideo;
   private RecordingFragment fragment;

    public static RecordingFragment newInstance() {
        RecordingFragment fragment = new RecordingFragment();
        fragment.setFragment(fragment);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recordvideo=(ImageButton)view.findViewById(R.id.recordvideo);


    }


}
