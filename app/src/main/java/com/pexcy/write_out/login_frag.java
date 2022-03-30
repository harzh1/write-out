package com.pexcy.write_out;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_frag extends AppCompatActivity {
    private EditText emaill, passwordd;
    private Button signinbutton;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_frag);
        firebaseAuth=FirebaseAuth.getInstance();
        emaill=findViewById(R.id.email);
        passwordd=findViewById(R.id.password);
        signinbutton=findViewById(R.id.signinbutton);
        progressDialog=new ProgressDialog(this);
        signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }


    private void Login() {
        String email = emaill.getText().toString();
        String password = passwordd.getText().toString();
        if (TextUtils.isEmpty(email)){
            emaill.setError("Enter your email");
            return;
        }
        else if (TextUtils.isEmpty(password)) {
            passwordd.setError("Enter your password");
            return;
        }
        else if (!isVallidEmail(email)){
            emaill.setError("Invalid email");
            return;
        }
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(login_frag.this, "Successfully Login",Toast.LENGTH_LONG).show();
                    Intent intent4=new Intent(login_frag.this,MainActivity.class);
                    startActivity(intent4);
                    finish();
                }
                else {
                    Toast.makeText(login_frag.this, "Login failed!",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });

    }
    private Boolean isVallidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());

    }

}
