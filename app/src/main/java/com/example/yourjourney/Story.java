package com.example.yourjourney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Story extends AppCompatActivity {

    EditText message;

    String messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        messageText = "";

        message = findViewById(R.id.message);

    }

    public void sendMessage(View view){

        messageText = message.getText()+"";

        if(messageText.equals("")){
            Toast.makeText(this, "Message is empty, Write Something..", Toast.LENGTH_SHORT).show();
        }
        else{

            String number = "+97562033600";

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);

            Intent intent=new Intent(getApplicationContext(),Story.class);
            PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

            //Get the SmsManager instance and call the sendTextMessage method to send message
            SmsManager sms=SmsManager.getDefault();
            sms.sendTextMessage(number, null, messageText, pi,null);

            Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                    Toast.LENGTH_LONG).show();

            message.setText("");


        }

    }

    public void whatsapp(View view){

        String link ="https://wa.me/97562033600";
        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse(link));
        startActivity(viewIntent);

    }
}