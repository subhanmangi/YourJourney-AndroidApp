package com.example.yourjourney;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.yourjourney.Retrofit.RetrofitBuilder;
import com.example.yourjourney.Retrofit.RetrofitInterface;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Converter extends AppCompatActivity {
    Button button;
    EditText currencyToBeConverted;
    EditText currencyConverted;
    Spinner convertToDropdown;
    Spinner convertFromDropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        //Initialization
        currencyConverted = (EditText) findViewById(R.id.currency_converted);
        currencyToBeConverted = (EditText) findViewById(R.id.currency_to_be_converted);
        convertToDropdown = (Spinner) findViewById(R.id.convert_to);
        convertFromDropdown = (Spinner) findViewById(R.id.convert_from);
        button = (Button) findViewById(R.id.button);

        //Adding Functionality
        String[] dropDownList = {"AED", "USD","EUR","INR"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, dropDownList);

        String[] dropDownList2 = {"AED"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, dropDownList2);

        convertToDropdown.setAdapter(adapter);
        convertFromDropdown.setAdapter(adapter2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double value = Double.parseDouble(currencyToBeConverted.getText()+"");


                String to = convertToDropdown.getSelectedItem().toString();
                switch(to){
                    case "AED":
                        currencyConverted.setText(""+value);
                        break;
                    case "USD":
                        value*=3.672;
                        currencyConverted.setText(""+value);
                        break;
                    case "EUR":
                        value*=4.13;
                        currencyConverted.setText(""+value);
                        break;
                    case "INR":
                        value*=0.048677;
                        currencyConverted.setText(""+value);
                        break;
                }

            }
        });



    }
}