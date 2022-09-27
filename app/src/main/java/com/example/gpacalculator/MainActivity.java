package com.example.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText class1;
    private EditText class2;
    private EditText class3;
    private EditText class4;
    private EditText class5;

    private TextView gpa;

    private View parentView;

    private Button calculateButton;

    private boolean isCalculated;
    private double averageGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        class1 = findViewById(R.id.class1_editText);
        class2 = findViewById(R.id.class2_editText);
        class3 = findViewById(R.id.class3_editText);
        class4 = findViewById(R.id.class4_editText);
        class5 = findViewById(R.id.class5_editText);
        gpa = findViewById(R.id.gpa_textView);
        parentView = findViewById(R.id.parent);

        gpa.setText("0");
        calculateButton = findViewById(R.id.calculate_button);
        clearFields();
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isCalculated){
                    if(!isClear()){
                        double grade = 0;
                        grade += Integer.parseInt(class1.getText().toString().trim());
                        grade += Integer.parseInt(class2.getText().toString().trim());
                        grade += Integer.parseInt(class3.getText().toString().trim());
                        grade += Integer.parseInt(class4.getText().toString().trim());
                        grade += Integer.parseInt(class5.getText().toString().trim());

                        averageGrade = grade/5.0;
                        String text = String.format("%.2f",averageGrade);
                        gpa.setText(text);
                        setBackGroundColor(averageGrade);
                        calculateButton.setText("Clear");
                        isCalculated = true;
                    }else{
                        Toast.makeText(MainActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    clearFields();
                    parentView.setBackgroundColor(getColor(R.color.white));
                    gpa.setText("0");
                    calculateButton.setText("Compute GPA");
                    isCalculated = false;
                }
            }
        });
    }

    private void setBackGroundColor(double grade) {
        if (grade<60){
            parentView.setBackgroundColor(getColor(R.color.red));
        }else if(grade>=60 && grade < 80){
            parentView.setBackgroundColor(getColor(R.color.yellow));
        }else if(grade>=80 && grade <= 100){
            parentView.setBackgroundColor(getColor(R.color.green));
        }else{
            parentView.setBackgroundColor(getColor(R.color.white));
        }
    }

    private boolean isClear() {
        return class1.getText().toString().trim().equals("")||class2.getText().toString().trim().equals("")||
                class3.getText().toString().trim().equals("")||class4.getText().toString().trim().equals("")||
                class5.getText().toString().trim().equals("");
    }

    private void clearFields() {
        class1.setText("");
        class2.setText("");
        class3.setText("");
        class4.setText("");
        class5.setText("");
    }
}
