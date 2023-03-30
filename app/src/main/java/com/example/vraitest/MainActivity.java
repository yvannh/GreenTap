package com.example.vraitest;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.ConcurrentHashMap;


public class MainActivity extends AppCompatActivity {
    private EditText ident; // Cela correspond à l'EditText pour l'Email de l'utilisateur.
    private EditText mdp; // Cela correspond à l'EditText pour le mot de passe de l'utilisateur.
    private Button register; // Cela correspond au Bouton pour s'inscrire.
    private Button loginbtn; // Cela correspond au Bouton pour s'identifier.
    private FirebaseAuth firebaseAuth;  // Cela correspond à notre base de données pour enregistrer les utilisateurs.

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);

        ident =(EditText) findViewById(R.id.ident);                 // On va référencier chacun des attributs sur un élement de notre layout "activity_main"
        mdp =(EditText) findViewById(R.id.mdp);                     // grâce à des id qu'on a défini au préalable dans nos fichier ".xml".
        register =(Button) findViewById(R.id.register);
        loginbtn =(Button) findViewById(R.id.loginbtn);

        register.setOnClickListener(new View.OnClickListener(){     // Cet évenement correspond a quand on clique sur le bouton "REGISTER" pour s'enregistrer.
            @Override
            public void onClick(View v){
                String getEmail = ident.getText().toString();       // On récupère la chaine de caractère entré dans notre EditText pour l'Email.
                String getPassword= mdp.getText().toString();       // On récupère la chaine de caractère entré dans notre EditText pour le mot de passe.
                if(!getEmail.isEmpty()&& getEmail!=null && !getPassword.isEmpty()&& getPassword !=null){    // Si les deux champs ne sont pas vide et null
                    firebaseAuth.createUserWithEmailAndPassword(getEmail, getPassword)                      // alors on crée un nouvelle utilisateur qu'on va rentrer dans base de donnée Firebase.
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {                     // si les conditions de Firebase on été respectées alors le compte est crée avec succès
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(MainActivity.this, "User account created", Toast.LENGTH_LONG).show();    // et le notifie à l'Utilisateur.
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_LONG).show();       // sinon on notifie la condition qui n'a pas été respectée.
                                }
                            });
                }else{
                    Toast.makeText(getApplicationContext(),"Please complete both fields",Toast.LENGTH_LONG).show(); // Si l'un des champs est vide ou null alors on affiche "Please complete both fields"
                }
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {        // Cet évenement correspond a quand on clique sur le bouton "LOGIN" pour s'identifier.
            @Override
            public void onClick(View v) {                               // Même chose que tout à l'heure.
                String getEmail = ident.getText().toString();           // On récupère la chaine de caractère entré dans notre EditText pour l'Email.
                String getPassword= mdp.getText().toString();           // On récupère la chaine de caractère entré dans notre EditText pour le mot de passe.
                firebaseAuth.signInWithEmailAndPassword(getEmail, getPassword) //On verifie que l'email et le mot de passe correspond bien à un utilisateur,
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() { // Si oui
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(MainActivity.this, "Welcome User", Toast.LENGTH_SHORT).show(); // On notifie à l'utilisateur qu'il est bien identifié
                                Intent intent = new Intent(MainActivity.this, welcomepage.class);            // et on le fait changer de page, pour aller sur le layout de la classe welcomepage.java
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {                                                       // L'utilisateur ne réussit pas à se connecter
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();  // alors on lui affiche ce qu'il ne va pas
                            }
                        });
            }
        });


    }
}
