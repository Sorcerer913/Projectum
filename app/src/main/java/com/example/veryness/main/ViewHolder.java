package com.example.veryness.main;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veryness.R;

import java.util.ArrayList;
import java.util.List;


    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        View itemView;
        public Actor item;
        public EditText name;
        public SeekBar timeline;
        public ImageView portrait;
        public List<ViewHolder> mPoints;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cardView=(CardView)itemView.findViewById(R.id.carder);
            this.name=(EditText)itemView.findViewById(R.id.object_name);
            this.timeline=(SeekBar)itemView.findViewById(R.id.time_appearance);
            this.portrait=(ImageView)itemView.findViewById(R.id.image_item);
            this.itemView=itemView;
        }
        public void onbindmodel(final Actor item){
            name.setText(item.getName());
            portrait.setImageBitmap(item.getPicture());
            timeline.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    item.setTime_disappearance(item.getTime_appearance()+progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.setName(name.getText().toString());
                }
            });

        }
        public void Insertmodel(Actor item){

        }
    }

