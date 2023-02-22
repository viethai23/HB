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

public class ApiJsoupListTruyen extends AsyncTask<Void,Void,Void> {

    LayTruyenCV layTruyenCV;
    ArrayList<TruyenKhamPha> KhamPharuyenArrayList = new ArrayList<>();

    public ApiJsoupListTruyen(LayTruyenCV layTruyenCV) {
        this.layTruyenCV = layTruyenCV;
        this.layTruyenCV.batDauCV();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            String urlWikidich = "https://wikidich3.com/";
            Document doc = Jsoup.connect(urlWikidich).get();
            Elements data = doc.select("div.book-item");
            int size = data.size();
            for(int i=0;i<size;i++){
                String linkAnh = "https://wikidich3.com/" + data.select("div.cover-col")
                        .select("img")
                        .eq(i)
                        .attr("src");
                String tenTruyen = data.select("div.info-col")
                        .select("h5")
                        .eq(i)
                        .text();
                String detailURL = "https://wikidich3.com/" + data.select("div.cover-col")
                        .select("a")
                        .eq(i)
                        .attr("href");
                KhamPharuyenArrayList.add(new TruyenKhamPha(tenTruyen,linkAnh,detailURL));
                Log.d("items"," img: " + linkAnh + " . title: " + tenTruyen + " . detail url: " + detailURL);
            }
        } catch (IOException e) {
            KhamPharuyenArrayList = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if(KhamPharuyenArrayList==null){
            this.layTruyenCV.biLoiCV();
        }else{
            this.layTruyenCV.ketThucCV(KhamPharuyenArrayList);
        }
    }
}
