package com.messas.epcladmin__11;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ActivepackageAdapter extends RecyclerView.Adapter<ActivepackageAdapter.myview> {
    public List<BloodBankModel> data;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    Context context;

    public ActivepackageAdapter(List<BloodBankModel> data, Context context) {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.packagess,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, final int position) {
firebaseAuth= FirebaseAuth.getInstance();
firebaseFirestore= FirebaseFirestore.getInstance();

holder.logout.setText("Package name : "+data.get(position).getName()+"\n" +
        "Package Price : "+data.get(position).getFeeentry()+" BDT\n" +
        "Daily Income : "+data.get(position).getTime()+" BDT");
holder.dddddd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String options[]={"Update","Delete"};

        AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
        builder.setTitle("Options")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                         if(i==1) {
                            firebaseFirestore.collection("Packages").document(data.get(position).getUuid())
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                firebaseFirestore.collection("Packages").document(data.get(position).getUuid())
                                                        .delete()
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    Toast.makeText(view.getContext(), "Done", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        })  ;
                                            }
                                        }
                                    });
                        }
                        else if(i==0) {
                            String op[]={"Package Name","Package Price","Daily Income","Amount Gained By User"};
                            AlertDialog.Builder mybuilder=new AlertDialog.Builder(view.getContext());
                            mybuilder.setTitle("Update")
                                    .setItems(op, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            if (i==3) {
                                                final EditText input2 = new EditText(view.getContext());
                                                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                                        LinearLayout.LayoutParams.MATCH_PARENT);
                                                input2.setLayoutParams(lp2);
                                                input2.setGravity(Gravity.CENTER);
                                                input2.setHint("Enter text");

                                                new androidx.appcompat.app.AlertDialog.Builder(view.getContext())
                                                        .setTitle("Update")
                                                        .setMessage("Enter update format")
                                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                                if (TextUtils.isEmpty(input2.getText().toString()))
                                                                {
                                                                    Toast.makeText(view.getContext(), "Enter Values", Toast.LENGTH_SHORT).show();
                                                                }
                                                                else {

                                                                    firebaseFirestore.collection("Packages")
                                                                            .document(data.get(position).getUuid())
                                                                            .update("roomid",input2.getText().toString())
                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                    if (task.isSuccessful()) {
                                                                                        Toast.makeText(view.getContext(), "Done", Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                }
                                                                            });

                                                                }
                                                            }
                                                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.dismiss();
                                                    }
                                                }). setIcon(R.drawable.wage)
                                                        .setView(input2)
                                                        .show();
                                            }
                                          else  if (i==2) {
                                                final EditText input2 = new EditText(view.getContext());
                                                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                                        LinearLayout.LayoutParams.MATCH_PARENT);
                                                input2.setLayoutParams(lp2);
                                                input2.setGravity(Gravity.CENTER);
                                                input2.setHint("Enter text");

                                                new androidx.appcompat.app.AlertDialog.Builder(view.getContext())
                                                        .setTitle("Update")
                                                        .setMessage("Enter update format")
                                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                                if (TextUtils.isEmpty(input2.getText().toString()))
                                                                {
                                                                    Toast.makeText(view.getContext(), "Enter Values", Toast.LENGTH_SHORT).show();
                                                                }
                                                                else {

                                                                    firebaseFirestore.collection("Packages")
                                                                            .document(data.get(position).getUuid())
                                                                            .update("time",input2.getText().toString())
                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                    if (task.isSuccessful()) {
                                                                                        Toast.makeText(view.getContext(), "Done", Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                }
                                                                            });

                                                                }
                                                            }
                                                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.dismiss();
                                                    }
                                                }). setIcon(R.drawable.wage)
                                                        .setView(input2)
                                                        .show();
                                            }

                                            else if (i==1) {
                                                final EditText input2 = new EditText(view.getContext());
                                                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                                        LinearLayout.LayoutParams.MATCH_PARENT);
                                                input2.setLayoutParams(lp2);
                                                input2.setGravity(Gravity.CENTER);
                                                input2.setHint("Enter text");

                                                new androidx.appcompat.app.AlertDialog.Builder(view.getContext())
                                                        .setTitle("Update")
                                                        .setMessage("Enter update format")
                                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                                if (TextUtils.isEmpty(input2.getText().toString()))
                                                                {
                                                                    Toast.makeText(view.getContext(), "Enter Values", Toast.LENGTH_SHORT).show();
                                                                }
                                                                else {

                                                                    firebaseFirestore.collection("Packages")
                                                                            .document(data.get(position).getUuid())
                                                                            .update("feeentry",input2.getText().toString())
                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                    if (task.isSuccessful()) {
                                                                                        Toast.makeText(view.getContext(), "Done", Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                }
                                                                            });

                                                                }
                                                            }
                                                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.dismiss();
                                                    }
                                                }). setIcon(R.drawable.wage)
                                                        .setView(input2)
                                                        .show();
                                            }
                                            else  if (i==0) {
                                                final EditText input2 = new EditText(view.getContext());
                                                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                                        LinearLayout.LayoutParams.MATCH_PARENT);
                                                input2.setLayoutParams(lp2);
                                                input2.setGravity(Gravity.CENTER);
                                                input2.setHint("Enter text");

                                                new androidx.appcompat.app.AlertDialog.Builder(view.getContext())
                                                        .setTitle("Update")
                                                        .setMessage("Enter update format")
                                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                                if (TextUtils.isEmpty(input2.getText().toString()))
                                                                {
                                                                    Toast.makeText(view.getContext(), "Enter Values", Toast.LENGTH_SHORT).show();
                                                                }
                                                                else {

                                                                    firebaseFirestore.collection("Packages")
                                                                            .document(data.get(position).getUuid())
                                                                            .update("name",input2.getText().toString())
                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                    if (task.isSuccessful()) {
                                                                                        Toast.makeText(view.getContext(), "Done", Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                }
                                                                            });

                                                                }
                                                            }
                                                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.dismiss();
                                                    }
                                                }). setIcon(R.drawable.wage)
                                                        .setView(input2)
                                                        .show();
                                            }
                                        }
                                    }).create().show();
                        }
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
logout=itemView.findViewById(R.id.morei);
            dddddd=itemView.findViewById(R.id.dddddd);

        }
    }
}

