package com.example.flappycage.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flappycage.GameActivity;
import com.example.flappycage.MainActivity;
import com.example.flappycage.R;
import com.example.flappycage.ScoreActivity;

public class MenuInicial extends AppCompatActivity {



    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);
        getSupportActionBar().hide();
        sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE);
        String result = sharedPreferences.getString("current_user", "");
    }

    public void moveToLogin(View view){
        Intent intent = new Intent(MenuInicial.this, MainActivity.class);
        startActivity(intent);
    }

    public void getPreferences(View view, String key) {

    }

    public void moveToScore(View view){
        Intent intent = new Intent(MenuInicial.this, ScoreActivity.class);
        startActivity(intent);
    }

    public void moveToGame(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
