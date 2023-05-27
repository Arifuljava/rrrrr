package com.messas.epcladmin__11;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MainPackageAdapter extends RecyclerView.Adapter<MainPackageAdapter.myview> {
    public List<BloodBankModel> data;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    public MainPackageAdapter(List<BloodBankModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.papa, parent, false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, final int position) {
        firebaseFirestore= FirebaseFirestore.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();

        holder.customer_name.setText("Package  Name : " + data.get(position).getName());
        holder.customer_number.setText("Package  Price : " + data.get(position).getFeeentry());
        holder.customer_area.setText("Daily Bonus : "+data.get(position).getFeeprice());
        holder.logout.setText("Refer Bonus : "+data.get(position).getTime());




    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder {
        TextView customer_name, customer_number, customer_area, logout;
        CardView card_view8;
        ImageView image;

        public myview(@NonNull View itemView) {
            super(itemView);
            customer_name = itemView.findViewById(R.id.customer_name);
            customer_number = itemView.findViewById(R.id.customer_number);
            customer_area = itemView.findViewById(R.id.customer_area);
            logout = itemView.findViewById(R.id.logout);
            card_view8=itemView.findViewById(R.id.card_view8);
            image=itemView.findViewById(R.id.image);

        }
    }
}