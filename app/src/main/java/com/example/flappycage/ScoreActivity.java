package com.example.flappycage;

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
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ScoreActivity extends AppCompatActivity {


    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        getSupportActionBar().hide();
        TableLayout tbl = (TableLayout)findViewById(R.id.ScoreTable);

        SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);
        Map<String, ?> keys = prefs.getAll();

        keys = sortByValues(keys);

        int index = 1;
        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
            addNewScore(entry.getValue().toString(),entry.getKey(),String.valueOf(index)+"º", tbl);
            index++;
        }

    }
    public void moveToMenu(View view){
        Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
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
    public static <K,V extends Comparable> Map<K,V> sortByValues(Map<String, ?> map)
    {
        List<Map.Entry<K,V>> mappings = new ArrayList(map.entrySet());
        Collections.sort(mappings, (o1, o2) ->
                o2.getValue().compareTo(o1.getValue()));

        Map<K,V> linkedHashMap = new LinkedHashMap<>();

        for (Map.Entry<K,V> entry: mappings) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }

        return linkedHashMap;
    }

}
