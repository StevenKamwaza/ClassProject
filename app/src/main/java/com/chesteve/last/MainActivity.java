package com.chesteve.last;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button gotoBookingView;
    Button gotoOperator;
    TextView aboutus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gotoBookingView=findViewById(R.id.gotoBookingView);
        gotoBookingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent gototaxis = new Intent(getApplicationContext(), TaxiOperatorsActivity.class);
               startActivity(gototaxis);


            }
        });

        gotoOperator = (Button) findViewById(R.id.gotoLogin);
        gotoOperator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoLog = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(gotoLog);
            }
        });

        aboutus = (TextView) findViewById(R.id.aboutUSView);
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });


    }



}