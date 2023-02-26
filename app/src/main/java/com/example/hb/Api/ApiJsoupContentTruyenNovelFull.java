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

public class ApiJsoupContentTruyenNovelFull extends AsyncTask<Void,Void,Void> {
    LayGioiThieuTruyen layGioiThieuTruyen;
    String url;
    ArrayList<ChapTruyen> arrChapters = new ArrayList<>();
    TruyenKhamPhaTruyen data;
    int page=0,lastP;
    public ApiJsoupContentTruyenNovelFull(LayGioiThieuTruyen layGioiThieuTruyen, String url) {
        this.layGioiThieuTruyen = layGioiThieuTruyen;
        this.url = url;
        this.layGioiThieuTruyen.batDau();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            Document maindoc = Jsoup.connect(url).get();
            String lastPage = maindoc.select("li.last").select("a").attr("data-page");
            lastP = Integer.parseInt(lastPage)+1;
            while(page<lastP){
                page+=1;
                String currentPage= url + "?page="+Integer.toString(page)+"&per-page=50";
                Document doc = Jsoup.connect(currentPage).get();
                Elements info = doc.select("div.info");
                String tenTacGia = "",theLoai = "",trangThai="";
                tenTacGia+=info.select("a").eq(0).text();
                Elements descText = doc.select("div.desc-text").select("p");
                String gioiThieu = "";
                int size = descText.size();
                for(int i=0;i<size;i++){
                    gioiThieu+=descText.eq(i).text();
                }
                String linkAnh = "https://novelfull.com"+doc.select("div.book").select("img").attr("src");
                String tenTruyen = doc.select("h3.title").text();
                Log.d("link anh",linkAnh);
                data = new TruyenKhamPhaTruyen(linkAnh,tenTacGia,theLoai,trangThai,gioiThieu);
                Elements content = doc.select("ul.list-chapter").select("a");
                int listChapsize = content.size();
                for(int i=0;i<listChapsize;i++) {
                    String tenChap = content.select("span.chapter-text")
                            .eq(i)
                            .text();
                    String chapUrl = "https://novelfull.top" + content
                            .eq(i)
                            .attr("href");
                    arrChapters.add(new ChapTruyen(tenChap, chapUrl));
                    Log.d("truyen chap", " .ten chap: " + tenChap + " .chapUrl: " + chapUrl);
                }
            }
        }catch (IOException e){

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if(data==null||arrChapters==null){
            this.layGioiThieuTruyen.biLoiTruyen();
        }else {
            this.layGioiThieuTruyen.ketThuc(data,arrChapters);
        }
    }
}
