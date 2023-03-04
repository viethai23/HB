package com.example.hb.Api;

import android.os.AsyncTask;
import android.util.Log;

import com.example.hb.Activities.ReadingChapActivity;
import com.example.hb.Interfaces.LayTruyenVe;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ApiJsoupContentChap extends AsyncTask<Void,Void,Void> {
    String contentChap = "    ";
    LayTruyenVe layTruyenVe;
    String url;

    public ApiJsoupContentChap(ReadingChapActivity layTruyenVe, String url) {
        this.layTruyenVe = layTruyenVe;
        this.url = url;
        this.layTruyenVe.batDau();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            Document doc = Jsoup.connect(url).get();
            Elements content = doc.select("div#chapter-content").select("p");
            for(int i=2;i<content.size();i++){
                contentChap += content.eq(i).text()+"\n    ";
                Log.d("lines"," line " + i + ": " + content.eq(i).text());
            }
            Log.d("content: ",contentChap);
        }catch (IOException e){
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if(contentChap==null){
            layTruyenVe.biLoi();
        }else {
            layTruyenVe.ketThuc(contentChap);
        }
    }
}
