package com.chesteve.last.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chesteve.last.MainActivity;
import com.chesteve.last.R;

public class AdminBookingDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_booking_data);
    }

    //on click back icon
    public void backHome(View view) {
        Intent intent = new Intent(AdminBookingDataActivity.this, AdminMainActivity.class);
        startActivity(intent);
    }

    public void logoutAdmin(View view) {
        Intent intent = new Intent(AdminBookingDataActivity.this, MainActivity.class);
        startActivity(intent);
    }
}