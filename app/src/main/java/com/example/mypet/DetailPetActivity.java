package com.example.mypet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailPetActivity extends AppCompatActivity {

    TextView txtDetailNama, txtDetailHewan, txtDetailJenis, txtDetailRas;
    Pet pet;

    FirebaseFirestore fireDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pet);

        txtDetailHewan = findViewById(R.id.txtDetailHewan);
        txtDetailJenis = findViewById(R.id.txtDetailJenis);
        txtDetailRas = findViewById(R.id.txtDetailRas);
        txtDetailNama = findViewById(R.id.txtNamaDetail);

        Intent it = getIntent();
        pet = (Pet) it.getSerializableExtra("current_pet");
        txtDetailNama.setText(pet.email);
        txtDetailHewan.setText(pet.hewan);
        txtDetailJenis.setText(pet.jenis);
        txtDetailRas.setText(pet.ras);

        fireDb=FirebaseFirestore.getInstance();
    }

    public void close(View v){
        finish();
    }

    public void deletePet(View v){
        fireDb.collection("pet").document(pet.uid).delete()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("error_delete",e.getMessage());
                    }
                });
        finish();
    }
}