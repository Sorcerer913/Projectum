package com.example.veryness.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.veryness.R;
import com.example.veryness.workingfragments.MainFragment;

import java.util.Objects;

public class LoadingDialog extends DialogFragment {
   private Fragment activity;
   private AlertDialog dialog;
   private ProgressBar load;
   private TextView warn;
   private static final String TAG="MyCuxtomDialog";
    //LoadingDialog(Fragment activity){this.activity=activity;
    //}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.custom_dialog, container, false);
        VideoFragment fragment=((MainActivity) Objects.requireNonNull(getActivity())).recfragment.getVideoFragment();
        load=(ProgressBar)view.findViewById(R.id.loadthing);
        warn=(TextView)view.findViewById(R.id.loadtext);
        return view ;

    }

    public void startLoadingDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity.getContext());
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog,null));
        builder.setCancelable(false);

        dialog=builder.create();
        dialog.show();

    }

}
