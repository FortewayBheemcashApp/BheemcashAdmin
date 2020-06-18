package com.example.bheemcashadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ProfilingQuestions extends AppCompatActivity {

    EditText dateText;
    RadioGroup q1,q3,q4,q5,q6,q7,q8;
    Button submitData;
    TextView qText;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiling_questions);
        q1=findViewById(R.id.question1);
        q3=findViewById(R.id.question3);
        q4=findViewById(R.id.question4);
        q5=findViewById(R.id.question5);
        q6=findViewById(R.id.question6);
        q7=findViewById(R.id.question7);
        q8=findViewById(R.id.question8);
        qText=findViewById(R.id.question8Text);
        dateText=findViewById(R.id.dateText);
        submitData=findViewById(R.id.profileButton);
        q6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton  radioButton = (RadioButton) findViewById(checkedId);
                Log.d("check", "onCreate: "+radioButton.getText().toString());
                if(radioButton.getText().toString().equals("Full time")||radioButton.getText().toString().equals("Part time"))
                {
                    q8.setVisibility(View.VISIBLE);
                    qText.setVisibility(View.VISIBLE);
                }
                else
                {
                    q8.setVisibility(View.INVISIBLE);
                    qText.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
      //For calender
    public void DatePicker(View view) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                ProfilingQuestions.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("check", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                dateText.setText(date);
            }
        };
    }

    public void SaveData(View view) {
        if(q1.getCheckedRadioButtonId()==-1||q3.getCheckedRadioButtonId()==-1||
                q4.getCheckedRadioButtonId()==-1||q5.getCheckedRadioButtonId()==-1||
                q6.getCheckedRadioButtonId()==-1||q7.getCheckedRadioButtonId()==-1)
        {
            Log.d("check", "onCreate:failed ");
        }
        else
        {
            RadioButton  radioButton = (RadioButton) findViewById(q6.getCheckedRadioButtonId());
            if((radioButton.getText().toString().equals("Full time")||radioButton.getText().toString().equals("Part time"))&&q8.getCheckedRadioButtonId()!=-1)
            Log.d("check", "onCreate:Passed ");
            else
                Log.d("check", "onCreate:failed ");
        }

    }
}
