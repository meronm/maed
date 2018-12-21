package com.example.atsi.maed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Help extends AppCompatActivity {
    HashMap<String,List<String>> Movies_catagories;
    List<String> Movies_list;
    ExpandableListView Exp_list;
    //Movies_addapter addapter;
    ExpandableListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Exp_list=(ExpandableListView) findViewById(R.id.exp_list);
        Movies_catagories=data_provider.get_info();
        Movies_list=new ArrayList<String>(Movies_catagories.keySet());
        listAdapter=new hotelAdapter(this,Movies_catagories,Movies_list);
        Exp_list.setAdapter(listAdapter);

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

    public void homeClicked(View view)
    {
        Intent i= new Intent(this,home.class);
        startActivity(i);

    }
}
