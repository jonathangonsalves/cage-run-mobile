package com.example.flappycage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.flappycage.data.model.GameObject;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    GameObject player;
    GameObject[] pipes = new GameObject[6];
    Boolean gameOver;
    ImageView bLimit;
    TextView drawScroe;
    TextView drawFPS;
    int gameAreaY;
    int frame;
    int player_Y;
    int gravity, aceleration;
    int screenWidth;
    int pipeSpeed;
    boolean jump;
    int score;
    int sleepTimeMS;
    boolean[] pipeScores = new boolean[6];


    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        SeekBar gameSpeed = (SeekBar) findViewById(R.id.seekBar_gameSpeed);
        drawScroe = (TextView) findViewById(R.id.textView_score);
        drawFPS = (TextView) findViewById(R.id.textView_fps);
        sleepTimeMS = 33;
        for(int i =0; i < 6; i++){
            pipeScores[i] = true;
        }

        gameSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sleepTimeMS = (progress + 33);
                float fps = (float)1/((float)sleepTimeMS/1000);
                drawFPS.setText("~" + String.format("%.0f", fps) + " fps");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        gameOver = false;
        jump = false;
        gravity = 1;
        aceleration = 0;
        pipeSpeed = 5;
        frame = 0;
        score = 0;

        random = new Random();
        // randomNumber = random.nextInt(max–min) + min;

        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                switch(frame){
                    case 0:
                        ((ImageView) findViewById(R.id.imageView_player)).setImageResource(R.drawable.perdonagem_idle_0);
                        break;
                    case 1:
                        ((ImageView) findViewById(R.id.imageView_player)).setImageResource(R.drawable.perdonagem_idle_1);
                        break;
                    case 2:
                        ((ImageView) findViewById(R.id.imageView_player)).setImageResource(R.drawable.perdonagem_idle_2);
                        frame = 0;
                        break;
                    case 4:
                        ((ImageView) findViewById(R.id.imageView_player)).setImageResource(R.drawable.perdonagem_dead);
                        break;
                }

            }
        });

        // eventListenar para a finaliza o carregamento da View
        ((ImageView) findViewById(R.id.imageView_player)).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                bLimit = (ImageView) findViewById(R.id.imageView_bLimit);
                gameAreaY = (int) ((int) bLimit.getY()*0.98);
                screenWidth = bLimit.getWidth();
                player = new GameObject((ImageView) findViewById(R.id.imageView_player), 80, 90);
                player_Y = player.getY();

                pipes[0] = new GameObject((ImageView) findViewById(R.id.imageView_pipe_0_b), 185, 50);
                pipes[1] = new GameObject((ImageView) findViewById(R.id.imageView_pipe_0_t), 185, 50);
                pipes[2] = new GameObject((ImageView) findViewById(R.id.imageView_pipe_1_b), 155, 50);
                pipes[3] = new GameObject((ImageView) findViewById(R.id.imageView_pipe_1_t), 155, 50);
                pipes[4] = new GameObject((ImageView) findViewById(R.id.imageView_pipe_2_b), 155, 50);
                pipes[5] = new GameObject((ImageView) findViewById(R.id.imageView_pipe_2_t), 155, 50);

                pipes[0].setX(1500);
                pipes[1].setX(1500);
                pipes[2].setX(2000);
                pipes[3].setX(2000);
                pipes[4].setX(2500);
                pipes[5].setX(2500);

                for(int i = 0; i <6; i += 2){
                    int size = random.nextInt(500 - 50)+50;
                    pipes[i].setHeight(size);
                    int middleSpace = 420;
                    if(size < 270)
                        pipes[i + 1].setHeight((middleSpace-size)+middleSpace);
                    else
                        pipes[i + 1].setHeight(middleSpace-(size-middleSpace));
                }

                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        gameLoop();
                    }
                }).start();

                // Remove eventListenner
                ((ImageView) findViewById(R.id.imageView_player)).getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }



    private void gameLoop(){
        while(!gameOver){
            try {
                Thread.sleep(sleepTimeMS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            aceleration += gravity;
            player_Y += aceleration;

            for(int i = 0; i<6; i += 2){
                pipes[i].setX(pipes[i].getX()-pipeSpeed);
                pipes[i + 1].setX(pipes[i + 1].getX()-pipeSpeed);
                if(pipes[i].getX() < -155){
                    pipes[i].setX(1400);
                    pipes[i + 1].setX(1400);
                    pipeScores[i] = true;
                    final int ii = i;
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            int size = random.nextInt(500 - 50)+50;
                            pipes[ii].setHeight(size);
                            int middleSpace = 420;
                            if(size < 270)
                                pipes[ii + 1].setHeight((middleSpace-size)+middleSpace);
                            else
                                pipes[ii + 1].setHeight(middleSpace-(size-middleSpace));
                        }
                    });
                }
                if((pipes[i].getX() < (player.getX() + player.getWidth())) && (pipes[i].getX() > player.getX())) {
                   if (GameObject.isColliding(pipes[i], player, 'b')) {
                        gameOver = true;
                    }
                    else if (GameObject.isColliding(pipes[i + 1], player, 't')) {
                       gameOver = true;
                    }
                    if((pipes[i].getX() < (player.getX() + player.getWidth())) && pipeScores[i]){
                        score++;
                        pipeScores[i] = false;
                    }
                }
            }

            frame++;
            if(frame > 2)
                frame = 0;
            if(player_Y+player.getHeight() > gameAreaY){
                gameOver = true;
            }
            if(player_Y < 0 ){
                player_Y = 0;
            }
            if(jump)
                aceleration = -10;
            player.setY(player_Y);
            jump = false;

            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    drawScroe.setText(Integer.toString(score));

                    switch(frame){
                        case 0:
                            ((ImageView) findViewById(R.id.imageView_player)).setImageResource(R.drawable.perdonagem_idle_0);
                            break;
                        case 1:
                            ((ImageView) findViewById(R.id.imageView_player)).setImageResource(R.drawable.perdonagem_idle_1);
                            break;
                        case 2:
                            ((ImageView) findViewById(R.id.imageView_player)).setImageResource(R.drawable.perdonagem_idle_2);
                            frame = 0;
                            break;
                    }

                }
            });
        }

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                ((ImageView) findViewById(R.id.imageView_player)).setImageResource(R.drawable.perdonagem_dead);
            }
        });
    }

    public void onClick(View view){
        if(!gameOver)
            jump = true;
        else{
            Intent it = new Intent(this, ScoreActivity.class);
            it.putExtra("score", score);
            startActivity(it);
        }

    }
}

