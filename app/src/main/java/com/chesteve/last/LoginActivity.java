package com.chesteve.last;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.chesteve.last.admin.AdminMainActivity;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText loguseremail, loguserpassword;
    TextView signUpText;
    ImageView imageViewBack;
    ProgressBar progressbar;
//    //auth refere
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // variable
        loguserpassword = (EditText) findViewById(R.id.loginPassword);
        loguseremail =(EditText) findViewById(R.id.loginUsername);
        imageViewBack = (ImageView) findViewById(R.id.imageView);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goBack);
            }
        });
        signUpText = (TextView) findViewById(R.id.create_account);
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createAcount = new Intent(getApplicationContext(), SighUpActivity.class);
                startActivity(createAcount);
            }
        });
        progressbar = (ProgressBar) findViewById(R.id.progressbar);

        btnLogin = (Button) findViewById(R.id.loginBtn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeWelcome();

            }
        });
    }

    private void homeWelcome(){

        progressbar.setVisibility(View.VISIBLE);

        String email, password;
        email = loguseremail.getText().toString();
        password = loguserpassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(
                LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, AdminMainActivity.class));
                }
                else {
                  Toast.makeText(LoginActivity.this, "Oops! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    public String validate(String user, String user1) {
        if(loguseremail.equals("email") && loguserpassword.equals("password"))
            return "Login was successful";
        else
            return "Invalid login!";
    }
}