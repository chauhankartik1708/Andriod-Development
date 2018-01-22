package com.example.kartiksinghchauhan.dictionary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleList;


public class MainActivity extends SimpleActivity {
    private static final int REQ_CODE_ADD_WORD = 1234;
    private int highScore;
    private int points;
    private MediaPlayer mp;
    private Map<String,String> dictionary;
    private List<String> words;
    private void chooseWords(){
        $LV(R.id.mylist).setVisibility(View.VISIBLE);
        Button nxt = (Button)findViewById(R.id.next);
        nxt.setVisibility(View.GONE);
        Random randy = new Random();
        int randomIndex = randy.nextInt(words.size());
        String theWord = words.get(randomIndex);
        String theDefn = dictionary.get(theWord);

        List<String> defns = new ArrayList<>(dictionary.values());
        defns.remove(theDefn);
        Collections.shuffle(defns);
        List<String> options = defns.subList(0,4);
        options.add(theDefn);
        Collections.shuffle(options);

        $TV(R.id.the_word).setText(theWord);
        SimpleList.with(this).setItems(R.id.mylist,options);
    }

    private void readFileData(){
            Scanner scn = new Scanner(getResources().openRawResource(R.raw.grewords));
            readFileHelper(scn);
            try {
                Scanner scn2 = new Scanner(openFileInput("added_words.txt"));
                readFileHelper(scn2);
            }catch(Exception e){

            }

    }

    private void readFileHelper(Scanner scn){
        while (scn.hasNextLine()) {
            String line = scn.nextLine();
            String[] parts = line.split("@");
            if (parts.length < 2) continue;
            dictionary.put(parts[0], parts[1]);
            words.add(parts[0]);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        $LV(R.id.mylist).setVisibility(View.VISIBLE);
        Button nxt = (Button)findViewById(R.id.next);
        nxt.setVisibility(View.GONE);

        setTraceLifecycle(true);
        points = 0;
        dictionary = new HashMap<>();
        words = new ArrayList<>();

        readFileData();
        chooseWords();

        $LV(R.id.mylist).setOnItemClickListener(this);

        SharedPreferences prefs = getSharedPreferences("myprefs",MODE_PRIVATE);
        highScore = prefs.getInt("highScore",0);

        mp = MediaPlayer.create(this, R.raw.raja);
        mp.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("points",points);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        points = savedInstanceState.getInt("points",0);
    }

    @Override
            public void onItemClick(ListView list,int index) {
                String defnClicked = list.getItemAtPosition(index).toString();
                String theWord = $TV(R.id.the_word).getText().toString();
                String correctDefn = dictionary.get(theWord);
                if(defnClicked.equals(correctDefn)){
                    points++;
                    if(points > highScore){
                        highScore = points;
                        SharedPreferences prefs = getSharedPreferences("myprefs",MODE_PRIVATE);
                        SharedPreferences.Editor prefsEditor = prefs.edit();
                        prefsEditor.putInt("highScore",highScore);
                        prefsEditor.apply();
                    }
                    toast("AWESOME ! Score = " + points + ", HighScore = " + highScore);
                    chooseWords();
                }else{
                    points--;
                    toast("Nope ! Score = " + points + ", HighScore = " + highScore);
                    $LV(R.id.mylist).setVisibility(View.INVISIBLE);
                    Button nxt = (Button)findViewById(R.id.next);
                    nxt.setText(correctDefn);
                    nxt.setVisibility(View.VISIBLE);


                }

            }


    public void addANewWord(View view) {
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

    public void muteOnClick(View view) {
        Button btn = $B(R.id.mute_button);
        String bttn = btn.getText().toString();
        if(bttn.equals("Mute ON")){
            mp.pause();
            btn.setText("Mute OFF");
        }else if(bttn.equals("Mute OFF")){
            mp.start();
            btn.setText("Mute ON");
        }
    }

    public void next_button(View view) {
        chooseWords();
    }
}
