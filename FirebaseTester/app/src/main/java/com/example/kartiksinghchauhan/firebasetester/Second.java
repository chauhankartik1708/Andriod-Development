package com.example.kartiksinghchauhan.firebasetester;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Second extends AppCompatActivity {

    Button button;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;

    private EditText newword,newdefn;
    private Button save;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        button = (Button)findViewById(R.id.logout);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        TextView welcome = (TextView)findViewById(R.id.welcome);
        welcome.setText("Welcome " + user.getEmail());


        newword = (EditText)findViewById(R.id.new_word);
        newdefn = (EditText)findViewById(R.id.new_defn);
        save = (Button)findViewById(R.id.add);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(Second.this,MainActivity.class));
                }
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWord();
            }
        });
    }

    private void saveWord(){
        FirebaseUser user = mAuth.getCurrentUser();
        String word = newword.getText().toString().trim();
        String defn = newdefn.getText().toString().trim();
        databaseReference = database.getReference("Users").child(user.getUid()).child(word);


        databaseReference.child("defn").setValue(defn);
        Toast.makeText(Second.this,"Information Saved",Toast.LENGTH_SHORT).show();

    }
}
