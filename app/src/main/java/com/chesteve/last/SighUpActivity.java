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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chesteve.last.admin.AdminMainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SighUpActivity extends AppCompatActivity {

    //widgets
    Button btnreg, btnlogin;
    EditText myOpername, myusername, mypassword, myPhone, mylocation;
    TextView alreadyHaveAccount;

    //firebase auth system
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseFirestore firebaseFirestore;
    String userId;
    ProgressBar progressBar;

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
        firebaseFirestore=FirebaseFirestore.getInstance();



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

        private void registerNewUser(){

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

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                     @Override
                                     public void onComplete(@NonNull Task<AuthResult> task) {
                                           if (task.isSuccessful()) {
                                               Toast.makeText(SighUpActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                                //get the id of this just created user
                                               userId = firebaseAuth.getCurrentUser().getUid();

                                               //creating document instance to store our members data,passing the id to the document
                                               DocumentReference registeredUsers=firebaseFirestore.collection("Users").document(email);

                                                //HashMap<String,Object> member=new HashMap<>();
                                               HashMap< String,Object> hashMap = new HashMap<>();

                                               hashMap.put("location", mylocation.getText().toString());
                                               hashMap.put("taxisname",myOpername.getText().toString());
                                               hashMap.put("username", myusername.getText().toString());
                                               hashMap.put("email",email);

                                               registeredUsers.set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                   @Override
                                                   public void onSuccess(Void unused) {
                                                       Toast.makeText(SighUpActivity.this, "User created", Toast.LENGTH_SHORT).show();
                                                    }
                                               }).addOnFailureListener(new OnFailureListener() {
                                                   @Override
                                                   public void onFailure(@NonNull Exception e) {
                                                       Toast.makeText(SighUpActivity.this, "Error! "+e.toString(), Toast.LENGTH_SHORT).show();
                                                   }
                                               });


                                               startActivity(new Intent(getApplicationContext(), AdminMainActivity.class));

                                           } else {
                                               Toast.makeText(SighUpActivity.this, "Oops! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                               //closing progressbar
                                               //progressbar.setVisibility(View.INVISIBLE);
                                           }
                                       }
                                    });
        }

    public void registerNewUser(View view) {
        registerNewUser();
    }
}