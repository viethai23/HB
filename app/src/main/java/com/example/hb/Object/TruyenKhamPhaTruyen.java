package com.example.hb.Object;

public class TruyenKhamPhaTruyen {
    private String linkAnh,tenTacGia,trangThai,theLoai,gioiThieu;

    public TruyenKhamPhaTruyen(String linkAnh, String tenTacGia, String trangThai, String theLoai, String gioiThieu) {
        this.linkAnh = linkAnh;
        this.tenTacGia = tenTacGia;
        this.trangThai = trangThai;
        this.theLoai = theLoai;
        this.gioiThieu = gioiThieu;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

}
