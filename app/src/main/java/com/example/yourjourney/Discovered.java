package com.example.yourjourney;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Discovered extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    DiscoveredClassAdapter discoveredClassAdapter;
    ArrayList<DiscoveredPlace> places;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovered);

        mRecyclerView = findViewById(R.id.discoveredRV);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("discovered");

        places = new ArrayList<>();
        discoveredClassAdapter = new DiscoveredClassAdapter(this, places);
        mRecyclerView.setAdapter(discoveredClassAdapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    DiscoveredPlace hotel = dataSnapshot.getValue(DiscoveredPlace.class);
                    places.add(hotel);
                }
                discoveredClassAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void addDiscoveredClicked(View v){
        Intent intent = new Intent(this, AddDiscovered

                .class);
        startActivity(intent);
    }
}