package com.example.hb.Api;

import android.os.AsyncTask;
import android.util.Log;

import com.example.hb.Interfaces.LayTruyenCV;
import com.example.hb.Object.TruyenKhamPha;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ApiJsoupListNovelFull extends AsyncTask<Void,Void,Void> {
    LayTruyenCV layTruyenCV;
    ArrayList<TruyenKhamPha> KhamPhaTruyenArrayList = new ArrayList<>();

    public ApiJsoupListNovelFull(LayTruyenCV layTruyenCV) {
        this.layTruyenCV = layTruyenCV;
        this.layTruyenCV.batDauCV();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            String urlNovelFull = "https://novelfull.top/index.php/hot-novel";
            Document doc = Jsoup.connect(urlNovelFull).get();
            Elements data = doc.select("div.col-truyen-main").select("div.row");
            int size = data.size();
            for(int i=0;i<size;i++){
                String linkAnh = "https://novelfull.top" + data
                        .select("img")
                        .eq(i)
                        .attr("src");
                String tenTruyen = data
                        .select("h3")
                        .eq(i)
                        .text();
                String detailURL = "https://novelfull.top" + data.select("h3")
                        .eq(i)
                        .select("a")
                        .attr("href");
                KhamPhaTruyenArrayList.add(new TruyenKhamPha(tenTruyen,linkAnh,detailURL));
                Log.d("items"," img: " + linkAnh + " . title: " + tenTruyen + " . detail url: " + detailURL);
            }
        } catch (IOException e) {
            KhamPhaTruyenArrayList = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if(KhamPhaTruyenArrayList==null){
            this.layTruyenCV.biLoiCV();
        }else{
            Log.d("array", String.valueOf(KhamPhaTruyenArrayList));
            this.layTruyenCV.ketThucCV(KhamPhaTruyenArrayList);
        }
    }
}
