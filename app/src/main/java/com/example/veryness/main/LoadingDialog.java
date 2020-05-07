package com.example.veryness.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import androidx.fragment.app.Fragment;

import com.example.veryness.R;

public class LoadingDialog {
   private Fragment activity;
   private AlertDialog dialog;

    LoadingDialog(Fragment activity){this.activity=activity;
    }
    public void startLoadingDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity.getContext());
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog,null));
        builder.setCancelable(false);

        dialog=builder.create();
        dialog.show();

    }

    public void dismiss(){
        dialog.dismiss();
    }
}
