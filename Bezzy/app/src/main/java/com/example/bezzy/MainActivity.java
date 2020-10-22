package com.example.bezzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bezzy.UI.LoginActivity;
import com.example.bezzy.UI.ProfileActivity;
import com.example.bezzy.Utils.Utility;

public class MainActivity extends AppCompatActivity {
    private View decorView;
    Button button;
    private static int splashtimeout=2000;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View overlay = findViewById(R.id.splash_layout);
        button = findViewById(R.id.splash_button);
        logo=(ImageView)findViewById(R.id.logo_bezzy);
        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Splash.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });*/
        if(Utility.getOtpScreen(MainActivity.this).equals("1")){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            },splashtimeout);
            Animation mysin= AnimationUtils.loadAnimation(this,R.anim.animation);
            logo.startAnimation(mysin);
        }else{
            if(Utility.getLogin(MainActivity.this).equals("1")){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        finish();

                    }
                },splashtimeout);
                Animation mysin= AnimationUtils.loadAnimation(this,R.anim.animation);
                logo.startAnimation(mysin);
            }else{
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        finish();

                    }
                },splashtimeout);
                Animation mysin= AnimationUtils.loadAnimation(this,R.anim.animation);
                logo.startAnimation(mysin);
            }

        }
    }
}