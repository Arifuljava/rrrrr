package com.messas.epcladmin__11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import es.dmoral.toasty.Toasty;

public class Lottery_Set extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    EditText lotteryName,lottery_price,name,numberoflottery;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery__set);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Lottery Set");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10.0f);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        lotteryName=findViewById(R.id.lotteryName);
        lottery_price=findViewById(R.id.lottery_price);
        name=findViewById(R.id.name);
        numberoflottery=findViewById(R.id.numberoflottery);
        login_btn=findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loname=lotteryName.getText().toString();
                String loprice=lottery_price.getText().toString();
                String loprize=name.getText().toString();
                String number=numberoflottery.getText().toString();
                if (TextUtils.isEmpty(loprice)||TextUtils.isEmpty(loname)||TextUtils.isEmpty(loprize)||
                        TextUtils.isEmpty(number)) {
                    Toasty.error(getApplicationContext(),"Give Right Information",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                else {
                    final KProgressHUD progressDialog=  KProgressHUD.create(Lottery_Set.this)
                            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                            .setLabel("Please wait")
                            .setCancellable(false)
                            .setAnimationSpeed(2)
                            .setDimAmount(0.5f)
                            .show();
                    Long tsLong = System.currentTimeMillis()/1000;
                  String  ts = tsLong.toString();
                    LotteryModel lotteryModel=new LotteryModel(loname,loprice,loprize,number,ts);
                    firebaseFirestore.collection("LotteryName")
                            .add(lotteryModel)
                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if (task.isSuccessful()) {
                                        firebaseFirestore.collection("Lottery_main")
                                                .document("abc@gmail.com")
                                                .set(lotteryModel)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            progressDialog.dismiss();
                                                            Toasty.success(getApplicationContext(),"Done",Toasty.LENGTH_SHORT,true).show();


                                                        }
                                                    }
                                                });

                                    }

                                }
                            });
                }
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));

        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }

}