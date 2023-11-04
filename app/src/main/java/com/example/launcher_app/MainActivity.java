package com.example.launcher_app;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()) {
            case (MotionEvent.ACTION_DOWN) :
                return true;
            case (MotionEvent.ACTION_MOVE) :
                return true;
            case (MotionEvent.ACTION_UP) :
                Intent intent = new Intent(getApplicationContext(), ListOfActivity.class);
                startActivity(intent);
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }
}