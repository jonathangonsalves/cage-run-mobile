package com.example.flappycage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flappycage.ui.MenuInicial;

public class MainActivity extends AppCompatActivity {
    public static final String FILE_NAME = "user.txt";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    String cur_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginBtn = findViewById(R.id.btn_login);
        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        getSupportActionBar().hide();
    }
    public void savePreferences(View view, String newUser) {
        Context context = getApplicationContext();
        sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt(newUser, 0);
        editor.commit();
    }

    public void getPreferences(View view, String key) {
        sharedPreferences = getSharedPreferences(key, Context.MODE_PRIVATE);
        String result = sharedPreferences.getString(key, "");
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }


    public void save(View view, String user){
        savePreferences(view, user);
        cur_user = user;
    }


    public void login(View view) {

        EditText user = findViewById(R.id.txt_in_user);

        if(user.length() == 0 || user.getText().toString() == " "){
            Toast.makeText(this, "Forneça um usuário válido!", Toast.LENGTH_LONG).show();
            return;
        }else{
            this.save(view, user.getText().toString());
            moveToMenu(view);
        }
    }

    public void moveToMenu(View view){
        Intent intent = new Intent(MainActivity.this, MenuInicial.class);
        intent.putExtra("cur", cur_user);
        startActivity(intent);
    }
}
