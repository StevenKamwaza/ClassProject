package com.chesteve.last.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.chesteve.last.R;

public class RegisterActivity extends AppCompatActivity {

    Button signUpButton;
    EditText username, passWord;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username =findViewById(R.id.username);
        passWord =findViewById(R.id.password1);
        signUpButton =findViewById(R.id.sign);
        firebaseAuth =FirebaseAuth.getInstance();
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString().trim();
                String password= passWord.getText().toString().trim();
                if(email.isEmpty())
                {
                    username.setError("Please enter email");
                    username.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    username.setError("Invalid email");
                    username.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    passWord.setError("Please enter password");
                    passWord.requestFocus();
                    return;
                }
                if(password.length()<6)
                {
                    passWord.setError("Length of the password should be more than 6");
                    passWord.requestFocus();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(RegisterActivity.this,"You are successfully Registered", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this,"You are not Registered! Try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}