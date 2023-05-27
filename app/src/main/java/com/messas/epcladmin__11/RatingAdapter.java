package com.messas.epcladmin__11;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class RatingAdapter  extends RecyclerView.Adapter<RatingAdapter.myview> {
    public List<Rating_Model> data;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    public RatingAdapter(List<Rating_Model> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ratign,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, final int position) {
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();
        holder.customer_name.setText("Username : "+data.get(position).getUsername());
        holder.customer_number.setText("Rating : "+data.get(position).getRating());
        holder.customer_area.setText("Total Refer : "+data.get(position).getTotal_refer());
        holder.logout.setText(data.get(position).getRating());
        holder.card_view8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText input = new EditText(view.getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                input.setHint("Enter Balance ");
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Accivement Bonus")
                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                String tt=input.getText().toString();
                                if (TextUtils.isEmpty(tt)) {
                                    Toasty.error(view.getContext(),"Error",Toasty.LENGTH_SHORT,true).show();
                                }
                                else {
                                    final KProgressHUD progressDialog=  KProgressHUD.create(view.getContext())
                                            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                            .setLabel("Uploading  Data....")
                                            .setCancellable(false)
                                            .setAnimationSpeed(2)
                                            .setDimAmount(0.5f)
                                            .show();
                                    firebaseFirestore.collection("Users")
                                            .document(data.get(position).getUuid())
                                            .collection("Main_Balance")
                                            .document(data.get(position).getEmail())
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        if (task.getResult().exists()) {
                                                            String fifth_level=task.getResult().getString("fifth_level");
                                                            String main_balance=task.getResult().getString("main_balance");
                                                            int  submain_balance=Integer.parseInt(main_balance)+Integer.parseInt(input.getText().toString());
                                                            int subfifth_level=Integer.parseInt(fifth_level)+Integer.parseInt(input.getText().toString());
                                                            firebaseFirestore.collection("Users")
                                                                    .document(data.get(position).getUuid())
                                                                    .collection("Main_Balance")
                                                                    .document(data.get(position).getEmail())
                                                                    .update("fifth_level",""+subfifth_level,
                                                                            "main_balance",""+submain_balance)
                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                           if (task.isSuccessful()) {
                                                                               firebaseFirestore.collection("Rating_user")
                                                                                       .document(data.get(position).getEmail())
                                                                                       .delete()
                                                                                       .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                           @Override
                                                                                           public void onComplete(@NonNull Task<Void> task) {
                                                                                               if (task.isSuccessful()) {
                                                                                                   progressDialog.dismiss();
                                                                                                   Toast.makeText(view.getContext(), "Done", Toast.LENGTH_SHORT).show();
                                                                                                   view.getContext().startActivity(new Intent(view.getContext(),HomeActivity.class));
                                                                                               }
                                                                                           }
                                                                                       });
                                                                           }
                                                                        }
                                                                    });
                                                        }
                                                    }
                                                }
                                            });

                                }
                            }
                        })
                        .setIcon(R.drawable.global)
                        .setView(input)
                        .show();
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
            customer_area=itemView.findViewById(R.id.customer_area);
            logout=itemView.findViewById(R.id.logout);
            card_view8=itemView.findViewById(R.id.card_view8);

        }
    }
}
