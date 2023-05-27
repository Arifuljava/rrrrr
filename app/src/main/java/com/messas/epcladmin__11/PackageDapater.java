package com.messas.epcladmin__11;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class PackageDapater extends  RecyclerView.Adapter<PackageDapater.myview> {
    public List<DetailsChecker> data;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    public PackageDapater(List<DetailsChecker> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.deletefile,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, final int position) {
        holder.cardcategory.setText(data.get(position).getCounter()+"( "+data.get(position).getPackage__checker()+" )");



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder{
        TextView customer_name,customer_number,customer_area,logout,cardcategory;
        public myview(@NonNull View itemView) {
            super(itemView);
            cardcategory=itemView.findViewById(R.id.cardcategory);


        }
    }
}
