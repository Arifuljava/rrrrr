package com.messas.epcladmin__11;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class AdapterSub extends RecyclerView.Adapter<AdapterSub.myview> {
    public List<Sublime1> data;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    public AdapterSub(List<Sublime1> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.subbb,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, final int position) {
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();
        holder.customer_name.setText(data.get(position).getName());
        holder.customer_number.setText(data.get(position).getDate());
        holder.logout.setText(data.get(position).getAmmount()+" Taka");
        holder.card_view8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("All_counter2")
                        .document(data.get(position).getName().toLowerCase()+"@gmail.com")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                               if(task.isSuccessful()) {
                                   if (task.getResult().exists()) {
                                       String counter=task.getResult().getString("count");
                                       firebaseFirestore.collection("Total_Imcone")
                                               .document(data.get(position).getName().toLowerCase()+"@gmail.com")
                                               .get()
                                               .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                   @Override
                                                   public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                       if (task.isSuccessful()) {
                                                           if (task.getResult().exists()) {
                                                               String ammm=task.getResult().getString("ammount");
                                                               firebaseFirestore.collection("Total_Income")
                                                                       .document(data.get(position).getName().toLowerCase()+"@gmail.com")
                                                                       .get()
                                                                       .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                           @Override
                                                                           public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                               if (task.isSuccessful()) {
                                                                                   if (task.getResult().exists()) {
                                                                                       String ammount=task.getResult().getString("ammount");
                                                                                       String message="Total Income : "+ammount+"\n" +
                                                                                               "Total Withdraw : "+ammm+"\n" +
                                                                                               "Account Age : "+counter;
                                                                                       AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                                                                                       builder.setTitle("User Details")
                                                                                               .setMessage(message)
                                                                                               .setPositiveButton("Okey", new DialogInterface.OnClickListener() {
                                                                                                   @Override
                                                                                                   public void onClick(DialogInterface dialogInterface, int i) {
                                                                                                       dialogInterface.dismiss();
                                                                                                   }
                                                                                               }).create().show();
                                                                                   }
                                                                               }
                                                                           }
                                                                       });
                                                           }
                                                       }
                                                   }
                                               });

                                   }
                               }
                            }
                        });


            }
        });

holder.card_view8.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View view) {
        String option[]={"Show Profile ","Show Referral"};
        AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
        builder.setTitle("Show")
                .setItems(option, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i==0) {
                            dialogInterface.dismiss();
                           /*
                            Intent intent=new Intent(view.getContext(),MainAccountActivity.class);
                            intent.putExtra("name",data.get(position).getName().toLowerCase()+"@gmail.com");
                            view.getContext().startActivity(intent);
                            */
                        }
                        else if (i==1) {
                            dialogInterface.dismiss();
                            /*
                            Intent intent=new Intent(view.getContext(),MYTeam2Activity.class);
                            intent.putExtra("name",data.get(position).getName().toLowerCase()+"@gmail.com");
                            view.getContext().startActivity(intent);
                             */
                        }

                    }
                }).create().show();

        return false;
    }
});


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder{
        TextView customer_name,customer_number,customer_area,logout,customer_area3,customer_area8;
        CardView card_view8;
        public myview(@NonNull View itemView) {
            super(itemView);
            customer_name=itemView.findViewById(R.id.customer_name);
            customer_number=itemView.findViewById(R.id.customer_number);
            logout=itemView.findViewById(R.id.logout);
            card_view8=itemView.findViewById(R.id.card_view8);
        }
    }
}


