package com.example.kartiksinghchauhan.transformation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Click(View view) {
        ImageView img = (ImageView)findViewById(R.id.picture);

        int id = view.getId();
        if(id == R.id.start_button){
            img.setImageResource(R.drawable.start);
            Toast.makeText(this,"Few Months",Toast.LENGTH_SHORT).show();
        }else if(id == R.id.stage2_button){
            img.setImageResource(R.drawable.stage2);
            Toast.makeText(this,"1-2yrs",Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.stage3_button){
            img.setImageResource(R.drawable.stage3);
            Toast.makeText(this,"11yrs",Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.stage4_button){
            img.setImageResource(R.drawable.stage4);
            Toast.makeText(this,"12yrs",Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.stage5_button){
            img.setImageResource(R.drawable.stage5);
            Toast.makeText(this,"13yrs",Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.stage6_button){
            img.setImageResource(R.drawable.stage6);
            Toast.makeText(this,"14yrs",Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.stage7_button){
            img.setImageResource(R.drawable.class9);
            Toast.makeText(this,"16yrs",Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.stage8_button){
            img.setImageResource(R.drawable.stage8);
            Toast.makeText(this,"18yrs",Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.stage9_button){
            img.setImageResource(R.drawable.stage9);
            Toast.makeText(this,"18yrs",Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.current_button){
            img.setImageResource(R.drawable.current);
            Toast.makeText(this,"20yrs",Toast.LENGTH_SHORT).show();
        }


    }
}
