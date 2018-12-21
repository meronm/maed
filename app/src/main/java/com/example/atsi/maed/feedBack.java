package com.example.atsi.maed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class feedBack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
    }
    public void submitButtonClicked(View view)
    {
        EditText et=(EditText)findViewById(R.id.feedbackText);
        String erf=et.getText().toString();
        et.setText("");
        Toast.makeText(getBaseContext(),"Thanks for your feedback",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,home.class);
        startActivity(i);
    }
}
