package com.jigar.android.resto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookedTabel extends AppCompatActivity {

    ProgressDialog pDialog;
    ArrayList<RowTabelBooked> arrayList_tabelBooked = new ArrayList();
    String ViewBookedTabelRecord_Url=LiveLink.WebservicesLink+"viewBookedTabelRecord.php";
    ImageView img_tabel1,img_tabel2,img_tabel3,img_tabel4,img_tabel5,img_tabel6,img_tabel7,img_tabel8,img_tabel9,img_tabel10,img_tabel11,img_tabel12,img_tabel13,img_tabel14,img_tabel15;
    TextView tv_1,tv_2,tv_3,tv_4,tv_5,tv_6,tv_7,tv_8,tv_9,tv_10,tv_11,tv_12,tv_13,tv_14,tv_15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_tabel);

        img_tabel1=(ImageView) findViewById(R.id.table1);
        img_tabel2=(ImageView)findViewById(R.id.table2);
        img_tabel3=(ImageView) findViewById(R.id.table3);
        img_tabel4=(ImageView)findViewById(R.id.table4);
        img_tabel5=(ImageView) findViewById(R.id.table5);
        img_tabel6=(ImageView)findViewById(R.id.table6);
        img_tabel7=(ImageView) findViewById(R.id.table7);
        img_tabel8=(ImageView)findViewById(R.id.table8);
        img_tabel9=(ImageView) findViewById(R.id.table9);
        img_tabel10=(ImageView)findViewById(R.id.table10);
        img_tabel11=(ImageView)findViewById(R.id.table11);
        img_tabel12=(ImageView)findViewById(R.id.table12);
        img_tabel13=(ImageView)findViewById(R.id.table13);
        img_tabel14=(ImageView)findViewById(R.id.table14);
        img_tabel15=(ImageView)findViewById(R.id.table15);

        tv_1=(TextView)findViewById(R.id.tv_1);
        tv_2=(TextView)findViewById(R.id.tv_2);
        tv_3=(TextView)findViewById(R.id.tv_3);
        tv_4=(TextView)findViewById(R.id.tv_4);
        tv_5=(TextView)findViewById(R.id.tv_5);
        tv_6=(TextView)findViewById(R.id.tv_6);
        tv_7=(TextView)findViewById(R.id.tv_7);
        tv_8=(TextView)findViewById(R.id.tv_8);
        tv_9=(TextView)findViewById(R.id.tv_9);
        tv_10=(TextView)findViewById(R.id.tv_10);
        tv_11=(TextView)findViewById(R.id.tv_11);
        tv_12=(TextView)findViewById(R.id.tv_12);
        tv_13=(TextView)findViewById(R.id.tv_13);
        tv_14=(TextView)findViewById(R.id.tv_14);
        tv_15=(TextView)findViewById(R.id.tv_15);

        new getInsertedTablesRecord().execute();

        img_tabel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_menu_list = new Intent(BookedTabel.this,MainActivity.class);
                intent_menu_list.putExtra("Key_tabel_no","1");
                startActivity(intent_menu_list);
            }
        });
        img_tabel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_menu_list = new Intent(BookedTabel.this,MainActivity.class);
                intent_menu_list.putExtra("Key_tabel_no","2");
                startActivity(intent_menu_list);
            }
        });

        img_tabel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_menu_list = new Intent(BookedTabel.this,MainActivity.class);
                intent_menu_list.putExtra("Key_tabel_no","3");
                startActivity(intent_menu_list);
            }
        });

        img_tabel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_menu_list = new Intent(BookedTabel.this,MainActivity.class);
                intent_menu_list.putExtra("Key_tabel_no","4");
                startActivity(intent_menu_list);
            }
        });

        img_tabel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_menu_list = new Intent(BookedTabel.this,MainActivity.class);
                intent_menu_list.putExtra("Key_tabel_no","5");
                startActivity(intent_menu_list);
            }
        });
        img_tabel6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_menu_list = new Intent(BookedTabel.this,MainActivity.class);
                intent_menu_list.putExtra("Key_tabel_no","6");
                startActivity(intent_menu_list);
            }
        });
        img_tabel7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_menu_list = new Intent(BookedTabel.this,MainActivity.class);
                intent_menu_list.putExtra("Key_tabel_no","7");
                startActivity(intent_menu_list);
            }
        });
        img_tabel8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_menu_list = new Intent(BookedTabel.this,MainActivity.class);
                intent_menu_list.putExtra("Key_tabel_no","8");
                startActivity(intent_menu_list);
            }
        });
        img_tabel9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_menu_list = new Intent(BookedTabel.this,MainActivity.class);
                intent_menu_list.putExtra("Key_tabel_no","9");
                startActivity(intent_menu_list);
            }
        });
        img_tabel10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_menu_list = new Intent(BookedTabel.this,MainActivity.class);
                intent_menu_list.putExtra("Key_tabel_no","10");
                startActivity(intent_menu_list);
            }
        });
        img_tabel11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_menu_list = new Intent(BookedTabel.this,MainActivity.class);
                intent_menu_list.putExtra("Key_tabel_no","11");
                startActivity(intent_menu_list);
            }
        });
        img_tabel12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_menu_list = new Intent(BookedTabel.this,MainActivity.class);
                intent_menu_list.putExtra("Key_tabel_no","12");
                startActivity(intent_menu_list);
            }
        });
        img_tabel13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_menu_list = new Intent(BookedTabel.this,MainActivity.class);
                intent_menu_list.putExtra("Key_tabel_no","13");
                startActivity(intent_menu_list);
            }
        });
        img_tabel14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_menu_list = new Intent(BookedTabel.this,MainActivity.class);
                intent_menu_list.putExtra("Key_tabel_no","14");
                startActivity(intent_menu_list);
            }
        });
        img_tabel15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_menu_list = new Intent(BookedTabel.this,MainActivity.class);
                intent_menu_list.putExtra("Key_tabel_no","15");
                startActivity(intent_menu_list);
            }
        });

    }

    class getInsertedTablesRecord extends AsyncTask<String, String, JSONObject>
    {
        JSONParser jsonParser = new JSONParser();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(BookedTabel.this);
            pDialog.setMessage("Please wait");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {

            try{

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("status", "1"));//stastus means customer on tabel

                JSONObject json = jsonParser.makeHttpRequest(
                        ViewBookedTabelRecord_Url, "POST",params);
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
                String result= json.toString();
                JSONArray arr = null;
                try {
                    JSONObject json_getcart_data = new JSONObject();

                    arr = json.getJSONArray("TabelsRecord");
                    for(int i=0;i<arr.length();i++) {
                        json_getcart_data = arr.getJSONObject(i);
                        String tabel_no = json_getcart_data.getString("tabel_no");

                        if(tabel_no.equals("1"))
                        {
                                img_tabel1.setImageResource(R.mipmap.ic_tabel_booked);
                                tv_1.setText("Booked");
                        }
                        if(tabel_no.equals("2"))
                        {
                                img_tabel2.setImageResource(R.mipmap.ic_tabel_booked);
                                tv_2.setText("Booked");
                        }
                        if(tabel_no.equals("3"))
                        {
                            img_tabel3.setImageResource(R.mipmap.ic_tabel_booked);
                            tv_3.setText("Booked");
                        }
                        if(tabel_no.equals("4"))
                        {
                            img_tabel4.setImageResource(R.mipmap.ic_tabel_booked);
                            tv_4.setText("Booked");
                        }
                        if(tabel_no.equals("5"))
                        {
                            img_tabel5.setImageResource(R.mipmap.ic_tabel_booked);
                            tv_5.setText("Booked");
                        }
                        if(tabel_no.equals("6"))
                        {
                            img_tabel6.setImageResource(R.mipmap.ic_tabel_booked);
                            tv_6.setText("Booked");
                        }
                        if(tabel_no.equals("7"))
                        {
                            img_tabel7.setImageResource(R.mipmap.ic_tabel_booked);
                            tv_7.setText("Booked");
                        }
                        if(tabel_no.equals("8"))
                        {
                            img_tabel8.setImageResource(R.mipmap.ic_tabel_booked);
                            tv_8.setText("Booked");
                        }
                        if(tabel_no.equals("9"))
                        {
                            img_tabel9.setImageResource(R.mipmap.ic_tabel_booked);
                            tv_9.setText("Booked");
                        }
                        if(tabel_no.equals("10"))
                        {
                            img_tabel10.setImageResource(R.mipmap.ic_tabel_booked);
                            tv_10.setText("Booked");
                        }
                        if(tabel_no.equals("11"))
                        {
                            img_tabel11.setImageResource(R.mipmap.ic_tabel_booked);
                            tv_11.setText("Booked");
                        }
                        if(tabel_no.equals("12"))
                        {
                            img_tabel12.setImageResource(R.mipmap.ic_tabel_booked);
                            tv_12.setText("Booked");
                        }
                        if(tabel_no.equals("13"))
                        {
                            img_tabel13.setImageResource(R.mipmap.ic_tabel_booked);
                            tv_13.setText("Booked");
                        }
                        if(tabel_no.equals("14"))
                        {
                            img_tabel14.setImageResource(R.mipmap.ic_tabel_booked);
                            tv_14.setText("Booked");
                        }
                        if(tabel_no.equals("15"))
                        {
                            img_tabel15.setImageResource(R.mipmap.ic_tabel_booked);
                            tv_15.setText("Booked");
                        }

                    }
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
