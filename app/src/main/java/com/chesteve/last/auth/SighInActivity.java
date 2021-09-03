package com.chesteve.last.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.chesteve.last.R;
import com.chesteve.last.admin.AdminMainActivity;

public class SighInActivity extends AppCompatActivity {

    private EditText username, password;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigh_in);
        username =findViewById(R.id.email);
        password =findViewById(R.id.password);
        Button btn_login = findViewById(R.id.btn_login);
        Button btn_sign = findViewById(R.id.btn_signup);
        firebaseAuth =FirebaseAuth.getInstance();



        btn_login.setOnClickListener(v -> {
            String email= username.getText().toString().trim();
            String password= this.password.getText().toString().trim();
            if(email.isEmpty())
            {
                username.setError("Email is empty");
                username.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                username.setError("Enter the valid email");
                username.requestFocus();
                return;
            }
            if(password.isEmpty())
            {
                this.password.setError("Password is empty");
                this.password.requestFocus();
                return;
            }
            if(password.length()<6)
            {
                this.password.setError("Length of password is more than 6");
                this.password.requestFocus();
                return;
            }
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                {
                    startActivity(new Intent(SighInActivity.this, AdminMainActivity.class));
                }
                else
                {
                    Toast.makeText(SighInActivity.this,
                            "Please Check Your login Credentials",
                            Toast.LENGTH_SHORT).show();
                }

            });
        });
        btn_sign.setOnClickListener(v -> startActivity(new Intent(SighInActivity.this,RegisterActivity.class )));
    }
}