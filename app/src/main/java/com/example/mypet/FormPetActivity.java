package com.example.mypet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class FormPetActivity extends AppCompatActivity {

    EditText edtPet, edtJenis, edtRas;
    Image imgHewan;

    FirebaseFirestore firedb;
    FirebaseUser user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pet);

        edtPet = findViewById(R.id.edtPet);
        edtJenis = findViewById(R.id.edtJenis);
        edtRas = findViewById(R.id.edtRas);

        user= FirebaseAuth.getInstance().getCurrentUser();
        firedb=FirebaseFirestore.getInstance();

    }

    public void postPet(View v){
        String userID=user.getUid();
        String email= user.getEmail();
        String hewan=edtPet.getText().toString();
        String jenis=edtJenis.getText().toString();
        String ras=edtRas.getText().toString();

        firedb.collection("Pet").document()
                .set(new Pet(userID,email,hewan,jenis,ras))
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("store_error",e.getMessage());
                    }
                });
        finish();
    }

}