package com.messas.epcladmin__11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    private EditText edtemail, edtpass;
    private String email, pass, email_gating;
    private TextView appname, forgotpass, registernow;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseUser user;
    private String userID;
    FirebaseAuth firebaseAuth;
    Long tsLong = System.currentTimeMillis() / 1000;
    String ts = tsLong.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.e("Login CheckPoint", "LoginActivity started");
        //check Internet Connection

        //new CheckInternetConnection(this).checkConnection();
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseApp.initializeApp(LoginActivity.this);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        //Typeface typeface = ResourcesCompat.getFont(this, R.font.blacklist);
        // appname = findViewById(R.id.appname);
        // appname.setTypeface(typeface);

        edtemail = findViewById(R.id.email);
        edtpass = findViewById(R.id.password);


        //if user wants to register
        registernow = findViewById(R.id.register_now);
        registernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FlatDialog flatDialog1 = new FlatDialog(LoginActivity.this);
                flatDialog1.setTitle("Forget Password")
                        .setSubtitle("User forget his/her password.Now  you change it.")
                        .setFirstTextFieldHint("Username")
                        .setSecondTextFieldHint("password")
                        .setFirstButtonText("Change")
                        .setSecondButtonText("Cancel")
                        .withFirstButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog1.dismiss();
                                final KProgressHUD progressDialog = KProgressHUD.create(LoginActivity.this)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setLabel("Please wait")
                                        .setCancellable(false)
                                        .setAnimationSpeed(2)
                                        .setDimAmount(0.5f)
                                        .show();
                                firebaseFirestore.collection("Password")
                                        .document(flatDialog1.getFirstTextField().toLowerCase() + "@gmail.com")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    if (task.getResult().exists()) {
                                                        firebaseFirestore.collection("Password")
                                                                .document(flatDialog1.getFirstTextField().toLowerCase() + "@gmail.com")
                                                                .update("uuid", flatDialog1.getSecondTextField().toLowerCase())
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if (task.isSuccessful()) {
                                                                            firebaseFirestore.collection("All_UserID")
                                                                                    .document(flatDialog1.getFirstTextField().toLowerCase() + "@gmail.com")
                                                                                    .get()
                                                                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                        @Override
                                                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                            if (task.isSuccessful()) {
                                                                                                if (task.getResult().exists()) {
                                                                                                    String uuid = task.getResult().getString("uuid");
                                                                                                    firebaseFirestore.collection("Users")
                                                                                                            .document(uuid)
                                                                                                            .get()
                                                                                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                                                @Override
                                                                                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                                                    if (task.isSuccessful()) {
                                                                                                                        if (task.getResult().exists()) {
                                                                                                                            firebaseFirestore.collection("Users")
                                                                                                                                    .document(uuid)
                                                                                                                                    .update("pass", flatDialog1
                                                                                                                                            .getSecondTextField().toLowerCase())
                                                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                        @Override
                                                                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                            if (task.isSuccessful()) {
                                                                                                                                                progressDialog.dismiss();
                                                                                                                                                Toasty.success(getApplicationContext(), "Done", Toasty.LENGTH_SHORT, true).show();
                                                                                                                                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                                                                                                                                finish();
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
                                                                    }
                                                                });

                                                    } else {
                                                        progressDialog.dismiss();
                                                        Toasty.error(getApplicationContext(), "No user found", Toasty.LENGTH_SHORT, true).show();
                                                    }
                                                }
                                            }
                                        });
                            }
                        })
                        .withSecondButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog1.dismiss();
                            }
                        })
                        .show();
            }
        });

        //if user forgets password
        forgotpass = findViewById(R.id.forgot_pass);
        forgotpass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });


        //Validating login details
        Button login_button = findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email_gating = edtemail.getText().toString();
                email = email_gating + "@gmail.com";
                pass = edtpass.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {

                    String text1 = email_gating;
                    String tet2 = pass;
                    if (TextUtils.isEmpty(text1) || TextUtils.isEmpty(tet2)) {
                        Toasty.error(getApplicationContext(), "Give all information", Toast.LENGTH_SHORT, true).show();
                    } else {
                        final KProgressHUD progressDialog = KProgressHUD.create(LoginActivity.this)
                                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                .setLabel("Checking Data.....")
                                .setCancellable(false)
                                .setAnimationSpeed(2)
                                .setDimAmount(0.5f)
                                .show();
                        firebaseFirestore.collection("BlockList")
                                .document(text1.toLowerCase().toString() + "@gmail.com")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            if (task.getResult().exists()) {
                                                progressDialog.dismiss();
                                                Toasty.error(getApplicationContext(), "You  are in blook list.", Toast.LENGTH_SHORT, true).show();
                                            } else {
                                                firebaseFirestore.collection("AdminAccess")
                                                        .document(email_gating+"@gmail.com")
                                                        .get()
                                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                if (task.isSuccessful()) {
                                                                    if (task.getResult().exists()) {
                                                                        firebaseAuth.signInWithEmailAndPassword("admin_allaccess@gmail.com","123456")
                                                                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                                                        if (task.isSuccessful()) {
                                                                                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                                                                            finish();
                                                                                        }
                                                                                    }
                                                                                });

                                                                    }
                                                                    else {
                                                                        progressDialog.dismiss();
                                                                        Toasty.error(getApplicationContext(),"Not Valid User",Toasty.LENGTH_SHORT,true).show();
                                                                    }
                                                                }
                                                                else {
                                                                    progressDialog.dismiss();
                                                                    Toasty.error(getApplicationContext(),"Not Valid User",Toasty.LENGTH_SHORT,true).show();
                                                                }
                                                            }
                                                        });

                                            }

                                        }
                                    }
                                });
                    }


//////////////////////////
                }

            }
        });
    }
}