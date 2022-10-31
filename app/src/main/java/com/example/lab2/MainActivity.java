package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class lets a user enter a password and checks if the password is complex enough
 * @author Emilio Dutra
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    /** This holds the text in the middle of the screen */
    private TextView tv = null;
    /** This holds editText that a user types in */
    private EditText et =null;
    /** This holds the button that a user would use to login */
    private Button btn =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        et = findViewById(R.id.passwordEdit);
        btn = findViewById(R.id.button);

        btn.setOnClickListener( clk ->{
            String password = et.getText().toString();

           if(checkPasswordComplexity ( password)){
               tv.setText("Your Password meets the requirements");
           }else{
               tv.setText("You shall not pass!");
           }
        });


    }

    /**
     * This function checks the complexity of a password, if the password has an uppercase letter, a symbol, a digit.
     * If the password doesn't have one of these, the boolean will return as false
     * @param password the password to be tested from the user
     * @return Returns true if the password meets the criteria for a complex password, false if not
     */
    boolean checkPasswordComplexity ( String password){
        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;

        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;

        for (int counter = 0; counter < password.length(); counter++){
            if(Character.isUpperCase(password.charAt(counter))){
                foundUpperCase = true;
            }
            if(Character.isLowerCase(password.charAt(counter))){
                foundLowerCase = true;
            }
            if(Character.isDigit(password.charAt(counter))){
                foundNumber = true;
            }
            if(isSpecialCharacter(password.charAt(counter))){
                foundSpecial = true;
            }
        }
        if(!foundUpperCase){
            Toast.makeText( this, "missing an uppercase Letter :/", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if( ! foundLowerCase) {
            Toast.makeText( this, "missing a lowercase Letter :/", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if( ! foundNumber) {
            Toast.makeText( this, "missing a number :/", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(! foundSpecial) {
            Toast.makeText( this, "missing a special character :/", Toast.LENGTH_SHORT).show();
            return false;
        }

        else

            return true; //only get here if they're all true

    }

    /**
     * This function checks if a character is special or not
     * @param c the character being passed to the function to check if its special or not
     * @return True if the character is special, false if not
     */
    boolean isSpecialCharacter(char c){
        switch (c){
            case '#':
            case '&':
            case '%':
            case '!':
            case '@':
            case '?':
            case '*':
            case '^':
            case '$':
            return true;
            default:
                return false;
        }
    }

}