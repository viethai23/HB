package com.example.hb.Api;

import android.os.AsyncTask;
import android.util.Log;

import com.example.hb.Interfaces.LayGioiThieuTruyen;
import com.example.hb.Object.ChapTruyen;
import com.example.hb.Object.TruyenKhamPhaTruyen;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ApiJsoupContentTruyenWikidich extends AsyncTask<Void,Void,Void> {
    LayGioiThieuTruyen layGioiThieuTruyen;
    String url;
    ArrayList<ChapTruyen> arrChapters = new ArrayList<>();
    TruyenKhamPhaTruyen data;
    public ApiJsoupContentTruyenWikidich(LayGioiThieuTruyen layGioiThieuTruyen, String url) {
        this.layGioiThieuTruyen = layGioiThieuTruyen;
        this.url = url;
        this.layGioiThieuTruyen.batDau();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            Document doc = Jsoup.connect(url).get();
            Elements generalContent = doc.select("div.cover-info").select("p");
            String tenTacGia = "Tác giả: " + generalContent.eq(2).select("a").text();
            String trangThai = "Tình Trạng: "+ generalContent.eq(3).select("a").text();
            Elements description = doc.select("div.book-desc").select("a");
            Elements content = doc.select("li.chapter-name");
            int size = content.size();
            for(int i=0;i<size;i++){
                String tenChap = content.select("a")
                        .eq(i)
                        .text();
                String chapUrl = "https://wikidich3.com/" + content.select("a")
                        .eq(i)
                        .attr("href");
                arrChapters.add(new ChapTruyen(tenChap,chapUrl));
                Log.d("truyen chap"," .ten chap: "+tenChap+" .chapUrl: " + chapUrl);
            }
            int sizeTheLoai = description.size();
            String theLoai = "Thể loại: ";
            for(int i=0;i<sizeTheLoai;i++){
                theLoai+=description.eq(i).text();
                if(i!=sizeTheLoai-1){
                    theLoai+=" ,";
                }
            }
            Elements des = doc.select("div.book-desc-detail").select("p");
            int sizeDes = des.size();
            String gioiThieu = "";
            for(int i=0;i<sizeDes;i++){
                gioiThieu+=des.text()+". ";
            }
            //data = new TruyenKhamPhaTruyen(tenTacGia,trangThai,theLoai,gioiThieu);

            Log.d("content:",tenTacGia+". " + trangThai +". " +theLoai + ". "+gioiThieu);

        }catch (IOException e){
            data = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if(data==null||arrChapters==null){
            this.layGioiThieuTruyen.biLoiTruyen();
            this.layGioiThieuTruyen.biLoiChap();
        }  else if(data!=null&&arrChapters!=null){
            this.layGioiThieuTruyen.ketThuc(data,arrChapters);
        }
    }
}
