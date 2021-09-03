package com.chesteve.last;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.chesteve.last.operators.OperatorAdapter;
import com.chesteve.last.operators.Operators;

public class TaxisListActivity extends AppCompatActivity {

    //widgets
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    CircularProgressIndicator progress_circular;
    private Context context;
    private Activity myActivity;
    private ArrayList<Operators> operatorsArrayList;
    private OperatorAdapter adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxis_list);

        //database
        myActivity = TaxisListActivity.this;
        context = getApplicationContext();
        FirebaseApp.initializeApp(this);
        recyclerView = findViewById(R.id.recyclerView);
        progress_circular = findViewById(R.id.progress_circular);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(myActivity, 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setNestedScrollingEnabled(false);
        operatorsArrayList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                operatorsArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Operators operators = dataSnapshot.getValue(Operators.class);
                    operatorsArrayList.add(operators);
                }
                adapter = new OperatorAdapter(context, myActivity, (ArrayList<Operators>) operatorsArrayList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                progress_circular.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TaxisListActivity.this,"Error:" + error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void goBack(View view) {
        Intent goBackto = new Intent(getApplicationContext(), TaxiOperatorsActivity.class);
        startActivity(goBackto);
    }
}