package com.example.a11thguidedexercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText name, age;
    RadioButton[] genderOptions;
    CheckBox[] subjectCheckboxes;
    Spinner job;
    ListView thesis;
    Button submit;
    Intent intent;
    ArrayAdapter<String> adapter;
    String[] jobList = {"Software Developer", "Software QA", "System Analyst", "Data Scientist"};
    String[] thesisTopics = {"Web Based/On-Line Application", "Network-Based Application ",
            "System/Software Development ", "Computer Aided Instruction "};
    String gender, subjects, topic = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        displayResult();
    }

    public void init() {
        name = findViewById(R.id.etNameGE11);
        age = findViewById(R.id.etAgeGE11);
        RadioGroup genderGroup = findViewById(R.id.radioGroup);
        genderOptions = new RadioButton[]{findViewById(R.id.rbtnMaleGE11), findViewById(R.id.rbtnFemaleGE11)};
        subjectCheckboxes = new CheckBox[]{findViewById(R.id.chkAppdetGE11), findViewById(R.id.chkIntcompGE11),
                findViewById(R.id.chkComprog1GE11), findViewById(R.id.chkComprog2GE11)};
        submit = findViewById(R.id.btnSubmitGE11);
        intent = new Intent(this, Ge11IntentResultActivity.class);

        job = findViewById(R.id.spnrJobGE11);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, jobList);
        job.setAdapter(adapter);

        thesis = findViewById(R.id.lvThesisGE11);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, thesisTopics);
        thesis.setAdapter(adapter);
    }

    public void displayResult() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty()) {
                    showToast("Please enter your name");
                    return;
                }
                if (age.getText().toString().isEmpty()) {
                    showToast("Please enter your age");
                    return;
                }
                if (getSelectedGender().isEmpty()) {
                    showToast("Please select your gender");
                    return;
                }
                if (getSelectedSubjects().isEmpty()) {
                    showToast("Please select at least 1 for your favorite subject");
                    return;
                }
                if (getSelectedThesis().isEmpty()) {
                    showToast("Please select your Thesis Topic");
                    return;
                }

                intent.putExtra("id_name", name.getText().toString());
                intent.putExtra("id_age", age.getText().toString());
                intent.putExtra("id_gender", getSelectedGender());
                intent.putExtra("id_subjects", getSelectedSubjects());
                intent.putExtra("id_job", jobList[job.getSelectedItemPosition()]);
                intent.putExtra("id_thesis", getSelectedThesis());
                startActivity(intent);
            }
        });
    }

    private String getSelectedGender() {
        for (RadioButton radioButton : genderOptions) {
            if (radioButton.isChecked()) {
                return radioButton.getText().toString();
            }
        }
        return "";
    }

    private String getSelectedSubjects() {
        StringBuilder subjectsBuilder = new StringBuilder();
        for (CheckBox checkBox : subjectCheckboxes) {
            if (checkBox.isChecked()) {
                subjectsBuilder.append(checkBox.getText().toString()).append("\n");
            }
        }
        return subjectsBuilder.toString().trim();
    }

    private String getSelectedThesis() {
        thesis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast("You selected " + thesisTopics[position]);
                topic = thesisTopics[position];
            }
        });
        return topic;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
