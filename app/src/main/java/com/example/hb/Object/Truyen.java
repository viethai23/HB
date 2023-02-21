package com.example.hb.Object;

import org.json.JSONException;
import org.json.JSONObject;

public class Truyen {
    private String tenTruyen,tenChap,linkAnh;

    public Truyen(){
    }
/*
{
"tenTruyen":"",
"tenChap":"",
"linkAnh":""
}
 */
    public Truyen(JSONObject o) throws JSONException{
        tenTruyen = o.getString("tenTruyen");
        tenChap = o.getString("tenChap");
        linkAnh = o.getString("linkAnh");
    }

    public Truyen(String tenTruyen, String tenChap, String linkAnh) {
        this.tenTruyen = tenTruyen;
        this.tenChap = tenChap;
        this.linkAnh = linkAnh;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }
}
