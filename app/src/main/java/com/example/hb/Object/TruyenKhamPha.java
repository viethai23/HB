package com.example.hb.Object;

import java.io.Serializable;

public class TruyenKhamPha implements Serializable {
    private String tenTruyen,linkAnh,detailURL;

    public TruyenKhamPha(String tenTruyen, String linkAnh,String detailURL) {
        this.tenTruyen = tenTruyen;
        this.linkAnh = linkAnh;
        this.detailURL = detailURL;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

    public String getDetailURL() {
        return detailURL;
    }

    public void setDetailURL(String detailURL) {
        this.detailURL = detailURL;
    }
}
