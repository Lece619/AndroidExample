package com.lece.vo;

import android.graphics.Bitmap;

import java.io.Serializable;


public class BookVO implements Serializable{
    //네이버 도서 서버에서 얻어올 정보를 저장할 변수
    //제목 저자 가격 이미지( url )
    private String b_title;
    private String b_author;
    private String b_price;
    private String b_image;


    public String getB_title() {
        return b_title;
    }

    public void setB_title(String b_title) {
        this.b_title = b_title;
    }

    public String getB_author() {
        return b_author;
    }

    public void setB_author(String b_author) {
        this.b_author = b_author;
    }

    public String  getB_price() {
        return b_price;
    }

    public void setB_price(String b_price) {
        this.b_price = b_price;
    }

    public String getB_image() {
        return b_image;
    }

    public void setB_image(String b_image) {
        this.b_image = b_image;
    }

}
