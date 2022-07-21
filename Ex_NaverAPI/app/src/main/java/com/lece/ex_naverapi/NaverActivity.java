package com.lece.ex_naverapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lece.parser.Parser;
import com.lece.parser.ParserJSON;
import com.lece.vo.BookVO;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class NaverActivity extends AppCompatActivity {

    public static EditText search;
    Button search_btn;
    TextView text_result;
    static String result;
    ListView myListView;
    Parser parser;
    ViewModelAdapter viewModelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver);
        
        search = findViewById(R.id.search);
        search_btn = findViewById(R.id.search_btn);
        text_result = findViewById(R.id.text_result);
        myListView = findViewById(R.id.myListView);
        parser = new Parser();

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //서버에 연결
                new NaverAsync().execute("홍","길","동");
            }
        });

        /* JSON Method
        ParserJSON parserJSON = new ParserJSON();

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
    }//OnCreate()

    class NaverAsync extends AsyncTask<String, Void, ArrayList<BookVO>>{

        @Override
        protected ArrayList<BookVO> doInBackground(String... strings) {
            return parser.connectNaver();
        }

        @Override
        protected void onPostExecute(ArrayList<BookVO> bookVOS) {
            //최종 작업결과를 받는 메서드 최종 return 값을 bookVOS가 받음

            //ListView를 그리기 위해 Adapter 클래스에게 넘겨줘야 한다.
            viewModelAdapter = new ViewModelAdapter(NaverActivity.this, R.layout.book_item, bookVOS);
            myListView.setAdapter(viewModelAdapter);

            Log.i("MY", "" + bookVOS.size());
        }
    }
    
    
}