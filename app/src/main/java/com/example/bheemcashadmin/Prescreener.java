package com.example.bheemcashadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Prescreener extends AppCompatActivity {
   EditText refText,nameText,categoryText,timeText,prizeText,linkText;
   static String ref,name,category,time,prize,link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescreener);
        refText=findViewById(R.id.projectRef);
        nameText=findViewById(R.id.projectName);
        categoryText=findViewById(R.id.projectCategory);
        timeText=findViewById(R.id.projectTime);
        prizeText=findViewById(R.id.projectPrize);
        linkText=findViewById(R.id.projectLink);

    }

    public void callQuestion(View view) {

        ref=refText.getText().toString().trim();
        name=nameText.getText().toString().trim();
        category=categoryText.getText().toString().trim();
        time=timeText.getText().toString().trim();
        prize=prizeText.getText().toString().trim();
        link=linkText.getText().toString().trim();

        if(ref.equals("")||name.equals("")||category.equals("")||time.equals("")||prize.equals("")||link.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please make sure all the fields are filled",Toast.LENGTH_SHORT).show();
        }
        else
        {
            AddDetails details=new AddDetails();
            Intent i=new Intent(getApplicationContext(),Question.class);
            startActivity(i);
        }
    }
}
