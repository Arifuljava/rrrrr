package com.messas.epcladmin__11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.UUID;

public class AddPackages extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    EditText add_notes_title, add_notes_title2, add_notes_ti6tle, add_notes_ti6tle11, add_notes_ti6tle1;
    Button submit;
    EditText makerules, bloas, bloas1, makerule1s, bloas1ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_packages);
        Toolbar toolbar = findViewById(R.id.profile_toolbar);

        toolbar.setTitle("Add Package");
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
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = add_notes_title.getText().toString();
                String area = add_notes_title2.getText().toString();
                String pho = add_notes_ti6tle.getText().toString();
                String fee1 = add_notes_ti6tle11.getText().toString();
                String room = add_notes_ti6tle1.getText().toString();
                String days = makerules.getText().toString();
                String month = bloas.getText().toString();
                String year = bloas1.getText().toString();
                String hour = makerule1s.getText().toString();
                String min = bloas1ss.getText().toString();


                if (TextUtils.isDigitsOnly(name) || TextUtils.isEmpty(area) || TextUtils.isEmpty(pho) ||
                        TextUtils.isEmpty(fee1) || TextUtils.isEmpty(room) ||
                        TextUtils.isEmpty(days) ||
                        TextUtils.isEmpty(month) || TextUtils.isEmpty(year) ||
                        TextUtils.isEmpty(hour) ||
                        TextUtils.isEmpty(min)
                ) {
                    Toast.makeText(AddPackages.this, "Error", Toast.LENGTH_SHORT).show();
                } else {
                    String[] option = {"Add Packages"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddPackages.this);
                    builder.setTitle("Options")
                            .setItems(option, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (i == 0) {
                                        final KProgressHUD progressDialog = KProgressHUD.create(AddPackages.this)
                                                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                                .setLabel("Uploading Data.....")
                                                .setCancellable(false)
                                                .setAnimationSpeed(2)
                                                .setDimAmount(0.5f)
                                                .show();
                                        Long tsLong = System.currentTimeMillis() / 1000;
                                        String ts = tsLong.toString();
                                        String uuid = UUID.randomUUID().toString();
                                        BloodBankModel bloodBankModel = new BloodBankModel(name, pho, area, fee1, room, ts, uuid, days, month, year, hour, min);
                                        firebaseFirestore.collection("Packages")
                                                .document(uuid)
                                                .set(bloodBankModel)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            progressDialog.dismiss();
                                                            Toast.makeText(AddPackages.this, "Done", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    } else if (i == 1) {

                                    }
                                }
                            }).create().show();

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