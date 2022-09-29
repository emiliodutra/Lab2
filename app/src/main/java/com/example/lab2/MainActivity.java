package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lab2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView( variableBinding.getRoot());

        TextView mytext = variableBinding.textview;
        //with variable binding you would use: variableBinding.textview;
        // instead of TextView mytext = findViewById(R.id.textview);
        Button mybutton = variableBinding.mybutton;
        EditText myedit = variableBinding.myedittext;
        String editString = myedit.getText().toString();

        mybutton.setOnClickListener((vw) -> {
                mytext.setText("Your edit has: " + editString);
            }
        );
    }
}