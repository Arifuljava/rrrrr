package com.messas.epcladmin__11;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
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
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;
import com.thecode.aestheticdialogs.OnDialogClickListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BonusAdapter extends RecyclerView.Adapter<BonusAdapter.myView> {
    private List<Bonus_Model> data;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    public BonusAdapter(List<Bonus_Model> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_pacvkage, parent, false);
        return new myView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myView holder, final int position) {
firebaseAuth=FirebaseAuth.getInstance();
firebaseFirestore=FirebaseFirestore.getInstance();
holder.customer_name.setText("Username : "+data.get(position).getPackage_()+"\n" +
        "Activate Plan : "+data.get(position).getPackage_name()+"\n" +
        "Daily Income : "+data.get(position).getDaily_income());
//daily checking
        Calendar calendar=Calendar.getInstance();
        final  int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        firebaseFirestore.collection("DailyEarn")
                .document(data.get(position).getEmail())
                .collection(""+year)
                .document(""+month)
                .collection(""+day)
                .document(data.get(position).getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                holder.card_view8.setVisibility(View.GONE);
                            }
                            else {
                                holder.card_view8.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
holder.card_view8.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
builder.setTitle("Confirmation")
        .setMessage("Are you want to give daily bonus?")
        .setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
dialog.dismiss();
        firebaseFirestore.collection("Packages_Checker")
                .document(data.get(position).getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                String active=task.getResult().getString("counter");
                                String tyepe=task.getResult().getString("daily_income");
                                if (active.equals("1")) {
                                    Calendar calendar=Calendar.getInstance();
                                    final  int year=calendar.get(Calendar.YEAR);
                                    final int month=calendar.get(Calendar.MONTH);
                                    final int day=calendar.get(Calendar.DAY_OF_MONTH);
                                    firebaseFirestore.collection("DailyEarn")
                                            .document(data.get(position).getEmail())
                                            .collection(""+year)
                                            .document(""+month)
                                            .collection(""+day)
                                            .document(data.get(position).getEmail())
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        if (task.getResult().exists()) {
                                                            new AestheticDialog.Builder((Activity) v.getContext(), DialogStyle.FLASH, DialogType.WARNING)
                                                                    .setTitle("Warning")
                                                                    .setMessage("You are alreday clime  task for today.\nWait for next day's task.")
                                                                    .setAnimation(DialogAnimation.SPLIT)
                                                                    .setOnClickListener(new OnDialogClickListener() {
                                                                        @Override
                                                                        public void onClick(AestheticDialog.Builder builder) {
                                                                            builder.dismiss();

                                                                        }
                                                                    }).show();

                                                        }
                                                        else {

                                                            firebaseFirestore.collection("Counttter")
                                                                    .document(data.get(position).getEmail())
                                                                    .get()
                                                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                            if (task.isSuccessful()) {
                                                                                if (task.getResult().exists()) {
                                                                                    String counter=task.getResult().getString("counter");
                                                                                    int  n=Integer.parseInt(counter)+1;
                                                                                    if (Integer.parseInt(counter)==45) {
                                                                                        new AestheticDialog.Builder((Activity) v.getContext(), DialogStyle.FLASH, DialogType.ERROR)
                                                                                                .setTitle("Information")
                                                                                                .setMessage("You have collect all activation investment on current deposit package.\nPlease active a invest.")
                                                                                                .setAnimation(DialogAnimation.SPLIT)
                                                                                                .setOnClickListener(new OnDialogClickListener() {
                                                                                                    @Override
                                                                                                    public void onClick(AestheticDialog.Builder builder) {
                                                                                                        builder.dismiss();

                                                                                                    }
                                                                                                }).show();
                                                                                    }
                                                                                    else if (n==45){

                                                                                        Handler handler=new Handler();
                                                                                        handler.postDelayed(new Runnable() {
                                                                                            @Override
                                                                                            public void run() {
                                                                                                new AestheticDialog.Builder((Activity) v.getContext(), DialogStyle.FLASH, DialogType.SUCCESS)
                                                                                                        .setTitle("Success")
                                                                                                        .setMessage(" Give daily bonus.")
                                                                                                        .setAnimation(DialogAnimation.SPLIT)
                                                                                                        .setOnClickListener(new OnDialogClickListener() {
                                                                                                            @Override
                                                                                                            public void onClick(AestheticDialog.Builder builder) {
                                                                                                                builder.dismiss();
                                                                                                                firebaseFirestore.collection("Counttter")
                                                                                                                        .document(data.get(position).getEmail())
                                                                                                                        .update("counter",""+n)
                                                                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                            @Override
                                                                                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                                                                            }
                                                                                                                        });
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
                                                                                                                                        try {

                                                                                                                                            String taka = task.getResult().getString("self_income");
                                                                                                                                            double total=Double.parseDouble(taka)+Double.parseDouble(tyepe);
                                                                                                                                            String taka_daily_income = task.getResult().getString("daily_income");
                                                                                                                                            double total_daily_income=Double.parseDouble(taka_daily_income)+Double.parseDouble(tyepe);
                                                                                                                                            firebaseFirestore.collection("Users")
                                                                                                                                                    .document(data.get(position).getUuid())
                                                                                                                                                    .collection("Main_Balance")
                                                                                                                                                    .document(data.get(position).getEmail())
                                                                                                                                                    .update("self_income",""+total,"daily_income",""+total_daily_income)
                                                                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                        @Override
                                                                                                                                                        public void onComplete(@NonNull Task<Void> task) {

                                                                                                                                                        }
                                                                                                                                                    });
                                                                                                                                            Calendar calendar = Calendar.getInstance();

                                                                                                                                            firebaseFirestore.collection("Users")
                                                                                                                                                    .document(data.get(position).getUuid())
                                                                                                                                                    .get()
                                                                                                                                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                                                                                        @Override
                                                                                                                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                                                                                            if (task.isSuccessful()) {
                                                                                                                                                                if (task.getResult().exists()) {
                                                                                                                                                                    Calendar calendar = Calendar.getInstance();
                                                                                                                                                                    final int year = calendar.get(Calendar.YEAR);
                                                                                                                                                                    final int month = calendar.get(Calendar.MONTH);
                                                                                                                                                                    final int day = calendar.get(Calendar.DAY_OF_MONTH);
                                                                                                                                                                    Map<String, String> userMap1 = new HashMap<>();

                                                                                                                                                                    userMap1.put("counter", data.get(position).getEmail());
                                                                                                                                                                    int a = 16;
                                                                                                                                                                    int b = 8;
                                                                                                                                                                    firebaseFirestore.collection("Packages_Checker")
                                                                                                                                                                            .document(data.get(position).getEmail())
                                                                                                                                                                            .delete()
                                                                                                                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                                                @Override
                                                                                                                                                                                public void onComplete(@NonNull Task<Void> task) {

                                                                                                                                                                                }
                                                                                                                                                                            });


                                                                                                                                                                    //todays_free(firebaseAuth.getCurrentUser().getEmail().toString());
                                                                                                                                                                    ///generation_bonus1("Gold", firebaseAuth.getCurrentUser().getEmail(), firebaseAuth.getCurrentUser().getUid(), "ariful", "TeamB", "TeamB", 24, a, b);
                                                                                                                                                                    firebaseFirestore.collection("DailyEarn")
                                                                                                                                                                            .document(data.get(position).getEmail())
                                                                                                                                                                            .collection("" + year)
                                                                                                                                                                            .document("" + month)
                                                                                                                                                                            .collection("" + day)
                                                                                                                                                                            .document(data.get(position).getEmail())
                                                                                                                                                                            .set(userMap1)
                                                                                                                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                                                @Override
                                                                                                                                                                                public void onComplete(@NonNull Task<Void> task) {

                                                                                                                                                                                }
                                                                                                                                                                            });



                                                                                                                                                                    Long tsLong = System.currentTimeMillis()/1000;
                                                                                                                                                                    String ts = tsLong.toString();
                                                                                                                                                                    String current = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                                                                                                                                                                    String current1 = DateFormat.getDateInstance().format(calendar.getTime());
                                                                                                                                                                    Income income = new Income(task.getResult().getString("username"), "Daily  Income", data.get(position).getEmail(),
                                                                                                                                                                            ""+tyepe, current1,ts,"Ltsx"+ts,"0");
                                                                                                                                                                    String username=task.getResult().getString("username");

                                                                                                                                                                    firebaseFirestore.collection("Income_History").document(data.get(position).getEmail())
                                                                                                                                                                            .collection("List")
                                                                                                                                                                            .document(UUID.randomUUID().toString())
                                                                                                                                                                            .set(income)
                                                                                                                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                                                @Override
                                                                                                                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                                                    if (task.isSuccessful()) {

                                                                                                                                                                                        new AestheticDialog.Builder((Activity) v.getContext(), DialogStyle.FLASH, DialogType.SUCCESS)
                                                                                                                                                                                                .setTitle("Successful")
                                                                                                                                                                                                .setMessage("You are successfully  give this task for today.\nWait for next day's task.")
                                                                                                                                                                                                .setAnimation(DialogAnimation.SPLIT)
                                                                                                                                                                                                .setOnClickListener(new OnDialogClickListener() {
                                                                                                                                                                                                    @Override
                                                                                                                                                                                                    public void onClick(AestheticDialog.Builder builder) {
                                                                                                                                                                                                        builder.dismiss();
                                                                                                                                                                                                        v.getContext().startActivity(new Intent(v.getContext(),HomeActivity.class));



                                                                                                                                                                                                    }
                                                                                                                                                                                                }).show();





                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            });
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    });




                                                                                                                                        } catch (Exception e) {

                                                                                                                                            String taka = task.getResult().getString("main_balance");

                                                                                                                                        }
                                                                                                                                    } else {

                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        });







                                                                                                            }


                                                                                                        }).show();

                                                                                            }
                                                                                        },0);
                                                                                    }
                                                                                    else  if (Integer.parseInt(counter)<45) {

                                                                                        Handler handler=new Handler();
                                                                                        handler.postDelayed(new Runnable() {
                                                                                            @Override
                                                                                            public void run() {
                                                                                                new AestheticDialog.Builder((Activity) v.getContext(), DialogStyle.FLASH, DialogType.SUCCESS)
                                                                                                        .setTitle("Success")
                                                                                                        .setMessage("Give daily bonus.")
                                                                                                        .setAnimation(DialogAnimation.SPLIT)
                                                                                                        .setOnClickListener(new OnDialogClickListener() {
                                                                                                            @Override
                                                                                                            public void onClick(AestheticDialog.Builder builder) {
                                                                                                                builder.dismiss();
                                                                                                                firebaseFirestore.collection("Counttter")
                                                                                                                        .document(data.get(position).getEmail())
                                                                                                                        .update("counter",""+n)
                                                                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                            @Override
                                                                                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                                                                            }
                                                                                                                        });
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
                                                                                                                                        try {

                                                                                                                                            String taka = task.getResult().getString("self_income");
                                                                                                                                            double total=Double.parseDouble(taka)+Double.parseDouble(tyepe);
                                                                                                                                            String taka_daily_income = task.getResult().getString("daily_income");
                                                                                                                                            double total_daily_income=Double.parseDouble(taka_daily_income)+Double.parseDouble(tyepe);
                                                                                                                                            firebaseFirestore.collection("Users")
                                                                                                                                                    .document(data.get(position).getUuid())
                                                                                                                                                    .collection("Main_Balance")
                                                                                                                                                    .document(data.get(position).getEmail())
                                                                                                                                                    .update("self_income",""+total,"daily_income",""+total_daily_income)
                                                                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                        @Override
                                                                                                                                                        public void onComplete(@NonNull Task<Void> task) {

                                                                                                                                                        }
                                                                                                                                                    });
                                                                                                                                            Calendar calendar = Calendar.getInstance();

                                                                                                                                            firebaseFirestore.collection("Users")
                                                                                                                                                    .document(data.get(position).getUuid())
                                                                                                                                                    .get()
                                                                                                                                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                                                                                        @Override
                                                                                                                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                                                                                            if (task.isSuccessful()) {
                                                                                                                                                                if (task.getResult().exists()) {
                                                                                                                                                                    Calendar calendar = Calendar.getInstance();
                                                                                                                                                                    final int year = calendar.get(Calendar.YEAR);
                                                                                                                                                                    final int month = calendar.get(Calendar.MONTH);
                                                                                                                                                                    final int day = calendar.get(Calendar.DAY_OF_MONTH);
                                                                                                                                                                    Map<String, String> userMap1 = new HashMap<>();

                                                                                                                                                                    userMap1.put("counter", data.get(position).getEmail());
                                                                                                                                                                    int a = 16;
                                                                                                                                                                    int b = 8;


                                                                                                                                                                    //todays_free(firebaseAuth.getCurrentUser().getEmail().toString());
                                                                                                                                                                    ///generation_bonus1("Gold", firebaseAuth.getCurrentUser().getEmail(), firebaseAuth.getCurrentUser().getUid(), "ariful", "TeamB", "TeamB", 24, a, b);
                                                                                                                                                                    firebaseFirestore.collection("DailyEarn")
                                                                                                                                                                            .document(data.get(position).getEmail())
                                                                                                                                                                            .collection("" + year)
                                                                                                                                                                            .document("" + month)
                                                                                                                                                                            .collection("" + day)
                                                                                                                                                                            .document(data.get(position).getEmail())
                                                                                                                                                                            .set(userMap1)
                                                                                                                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                                                @Override
                                                                                                                                                                                public void onComplete(@NonNull Task<Void> task) {

                                                                                                                                                                                }
                                                                                                                                                                            });



                                                                                                                                                                    Long tsLong = System.currentTimeMillis()/1000;
                                                                                                                                                                    String ts = tsLong.toString();
                                                                                                                                                                    String current = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                                                                                                                                                                    String current1 = DateFormat.getDateInstance().format(calendar.getTime());
                                                                                                                                                                    Income income = new Income(task.getResult().getString("username"), "Daily  Income", data.get(position).getEmail(),
                                                                                                                                                                            ""+tyepe, current1,ts,"Ltsx"+ts,"0");
                                                                                                                                                                    String username=task.getResult().getString("username");
                                                                                                                                                                    firebaseFirestore.collection("Income_History").document(data.get(position).getEmail())
                                                                                                                                                                            .collection("List")
                                                                                                                                                                            .document(UUID.randomUUID().toString())
                                                                                                                                                                            .set(income)
                                                                                                                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                                                @Override
                                                                                                                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                                                    if (task.isSuccessful()) {

                                                                                                                                                                                        new AestheticDialog.Builder((Activity) v.getContext(), DialogStyle.FLASH, DialogType.SUCCESS)
                                                                                                                                                                                                .setTitle("Successful")
                                                                                                                                                                                                .setMessage("You are successfully  give this task for today.\nWait for next day's task.")
                                                                                                                                                                                                .setAnimation(DialogAnimation.SPLIT)
                                                                                                                                                                                                .setOnClickListener(new OnDialogClickListener() {
                                                                                                                                                                                                    @Override
                                                                                                                                                                                                    public void onClick(AestheticDialog.Builder builder) {
                                                                                                                                                                                                        builder.dismiss();
                                                                                                                                                                                                       v.getContext().startActivity(new Intent(v.getContext(),HomeActivity.class));



                                                                                                                                                                                                    }
                                                                                                                                                                                                }).show();





                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            });
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    });




                                                                                                                                        } catch (Exception e) {

                                                                                                                                            String taka = task.getResult().getString("main_balance");

                                                                                                                                        }
                                                                                                                                    } else {

                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        });







                                                                                                            }


                                                                                                        }).show();

                                                                                            }
                                                                                        },0);

                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    });


                                                        }
                                                    }
                                                    else {
                                                        new AestheticDialog.Builder((Activity) v.getContext(), DialogStyle.FLASH, DialogType.ERROR)
                                                                .setTitle("Failed")
                                                                .setMessage("You havn't any activation investment.\nPlease active a invest.")
                                                                .setAnimation(DialogAnimation.SPLIT)
                                                                .setOnClickListener(new OnDialogClickListener() {
                                                                    @Override
                                                                    public void onClick(AestheticDialog.Builder builder) {
                                                                        builder.dismiss();

                                                                    }
                                                                }).show();
                                                    }
                                                }
                                            });


                                }
                                else {
                                    new AestheticDialog.Builder((Activity) v.getContext(), DialogStyle.FLASH, DialogType.ERROR)
                                            .setTitle("Failed")
                                            .setMessage("You havn't any activation investment.\nPlease active a invest.")
                                            .setAnimation(DialogAnimation.SPLIT)
                                            .setOnClickListener(new OnDialogClickListener() {
                                                @Override
                                                public void onClick(AestheticDialog.Builder builder) {
                                                    builder.dismiss();

                                                }
                                            }).show();

                                }
                            }
                            else {
                                new AestheticDialog.Builder((Activity) v.getContext(), DialogStyle.FLASH, DialogType.ERROR)
                                        .setTitle("Failed")
                                        .setMessage("You havn't any activation investment.\nPlease active a invest.")
                                        .setAnimation(DialogAnimation.SPLIT)
                                        .setOnClickListener(new OnDialogClickListener() {
                                            @Override
                                            public void onClick(AestheticDialog.Builder builder) {
                                                builder.dismiss();

                                            }
                                        }).show();
                            }
                        }
                    }
                });
    }
}).create().show();
    }
});



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myView extends RecyclerView.ViewHolder {
       TextView customer_name;
       CardView card_view8;

        public myView(@NonNull View itemView) {

            super(itemView);
            customer_name=itemView.findViewById(R.id.my_details);
card_view8=itemView.findViewById(R.id.dds);
        }
    }
}
