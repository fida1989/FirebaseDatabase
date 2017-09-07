package com.hungrydroid.firebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button bSend,bView;
    private EditText email,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FirebaseApp.initializeApp()
        bSend = (Button)findViewById(R.id.send);
        bView = (Button)findViewById(R.id.view);
        email = (EditText)findViewById(R.id.email);
        phone = (EditText)findViewById(R.id.phone);
        //
        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");
               // Random rand = new Random();
                // Generate random integers in range 0 to 999
                //int rand_int1 = rand.nextInt(1000);
               /* DatabaseReference ch = myRef.child("data "+ rand_int1);
                ch.setValue("Hello World " + rand_int1, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        Toast.makeText(MainActivity.this,"Data Sent",Toast.LENGTH_SHORT).show();
                    }
                });*/

               long yourmilliseconds = System.currentTimeMillis();

                User user = new User(phone.getText().toString(), email.getText().toString());

                myRef.child("users").child(yourmilliseconds+"").setValue(user, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        Toast.makeText(MainActivity.this,"Data Sent",Toast.LENGTH_SHORT).show();
                        phone.setText(null);
                        email.setText(null);
                    }
                });
            }
        });
        bView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ViewActivity.class));
            }
        });

    }
}
