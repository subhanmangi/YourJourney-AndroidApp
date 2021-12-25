package com.example.yourjourney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void hotelsClicked(View v){
        Intent intent = new Intent(this, Hotels.class);
        startActivity(intent);
    }

    public void restaurantsClicked(View v){
        Intent intent = new Intent(this, Restaurants.class);
        startActivity(intent);
    }

    public void tourismClicked(View v){
        Intent intent = new Intent(this, Places.class);
        startActivity(intent);
    }

    public void utilityClicked(View v){
        Intent intent = new Intent(this, Utilities.class);
        startActivity(intent);
    }

    public void discoveredClicked(View v){
        Intent intent = new Intent(this, Discovered.class);
        startActivity(intent);
    }

    public void placesStoryClicked(View view){
        Intent intent = new Intent(this, Story.class);
        startActivity(intent);
    }

    public void profileClicked(View view){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void convertedClicked(View view){
        Intent intent = new Intent(this, Converter.class);
        startActivity(intent);
    }
}