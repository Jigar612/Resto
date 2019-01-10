package com.jigar.android.resto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by COMP11 on 04-Jul-18.
 */

public class AdapterTabelRecord extends BaseAdapter {

    ArrayList<Row_Table_Record> arrayList = new ArrayList<Row_Table_Record>();
    LayoutInflater inflater;
    Context context;

    public AdapterTabelRecord(ArrayList<Row_Table_Record> arrayList, LayoutInflater inflater, Context context) {
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
        convertView=inflater.inflate(R.layout.list_tabel_record,null);

        TextView tv_name,tv_price,tv_qty,tv_total,tv_note;

        tv_name=(TextView)convertView.findViewById(R.id.lst_tv_item_name);
        tv_price=(TextView)convertView.findViewById(R.id.lst_tv_item_price);
        tv_qty=(TextView)convertView.findViewById(R.id.lst_tv_item_qty);
        tv_total=(TextView)convertView.findViewById(R.id.lst_tv_item_total);
        tv_note=(TextView)convertView.findViewById(R.id.lst_tv_note);


        String item_name =arrayList.get(position).getItem_name();
        String item_price =arrayList.get(position).getPrice();
        String item_qty =arrayList.get(position).getQty();
        String item_total =arrayList.get(position).getTotal();
        String note =arrayList.get(position).getNote();

        tv_name.setText(item_name);
        tv_price.setText(item_price);
        tv_qty.setText(item_qty);
        tv_total.setText(item_total);
        tv_note.setText(note);



        return convertView;
    }
}
