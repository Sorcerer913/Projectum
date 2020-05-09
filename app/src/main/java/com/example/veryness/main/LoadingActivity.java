package com.example.veryness.main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.example.veryness.R;

import org.jcodec.api.android.AndroidSequenceEncoder;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.Rational;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class LoadingActivity extends AppCompatActivity {
    File file;
    LazyLoader load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        load=findViewById(R.id.lazyy);
        setContentView(R.layout.activity_loading);
        ActionBar actionBar=getSupportActionBar();
        Intent intent=getIntent();
        ArrayList<Bitmap>framers= Objects.requireNonNull(intent.getExtras()).getParcelableArrayList("BITMAP_LIST");
        assert actionBar != null;
        actionBar.hide();
        LazyLoader loader = new LazyLoader(this, 30, 20, ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected));
        loader.setAnimDuration(500);
        loader.setFirstDelayDuration(100);
        loader.setSecondDelayDuration(200);
        loader.setInterpolator(new LinearInterpolator());
        load.addView(loader);
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
        Toast.makeText(this, "Picture saved", Toast.LENGTH_SHORT).show();
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
        Intent recurn=new Intent(LoadingActivity.this,MainActivity.class);
        startActivity(recurn);

    }
}
