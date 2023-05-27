package com.messas.epcladmin__11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailsActivity2 extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    TextView detailsbasic,dde;
    Button login_btn_details,login_btn_details__;
    String daily,monthly;
    String usernams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10.0f);
        detailsbasic=findViewById(R.id.detailsbasic);
        login_btn_details=findViewById(R.id.login_btn_details);
        dde=findViewById(R.id.dde);
        login_btn_details__=findViewById(R.id.login_btn_details__);

        try {
            daily=getIntent().getStringExtra("daily");
            monthly=getIntent().getStringExtra("monthly");
            detailsbasic.setText("Paid Refer : "+monthly);
            dde.setText("Free Refer : "+daily);
            usernams=getIntent().getStringExtra("name");

        }catch (Exception e) {
            daily=getIntent().getStringExtra("daily");
            monthly=getIntent().getStringExtra("monthly");
            detailsbasic.setText("Paid Refer : "+monthly);
            dde.setText("Free Refer : "+daily);
            usernams=getIntent().getStringExtra("name");
        }
        login_btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ReferList2.class);
                intent.putExtra("daily1",""+ "Paid_Month");
                intent.putExtra("name",usernams);
                intent.putExtra("daily",daily);
                intent.putExtra("monthly",monthly);
                startActivity(intent);

            }
        });
        login_btn_details__.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ReferList2.class);
                intent.putExtra("daily1",""+ "Free_Daily");
                intent.putExtra("name",usernams);
                intent.putExtra("daily",daily);
                intent.putExtra("monthly",monthly);
                startActivity(intent);
            }
        });
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