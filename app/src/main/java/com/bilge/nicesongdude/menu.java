package com.bilge.nicesongdude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void friends(View view){
        Intent intent = new Intent(menu.this,friends.class);
        startActivity(intent);
    }
    public void music(View view){
        Intent intent = new Intent(menu.this,music.class);
        startActivity(intent);

    }
    public void city(View view){
        Intent intent = new Intent(menu.this,city.class);
        startActivity(intent);

    }
    public void signout(View view){
        Intent intent = new Intent(menu.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}