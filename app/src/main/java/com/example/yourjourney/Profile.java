package com.example.yourjourney;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Profile extends AppCompatActivity {


    FirebaseAuth auth;

    String currentPhotoPath;

    String imageFileName;

    ImageView imageView;

    DatabaseReference databaseReference;
    StorageReference storageReference;
    Uri contentUri;

    EditText firstName, lastName, bio;

    Dialog dialog;

    User user;

    Button saveBtn;

    String userId;

    public static final int GALLERY_PERMISSION_CODE = 105;

    public static final int CAMERA_PERMISSION_CODE = 101;

    public static final int CAMERA_REQUEST_CODE = 102;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);




        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        storageReference = FirebaseStorage.getInstance().getReference("Users/"+auth.getCurrentUser().getUid());
        firstName = findViewById(R.id.etFirstName);
        lastName = findViewById(R.id.etLastName);
        bio = findViewById(R.id.etBio);



        DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference("Users/"+userId);
        myDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                if(user!=null){
                    firstName.setText(user.getFirstName());
                    //Values.NAME = user.getFirstName();
                    lastName.setText(user.getLastName());
                    bio.setText(user.getBio());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        storageReference = FirebaseStorage.getInstance().getReference("Users/"+auth.getCurrentUser().getUid());







    }



    public void saveButtonClicked(View v){

        showProgressBar();

        String first_name = ""+firstName.getText();
        String last_name = ""+lastName.getText();
        String bio_text = ""+bio.getText();



        user = new User(first_name, last_name, bio_text);

        if(userId!=null){
            databaseReference.child(userId).setValue(user).addOnCompleteListener(
                    new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){

                                if(contentUri!=null){

                                }
                                else{
                                    hideProgressBar();
                                     }



                                //  Toast.makeText(ViewProfile.this, "Update Successful", Toast.LENGTH_SHORT).show();

                            }else{
                                hideProgressBar();
                                Toast.makeText(Profile.this, "Update Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            );
        }


    }


    public void showProgressBar(){
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_wait);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void hideProgressBar()
    {
        dialog.dismiss();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void imageViewClicked(View view){
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        }
        else
        {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }


        if(requestCode==GALLERY_PERMISSION_CODE){

            if(resultCode== Activity.RESULT_OK){

                contentUri = data.getData();
                Toast.makeText(this, ""+contentUri, Toast.LENGTH_SHORT).show();
                imageView.setImageURI(null);
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                imageView.setImageURI(contentUri);

            }
        }
    }





}