package com.example.bheemcashadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LogicActivity extends AppCompatActivity {
   ArrayAdapter<String> adapter,choiceadapter;
   ListView listView,choiceList;
   TextView optionText;
   AddDetails details;
    ArrayList<String> value;
    DatabaseReference ref,ref1;
    String count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic);
        listView=findViewById(R.id.list);
        choiceList=findViewById(R.id.choiceList);
        optionText=findViewById(R.id.optionText);
        if(Question.punch==1)
        {
            value=new ArrayList<>(1);
        }
        else
        {
            value=new ArrayList<>();
        }
        adapter=new ArrayAdapter<>(LogicActivity.this,android.R.layout.simple_list_item_1,Question.stringArrayList);

        listView.setAdapter(adapter);
        checkForCount();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choiceadapter = new ArrayAdapter<>(LogicActivity.this, android.R.layout.simple_list_item_1, value);
                choiceList.setAdapter(choiceadapter);
                if(Question.punch==1&&value.size()==0) {
                    value.add(listView.getItemAtPosition(position).toString());
                    choiceadapter.notifyDataSetChanged();
                }
                else if(Question.punch==2)
                {
                    if(!value.contains(listView.getItemAtPosition(position).toString())) {
                        value.add(listView.getItemAtPosition(position).toString());
                        choiceadapter.notifyDataSetChanged();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"This item is already selected",Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Sorry u can enter only once",Toast.LENGTH_SHORT).show();
                }
              //optionText.setText("Your selected option is:"+value);
            }
        });

    }

    public void addToDatabase(View view) {
        details=new AddDetails();
        details.setProjectRef(Prescreener.ref);
        details.setProjectName(Prescreener.name);
        details.setCategory(Prescreener.category);
        details.setTime(Integer.parseInt(Prescreener.time));
        details.setPrize(Integer.parseInt(Prescreener.prize));
        details.setSurveyLink(Prescreener.link);
        details.setOptions(Question.stringArrayList);
        details.setChoice(value);


        ref= FirebaseDatabase.getInstance().getReference().child("Survey");
        ref.child(String.valueOf(count)).setValue(details);
        ref1=FirebaseDatabase.getInstance().getReference().child("TotalSurvey");
        ref1.setValue(String.valueOf(Integer.parseInt(count)+1));
        Toast.makeText(getApplicationContext(),"Successfully inserted",Toast.LENGTH_SHORT).show();
        Intent i= new Intent(getApplicationContext(),Dashboard.class);
        startActivity(i);
    }

    private void checkForCount() {
        ref1= FirebaseDatabase.getInstance().getReference().child("TotalSurvey");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                count=value;
                Log.d("check", "Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("check", "Failed to read value.", error.toException());
            }
        });
    }

    public void CallDashboard(View view) {
        Intent i=new Intent(getApplicationContext(),Dashboard.class);
        startActivity(i);
    }
}
