package edu.utsa.cs3443.pfv610_lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
This class only takes care of onclick events
that is why we can make the class to implement the interface for onclick events
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupButtons();
    }

    @Override
    public void onClick(View view) {
        //Do I need to send anything to the other views from this view?
        //if button clicked is trick the go to TrickActivity
        //else go to the treat activity
    }

    private void setupButtons(){
        //two buttons and they both have an id which is an integer
        int[] buttonIDs = {R.id.button1, R.id.button2};
        String[] buttonText = {"Trick", "Treat"};
        for(int i = 0; i < buttonIDs.length; i++){
            Button button = findViewById(buttonIDs[i]);
            button.setText(buttonText[i]);
            button.setOnClickListener(this);
        }
    }
}