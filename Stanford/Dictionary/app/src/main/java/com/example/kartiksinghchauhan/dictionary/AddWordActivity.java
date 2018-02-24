package com.example.kartiksinghchauhan.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.PrintStream;

import stanford.androidlib.SimpleActivity;

public class AddWordActivity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        setTraceLifecycle(true);
    }

    public void addThisWordClick(View view) {
        String newword = $ET(R.id.new_word).getText().toString();
        String newdefn = $ET(R.id.new_defn).getText().toString();
        if (!newword.isEmpty() && !newdefn.isEmpty()) {
            PrintStream output = new PrintStream(openFileOutput("added_words.txt", MODE_PRIVATE | MODE_APPEND));
            output.println(newword + "@" + newdefn);
            output.close();
            try {
                Intent goBack = new Intent();
                goBack.putExtra("newword", newword);
                goBack.putExtra("newdefn", newdefn);
                setResult(RESULT_OK, goBack);
            } catch (Exception e) {

            }
            finish();

        } else {
            toast("These fields can't be empty");
        }
    }
}
