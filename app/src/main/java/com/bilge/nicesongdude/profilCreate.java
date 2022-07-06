package com.bilge.nicesongdude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class profilCreate extends AppCompatActivity {
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseUser user;

    TextView name;
    TextView number;
    TextView music;
    TextView city;
    String documant;
    String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_create);


        name = findViewById(R.id.nameText);
        number = findViewById(R.id.numberText);
        music = findViewById(R.id.musicText);
        city = findViewById(R.id.cityText);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        user = auth.getCurrentUser();
        mail = user.getEmail();
        documant = mail + "xyz";




    }

public String toStr(TextView x){
        String str =  x.getText().toString();
        return str;
}

    public void create(View view){

        String nameSave = toStr(name);
        String numberSave = toStr(number);
        String musicSave = toStr(music);
        String citySave = toStr(city);

        HashMap<String,Object> userData = new HashMap<>();
        userData.put("Name", nameSave);
        userData.put("Number", numberSave);
        userData.put("Music", musicSave);
        userData.put("City", citySave);
        userData.put("mail", mail);
        db.collection("user").document(documant).set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent intent = new Intent(profilCreate.this, menu.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(profilCreate.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
}