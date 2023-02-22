package com.example.hb.Object;

public class TruyenKhamPha {
    private String tenTruyen,linkAnh,detailURL;

    public TruyenKhamPha(String tenTruyen, String linkAnh,String detailURL) {
        this.tenTruyen = tenTruyen;
        this.linkAnh = linkAnh;
        this.detailURL = detailURL;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTryen) {
        this.tenTruyen = tenTryen;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }
}
