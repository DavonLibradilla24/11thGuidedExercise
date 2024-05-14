package com.example.a11thguidedexercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Ge11IntentResultActivity extends AppCompatActivity {

    TextView name, age, gender, subjects, job, thesis;
    Button btnEnterAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge11_intent_result);

        // Initialize views
        initViews();

        // Display results
        showResults();

        // Setup button click listener
        btnEnterAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to the first screen
                startActivity(new Intent(Ge11IntentResultActivity.this, MainActivity.class));
                finish(); // Optional: Finish this activity to prevent going back to it when pressing back button
            }
        });
    }

    private void initViews() {
        name = findViewById(R.id.tvNameGE11);
        age = findViewById(R.id.tvAgeGE11);
        gender = findViewById(R.id.tvGenderGE11);
        subjects = findViewById(R.id.tvSubjectsGE11);
        job = findViewById(R.id.tvJobGE11);
        thesis = findViewById(R.id.tvThesisGE11);
        btnEnterAgain = findViewById(R.id.btnEnterAgain);
    }

    private void showResults() {
        //getStringExtra(Home.EXTRA_MESSAGE).equals(getString(R.string...
        // Note: If you have set values using putExtra(key, String) then you can simply get values using intent.
        name.setText("Name: " + getIntent().getStringExtra("id_name"));
        age.setText("Age: " + getIntent().getStringExtra("id_age"));
        gender.setText("Gender: " + getIntent().getStringExtra("id_gender"));
        subjects.setText("Subjects: \n" + getIntent().getStringExtra("id_subjects"));
        job.setText("Job: " + getIntent().getStringExtra("id_job"));
        thesis.setText("Thesis Topic: " + getIntent().getStringExtra("id_thesis"));
    }
}
