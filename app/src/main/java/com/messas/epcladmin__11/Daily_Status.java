package com.messas.epcladmin__11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Daily_Status extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    TextView detailsbasic,dde,dde1;
    Button login_btn_details,login_btn_details__;
    String daily,monthly;
    String usernams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily__status);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Transcation Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10.0f);
        detailsbasic=findViewById(R.id.detailsbasic);
        dde=findViewById(R.id.dde);
        dde1=findViewById(R.id.dde1);
        try {
            detailsbasic.setText("Registration Balance : "+getIntent().getStringExtra("registration"));
            dde.setText("Shopping Balance : "+getIntent().getStringExtra("shopping"));
            dde1.setText("Main Balance : "+getIntent().getStringExtra("main"));
        }catch (Exception e) {
            detailsbasic.setText("Registration Balance : "+getIntent().getStringExtra("registration"));
            dde.setText("Shopping Balance : "+getIntent().getStringExtra("shopping"));
            dde1.setText("Main Balance : "+getIntent().getStringExtra("main"));
        }

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