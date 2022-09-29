package com.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.data.MainViewModel;
import com.example.lab2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel model;
    private ActivityMainBinding variableBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);
//        setContentView(R.layout.activity_main);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView( variableBinding.getRoot());

        TextView mytext = variableBinding.textview;
        //with variable binding you would use: variableBinding.textview;
        // instead of TextView mytext = findViewById(R.id.textview);
        EditText myedit = variableBinding.myedittext;
        String editString = myedit.getText().toString();

        variableBinding.mybutton.setOnClickListener(click -> {
            model.editString = variableBinding.myedittext.getText().toString();
            mytext.setText("Your edit has: " + model.editString);//used mytext but could have used
            //variableBinding.myText
        });
    }
}