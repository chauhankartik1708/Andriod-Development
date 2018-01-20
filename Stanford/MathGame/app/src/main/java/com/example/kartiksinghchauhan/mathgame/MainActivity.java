package com.example.kartiksinghchauhan.mathgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int rand1;
    private int rand2;
    private int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        points = 0;
        pickRandomNumbers();
    }

    private void pickRandomNumbers(){
        Random randy = new Random();
        rand1 = randy.nextInt(100);
        while(true){
            rand2 = randy.nextInt(100);
            if(rand2 != rand1) break;
        }

        Button leftbtn = (Button) findViewById(R.id.left_button);
        leftbtn.setText(Integer.toString(rand1));
        Button rightbtn = (Button) findViewById(R.id.right_button);
        rightbtn.setText(Integer.toString(rand2));
    }
    public void leftButtonClick(View view){
    if(rand1 >= rand2){
        points++;
        Toast.makeText(this,"Shabaash bete !",Toast.LENGTH_SHORT).show();
    }else{
        points--;
        Toast.makeText(this,"Chutiya Hai Kya ?",Toast.LENGTH_SHORT).show();
    }

        TextView tv = (TextView)findViewById(R.id.points_field);
        tv.setText("Points: " + points);
        pickRandomNumbers();
    }

    public void rightButtonClick(View view){
        if(rand2 >= rand1){
            points++;
            Toast.makeText(this,"Shabaash bete !",Toast.LENGTH_SHORT).show();
        }else{
            points--;
            Toast.makeText(this,"Chutiya Hai Kya ?",Toast.LENGTH_SHORT).show();
        }

        TextView tv = (TextView)findViewById(R.id.points_field);
        tv.setText("Points: " + points);
        pickRandomNumbers();
    }
}
