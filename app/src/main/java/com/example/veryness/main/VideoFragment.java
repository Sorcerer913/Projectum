package com.example.veryness.main;

import android.Manifest;
import android.app.AppComponentFactory;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.display.VirtualDisplay;
import android.icu.text.TimeZoneFormat;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;

import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.veryness.R;
import com.example.veryness.workingfragments.MainFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.jcodec.api.android.AndroidSequenceEncoder;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.Rational;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.LogRecord;

import static android.app.Activity.RESULT_OK;
import static android.os.Environment.DIRECTORY_PICTURES;

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
    private FloatingActionButton floatingActionButton;
    private MainFragment fragmentofvideo;
    private MainFragment newInstance;
    private ActionBar actionBar;
    private VideoFragment videoFragment;
    private ToggleButton mToggleButton;
    private FragmentManager fragmentManager;
    public File file;
    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE = 1000;
    private static final int REQUEST_PERMISSIONS = 10;


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

    private MainFragment recreateFragment(MainFragment f)
    {
        try {
            FragmentManager fragmentManager1= Objects.requireNonNull(getActivity()).getSupportFragmentManager();
            MainFragment.SavedState savedState = fragmentManager1.saveFragmentInstanceState(f);
            MainFragment newInstance = f.getClass().newInstance();
            newInstance.setItems(f.getItems());
            newInstance.setMySurfaceView(f.getMySurfaceView());
            newInstance.setInitialSavedState(savedState);
            return newInstance;
        }
        catch (Exception e) // InstantiationException, IllegalAccessException
        {
            throw new RuntimeException("Cannot reinstantiate fragment " + f.getClass().getName(), e);
        }
    }

    public void setFragmentofVideo(MainFragment fragmentofVideo,ActionBar actionBar){
        this.actionBar=actionBar;
        this.fragmentofvideo=fragmentofVideo;
    }
    public void setFragment(VideoFragment fragment){
        this.videoFragment=fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.v("videofragmentadding","1     "+fragmentofvideo.toString());
        fragmentManager= Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        Log.v("videofragmentadding","3    "+fragmentofvideo.toString());
        Log.v("videofragmentadding","2    "+fragmentManager.toString());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingActionButton=view.findViewById(R.id.floatingActionButton);
        final LoadingDialog loadingDialog=new LoadingDialog(videoFragment);
        //((MainActivity) getActivity()).setVideoFragment(videoFragment);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.remove(fragmentofvideo);
        newInstance = recreateFragment(fragmentofvideo);
        ft.add(R.id.fragmentofvideo, newInstance);
        ft.commit();
        assert actionBar != null;
        actionBar.hide();
        mToggleButton=view.findViewById(R.id.recbut);
        //recording
        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mToggleButton.isChecked()) {
                    fragmentofvideo.getMySurfaceView().getThread().setKey(1);
                } else {
                    fragmentofvideo.getMySurfaceView().getThread().setKey(0);
                    ArrayList<Bitmap> framers = (ArrayList<Bitmap>) fragmentofvideo.getMySurfaceView().getThread().getFrames();
                    Log.v("Bitmap_array", framers.toString());
                    File filepath = new File(Environment.getExternalStorageDirectory().toString());
                    Log.v("EXTERNAL_STORAGE", filepath.getAbsolutePath());
                    File dir = new File(filepath.getAbsolutePath() + "/Veryness_videos/");
                    dir.mkdir();
                    Log.v("NEW_DIRECTORY", dir.getAbsolutePath());
                    file = new File(dir, "test_three"+ ".mp4");
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.v("FILE_EXISTENCE", String.valueOf(file.exists()));
                    SeekableByteChannel out = null;
                    Toast.makeText(getContext(), "Picture saved", Toast.LENGTH_SHORT).show();
                    Log.v("FILE_PATH", file.getAbsolutePath());
                    Log.v("FILE_WRITABLE", String.valueOf(file.canWrite()));

                   try {
                        try {
                            out = (SeekableByteChannel) NIOUtils.writableChannel(file);
                            //Permissions are denying,
                            Log.v("Something_happened", out.toString());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        // for Android use: AndroidSequenceEncoder
                        AndroidSequenceEncoder encoder;
                        encoder = new AndroidSequenceEncoder(out, Rational.R(25, 1));
                        for (int i = 0; i < framers.size(); i++) {
                            // Generate the image, for Android use Bitmap
                            // Encode the image
                            try {
                                encoder.encodeImage(framers.get(i));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        // Finalize the encoding, i.e. clear the buffers, write the header, etc.
                        encoder.finish();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        NIOUtils.closeQuietly(out);
                    }
                }
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction sd = fragmentManager.beginTransaction();
                sd.remove(newInstance);
                MainFragment returnfragment=recreateFragment(newInstance);
                returnfragment.getMySurfaceView().setFragment(returnfragment);
                sd.add(R.id.container,returnfragment);
                sd.remove(videoFragment);
                sd.commit();
                ((MainActivity) Objects.requireNonNull(getActivity())).setMainFragment(returnfragment);
                actionBar.show();
            }
        });
        //fragmentManager.beginTransaction().add(R.id.fragmentofvideo,fragmentofvideo).addToBackStack(null).commit();
    }



}
