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

public class menu extends AppCompatActivity {
    Activity context;
    HttpPost httpPost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpClient;
    ProgressDialog pd;
    Customer_Adapter adapter;
    ListView listProduct;
    ArrayList<product_list> records;
    userInRole us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final TextView img=(TextView) findViewById(R.id.img);
        Intent intent=getIntent();
        Bundle extras=intent.getExtras();
        String hotelId=extras.getString("lable1");
        final String hotelName=extras.getString("lable2");
        img.setText(hotelId);
        context=this;
        records=new ArrayList<product_list>();
        listProduct=(ListView)findViewById(R.id.p_list);
        adapter=new Customer_Adapter(context,R.layout.list_i,R.id.p_name,records);
        listProduct.setAdapter(adapter);
        final TextView t=(TextView)findViewById(R.id.p_name);
        listProduct.setClickable(true);
        us=new userInRole();
        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tg=(TextView) view.findViewById(R.id.p_name);
                String tag=tg.getText().toString();
                String abc=us.getUserId();
                String [] inputs={"customer_id","hotel_id","menu_id"};
                onBack bs=new onBack(inputs);
                //tag=tag.replaceAll("\\s","");
                String hotelId=img.getText().toString();
                TextView tp=(TextView)view.findViewById(R.id.m_id);
                String ty=tp.getText().toString();
                bs.execute(abc,hotelId,ty);
                Toast.makeText(getBaseContext(),tag+" is added to Mycart",Toast.LENGTH_SHORT).show();
            }
        });

    }
     public void onStart(){
         TextView ts=(TextView)findViewById(R.id.img);
         String hotelId=ts.getText().toString();
        super.onStart();
        String in="hotel_id";
        BackTask bt=new BackTask(in);
        bt.execute(hotelId);
        System.out.println("was here1");
    }
    private class BackTask extends AsyncTask<String,String,String> {
        // String link;
        String inputs;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("was here2");
            pd=new ProgressDialog(context);
            pd.setTitle("Retriving data");
            pd.setMessage("please wait");
            pd.setCancelable(true);
            pd.setIndeterminate(true);
            pd.show();
        }
        public BackTask(String inputs)
        {
            this.inputs=inputs;
        }

        @Override
        protected String doInBackground(String... arg0) {
            InputStream is=null;
            String result="";
            try{
                String link="http://192.168.43.248/android_connect/try.php/";
                link+=link+"?"+inputs+"="+arg0[0];
                httpClient=new DefaultHttpClient();
                httpPost=new HttpPost(link);
                response=httpClient.execute(httpPost);
                HttpEntity entity=response.getEntity();
                is=entity.getContent();
                System.out.println("was here3");


            }catch (Exception e)
            {
                if(pd!=null)
                    pd.dismiss();
                Log.e("ERROR",e.getMessage());
                System.out.println("error1");

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
                System.out.println("was here4");

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
                    product_list p=new product_list();
                    p.setproName(json_data.getString("item_name"));
                    p.setunitPrice(json_data.getInt("item_price"));
                    p.setId(json_data.getString("id"));
                    records.add(p);
                    System.out.println("was here5");

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
            System.out.println("was here6");
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
                String link="http://192.168.43.248/android_connect/insert_into_mycart.php/";
                link=link+"?"+inputs[0]+"="+arg0[0]+"&"+inputs[1]+"="+arg0[1]+"&"+inputs[2]+"="+arg0[2];
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
}

