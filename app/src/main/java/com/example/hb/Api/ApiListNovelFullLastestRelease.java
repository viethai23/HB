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

public class ApiListNovelFullLastestRelease extends AsyncTask<Void,Void,Void> {
    private LayTruyenCV layTruyenCV;
    private ArrayList<TruyenKhamPha> KhamPhaTruyenArrayList = new ArrayList<>();
    private String url;
    private int page=0,lastP;

    public ApiListNovelFullLastestRelease(LayTruyenCV layTruyenCV, String url) {
        this.layTruyenCV = layTruyenCV;
        this.url = url;
        this.layTruyenCV.batDauCV();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
        Document maindoc = Jsoup.connect(url).get();
        String lastPage = maindoc.select("li.last").select("a").attr("href");
        lastP = Integer.parseInt(lastPage.substring(lastPage.length()-1));
        int check = Integer.min(lastP,20);
        while (page<lastP) {
            page += 1;
            String currentPage = url + "?page=" + Integer.toString(page);
            Document doc = Jsoup.connect(currentPage).get();
            Elements data = doc.select("div.col-truyen-main").select("div.row");
            int size = data.size();
            for (int i = 0; i < size; i++) {
                String linkAnh = "https://novelfull.com" + data
                        .select("img")
                        .eq(i)
                        .attr("src");
                String tenTruyen = data
                        .select("h3")
                        .eq(i)
                        .text();
                String detailURL = "https://novelfull.com" + data.select("h3")
                        .eq(i)
                        .select("a")
                        .attr("href");
                KhamPhaTruyenArrayList.add(new TruyenKhamPha(tenTruyen, linkAnh, detailURL));
                Log.d("items", " img: " + linkAnh + " . title: " + tenTruyen + " . detail url: " + detailURL);
            }
        }
        }catch(IOException e){

        }
        return null;
    }
    protected void onPostExecute(Void unused) {
        if(KhamPhaTruyenArrayList==null){
            this.layTruyenCV.biLoiCV();
        }else{
            Log.d("array", String.valueOf(KhamPhaTruyenArrayList.size()));
            this.layTruyenCV.ketThucCV(KhamPhaTruyenArrayList);
        }
    }
}
