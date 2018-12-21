package com.example.atsi.maed;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class MyCart extends AppCompatActivity {

    Activity context;
    HttpPost httpPost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpClient;
    ProgressDialog pd;
    Customer_adp adapter;
    ListView listProduct;
    ArrayList<productList> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        context=this;
        records=new ArrayList<productList>();
        listProduct=(ListView)findViewById(R.id.myproduct_list);
        adapter=new Customer_adp(context,R.layout.list,R.id.product_name,records);
        listProduct.setAdapter(adapter);
        final TextView t=(TextView)findViewById(R.id.product_name);
        listProduct.setClickable(true);
        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Toast.makeText(getBaseContext(),"You order is placed",Toast.LENGTH_SHORT).show();
                TextView t=(TextView)view.findViewById(R.id.c_id);
                String ta=t.getText().toString();
                TextView y=(TextView)view.findViewById(R.id.me_id);
                String yg=y.getText().toString();
                TextView u=(TextView)view.findViewById(R.id.h_id);
                String ug=u.getText().toString();
                //SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm:ss");

                //String currentDateTime=dateFormat.format(new Date());
                //currentDateTime=currentDateTime.replaceAll("\\s","");
                String []input={"customer_id","menu_id","hotel_id","quantity"};
                onBack be=new onBack(input);
                be.execute(ta,yg,ug,"1");
            }
        });

    }
    public void onStart(){

        super.onStart();
        BackTask bt=new BackTask();
        bt.execute();
        //bt.execute();
    }
    public void approveClicked(View view)
    {
        userInRole u=new userInRole();
        String tb=u.getUserId();
        String in[]={"customer_id"};
        Back bc=new Back(in);
        bc.execute(tb);
        Toast.makeText(getBaseContext(),"Thanks for choosing MAED your food is in the way "+tb,Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,home.class);
        startActivity(i);
    }
    public void myAccountClicked(View view)
    {
        Intent i= new Intent(this,MyAccount.class);
        startActivity(i);

    }
    public void helpClicked(View view)
    {
        Intent i= new Intent(this,Help.class);
        startActivity(i);

    }
    public void homeClicked(View view)
    {
        Intent i= new Intent(this,home.class);
        startActivity(i);

    }
    private class BackTask extends AsyncTask<String,String,String> {
        // String link;
        String inputs;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(context);
            pd.setTitle("Retriving data");
            pd.setMessage("please wait");
            pd.setCancelable(true);
            pd.setIndeterminate(true);
            pd.show();
            System.out.println("was here 122");
        }
        /*public BackTask(String inputs)
        {
            this.inputs=inputs;
        }*/

        @Override
        protected String doInBackground(String... arg0) {
            InputStream is=null;
            String result="";
            try{
                String link="http://192.168.43.248/android_connect/get_from_mycart.php/";
                //link+=link+"?"+inputs+"="+arg0[0];
                httpClient=new DefaultHttpClient();
                httpPost=new HttpPost(link);
                response=httpClient.execute(httpPost);
                HttpEntity entity=response.getEntity();
                is=entity.getContent();
                System.out.println("was here 123");


            }catch (Exception e)
            {
                if(pd!=null)
                    pd.dismiss();
                Log.e("ERROR",e.getMessage());
                System.out.println("error 123");

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
                System.out.println("was here 124");

            }catch (Exception e)
            {
                Log.e("ERROR","Error converting result"+e.toString());
                System.out.println("error124");

            }
            try{
                result= result.substring(result.indexOf("["));
                JSONArray jArray=new JSONArray(result);
                for(int i=0;i<jArray.length();i++)
                {
                    JSONObject json_data=jArray.getJSONObject(i);
                    productList p=new productList();
                    p.setItem_name(json_data.getString("item_name"));

                    p.setItem_price(json_data.getInt("item_price"));
                    p.setCustomer_id(json_data.getInt("customer_id"));
                    p.setHotel_id(json_data.getInt("hotel_id"));
                    p.setMenu_id(json_data.getInt("menu_id"));
                    records.add(p);

                }
                System.out.println("was here 125");


            }catch (Exception e)
            {
                Log.e("ERROR","Error pasting data"+e.toString());
                System.out.println("error125");
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if(pd!=null)
                pd.dismiss();
            Log.e("size",records.size()+"");
            System.out.println("was here 126");
            adapter.notifyDataSetChanged();
            //super.onPostExecute(result);
        }
    }
    private class onBack extends AsyncTask<String,String,String>{
        String []inputs;
        public onBack(String []inputs)
        {

            this.inputs=inputs;
        }
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
                //arg0[2]=arg0[2].replaceAll("\\s+","%20");
                String link="http://192.168.43.248/android_connect/insert_into_orders.php/";
                link=link+"?"+inputs[0]+"="+arg0[0]+"&"+inputs[1]+"="+arg0[1]+"&"+inputs[2]+"="+arg0[2]+"&"+inputs[3]+"="+arg0[3];
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
                System.out.println("error10");

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
                System.out.println("error11");

            }
            try{
                result= result.substring(result.indexOf("["));
                JSONArray jArray=new JSONArray(result);
                for(int i=0;i<jArray.length();i++)
                {
                    //JSONObject json_data=jArray.getJSONObject(i);
                    //Product p=new Product();
                    //p.setpName(json_data.getString("item"));
                    // p.setuPrice(json_data.getInt("price"));
                    // records.add(p);

                }


            }catch (Exception e)
            {
                Log.e("ERROR","Error pasting data"+e.toString());
                System.out.println("error12");
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if(pd!=null)
                pd.dismiss();
            Log.e("size",records.size()+"");
            // adapter.notifyDataSetChanged();
            //super.onPostExecute(result);
        }

    }
    private class Back extends AsyncTask<String,String,String>{
        private String [] input;
        public Back(String []input){
            this.input=input;
        }


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
                System.out.println("it works");
                //arg0[2]=arg0[2].replaceAll("\\s+","%20");
                String link="http://192.168.43.248/android_connect/delete_from_mycart.php/";
                link=link+"?"+input[0]+"="+arg0;
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
                System.out.println("error10");

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
                System.out.println("error11");

            }
            try{
                result= result.substring(result.indexOf("["));
                JSONArray jArray=new JSONArray(result);
                for(int i=0;i<jArray.length();i++)
                {
                    //JSONObject json_data=jArray.getJSONObject(i);
                    //Product p=new Product();
                    //p.setpName(json_data.getString("item"));
                    // p.setuPrice(json_data.getInt("price"));
                    // records.add(p);

                }


            }catch (Exception e)
            {
                Log.e("ERROR","Error pasting data"+e.toString());
                System.out.println("error50");
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if(pd!=null)
                pd.dismiss();
            Log.e("size",records.size()+"");
            // adapter.notifyDataSetChanged();
            //super.onPostExecute(result);
        }

    }


}
