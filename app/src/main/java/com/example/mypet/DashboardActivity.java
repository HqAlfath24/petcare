package com.example.mypet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class DashboardActivity extends AppCompatActivity {

    RecyclerView recPet;
    String namaUser;

    FirebaseUser user;
    FirebaseAuth mAuth;

    FirebaseFirestore fireDb;
    PetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initFab();

        recPet = findViewById(R.id.rec_pet);
        recPet.setLayoutManager(new LinearLayoutManager(this));

        fireDb = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        namaUser = user.getEmail();

        Snackbar.make(toolbar, "Anda login sebagai: "+namaUser, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        Query query = fireDb.collection("Pet").whereEqualTo("userId", user.getUid());

        FirestoreRecyclerOptions<Pet>options=new FirestoreRecyclerOptions.Builder<Pet>()
                .setQuery(query,Pet.class).build();
        adapter = new PetAdapter(options);
        recPet.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initFab(){
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), FormPetActivity.class));
            }
        });

        FloatingActionButton fabLogOff = findViewById(R.id.fabLogoff);
        fabLogOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(getBaseContext(), MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
    }

}