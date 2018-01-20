package com.example.kartiksinghchauhan.dictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import stanford.androidlib.SimpleActivity;


public class MainActivity extends SimpleActivity {
    private static final String[] WORDS = {
            "Defenestrate",  "to throw out of the window",
            "Kartik",        "Chauhan",
            "Kushal",        "Kumar Jawa"
    };

    private Map<String,String> dictionary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dictionary = new HashMap<>();
        for(int i = 0; i < WORDS.length;i+=2){
            dictionary.put(WORDS[i],WORDS[i + 1]);
        }
        ListView list = (ListView) findViewById(R.id.mylist);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(dictionary.keySet())
        );

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String word = parent.getItemAtPosition(position).toString();
                String defn = dictionary.get(word);
                toast(defn);
            }
        });

    }
}
