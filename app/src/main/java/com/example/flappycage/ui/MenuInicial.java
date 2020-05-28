package com.example.flappycage.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flappycage.GameActivity;
import com.example.flappycage.MainActivity;
import com.example.flappycage.R;
import com.example.flappycage.ScoreActivity;

import java.util.Map;

public class MenuInicial extends AppCompatActivity {



    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    String cur_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        cur_user = intent.getStringExtra("cur");
        Log.d("user_menu",cur_user);
    }

    public void moveToLogin(View view){
        Intent intent = new Intent(MenuInicial.this, MainActivity.class);
        startActivity(intent);
    }

    public void getPreferences(View view, String key) {
        Map<String, ?> all = prefs.getAll();


    }

    public void moveToScore(View view){
        Intent intent = new Intent(MenuInicial.this, ScoreActivity.class);
        intent.putExtra("cur", cur_user);
        startActivity(intent);
    }

    public void moveToGame(View view){
        Intent intent2 = new Intent(this, GameActivity.class);
        intent2.putExtra("cur", cur_user);
        startActivity(intent2);
    }
}
