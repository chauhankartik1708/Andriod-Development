package com.example.kartiksinghchauhan.dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import stanford.androidlib.SimpleActivity;

public class AddWordActivity extends SimpleActivity {
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        setTraceLifecycle(true);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(AddWordActivity.this,StartMenuActivity.class));
                }
            }
        };
    }

    public void addThisWordClick(View view) {
        String newword = $ET(R.id.new_word).getText().toString();
        String newdefn = $ET(R.id.new_defn).getText().toString();
        if (!newword.isEmpty() && !newdefn.isEmpty()) {
            FirebaseUser user = mAuth.getCurrentUser();
            String word = newword.trim();
            String defn = newdefn.toString().trim();
            databaseReference = database.getReference("Users").child(user.getUid()).child(word);
            databaseReference.child("defn").setValue(defn);
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
