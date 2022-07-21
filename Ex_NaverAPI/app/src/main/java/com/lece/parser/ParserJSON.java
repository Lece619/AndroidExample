package com.lece.parser;


import com.lece.ex_naverapi.NaverActivity;
import com.lece.vo.BookVO;
import com.lece.vo.NaverProperty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ParserJSON {
    //웹에서 받은 요소들을 저장 및 List로 저장
    BookVO vo;
    String myQuery = ""; //검색어

    public String connectNaver(){
        ArrayList<BookVO> bookList = new ArrayList<>();
        String result="";
        try {
            //검색어 ( myQuery ) 를 UTF-8 형태로 인코딩
            myQuery = URLEncoder.encode(NaverActivity.search.getText().toString(),"UTF-8");

            String apiUrl = "https://openapi.naver.com/v1/search/book.json?query="+myQuery+"&display=100";

            URL url = new URL(apiUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            //발급 받은 ID 와 Secret을 서버로 전달
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("X-Naver-Client-Id", NaverProperty.id);
            httpURLConnection.setRequestProperty("X-Naver-Client-Secret",NaverProperty.key);
            //httpURLConnection.setRequestProperty("Content-type", "application/json");
            //URL을 수행하여 받은 자원들을 parsing 해야한다.

            //url에 정보를 불러와서 저장
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
            String inputLine;
            while((inputLine=br.readLine()) != null){
                sb.append(inputLine);
            }
            result = sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        parsingJSON(result);
        return result;
    }

    private void parsingJSON(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("items");

            for (int i = 0; i < jsonArray.length(); i++) {
                 JSONObject bookObject = jsonArray.getJSONObject(i);
                System.out.println("bookObject.get(\"title\") = " + bookObject.get("title"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
