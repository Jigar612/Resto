package com.jigar.android.resto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText ed_pass,ed_user_nm;
    Button btn_login;
    String user_nm,pass;
    ProgressDialog pDialog;
    String getLoginDetail_Url=LiveLink.WebservicesLink+"login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_user_nm=(EditText)findViewById(R.id.ed_user_nm);
        ed_pass=(EditText)findViewById(R.id.ed_pass);
        btn_login=(Button)findViewById(R.id.btn_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 user_nm = ed_user_nm.getText().toString();
                 pass = ed_pass.getText().toString();

                 new Login_service().execute();

            }
        });
    }
    class Login_service extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Please wait");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("user_nm", user_nm));
                params.add(new BasicNameValuePair("pass", pass));

                JSONObject json = jsonParser.makeHttpRequest(
                        getLoginDetail_Url, "POST", params);

                if (json != null) {
                    Log.d("JSON result", json.toString());
                    return json;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
               return null;
        }
        protected void onPostExecute(JSONObject json) {

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
            if (json != null) {
                String result = json.toString();
                JSONArray arr;
                try {

                    JSONObject jsonObject = new JSONObject(result);
                    String msg=jsonObject.getString("message");
                    if(msg.equals("login success"))
                    {
                        Intent intent_menu=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent_menu);

                    }
                    else
                    {

                    }
                       // arr = json.getJSONArray("LoginDetail");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
