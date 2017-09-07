package com.hungrydroid.firebaseapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    //TextView tv;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        //tv = (TextView)findViewById(R.id.textView);
        listView = (ListView) findViewById(R.id.lv);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message").child("users");
        ;
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //System.out.println(dataSnapshot.toString());
                //System.out.println(dataSnapshot.getKey());
                ArrayList<User> arrayOfUsers = new ArrayList<User>();
                //String value = dataSnapshot.getValue(String.class);
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    User u = singleSnapshot.getValue(User.class);

                    arrayOfUsers.add(u);
                    System.out.println(u.getEmail());
                }


                UsersAdapter adapter = new UsersAdapter(ViewActivity.this, arrayOfUsers);
                listView.setAdapter(adapter);
                //tv.setText(dataSnapshot.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //tv.setText("Failed to read value.");
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


}
