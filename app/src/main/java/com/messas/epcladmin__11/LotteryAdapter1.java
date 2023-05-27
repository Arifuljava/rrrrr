package com.messas.epcladmin__11;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class LotteryAdapter1 extends RecyclerView.Adapter<LotteryAdapter1.myview> {
    public List<Lottery_Model> data;
    FirebaseFirestore firebaseFirestore;

    public LotteryAdapter1(List<Lottery_Model> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lottery_image,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, final int position) {
        holder.customer_name.setText("Username : "+data.get(position).getUsername()+"\nLottery Name : "+data.get(position).getLottery_name()+"\n"
               +" Lottery Price :"+data.get(position).getLottery_price()+"\nLottery Winner Prize : "+data.get(position).getLotteryPrize());




    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder{
        TextView customer_name,customer_number,customer_area,logout;
        public myview(@NonNull View itemView) {
            super(itemView);
           customer_name=itemView.findViewById(R.id.lottery_card);
        }
    }
}

