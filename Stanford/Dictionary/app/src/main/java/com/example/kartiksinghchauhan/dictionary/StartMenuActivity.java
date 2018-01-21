package com.example.kartiksinghchauhan.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import stanford.androidlib.SimpleActivity;

public class StartMenuActivity extends SimpleActivity {
    private static final int REQ_CODE_ADD_WORD = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        setTraceLifecycle(true);
    }

    public void playTheGameClick(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void addANewWordClick(View view) {
        Intent intent = new Intent(this,AddWordActivity.class);
        startActivityForResult(intent,REQ_CODE_ADD_WORD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        try {
            if (requestCode == REQ_CODE_ADD_WORD) {
                String newWord = intent.getStringExtra("newword");
                String newDefn = intent.getStringExtra("newdefn");

                toast("You added the word: " + newWord);
            }
        }catch(Exception e){

        }

    }
}
