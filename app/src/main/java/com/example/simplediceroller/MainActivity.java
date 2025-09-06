package com.example.simplediceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView diceImg;
    private TextView diceResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        diceImg = findViewById(R.id.diceImage);
        diceResult = findViewById(R.id.rollResult);
        myButtonListenerMethod();
    }

    public void myButtonListenerMethod() {
        Button button = (Button) findViewById(R.id.rollButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                final int rollResult = rand.nextInt(6) + 1;

                // start rolling animation
                startRollingAnimation();

                // delay to show final result after animation
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stopRollingAnimation();
                        diceResult.setText(Integer.toString(rollResult));
                        switch (rollResult) {
                            case 1:
                                diceImg.setImageResource(R.drawable.dice1);
                                break;
                            case 2:
                                diceImg.setImageResource(R.drawable.dice2);
                                break;
                            case 3:
                                diceImg.setImageResource(R.drawable.dice3);
                                break;
                            case 4:
                                diceImg.setImageResource(R.drawable.dice4);
                                break;
                            case 5:
                                diceImg.setImageResource(R.drawable.dice5);
                                break;
                            case 6:
                                diceImg.setImageResource(R.drawable.dice6);
                                break;
                        }
                    }
                }, 1000);
            }
        });
    }

    private void startRollingAnimation() {
        AnimationDrawable animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.roll1), 100);
        animation.addFrame(getResources().getDrawable(R.drawable.roll2), 100);
        animation.addFrame(getResources().getDrawable(R.drawable.roll3), 100);
        animation.addFrame(getResources().getDrawable(R.drawable.roll4), 100);
        animation.addFrame(getResources().getDrawable(R.drawable.roll5), 100);
        animation.addFrame(getResources().getDrawable(R.drawable.roll6), 100);
        animation.setOneShot(false); // Loop until stopped

        diceImg.setImageDrawable(animation);
        animation.start();
    }

    private void stopRollingAnimation() {
        if (diceImg.getDrawable() instanceof AnimationDrawable) {
            AnimationDrawable animation = (AnimationDrawable) diceImg.getDrawable();
            animation.stop();
        }
    }
}