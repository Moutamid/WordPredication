package com.moutamid.wordpredication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SplashScreen extends AppCompatActivity {

    private AppCompatButton reviewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        reviewBtn = findViewById(R.id.review);
        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SplashScreen.this,MainActivity.class);
                intent.putExtra("number",0);
                startActivity(intent);
                finish();
            }
        });
    }

}