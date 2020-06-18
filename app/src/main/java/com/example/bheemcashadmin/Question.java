package com.example.bheemcashadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Question extends AppCompatActivity {
   EditText answerText,questionText;
   Button addButton;
   static int punch=0;
  static ArrayList<String> stringArrayList;
   ArrayAdapter<String> adapter;
   ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        questionText =findViewById(R.id.question);
        answerText =findViewById(R.id.Answer);
        listview=findViewById(R.id.listView);
        addButton=findViewById(R.id.addButton);
        stringArrayList=new ArrayList<>();
        adapter=new ArrayAdapter<>(Question.this,android.R.layout.simple_list_item_1,stringArrayList);
        listview.setAdapter(adapter);

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(Question.this)
                        .setIcon(R.drawable.ic_clear)
                        .setTitle("Warning!!")
                        .setMessage("Are you sure you want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                stringArrayList.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No",null).show();
                return true;
            }
        });
    }

    public void addOptions(View view) {
        String value= answerText.getText().toString().trim();
        if(!value.equals(""))
        {
            stringArrayList.add(value);
            adapter.notifyDataSetChanged();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please enter some value",Toast.LENGTH_SHORT).show();
        }

        answerText.getText().clear();
    }


    public void MutliPunch(View view) {
        punch=2;
       // addButton.setEnabled(true);
        adapter.notifyDataSetChanged();

    }

    public void SinglePunch(View view) {
        punch=1;
        adapter.notifyDataSetChanged();
      //  addButton.setEnabled(false);
      //  stringArrayList.add("yes");
     //   stringArrayList.add("no");
    }


    public void callLogic(View view) {
        if (!(questionText.getText().toString()).equals("")) {
            Intent i = new Intent(getApplicationContext(), LogicActivity.class);
            startActivity(i);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please make sure question field is filled",Toast.LENGTH_SHORT).show();
        }
    }
}
