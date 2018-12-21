package com.example.atsi.maed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }
    public void myAccountClicked(View view)
    {
        Intent i= new Intent(this,MyAccount.class);
        startActivity(i);

    }
    public void mycartClicked(View view)
    {
        Intent i= new Intent(this,MyCart.class);
        startActivity(i);

    }
    public void aboutUsClicked(View view)
    {
        Intent i= new Intent(this,AboutUs.class);
        startActivity(i);

    }
    public void helpClicked(View view)
    {
        Intent i= new Intent(this,Help.class);
        startActivity(i);

    }
    public void homeClicked(View view)
    {
        Intent i= new Intent(this,Help.class);
        startActivity(i);

    }
}
