package com.jigar.android.resto;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class getIDSelectedName extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();
        ProgressDialog pDialog;
        resultInterface mresultInterface;
        Context context;

    public getIDSelectedName(com.jigar.android.resto.resultInterface resultInterface, Context context) {
        this.mresultInterface = resultInterface;
        this.context = context;
    }

    @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Please wait");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
            //    params.add(new BasicNameValuePair("item_category", spinner_id));

                JSONObject json = jsonParser.makeHttpRequest(
                        "http://192.185.129.71/~webservices1/resto/getAllCategoryList.php", "POST", params);

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


                    mresultInterface.jsonResult(arr);
//                    for (int i = 0; i < arr.length(); i++) {
//                        json_getcart_data = arr.getJSONObject(i);
//                        String item_name = json_getcart_data.getString("category_name");
////                        if(selected_category_name.equals(item_name))
////                        {
////                             getSelectedName_item_id = json_getcart_data.getString("id");
////                        }
//                    }
                 //   new get_menuDetails().execute(getSelectedName_item_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }