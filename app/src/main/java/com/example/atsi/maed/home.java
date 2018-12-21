package com.example.atsi.maed;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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


public class home extends AppCompatActivity {
    Activity context;
    HttpPost httpPost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpClient;
    ProgressDialog pd;
    CustomerAdapter adapter;
    ListView listProduct;
    ArrayList<Product> records;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context=this;
        records=new ArrayList<Product>();
        listProduct=(ListView)findViewById(R.id.product_list);
        adapter=new CustomerAdapter(context,R.layout.list_item,R.id.pro_name,records);
        listProduct.setAdapter(adapter);
        listProduct.setClickable(true);
        //listProduct.setVisibility(View.INVISIBLE);
        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tg=(TextView) view.findViewById(R.id.pro_uprice);
                TextView hotelNam=(TextView)view.findViewById(R.id.pro_name);
                String hotelName=hotelNam.getText().toString();

                String tag=tg.getText().toString();
                Intent i=new Intent(context,menu.class);
                Bundle extras=new Bundle();
                extras.putString("lable1",tag);
                extras.putString("lable2",hotelName);
                i.putExtras(extras);
                startActivity(i);

                Toast.makeText(getBaseContext(),"Here is the menu of "+hotelName,Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void onStart(){
        super.onStart();
        // BackTask bt=new BackTask();
        //bt.execute();
        //bt.execute();
    }
    public void sendUsFeedBackClicked(View view)
    {
        Intent i= new Intent(this,feedBack.class);
        startActivity(i);

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
    public void hotelClicked(View view)
    {
        BackTask bt=new BackTask();
        bt.execute();

    }
    private class BackTask extends AsyncTask<String,String,String> {
        // String link;
        //String inputs;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(context);
            pd.setTitle("Retriving data");
            pd.setMessage("please wait");
            pd.setCancelable(true);
            pd.setIndeterminate(true);
            pd.show();
        }


        @Override
        protected String doInBackground(String... arg0) {
            InputStream is=null;
            String result="";
            try{
                String link="http://192.168.43.248/android_connect/geting_hotelName.php/";

                httpClient=new DefaultHttpClient();
                httpPost=new HttpPost(link);
                response=httpClient.execute(httpPost);
                HttpEntity entity=response.getEntity();
                is=entity.getContent();


            }catch (Exception e)
            {
                if(pd!=null)
                    pd.dismiss();
                Log.e("ERROR",e.getMessage());
                System.out.println("erro1r");

            }
            try {
                BufferedReader reader=new BufferedReader(new InputStreamReader(is,"utf-8"),8);
                StringBuilder sb=new StringBuilder();
                String line=null;
                while((line=reader.readLine())!=null)
                {
                    sb.append(line+"\n");
                }
                is.close();
                result=sb.toString();

            }catch (Exception e)
            {
                Log.e("ERROR","Error converting result"+e.toString());
                System.out.println("error2");

            }
            try{
                result= result.substring(result.indexOf("["));
                JSONArray jArray=new JSONArray(result);
                for(int i=0;i<jArray.length();i++)
                {
                    JSONObject json_data=jArray.getJSONObject(i);
                    Product p=new Product();
                    p.setpName(json_data.getString("hotel_name"));
                    p.setuPrice(json_data.getString("id"));
                    records.add(p);

                }


            }catch (Exception e)
            {
                Log.e("ERROR","Error pasting data"+e.toString());
                System.out.println("error3");
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if(pd!=null)
                pd.dismiss();
            Log.e("size",records.size()+"");
            adapter.notifyDataSetChanged();
            //super.onPostExecute(result);
        }
    }
}

