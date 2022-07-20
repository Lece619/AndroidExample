package com.lece.parser;


import com.lece.ex_naverapi.NaverActivity;
import com.lece.vo.BookVO;
import com.lece.vo.NaverProperty;

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
            httpURLConnection.setRequestProperty("X-Naver-Client-Id", NaverProperty.id);
            httpURLConnection.setRequestProperty("X-Naver-Client-Secret",NaverProperty.key);
            httpURLConnection.setRequestProperty("Content-type", "application/json");
            //URL을 수행하여 받은 자원들을 parsing 해야한다.

            //url에 정보를 불러와서 저장
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
            while(br.ready()){
                sb.append(br.readLine());
            }
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
