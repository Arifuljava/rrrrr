package com.messas.epcladmin__11;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class LotteryAdapter extends RecyclerView.Adapter<LotteryAdapter.myview> {
    public List<LotteryModel> data;
    FirebaseFirestore firebaseFirestore;

    public LotteryAdapter(List<LotteryModel> data) {
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
        holder.customer_name.setText("Lottery Name : "+data.get(position).getLottery_name()+"\n"
               +" Lottery Price :"+data.get(position).getLottery_Price()+"\nLottery Winner Prize : "+data.get(position).getLottery_Price());

holder.dddddd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
        builder.setTitle("Conform")
                .setMessage("Are You  Want To See List")
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Intent intent=new Intent(view.getContext(),LotteryList.class);
                intent.putExtra("username",data.get(position).getLottery_name().toLowerCase().toString());
                view.getContext().startActivity(intent);

            }
        }).create().show();
    }
});


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder{
        TextView customer_name,customer_number,customer_area,logout;
        CardView dddddd;
        public myview(@NonNull View itemView) {
            super(itemView);
           customer_name=itemView.findViewById(R.id.lottery_card);
            dddddd=itemView.findViewById(R.id.dddddd);
        }
    }
}

