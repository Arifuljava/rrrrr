package com.messas.epcladmin__11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import es.dmoral.toasty.Toasty;

public class Valid_user extends AppCompatActivity {

    EditText name,phone,area,area11;
    Button login_btn;
    KProgressHUD progressHUD;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valid_user);
        firebaseAuth=FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Subadmin");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10.0f);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        area=findViewById(R.id.area);
        area11=findViewById(R.id.area11);
        login_btn=findViewById(R.id.login_btn);
        progressHUD=KProgressHUD.create(Valid_user.this);
        firebaseFirestore=FirebaseFirestore.getInstance();
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name1,phone1,category;
                name1=name.getText().toString();
                phone1=phone.getText().toString();
                category=area.getText().toString();
                if (TextUtils.isEmpty(name1)||TextUtils.isEmpty(phone1)||
                        TextUtils.isEmpty(category)) {
                    Toasty.error(getApplicationContext(),"Error", Toast.LENGTH_SHORT,true).show();
                    return;
                }
                else {
                  /*
                    progress_check();
                    Long tsLong = System.currentTimeMillis()/1000;
                    String ts = tsLong.toString();
                    String uuid= UUID.randomUUID().toString();
                    Help_Model help_model=new Help_Model(name1,category,phone1,uuid,area11.getText().toString().toLowerCase().toString(),ts);
                    firebaseFirestore.collection("ALl_Subadmin")
                            .add(help_model)
                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if (task.isSuccessful()) {
                                        progressHUD.dismiss();
                                        Toasty.success(getApplicationContext(),"Done",Toast.LENGTH_SHORT,true).show();
                                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                    }
                                }
                            });

                   */

                }
            }
        });
    }
    private void progress_check() {
        progressHUD=  KProgressHUD.create(Valid_user.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        return true;
    }
}