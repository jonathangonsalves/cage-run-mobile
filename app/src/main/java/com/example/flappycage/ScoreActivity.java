package com.example.flappycage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flappycage.ui.MenuInicial;

import java.util.ArrayList;
import java.util.Map;


public class ScoreActivity extends AppCompatActivity {


    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        getSupportActionBar().hide();
        TableLayout tbl = (TableLayout)findViewById(R.id.ScoreTable);


        sharedPreferences = getSharedPreferences("jo", Context.MODE_PRIVATE);
        Map<String, ?> keys = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
        }

        ArrayList<Integer> Jonathan = new ArrayList<Integer>();
        Jonathan.add(1);
        Jonathan.add(5);
        Jonathan.add(3);

        ArrayList<Integer> Leonardo = new ArrayList<Integer>();
        Leonardo.add(0);
        Leonardo.add(8);
        Leonardo.add(6);


        addNewScore("52","Jonathan","1º", tbl);
        addNewScore("48","Leonardo","2º", tbl);
    }
    public void moveToMenu(View view){
        Intent intent = new Intent(ScoreActivity.this, MenuInicial.class);
        startActivity(intent);
    }


    public void addNewScore(String score, String name, String position, TableLayout tbl){

        TableRow tableRow = new TableRow(this);
        tableRow.setBackgroundResource(R.color.colorPrimary);
        TableRow.LayoutParams tableRowLayout = new TableRow.LayoutParams();
        tableRowLayout.width = 250;
        tableRowLayout.height = 200;
        tableRow.setLayoutParams(tableRowLayout);

        TextView numberCol = new TextView(this);
        //numberCol.setId(R.id.nbmLabel);
        numberCol.setGravity(Gravity.CENTER);
        numberCol.setText(position);
        numberCol.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TableRow.LayoutParams numberLayout = new TableRow.LayoutParams();
        numberLayout.width = 50;
        numberLayout.height = 80;
        numberLayout.leftMargin = 10;
        numberCol.setLayoutParams(numberLayout);
        tableRow.addView(numberCol);

        TextView nameCol = new TextView(this);
        //nameCol.setId(R.id.runLabel);jmhg
        nameCol.setGravity(Gravity.CENTER);
        nameCol.setText("    "+name);
        nameCol.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TableRow.LayoutParams nameLayout = new TableRow.LayoutParams();
        nameLayout.width = 570;
        nameLayout.height = 50;
        numberLayout.leftMargin = 10;
        nameCol.setLayoutParams(nameLayout);
        tableRow.addView(nameCol);

        TextView scoreCol = new TextView(this);
        //scoreCol.setId(R.id.hitLabel);
        scoreCol.setGravity(Gravity.CENTER);
        scoreCol.setText(score);
        scoreCol.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TableRow.LayoutParams scoreLayout = new TableRow.LayoutParams();
        scoreLayout.width = 100;
        scoreLayout.height = 50;
        scoreCol.setLayoutParams(scoreLayout);
        tableRow.addView(scoreCol);

        tbl.addView(tableRow);



    }


}
