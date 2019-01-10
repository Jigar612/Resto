package com.jigar.android.resto;

/**
 * Created by COMP11 on 04-Jul-18.
 */

public class Row_Table_Record {
    String id;
    String tabel_no;
    String item_name;
    String price;
    String qty;
    String total;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    String note;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTabel_no() {
        return tabel_no;
    }

    public void setTabel_no(String tabel_no) {
        this.tabel_no = tabel_no;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;

}
