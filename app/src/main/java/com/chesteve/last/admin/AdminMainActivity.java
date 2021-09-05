package com.chesteve.last.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chesteve.last.MainActivity;
import com.chesteve.last.R;

public class AdminMainActivity extends AppCompatActivity {


    Button messages;
    TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        messages = (Button) findViewById(R.id.view_booking_list_messages);
        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this, AdminBookingDataActivity.class));
            }
        });

        logout = (TextView) findViewById(R.id.logout_admin);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this, MainActivity.class));
            }
        });
    }
}