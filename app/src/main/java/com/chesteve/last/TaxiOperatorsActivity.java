package com.chesteve.last;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;

import com.chesteve.last.useermap.Maps;

import java.util.Calendar;

public class TaxiOperatorsActivity extends AppCompatActivity {
    EditText tolocation,fromlocation,paphone,dateTextRaw, timeTextRaw;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    Button  golive;
    Spinner taxiname;

    //firebase
    DatabaseReference spinnerReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxi_operators);

        //initialising
        tolocation = (EditText) findViewById(R.id.tolocation);
        fromlocation = (EditText) findViewById(R.id.fromlocation);
        taxiname = (Spinner) findViewById(R.id.operaname);
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
            Intent goBack = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(goBack);
        }
        else {

            //inserting data
            Log.d("D","Success");
        }
    }
}