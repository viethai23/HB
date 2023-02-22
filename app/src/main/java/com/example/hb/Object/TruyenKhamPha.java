package com.example.hb.Object;

public class TruyenKhamPha {
    private String tenTruyen,linkAnh;

    public TruyenKhamPha(String tenTruyen, String linkAnh) {
        this.tenTruyen = tenTruyen;
        this.linkAnh = linkAnh;
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
