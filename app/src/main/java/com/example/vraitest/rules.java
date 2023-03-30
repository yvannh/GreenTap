package com.example.vraitest;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class rules extends AppCompatActivity {

    Button ok; // Cela correspond au Bouton OK pour revenir sur la page d'accueil

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules);
        ok=(Button)findViewById(R.id.ok); // On référencie notre attribut comme étant le Button de id"ok" dans le layout "rules.xml".
        ok.setOnClickListener(new View.OnClickListener() {// On clique sur le Bouton OK.
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rules.this, welcomepage.class); // On est alors rédiriger sur la page d'accueil.
                startActivity(intent);
            }
        });
    }
}