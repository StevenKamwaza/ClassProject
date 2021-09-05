package com.chesteve.last;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.chesteve.last.useermap.Maps;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class TaxiOperatorsActivity extends AppCompatActivity {

    EditText tolocation,fromlocation,paphone,dateTextRaw, timeTextRaw;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    Button  golive;
    Spinner mylist;
    //ArrayList <List> sinnerdata;
    //firebase
    DatabaseReference spinnerReference;
    ValueEventListener valueEventListener;
    ArrayList <String> arrayList;
    ArrayAdapter <String> adapter;
    String taxisId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxi_operators);

        //initialising
        tolocation = (EditText) findViewById(R.id.tolocation);
        fromlocation = (EditText) findViewById(R.id.fromlocation);
        mylist = (Spinner) findViewById(R.id.mySpinner);

        spinnerReference = FirebaseDatabase.getInstance().getReference("Users");
        mySpinnerData();
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        mylist.setAdapter(adapter);


        paphone = (EditText) findViewById(R.id.pphonenumber);
        //datepicker
        dateTextRaw = (EditText) findViewById(R.id.dateText);
        dateTextRaw.setInputType(InputType.TYPE_NULL);
        dateTextRaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //picker
                datePickerDialog = new DatePickerDialog(TaxiOperatorsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateTextRaw.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        },
                        year, month, day
                );
                datePickerDialog.show();
            }
        });

        //timepicker
        timeTextRaw = (EditText) findViewById(R.id.myTimePicker);
        timeTextRaw.setInputType(InputType.TYPE_NULL);
        timeTextRaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                //dialog
                timePickerDialog = new TimePickerDialog(TaxiOperatorsActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                timeTextRaw.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                timePickerDialog.show();
            }
        });

        golive = (Button) findViewById(R.id.goLive);
        golive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goli = new Intent(TaxiOperatorsActivity.this, Maps.class);
                startActivity(goli);
            }
        });

    }

    private void mySpinnerData() {
        valueEventListener = spinnerReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                for (DataSnapshot myList : snapshot.getChildren()){
                    arrayList.add(myList.child("taxisname").getValue().toString());

                    taxisId = myList.getKey();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }

    public void goBack(View view) {
        Intent goBack = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goBack);

    }

    //insert data fromm passengers

    public void taxisList(View view) {
        Intent intent = new Intent(getApplicationContext(), TaxisListActivity.class);
        startActivity(intent);
    }

    public void bookingBtn(View view) {
        if (tolocation.getText().toString().isEmpty()||fromlocation.getText().toString().isEmpty()||
                dateTextRaw.getText().toString().isEmpty()||timeTextRaw.getText().toString().isEmpty()||
                paphone.getText().toString().isEmpty()){


            HashMap< String,Object> hashMap = new HashMap<>();

            hashMap.put("to_location",tolocation.getText().toString());
            hashMap.put("from_location",fromlocation.getText().toString());
            hashMap.put("date", dateTextRaw.getText().toString());
            hashMap.put("time",timeTextRaw.getText().toString());
            hashMap.put("phone",paphone.getText().toString());

          // spinnerReference = FirebaseDatabase.getInstance().getReference();

           // spinnerReference.getKey(taxisId).child("Messages").setValue(hashMap);

                Intent goBack = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(goBack);
        }
        else {

            //inserting data
            Log.d("D","Success");
        }
    }
}