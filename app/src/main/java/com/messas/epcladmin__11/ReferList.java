package com.messas.epcladmin__11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Nullable;

public class ReferList extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    TextView detailsbasic,dde;
    Button login_btn_details,login_btn_details__;



    LottieAnimationView empty_cart;
    DocumentReference documentReference;
    RecyclerView recyclerView;
    PackageDapater getDataAdapter1;
    List<DetailsChecker> getList;
    String url;

    FirebaseUser firebaseUser;
    KProgressHUD progressHUD;
    String cus_name;

    androidx.appcompat.widget.SearchView name;
    String daily,nameb;
    String maindaily,mainmonth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_list);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10.0f);
        try {
            daily=getIntent().getStringExtra("daily1");
            nameb=getIntent().getStringExtra("name");
            maindaily=getIntent().getStringExtra("daily");
            mainmonth=getIntent().getStringExtra("monthly");

        }catch (Exception e) {
            daily=getIntent().getStringExtra("daily1");
            nameb=getIntent().getStringExtra("name");
            maindaily=getIntent().getStringExtra("daily");
            mainmonth=getIntent().getStringExtra("monthly");
        }
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH)+1;
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        getList = new ArrayList<>();
        getDataAdapter1 = new PackageDapater(getList);
        firebaseFirestore = FirebaseFirestore.getInstance();
        documentReference  =      firebaseFirestore.collection(daily)
                .document(nameb+"@gmail.com")
                .collection("Days")
                .document(""+year)
                .collection(""+month)
                .document(""+day)
                .collection("List").document();
        recyclerView =findViewById(R.id.rreeeed);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ReferList.this));
        recyclerView.setAdapter(getDataAdapter1);
        reciveData();


    }
    private void reciveData() {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH)+1;
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        firebaseFirestore.collection(daily)
                .document(nameb+"@gmail.com")
                .collection("Days")
                .document(""+year)
                .collection(""+month)
                .document(""+day)
                .collection("List").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange ds : queryDocumentSnapshots.getDocumentChanges()) {
                    if (ds.getType() == DocumentChange.Type.ADDED) {

                 /*String first;
                 first = ds.getDocument().getString("name");
                 Toast.makeText(MainActivity2.this, "" + first, Toast.LENGTH_SHORT).show();*/
                        DetailsChecker get = ds.getDocument().toObject(DetailsChecker.class);
                        getList.add(get);
                        getDataAdapter1.notifyDataSetChanged();
                    }

                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),DetailsActivity.class);
        intent.putExtra("daily",maindaily);
        intent.putExtra("monthly",mainmonth);
        intent.putExtra("name",nameb);
        startActivity(intent);
        
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent=new Intent(getApplicationContext(),DetailsActivity.class);
        intent.putExtra("daily",maindaily);
        intent.putExtra("monthly",mainmonth);
        intent.putExtra("name",nameb);
        startActivity(intent);
        return true;
    }
}