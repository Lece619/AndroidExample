package com.lece.ex_0726;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class SqliteActivity extends AppCompatActivity {

    SQLiteDatabase mDatabase;
    boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        //assets폴더의 DB를 휴대폰 내부 장소에 저장
        copyAssets();
    }

    private void copyAssets() {

        AssetManager assetManager = getAssets();
        String[] files = null;
        String mkdir = "";
        try{
            //assets폴더의 모든 파일을 모두 가져온다.
            files = assetManager.list("");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            inputStream = assetManager.open(files[1]);

            //내부 저장소에 폴더 샌성
            //휴대폰 내부 저장
            String str = ""+ Environment.getExternalStorageDirectory();
            mkdir = str + "/database";

            File mpath = new File(mkdir);
            if(!mpath.exists()){
                isFirst = true;
            }
            if(isFirst){
                mpath.mkdirs();
            }

        }catch (Exception e){

        }
    }
}