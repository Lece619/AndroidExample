package com.lece.parser;


import android.text.Html;
import android.util.JsonReader;

import com.lece.ex_naverapi.NaverActivity;
import com.lece.vo.BookVO;
import com.lece.vo.NaverProperty;

import org.json.JSONArray;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    //웹에서 받은 요소들을 저장 및 List로 저장
    BookVO vo;
    String myQuery = ""; //검색어

    //버튼을 눌렀을때 작동?
    public ArrayList<BookVO> connectNaver() {

        ArrayList<BookVO> bookList = new ArrayList<>();
        String error = null;

        try {
            //검색어 ( myQuery ) 를 UTF-8 형태로 인코딩
            myQuery = URLEncoder.encode(NaverActivity.search.getText().toString(), "UTF-8");

            String apiUrl = "https://openapi.naver.com/v1/search/book.xml?query=" + myQuery + "&display=100";

            URL url = new URL(apiUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            //발급 받은 ID 와 Secret을 서버로 전달
            httpURLConnection.setRequestProperty("X-Naver-Client-Id", NaverProperty.id);
            httpURLConnection.setRequestProperty("X-Naver-Client-Secret", NaverProperty.key);

            //URL을 수행하여 받은 자원들을 parsing 해야한다.
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            //connection 객체가 접속후 가지게 된 내용을 parser가 스트림으로 읽어온다
            parser.setInput(httpURLConnection.getInputStream(), null);

            //파서 객체를 통해 각 요소별 접근을 하게되고, 태그(요소) 내부의 값들을 가져온다.
            //while문을 돌리면서 더이상 읽어올 책이 없을 때 가지 모든 정보를 다 가져올 것이다.
            int parserEvent = parser.getEventType();
            //문서가 끝이 나지 않을때 까지
            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                //서버쪽 xml 만나서 끝나면 종료

                //시작태그의 이름을 가져와 vo 에 담을 수 있는 정보라면  vo에 추가
                if (parserEvent == XmlPullParser.START_TAG) {
                    String tagName = parser.getName();

                    if (tagName.equals("title")) {
                        vo = new BookVO();
                        //String title = Html.fromHtml(parser.nextText()).toString();
                        String title = parser.nextText();
                        Pattern pattern = Pattern.compile("<.*?>");
                        Matcher matcher = pattern.matcher(title);
                        if(matcher.find()){
                            title = matcher.replaceAll("");
                        }

                        vo.setB_title(title);
                    } else if (tagName.equals("image")) {
                        String img = parser.nextText();
                        vo.setB_image(img);
                    } else if (tagName.equals("author")) {
                        String author = parser.nextText();
                        vo.setB_author(author);
                    } else if (tagName.equals("price")) {
                        String price = parser.nextText();
                        int comma = price.indexOf('.');
                        if(comma!=-1) {
                            price = price.substring(0, price.indexOf('.'));
                        }
                        vo.setB_price(Integer.parseInt(price));

                        bookList.add(vo);
                    }
                }
                parserEvent = parser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error = " + error);
        }

        return bookList;
    }

}
