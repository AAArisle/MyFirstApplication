package com.jnu.student.data;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ShopDownLoader {
    // 下载文件返回字符串
    @NonNull
    public String download(String path){
        try{
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String tempLine = null;
                StringBuffer resultBuffer = new StringBuffer();
                while ((tempLine = reader.readLine()) != null) {
                    resultBuffer.append(tempLine);
                    resultBuffer.append("\n");
                }
                Log.i("test data",resultBuffer.toString());

                return resultBuffer.toString();
            }
        }catch(Exception exception)
        {
            Log.e("error",exception.getMessage());

        }
        return "";
    }

    // 解析json对象，并返回对象ArrayList
    @NonNull
    public List<ShopLocation> parsonJson(String JsonText)
    {
        List<ShopLocation> locations=new ArrayList<>();
        try {
            JSONObject root = new JSONObject(JsonText);

            JSONArray shops = root.getJSONArray("shops");
            for(int i=0;i<shops.length();++i){
                JSONObject shop=shops.getJSONObject(i);

                ShopLocation shopLocation=new ShopLocation();
                shopLocation.setName(shop.getString("name"));
                shopLocation.setLatitude(shop.getDouble("latitude"));
                shopLocation.setLongitude(shop.getDouble("longitude"));
                shopLocation.setMemo(shop.getString("memo"));

                locations.add(shopLocation);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locations;
    }
}
