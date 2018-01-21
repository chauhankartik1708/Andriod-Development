package com.example.kartiksinghchauhan.dictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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


    private Map<String,String> dictionary;
    private List<String> words;
    private void chooseWords(){
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

        dictionary = new HashMap<>();
        words = new ArrayList<>();

        readFileData();
        chooseWords();
        ListView list = (ListView) findViewById(R.id.mylist);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String defnClicked = parent.getItemAtPosition(position).toString();
                String theWord = $TV(R.id.the_word).getText().toString();
                String correctDefn = dictionary.get(theWord);
                if(defnClicked.equals(correctDefn)){
                    toast("AWESOME");
                }else{
                    toast("Chutiya Hai kya");
                }
                chooseWords();
            }
        });

    }
}
