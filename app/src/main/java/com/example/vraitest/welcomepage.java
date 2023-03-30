package com.example.vraitest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class welcomepage extends AppCompatActivity {
    Button rules; // Cela correspond au Bouton pour lire les règles.
    Button play;  // Cela correspond au Bouton pour jouer.
    Button disc;  // Cela correspond au Bouton pour se déconnecter.



    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main);
        rules =(Button) findViewById(R.id.rules);           // On va référencier chacun des attributs sur un élement de notre layout "welcome_main"
        play =(Button) findViewById(R.id.play);             // grâce à des id qu'on a défini au préalable dans nos fichier ".xml".
        disc =(Button) findViewById(R.id.disconnect);

        disc.setOnClickListener(new View.OnClickListener() { // On clique sur le bouton "Disconnect".
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(welcomepage.this, MainActivity.class);  // On est alors rediriger sur la page d'authentification.
                startActivity(intent1);
            }
        });

        rules.setOnClickListener(new View.OnClickListener() {   // On clique sur le bouton "Rules".
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(welcomepage.this, rules.class); // On est alors rediriger sur la page des règles du jeu.
                startActivity(intent2);
            }
        });

        play.setOnClickListener(new View.OnClickListener() { // On clique sur le bouton "Play"
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(welcomepage.this, play.class);  // On est alors rediriger sur la page de notre jeu.
                startActivity(intent3);
            }
        });
    }



}
