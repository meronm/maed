package com.example.atsi.maed;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class create_account_activity extends AppCompatActivity {
    Activity context;
    HttpPost httpPost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpClient;
    ProgressDialog pd;
    CustomerAdapter adapter;
    //ListView listProduct;
    //ArrayList<Product> records;
    private EditText first_name, last_name, middle_name, user_name, pass_word, cpass_word, birth_day, Sex, phoneNumber, Address;
    private Button finish;
    private TextView messageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_activity);
        first_name = (EditText) findViewById(R.id.first_name);
        last_name = (EditText) findViewById(R.id.last_name);
        middle_name = (EditText) findViewById(R.id.middle_name);
        user_name = (EditText) findViewById(R.id.user_name);
        pass_word = (EditText) findViewById(R.id.cmpassword);
        cpass_word = (EditText) findViewById(R.id.cpassword);
        birth_day = (EditText) findViewById(R.id.birthday);
        Sex = (EditText) findViewById(R.id.sex);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        messageView=(TextView) findViewById(R.id.mesView);
        Address = (EditText) findViewById(R.id.address);
        finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishCl(v);

            }
        });


    }

    public void finishCl(View view) {

        String firstName = first_name.getText().toString();
        String lastName = last_name.getText().toString();
        String middleName = middle_name.getText().toString();
        String userName = user_name.getText().toString();
        String password = pass_word.getText().toString();
        String cpassword = cpass_word.getText().toString();
        String sex = Sex.getText().toString();
        String birthday = birth_day.getText().toString();
        String phonenumber = phoneNumber.getText().toString();
        String address = Address.getText().toString();
        String messaView=messageView.getText().toString();
        String link1 = "http://192.168.173.1/android_connect/insert_user_info.php";
        String[] inputs = {"hotel_id","first_name", "middle_name","last_name","birthday","phoneNumber","sex","address"};
        new connect_mysql(this, messageView, 0, link1, inputs).execute("1",firstName,middleName, lastName,birthday,phonenumber,sex,address);
        String link2 = "http://192.168.173.1/android_connect/insert_user_info.php";
        String[] inputs1 = {"hotel_id","first_name", "middle_name","last_name","birthday","phoneNumber","sex","address"};
        new connect_mysql(this, messageView, 0, link2, inputs1).execute("1",firstName,middleName, lastName,birthday,phonenumber,sex,address);



    }
}