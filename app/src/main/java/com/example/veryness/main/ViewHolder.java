package com.example.veryness.main;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veryness.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import me.bendik.simplerangeview.SimpleRangeView;


public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        View itemView;
        public Actor item;
        public EditText name;
        public SimpleRangeView timeline;
        public ImageView portrait;
        public List<ViewHolder> mPoints;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cardView=(CardView)itemView.findViewById(R.id.carder);
            this.name=(EditText)itemView.findViewById(R.id.object_name);
            this.timeline=(SimpleRangeView) itemView.findViewById(R.id.time_appearance);
            this.portrait=(ImageView)itemView.findViewById(R.id.image_item);
            this.itemView=itemView;
        }
        public void onbindmodel(final Actor item){
            timeline.setStart(item.getTime_appearance());
            timeline.setEnd(item.getTime_disappearance());
            int height=LinearLayout.LayoutParams.WRAP_CONTENT;
            LinearLayout.LayoutParams pa=new LinearLayout.LayoutParams(item.getWidth(),height);
            cardView.setLayoutParams(pa);
            name.setText(item.getName());
            portrait.setImageBitmap(item.getPicture());
            timeline.setOnChangeRangeListener(new SimpleRangeView.OnChangeRangeListener() {
                @Override
                public void onRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i, int i1) {

                }
            });


        }
        public void Insertmodel(Actor item){

        }
    }

