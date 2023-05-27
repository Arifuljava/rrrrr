package com.messas.epcladmin__11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class CalculatingDataActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    EditText add_notes_title, add_notes_title2, add_notes_ti6tle, add_notes_ti6tle11, add_notes_ti6tle1;
    Button submit;
    EditText makerules, bloas, bloas1, makerule1s, bloas1ss;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculating_data);
        Toolbar toolbar = findViewById(R.id.profile_toolbar);

        toolbar.setTitle("Daily Income details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10.0f);
        firebaseFirestore = FirebaseFirestore.getInstance();
        add_notes_title = findViewById(R.id.add_notes_title);
        makerules = findViewById(R.id.makerules);
        bloas = findViewById(R.id.bloas);
        bloas1 = findViewById(R.id.bloas1);
        makerule1s = findViewById(R.id.makerule1s);
        bloas1ss = findViewById(R.id.bloas1ss);

        add_notes_title2 = findViewById(R.id.add_notes_title2);
        add_notes_ti6tle = findViewById(R.id.add_notes_ti6tle);
        add_notes_ti6tle11 = findViewById(R.id.add_notes_ti6tle11);
        add_notes_ti6tle1 = findViewById(R.id.add_notes_ti6tle1);
        submit = findViewById(R.id.submit);

        ///
        firebaseFirestore=FirebaseFirestore.getInstance();

        firebaseFirestore.collection("Package_User")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots) {
                            String dta = documentSnapshot.getString("username");
                            String status=documentSnapshot.getString("status");
                            if (status.toLowerCase().equals("active")) {
                                int ncountqq=0;

                                for (DocumentSnapshot document :queryDocumentSnapshots) {
                                    ncountqq++;
                                }
                                add_notes_title.setText("Total Packaged user : "+ncountqq);
                                message=message+"\n"+count11;
                                ///Toast.makeText(MainActivity.this, ""+count11, Toast.LENGTH_SHORT).show();

                                firebaseFirestore.collection("Income_History")
                                        .document(dta.toLowerCase().toString()+"@gmail.com")
                                        .collection("List")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                int ncount=0;
                                                int count = 0;
                                                for (DocumentSnapshot document : task.getResult()) {
                                                    count++;
                                                    secondd++;
                                                }
                                                Double dddddd=Double.parseDouble(String.valueOf(secondd))*235;
                                                add_notes_title2.setText("Total Income : "+dddddd);

                                                ///  Toast.makeText(HomeActivity.this, ""+secondd, Toast.LENGTH_SHORT).show();
                                                // message=message+"\n"+dta+"("+count+")"+"Final Value : "+total;
                                                ///  Toast.makeText(MainActivity.this, ""+total, Toast.LENGTH_SHORT).show();
                                                Handler handler=new Handler();
                                                handler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                       /// double ddd=Double.parseDouble(String.valueOf(secondd))*235;



                                                    }
                                                },10000);
                                            }
                                        });

                            }



                        }
                    }
                });
    }
    int count11=0;
    String message="\n";
    int secondd=0;
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