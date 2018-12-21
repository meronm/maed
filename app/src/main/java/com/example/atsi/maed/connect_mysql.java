package com.example.atsi.maed;



        import android.app.DownloadManager;
        import android.content.Context;
        import android.content.Intent;
        import android.net.ConnectivityManager;
        import android.os.AsyncTask;

        import org.apache.http.*;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;


        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
        import java.net.URI;
        import java.net.URL;
        import java.net.URLConnection;
        import java.net.URLEncoder;
        import java.util.ArrayList;
        import java.util.List;

public class connect_mysql extends AsyncTask<String, String, String> {
    private TextView nameResult;
    private Context context;
    private int byGetOrPost = 0;
    String[] inputs;
    String link1,link2;

    public connect_mysql(Context context,TextView nameResult,int flag,String link1,String[] inputs) {
        this.context = context;
        this.nameResult = nameResult;
        byGetOrPost = flag;
        this.link1=link1;
        this.link2=link2;
        this.inputs=inputs;
    }

    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {

        if (byGetOrPost == 0) { //means by Get Method

            InputStream inputStream = null;
            String result = "";

            try {
                String medication = arg0[0];
                //String link = "http://192.168.122.1/project.php?medication="+medication;
                link1 += "?" + inputs[0] + "=" + arg0[0];
                for (int i = 1; i < arg0.length; i++)
                    link1 += "&" + inputs[i] + "=" + arg0[i];


                //link1 +="?medication="+medication;
                URL url = new URL(link1);
                HttpClient client = new DefaultHttpClient();
                HttpResponse response = client.execute(new HttpGet(link1));
                inputStream = response.getEntity().getContent();
                if (inputStream != null)
                    result = converToString(inputStream);
                else
                    result = "did not work!";
            } catch (Exception e) {
                Log.d("inputStream", e.getLocalizedMessage());
            }
            return result;
        }
        else
            return "Error";
    }

    @Override
    protected void onPostExecute(String result) {
        this.nameResult.setText(result);
    }

    private  static  String converToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        String line="";
        String result="";
        //String [] resArray= new String[10];
        //int resultIndex=0;
        while ((line=bufferedReader.readLine())!=null)
            result +=line;
        //resArray[resultIndex]=line;
        //resultIndex++;
        inputStream.close();
        return result;
    }
}



