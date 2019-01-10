package com.jigar.android.resto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by COMP11 on 12-Jul-18.
 */

public class AdapterBookedTabel extends BaseAdapter {
    ArrayList<RowTabelBooked> arrayList = new ArrayList<RowTabelBooked>();
    LayoutInflater inflater;
    Context context;

    public AdapterBookedTabel(ArrayList<RowTabelBooked> arrayList, LayoutInflater inflater, Context context) {
        this.arrayList = arrayList;
        this.inflater = inflater;
        this.context = context;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.table_booking,null);

        ImageView img_tabel1,img_tabel2;

        img_tabel1=(ImageView) convertView.findViewById(R.id.table1);
        img_tabel2=(ImageView)convertView.findViewById(R.id.table2);



        String tabel_no =arrayList.get(position).getTabel_no();
        if(tabel_no.equals("1"))
        {
            img_tabel1.setImageResource(R.mipmap.ic_launcher);
        }
        if(tabel_no.equals("2"))
        {
            img_tabel2.setImageResource(R.mipmap.ic_launcher);
        }






        return convertView;
    }
}
