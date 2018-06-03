package com.badran.suzan.suzanproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    private TextView Name;
    private TextView LastName;
    private TextView Password;
    private TextView RePassword;
    private EditText Email;
    private Button Save;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        super.onCreate(savedInstanceState);
        Name = (TextView) findViewById(R.id.etName);
        LastName = (TextView) findViewById(R.id.etLastName);
        Password = (TextView) findViewById(R.id.etPassword);
        RePassword = (TextView) findViewById(R.id.etRePassword);
        Email = (EditText) findViewById(R.id.etEmail);
        Save = (Button) findViewById(R.id.btnSave);
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
    }

    private void dataHandler() {
        String stEmail = Email.getText().toString();
        String stName = Name.getText().toString();
        String stPassword = Password.getText().toString();
        String stRepassword = RePassword.getText().toString();
        boolean isOk = true;
        if ((stEmail.length() == 0) || stEmail.indexOf('@') < 1) {
            Email.setError("wrong email");
            isOk = false;

        }
        if ((stPassword.length() < 8) || stPassword.equals(stRepassword) == false) {
            Password.setError("Bad Password");
            isOk = false;

        }
        if (isOk)
            creatAcount(stEmail, stPassword);


    }

    private void creatAcount(String Email, String Password) {
        auth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUp.this, "Authentication Successful", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(SignUp.this, "Authentication failed." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }


        });
    }


}















