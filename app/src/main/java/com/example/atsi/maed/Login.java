package com.example.atsi.maed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//import java.net.HttpURLConnection;
//import java.net.HttpClient;


//import static android.R.attr.value;

public class Login extends AppCompatActivity {
    private EditText userNameField, passwordField;
    private String userName, password;
    private TextView messageView;
    private Button btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameField = (EditText) findViewById(R.id.userName);
        passwordField = (EditText) findViewById(R.id.password);
        messageView = (TextView) findViewById(R.id.messageView);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(v);

            }
        });

    }



    public void signIn(View view) {
        String username = userNameField.getText().toString();
        String password = passwordField.getText().toString();
        String link1 = "http://192.168.43.248/android_connect/login.php";
        String[] inputs = {"username", "password"};
        new connect_mysql(this, messageView, 0, link1, inputs).execute(username, password);

        String userID = messageView.getText().toString();
        messageView.setVisibility(View.INVISIBLE);

        messageView.setText(userID);
        //String abc=messageView.getText().toString();
        switch (userID) {
            case "No user":
                Toast.makeText(getBaseContext(), "invalid user", Toast.LENGTH_SHORT).show();
                break;
            case "hello":
                Toast.makeText(getBaseContext(), "invalid user name", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getBaseContext(), "well come to maed", Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(this, home.class);
                Login.this.finish();
                userInRole inrole = new userInRole();
                inrole.setUserId(userID);
                startActivity(i2);
                break;
        }
    }

    public void signupButtonClicked(View view) {

        Intent intent = new Intent(this, create_account_activity.class);
        startActivity(intent);
    }
}
/*
private class loginA extends AsyncTask<String,Void,String>
{
    public String returnValue = "HELLO";
    @Override
    protected void onPostExecute(String s) {
        System.out.println("sucess");
        returnValue = s;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... arg0) {


        String link="";
        boolean flag=true;
        try {
            String userName = (String) arg0[0];
            String password =(String) arg0[1];
            link = "http://192.168.173.1/android_connect/login.php?userName="
                    +userName + "&password="+password;
            System.out.print("it is working");
        }catch(Exception e)
        {
            //todo warn the user that integer conversion gave problem
            flag = false;
            System.out.println("doesnt work");
            return "invalid username or password";
        }
        if(flag){
            try{
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                //HttpGet request = new HttpGet();
                //request.setURI(new URI(link));
                HttpResponse response = client.execute(new HttpGet(link));
                BufferedReader in = new BufferedReader
                        (new InputStreamReader(response.getEntity().getContent()));
                StringBuffer sb = new StringBuffer("");

                Log.d("info",in.readLine());

                String line="";
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
            }
            catch(Exception e)
            {
                //todo warn the user that connection problem is around
                return "connection problem, try again";
            }
        }
        else{
            return "Please try again";
        }


        //return null;
    }
}*/
