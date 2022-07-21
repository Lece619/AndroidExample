package com.lece.ex_naverapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lece.parser.ParserJSON;

import java.net.MalformedURLException;

public class NaverActivity extends AppCompatActivity {

    public static EditText search;
    Button search_btn;
    TextView text_result;
    static String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver);

        search = findViewById(R.id.search);
        search_btn = findViewById(R.id.search_btn);
        text_result = findViewById(R.id.text_result);

        ParserJSON parserJSON = new ParserJSON();

        /* JSON Method

        search_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        result = parserJSON.connectNaver();
                    }
                };
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                text_result.setText(result);
            }
        });

        */
    }

}