package com.lece.parser;


import com.lece.ex_naverapi.NaverActivity;
import com.lece.vo.BookVO;
import com.lece.vo.NaverProperty;

import org.xmlpull.v1.XmlPullParserFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Parser {
    //웹에서 받은 요소들을 저장 및 List로 저장
    BookVO vo;
    String myQuery = ""; //검색어

    public ArrayList<BookVO> connectNaver(){
        ArrayList<BookVO> bookList = new ArrayList<>();
        try {
            //검색어 ( myQuery ) 를 UTF-8 형태로 인코딩
            myQuery = URLEncoder.encode(NaverActivity.search.getText().toString(),"UTF-8");

            String apiUrl = "https://openapi.naver.com/v1/search/book.xml?query="+myQuery+"&display=100";

            URL url = new URL(apiUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            //발급 받은 ID 와 Secret을 서버로 전달
            httpURLConnection.setRequestProperty("X-Naver-Client-Id", NaverProperty.id);
            httpURLConnection.setRequestProperty("X-Naver-Client-Secret",NaverProperty.key);

            //URL을 수행하여 받은 자원들을 parsing 해야한다.
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookList;
    }

}
