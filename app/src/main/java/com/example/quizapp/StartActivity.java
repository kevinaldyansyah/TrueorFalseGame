package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    private EditText name;
    private Button buttonStart;
    private String KEY_NAME = "NAMA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        name = findViewById(R.id.edtNamaTxt);

        buttonStart = findViewById(R.id.btnStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String nama = name.getText().toString();
                    if (nama != null && nama != "") {
                        Intent i = new Intent(StartActivity.this, MainActivity.class);
                        i.putExtra(KEY_NAME, nama);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplication(), "YOU NEED TO FILL YOUR NAME", Toast.LENGTH_SHORT);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplication(), "ERROR TRY AGAIN", Toast.LENGTH_SHORT);
                }
            }
        });
    }
}