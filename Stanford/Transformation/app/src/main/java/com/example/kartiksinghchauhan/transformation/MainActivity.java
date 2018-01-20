package com.example.kartiksinghchauhan.transformation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void radioClick(View view) {
        ImageView img = (ImageView)findViewById(R.id.picture);

        int id = view.getId();
        if(id == R.id.start_button){
            img.setImageResource(R.drawable.start);
        }else if(id == R.id.stage2_button){
            img.setImageResource(R.drawable.stage2);
        }
        else if(id == R.id.stage3_button){
            img.setImageResource(R.drawable.stage3);
        }
        else if(id == R.id.stage4_button){
            img.setImageResource(R.drawable.stage4);
        }
    }
}
