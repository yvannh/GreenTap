package com.example.vraitest;

import static android.graphics.Color.parseColor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class play extends AppCompatActivity {
    TextView pause; // Cela correspond au fond du jeu qu'on va faire changer de couleur.
    TextView timer; // Cela correspond au chronomètre.
    Button rdy; // Cela correspond au Bouton "Ready".
    Button disc; // Cela correspond au Bouton "Disconnect".
    Runnable runnable;
    int ms = 0, sec = 0; // Cela correspond au mesure de notre Chronomètre
    boolean clicked, stop, cancel, tmp, testeuh;
    Random rd = new Random();
    int tps = rd.nextInt(3) + 2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);
        timer = findViewById(R.id.timer);
        rdy = findViewById(R.id.rdy);
        pause = findViewById(R.id.background);
        disc = findViewById(R.id.disc);


        disc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(play.this, MainActivity.class);
                startActivity(intent);
            }
        });

        rdy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked == false) {
                    clicked = true;
                    new Handler().postDelayed(runnable, 1);
                    stop = false;
                    cancel = false;
                    rdy.setVisibility(View.INVISIBLE);
                    disc.setVisibility(View.INVISIBLE);
                    timer.setVisibility(View.INVISIBLE);
                    ms=0;
                    sec=0;
                    pause.setBackgroundColor(Color.parseColor("#FF0000"));
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stop == false && tmp == true) {
                    stop = true;
                    testeuh = true;
                    rdy.setVisibility(View.VISIBLE);
                    disc.setVisibility(View.VISIBLE);
                    pause.setVisibility(View.INVISIBLE);
                    pause.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    clicked = false;
                    cancel = true;
                }

            }
        });

        runnable = new Runnable() {
            @Override
            public void run() {
                    ms += 1;
                    if (ms == 99) {
                        sec += 1;
                        ms = 0;
                    }
                    if(testeuh==false && sec > tps) {
                        timer.setVisibility(View.VISIBLE);
                        tmp = true;
                        ms = 0;
                        sec = 0;
                        testeuh=true;
                        pause.setBackgroundColor(Color.parseColor("#32CD32"));
                    }
                    if (cancel == false) {
                        if (stop == false) {
                            new Handler().postDelayed(runnable, 1);
                        } else {
                            ms = 0;
                            sec = 0;
                        }
                    }
                    timer.setText("" + sec + ". " + ms);
            }
        };
    }
}