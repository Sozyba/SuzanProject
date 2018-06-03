package com.badran.suzan.suzanproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class signUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    private EditText UserName;
    private EditText Password;
    private EditText Singin;
    private Button SingUp;

    public static class Login extends AppCompatActivity {

        private TextView etUser;
        private TextView etPassword;
        private TextView etSignin;
        private Button bttnSignup ;
        private Button fotgetmypass;
        private FirebaseAuth auth;
        private FirebaseUser firebaseUser;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login);
            etUser=(TextView)findViewById(R.id.etUser);
            etPassword= (TextView) findViewById (R.id.etPassword);
            bttnSignup =(Button) findViewById(R.id.bttnSignup);
            etSignin=(TextView)findViewById(R.id.etSignin);
            etSignin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(getBaseContext(),MainActivity.class);
                    startActivity(i);
                }
            });
            auth = FirebaseAuth.getInstance();
            firebaseUser = auth.getCurrentUser();
        }
        private void dataHandler()
        {

        }
        private  void signIn(String email, String Password){
            auth.signInWithEmailAndPassword(email, Password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {


                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "singIn Successful.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(Login.this, "singIn failed." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        task.getException().printStackTrace();
                    }
                }



            });
        }
    }
}
