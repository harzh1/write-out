package com.pexcy.write_out;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class article_write extends AppCompatActivity {
private EditText editTopic;
private EditText editBody;
DatabaseReference articledbRef;
private ImageButton donewrite;
private ImageButton deletewrite;
DatabaseReference databaseReference;
private ProgressDialog progressDialogg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_write);

        editTopic=findViewById(R.id.editTopic);
        editBody=findViewById(R.id.editBody);
        donewrite=findViewById(R.id.donewrite);
        progressDialogg = new ProgressDialog(this);
        databaseReference=FirebaseDatabase.getInstance().getReference();

        articledbRef = FirebaseDatabase.getInstance().getReference().child("Articles");



        donewrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertarticle();


            }
        });


    }
    private void insertarticle(){
        String TopicName = editTopic.getText().toString();
        String Article = editBody.getText().toString();

        progressDialogg.setMessage("Please wait...");
        progressDialogg.show();
        progressDialogg.setCanceledOnTouchOutside(false);


        article_data articles = new article_data(TopicName,Article);

        Toast.makeText(article_write.this,"Article Saved",Toast.LENGTH_SHORT).show();
        articledbRef.push().setValue(articles);
        Intent intent7=new Intent(article_write.this,dashbord.class);
        startActivity(intent7);
        finish();
        progressDialogg.dismiss();
    }

}