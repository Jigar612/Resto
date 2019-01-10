package com.jigar.android.resto;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spinner_item_category;
    Spinner spinner_item_name;
   // Spinner spineer_table_no;
    TextView tv_tabel_no;

//    String GetMenuList_Url="http://10.0.2.2/resto/getMenuDetails.php";
//    String GetAllCategory_Url="http://10.0.2.2/resto/getAllCategoryList.php";
//
//    String InsertRecord_Url="http://10.0.2.2/resto/insertRecrodList.php";
//    String ViewInsertedRecord_Url="http://10.0.2.2/resto/viewInsertedTabelRecordList.php";
//
//    String UpdatePlaceOrderRecord_Url="http://10.0.2.2/resto/updatePlaceOrderTabel.php";
//    String UpdateStatusOrderRecord_Url="http://10.0.2.2/resto/updateStatusOrderTabel.php";

    String GetMenuList_Url=LiveLink.WebservicesLink+"getMenuDetails.php";
    String GetAllCategory_Url=LiveLink.WebservicesLink+"getAllCategoryList.php";

    String InsertRecord_Url=LiveLink.WebservicesLink+"insertRecrodList.php";
    String ViewInsertedRecord_Url=LiveLink.WebservicesLink+"viewInsertedTabelRecordList.php";

    String UpdatePlaceOrderRecord_Url=LiveLink.WebservicesLink+"updatePlaceOrderTabel.php";
    String UpdateStatusOrderRecord_Url=LiveLink.WebservicesLink+"updateStatusOrderTabel.php";



    ProgressDialog pDialog;
    String spinner_id;
    ArrayList arrayList_spinner_category= new ArrayList();
    ArrayAdapter<String>adapter_category_name;

    //ArrayList arrayList_tabel_no= new ArrayList();
    ArrayAdapter<String>adapter_table_no;

    ArrayList arrayList_spinner_item_nm= new ArrayList();
    ArrayAdapter<String>adapter_item_name;

    ArrayList<Row_Table_Record> arrayList_tableRecord = new ArrayList();

    TextView tv_item_price,tv_total;
    EditText ed_qty,ed_note;
    Button btn_add,btn_placed_order,btn_bill;
    ListView listView_TabelRecord;
    TextView tv_empty_msg;

    String selected_category_name;
    String selected_item_name;
    String seleted_tabel_no;
    String getSelectedName_item_id;
    String menu_item_id;

    String inser_tabel_no;
    String inser_item_name;
    String inser_price;
    String inser_qty;
    String inser_total;
    String insert_note;

    double cnvt_price,cnvt_qty;

    resultInterface resultInterface=null;

    @Override
    public void onBackPressed() {
        Intent intent_back= new Intent(MainActivity.this,BookedTabel.class);
        startActivity(intent_back);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner_item_category=(Spinner)findViewById(R.id.item_category);
        spinner_item_name = (Spinner)findViewById(R.id.item_name);
      //  spineer_table_no =  (Spinner)findViewById(R.id.table_no);
        tv_tabel_no = (TextView)findViewById(R.id.table_no);
        ed_qty=(EditText)findViewById(R.id.ed_qty);
        tv_total =(TextView)findViewById(R.id.tv_total);
        tv_empty_msg=(TextView)findViewById(R.id.txt_empty_msg);
        ed_note=(EditText)findViewById(R.id.ed_note);

        tv_item_price=(TextView)findViewById(R.id.item_price);
        listView_TabelRecord=(ListView)findViewById(R.id.list_oredr);
        btn_add=(Button)findViewById(R.id.btn_add);
        btn_placed_order=(Button)findViewById(R.id.btn_order_book);
        btn_bill=(Button)findViewById(R.id.btn_bill);

        Intent getIntent= getIntent();
        seleted_tabel_no =  getIntent.getStringExtra("Key_tabel_no");

        tv_tabel_no.setText(seleted_tabel_no);

         new getAllCategory().execute();

        new getInsertedTablesRecord().execute();
       spinner_item_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position_nm, long id) {
                arrayList_spinner_item_nm.clear();
                selected_category_name = (String) parent.getItemAtPosition(position_nm);
//                new getIDSelectedName().execute();

//                resultInterface = new resultInterface() {
//                    @Override
//                    public void jsonResult(JSONArray jsonArray) {
//                        Log.d("Result",jsonArray.toString());
//                        JSONObject json_getcart_data = new JSONObject();
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                        try {
//                            json_getcart_data = jsonArray.getJSONObject(i);
//                            String item_name = json_getcart_data.getString("category_name");
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
////                        if(selected_category_name.equals(item_name))
////                        {
////                             getSelectedName_item_id = json_getcart_data.getString("id");
////                        }
//                    }
//                    }
//                };
//                getIDSelectedName getIDSelectedName = new getIDSelectedName(resultInterface,MainActivity.this);
//                getIDSelectedName.execute();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner_item_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position_nm, long id) {
               // arrayList_spinner_item_nm.clear();
                selected_item_name = (String) parent.getItemAtPosition(position_nm);
                new get_Id_selected_item_name().execute();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
//        spineer_table_no.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position_nm, long id) {
//                arrayList_tableRecord.clear();
//                listView_TabelRecord.invalidateViews();
//                // arrayList_spinner_item_nm.clear();
//               // seleted_tabel_no = (String) parent.getItemAtPosition(position_nm);
//                seleted_tabel_no= spineer_table_no.getSelectedItem().toString();
//
//
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
       // spineer_table_no.requestFocus();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inser_tabel_no=   seleted_tabel_no;
                inser_item_name = spinner_item_name.getSelectedItem().toString();
                inser_price = tv_item_price.getText().toString();
                inser_qty =ed_qty.getText().toString();
                inser_total = tv_total.getText().toString();
                insert_note = ed_note.getText().toString();
                new insertRecord_TableOrder().execute();//dd
            }
        });
        btn_placed_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new updatePlaceOrderRecord_TableOrder().execute();
            }
        });
        btn_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new updateStatusOrderRecord_TableOrder().execute();

            }
        });

        tv_item_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //if(inser_total.equals("0.0"))
                //{
                String price = s.toString();
                cnvt_price=Double.parseDouble(price.toString());
                String qty =ed_qty.getText().toString();
                if(!qty.equals(""))
                {
                    cnvt_qty=   Double.parseDouble(qty.toString());
                    double ans_total= cnvt_price*cnvt_qty;
                    tv_total.setText(String.valueOf(ans_total));
                    inser_total = tv_total.getText().toString();
                }

//                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ed_qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {

                String price = tv_item_price.getText().toString();
                cnvt_price=Double.parseDouble(price.toString());

                String qty =s.toString();

                if(!qty.equals(""))
                {
                    cnvt_qty=   Double.parseDouble(qty.toString());
                    double ans_total= cnvt_price*cnvt_qty;
                    tv_total.setText(String.valueOf(ans_total));
                }
                else
                {
                    tv_total.setText("0.0");
                }
            }
        });
//        arrayList_tabel_no.add(1);
//        arrayList_tabel_no.add(2);
//        arrayList_tabel_no.add(3);
//        arrayList_tabel_no.add(4);
//        arrayList_tabel_no.add(5);
//        arrayList_tabel_no.add(6);
//        arrayList_tabel_no.add(7);
//        arrayList_tabel_no.add(8);
//        adapter_table_no = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,arrayList_tabel_no);
//        spineer_table_no.setAdapter(adapter_table_no);


        LayoutInflater  layoutinflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)layoutinflater.inflate(R.layout.header_list_tabel,listView_TabelRecord,false);
        listView_TabelRecord.addHeaderView(header);

    }

    class getAllCategory extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {

            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
             //   params.add(new BasicNameValuePair("item_category", spinner_id));

                JSONObject json = jsonParser.makeHttpRequest(
                        GetAllCategory_Url, "POST", params);

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
                JSONArray arr = null;
                try {
                    JSONObject json_getcart_data = new JSONObject();
                    arr = json.getJSONArray("CategoryList");
                    for (int i = 0; i < arr.length(); i++) {

                        json_getcart_data = arr.getJSONObject(i);
                        String item_name = json_getcart_data.getString("category_name");

                        arrayList_spinner_category.add(item_name);
                        adapter_category_name = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item, arrayList_spinner_category);
                    }
                    spinner_item_category.setAdapter(adapter_category_name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class getIDSelectedName extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("item_category", spinner_id));

                JSONObject json = jsonParser.makeHttpRequest(
                        GetAllCategory_Url, "POST", params);

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
                JSONArray arr = null;
                try {
                    JSONObject json_getcart_data = new JSONObject();
                    arr = json.getJSONArray("CategoryList");
                    for (int i = 0; i < arr.length(); i++) {
                        json_getcart_data = arr.getJSONObject(i);
                        String item_name = json_getcart_data.getString("category_name");
                        if(selected_category_name.equals(item_name))
                        {
                             getSelectedName_item_id = json_getcart_data.getString("id");
                        }
                    }
                    new get_menuDetails().execute(getSelectedName_item_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class get_menuDetails extends AsyncTask<String, String, JSONObject>
    {
        JSONParser jsonParser = new JSONParser();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {
            try{
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("item_category", getSelectedName_item_id));

                JSONObject json = jsonParser.makeHttpRequest(
                        GetMenuList_Url, "POST",params);
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
                    arr = json.getJSONArray("MenuList");
                    for(int i=0;i<arr.length();i++) {
                        json_getcart_data = arr.getJSONObject(i);
                        String item_id = json_getcart_data.getString("id");
                        String item_name = json_getcart_data.getString("item_name");
                        String item_price = json_getcart_data.getString("item_price");

                        arrayList_spinner_item_nm.add(item_name);
                        adapter_item_name=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,arrayList_spinner_item_nm);
                        spinner_item_name.setAdapter(adapter_item_name);

                      //  tv_item_price.setText("Rs."+item_price +" X ");
                       // tv_item_price.setText(item_price );
                    }
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    class get_Id_selected_item_name extends AsyncTask<String, String, JSONObject>
    {
        JSONParser jsonParser = new JSONParser();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {
            try{
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("item_category", getSelectedName_item_id));

                JSONObject json = jsonParser.makeHttpRequest(
                        GetMenuList_Url, "POST",params);
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
                    arr = json.getJSONArray("MenuList");
                    for(int i=0;i<arr.length();i++) {
                        json_getcart_data = arr.getJSONObject(i);
                        String item_name = json_getcart_data.getString("item_name");
                        if(item_name.equals(selected_item_name))
                        {

                           menu_item_id = json_getcart_data.getString("id");
                           String item_price = json_getcart_data.getString("item_price");
                          //  String item_name1 = json_getcart_data.getString("item_name");
                           tv_item_price.setText(item_price);

                          //  new get_ItemPrice_itemnameId().execute(menu_item_id);
                        }



                    }
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    class insertRecord_TableOrder extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {

            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
             //      params.add(new BasicNameValuePair("item_category", spinner_id));
                params.add(new BasicNameValuePair("tabel_no", inser_tabel_no));
                params.add(new BasicNameValuePair("item_name", inser_item_name));
                params.add(new BasicNameValuePair("price", inser_price));
                params.add(new BasicNameValuePair("qty", inser_qty));
                params.add(new BasicNameValuePair("total", inser_total));
                params.add(new BasicNameValuePair("status", "1"));
                params.add(new BasicNameValuePair("placed_order", "0"));
                params.add(new BasicNameValuePair("note",insert_note));

                JSONObject json = jsonParser.makeHttpRequest(
                        InsertRecord_Url, "POST", params);

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
              //  JSONArray arr = null;
                try {
                    JSONObject json_return_msg = new JSONObject(result);
                    String msg=json_return_msg.getString("message");
                    listView_TabelRecord.invalidateViews();
                    arrayList_tableRecord.clear();
                    new getInsertedTablesRecord().execute();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
   //
   class getInsertedTablesRecord extends AsyncTask<String, String, JSONObject>
   {

       JSONParser jsonParser = new JSONParser();

//       @Override
//       protected void onPreExecute() {
//           super.onPreExecute();
//           pDialog = new ProgressDialog(MainActivity.this);
//           pDialog.setMessage("Please wait");
//           pDialog.setIndeterminate(false);
//           pDialog.setCancelable(true);
//           pDialog.show();
//       }
       @Override
       protected JSONObject doInBackground(String... args) {

           try{

               List<NameValuePair> params = new ArrayList<NameValuePair>();
               params.add(new BasicNameValuePair("tabel_no", seleted_tabel_no));
               params.add(new BasicNameValuePair("status", "1"));//stastus means customer on tabel

               JSONObject json = jsonParser.makeHttpRequest(
                       ViewInsertedRecord_Url, "POST",params);
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

//           if (pDialog != null && pDialog.isShowing()) {
//               pDialog.dismiss();
//           }
           if (json != null) {
               String result= json.toString();
               JSONArray arr = null;
               try {
                   JSONObject json_getcart_data = new JSONObject();

                   arr = json.getJSONArray("TabelsRecord");
                   for(int i=0;i<arr.length();i++) {
                       json_getcart_data = arr.getJSONObject(i);
                       String tabel_no = json_getcart_data.getString("tabel_no");

                       if(tabel_no.equals(seleted_tabel_no))
                       {
                           String status = json_getcart_data.getString("status");
                           String id = json_getcart_data.getString("id");
                           String item_name = json_getcart_data.getString("item_name");
                           String item_price = json_getcart_data.getString("price");
                           String qty = json_getcart_data.getString("qty");
                           String total = json_getcart_data.getString("total");
                           String note = json_getcart_data.getString("note");

                           Row_Table_Record row_table_record = new Row_Table_Record();
                           row_table_record.setId(id);
                           row_table_record.setItem_name(item_name);
                           row_table_record.setPrice(item_price);
                           row_table_record.setQty(qty);
                           row_table_record.setTabel_no(tabel_no);
                           row_table_record.setTotal(total);
                           row_table_record.setStatus(status);
                           row_table_record.setNote(note);
                           arrayList_tableRecord.add(row_table_record);

                           String get_placeorder = json_getcart_data.getString("placed_order");
                           if(get_placeorder.equals("0")){
                               btn_bill.setVisibility(View.GONE);
                           }
                           else
                           {
                               btn_bill.setVisibility(View.VISIBLE);
                           }



                       }
                       AdapterTabelRecord adapterTabelRecord = new AdapterTabelRecord(arrayList_tableRecord,getLayoutInflater(),MainActivity.this);
                       listView_TabelRecord.setAdapter(adapterTabelRecord);
                       listView_TabelRecord.setEmptyView(tv_empty_msg);
                     //  adapterTabelRecord.notifyDataSetChanged();
                       //listView_TabelRecord.setAdapter();
                  }
               } catch (JSONException e)
               {
                   e.printStackTrace();
               }
           }
       }
   }
    class updatePlaceOrderRecord_TableOrder extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {

            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                //      params.add(new BasicNameValuePair("item_category", spinner_id));
                params.add(new BasicNameValuePair("tabel_no", seleted_tabel_no));
                params.add(new BasicNameValuePair("status", "1"));
                params.add(new BasicNameValuePair("placed_order", "1"));



                JSONObject json = jsonParser.makeHttpRequest(
                        UpdatePlaceOrderRecord_Url, "POST", params);

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
                //  JSONArray arr = null;
                try {
                    JSONObject json_return_msg = new JSONObject(result);
                    String msg=json_return_msg.getString("message");
                    listView_TabelRecord.invalidateViews();
//                    arrayList_tableRecord.clear();
//                    new getInsertedTablesRecord().execute();listView_TabelRecord.invalidateViews();
//                    arrayList_tableRecord.clear();
//                    new getInsertedTablesRecord().execute();
                    btn_bill.setVisibility(View.VISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class updateStatusOrderRecord_TableOrder extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {

            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                //      params.add(new BasicNameValuePair("item_category", spinner_id));
                params.add(new BasicNameValuePair("tabel_no", seleted_tabel_no));
                params.add(new BasicNameValuePair("status", "0"));
                params.add(new BasicNameValuePair("placed_order", "1"));



                JSONObject json = jsonParser.makeHttpRequest(
                        UpdateStatusOrderRecord_Url, "POST", params);

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
                //  JSONArray arr = null;
                try {
                    JSONObject json_return_msg = new JSONObject(result);
                    String msg=json_return_msg.getString("message");
                    listView_TabelRecord.invalidateViews();
                    arrayList_tableRecord.clear();
//                    new getInsertedTablesRecord().execute();listView_TabelRecord.invalidateViews();
                    btn_bill.setVisibility(View.GONE);
                    arrayList_tableRecord.clear();
                    new getInsertedTablesRecord().execute();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
