package com.example.veryness.main;

import android.icu.text.TimeZoneFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.veryness.R;
import com.example.veryness.workingfragments.MainFragment;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainFragment fragmentofvideo;
    private ActionBar actionBar;
    private FragmentManager fragmentManager;

    public VideoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment VideoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoFragment newInstance() {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void setFragmentofVideo(MainFragment fragmentofVideo,ActionBar actionBar){
        this.actionBar=actionBar;
        this.fragmentofvideo=fragmentofVideo;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager= Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        //Log.v("videofragmentadding","1     "+fragmentofvideo.toString());
        Log.v("videofragmentadding","2    "+fragmentManager.toString());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert actionBar != null;
        actionBar.hide();
        //fragmentManager.beginTransaction().add(R.id.fragmentofvideo,fragmentofvideo).addToBackStack(null).commit();
    }
}
