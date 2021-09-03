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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SighUpActivity extends AppCompatActivity {

    //widgets
    Button btnreg, btnlogin;
    EditText myOpername, myusername, mypassword, myPhone, mylocation;
    TextView alreadyHaveAccount;

    //firebase auth system
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigh_up);
        //assigning widgets
        myOpername =(EditText)findViewById(R.id.regiOpername);
        myusername = (EditText)findViewById(R.id.regiUsername);
        mypassword = (EditText)findViewById(R.id.regiPassword);
        myPhone =(EditText)findViewById(R.id.regiPhoneNumber);
        mylocation =(EditText) findViewById(R.id.reglocation);


        //auth
        firebaseAuth = FirebaseAuth.getInstance();



    }

    //add a user in database
    private  void addTaxi(String taxis, String username, String pass
          ,String email, String loca){

    }

    public void backLoginNow(View view) {
        Intent backLogin = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(backLogin);
    }

    //adding dat

        private void registerNewUser()
        {

            String email, password;
            email = myPhone.getText().toString();
            password = mypassword.getText().toString();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Please enter email!!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Please enter password!!",Toast.LENGTH_SHORT).show();
                return;
            }

            // create new user or register new user
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful()) {

                                HashMap hashMap = new HashMap();

                                hashMap.put("location", mylocation.getText().toString());
                                hashMap.put("taxisname",myOpername.getText().toString());
                                hashMap.put("username", myusername.getText().toString());
                                hashMap.put("email",email);

                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                String taxiid = firebaseUser.getUid();

                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(taxiid);
                                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull  Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Intent intent = new Intent(SighUpActivity.this,LoginActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();

                                        }
                                    }
                                });



//                                Toast.makeText(getApplicationContext(),
//                                        "Registration successful!",
//                                        Toast.LENGTH_LONG)
//                                        .show();
//
//
//
//                                // if the user created intent to login activity
//                                Intent intent
//                                        = new Intent(SighUpActivity.this,
//                                        MainActivity.class);
//                                startActivity(intent);
                            }
                            else {

                                // Registration failed
                                Toast.makeText( getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG) .show();

                            }
                        }
                    });
        }

    public void registerNewUser(View view) {
        registerNewUser();
    }
}