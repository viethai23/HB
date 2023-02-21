package com.example.hb.Api;

import android.os.AsyncTask;

import com.example.hb.Interfaces.LayTruyenVe;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ApiLayTruyen extends AsyncTask<Void,Void,Void> {

    String data;
    LayTruyenVe layTruyenVe;

    public ApiLayTruyen(LayTruyenVe layTruyenVe) {
        this.layTruyenVe = layTruyenVe;
        this.layTruyenVe.batDau();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.jsonbin.io/v3/b/63f4c7b1ebd26539d0828302")
                .build();
        data = null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string(); //-->1
        }catch (IOException e){
            data = null;
        }
        return null;
    }
    //-->2
    @Override
    protected void onPostExecute(Void unused) {
        if(data==null){
            this.layTruyenVe.biLoi();
        }else{
            this.layTruyenVe.ketThuc(data);
        }
    }
}
