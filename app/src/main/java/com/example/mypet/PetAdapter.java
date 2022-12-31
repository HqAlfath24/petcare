package com.example.mypet;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class PetAdapter extends FirestoreRecyclerAdapter<Pet,PetAdapter.ViewHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PetAdapter(@NonNull FirestoreRecyclerOptions<Pet> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Pet model) {
        holder.txtNama.setText(model.email);
        holder.txtHewan.setText(model.hewan);
        holder.txtJenis.setText(model.jenis);
        holder.txtRas.setText(model.ras);

        String uid = getSnapshots().getSnapshot(position).getId();
        model.uid=uid;

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.itemLayout.getContext();
                Intent it = new Intent(context, DetailPetActivity.class);
                it.putExtra("current_pet",model);
                context.startActivity(it);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.pet_item,parent,false));
        return holder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtHewan, txtJenis, txtRas, txtNama;
        public ConstraintLayout itemLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtHewan = itemView.findViewById(R.id.txtItemPet);
            txtJenis = itemView.findViewById(R.id.txtItemJenis);
            txtRas = itemView.findViewById(R.id.txtItemRas);
            txtNama = itemView.findViewById(R.id.txtItemNama);
            itemLayout = itemView.findViewById(R.id.item_layout);
        }
    }
}