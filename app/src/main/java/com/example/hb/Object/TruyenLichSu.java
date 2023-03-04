package com.example.hb.Object;

import java.util.ArrayList;
import java.util.Objects;

public class TruyenLichSu {
    ArrayList<ChapTruyen> chapTruyen;
    TruyenKhamPhaTruyen truyen;
    String tenTruyen;
    int currentChap;

    public TruyenLichSu(ArrayList<ChapTruyen> chapTruyen, TruyenKhamPhaTruyen truyen, int currentChap, String tenTruyen) {
        this.chapTruyen = chapTruyen;
        this.truyen = truyen;
        this.currentChap = currentChap;
        this.tenTruyen = tenTruyen;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public int getCurrentChap() {
        return currentChap;
    }

    public void setCurrentChap(int currentChap) {
        this.currentChap = currentChap;
    }

    @Override
    public boolean equals(TruyenLichSu o) {
        return o.getTenTruyen().equals(this.tenTruyen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chapTruyen, truyen, tenTruyen, currentChap);
    }

    public ArrayList<ChapTruyen> getChapTruyen() {
        return chapTruyen;
    }

    public void setChapTruyen(ArrayList<ChapTruyen> chapTruyen) {
        this.chapTruyen = chapTruyen;
    }

    public TruyenKhamPhaTruyen getTruyen() {
        return truyen;
    }

    public void setTruyen(TruyenKhamPhaTruyen truyen) {
        this.truyen = truyen;
    }
}
