package com.product.bookmyvenue.activity.derived;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.product.bookmyvenue.R;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference ref = storage.getReference().child("data");

        String myString = "this is my example string.";

        byte myBytes[] = myString.getBytes();

        ref.putBytes(myBytes);


    }

    @Override
    public void onBackPressed() {

        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference ref = storage.getReference().child("data");

        ref.getBytes(1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {

                String myString = bytes.toString();
                Toast.makeText(DashboardActivity.this, myString, Toast.LENGTH_SHORT).show();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

                Toast.makeText(DashboardActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
