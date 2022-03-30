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

public class signup_frag extends AppCompatActivity {
private EditText emaill2, nwpassword, cfmpassword;
private Button signupbutton;
private ProgressDialog progressDialog;
private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_frag);
        firebaseAuth=FirebaseAuth.getInstance();
        emaill2=findViewById(R.id.email2);
        nwpassword=findViewById(R.id.newpassword);
        cfmpassword=findViewById(R.id.cnfmpassword);
        signupbutton=findViewById(R.id.signupbutton);
        progressDialog=new ProgressDialog(this);
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Register();
            }
        });

    }
    private void Register() {
        String email2 = emaill2.getText().toString();
        String newpassword = nwpassword.getText().toString();
        String cnfmpassword = cfmpassword.getText().toString();
        if (TextUtils.isEmpty(email2)){
            emaill2.setError("Enter your email");
            return;
        }
        else if (TextUtils.isEmpty(newpassword)){
            nwpassword.setError("Enter your password");
            return;
        }
        else if (TextUtils.isEmpty(cnfmpassword)){
            cfmpassword.setError("Confirm your password");
            return;
        }
        else if (!newpassword.equals(cnfmpassword)){
            nwpassword.setError("Different password");
            return;
        }
        else if (newpassword.length()<6){
            nwpassword.setError("Length should be at least 6");
            return;
        }
        else if (!isVallidEmail(email2)){
            emaill2.setError("Invalid email");
            return;
        }
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(email2,newpassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(signup_frag.this, "Successfully registered",Toast.LENGTH_LONG).show();
                    Intent intent3=new Intent(signup_frag.this,MainActivity.class);
                    startActivity(intent3);
                    finish();
                }
                else {
                    Toast.makeText(signup_frag.this, "Sign Up failed!",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });

    }
    private Boolean isVallidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());

    }

}
