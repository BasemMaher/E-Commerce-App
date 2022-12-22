package com.example.app_ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

public class yalla extends AppCompatActivity   {
    Timer timer;
    Animation top,butom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        top= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        butom= AnimationUtils.loadAnimation(this,R.anim.butom_anim);
        ImageView img=(ImageView)findViewById(R.id.imageView8);
        img.setAnimation(top);
        TextView tx=(TextView)findViewById(R.id.textView2);
        tx.setAnimation(butom);
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    boolean a=preferences.getBoolean("remember",false);
                    if(a) {
                        Intent intent = new Intent(yalla.this, first.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(yalla.this, Rigster_fragment.class);
                        startActivity(intent);
                    }
            }
        },2500);
    }
}