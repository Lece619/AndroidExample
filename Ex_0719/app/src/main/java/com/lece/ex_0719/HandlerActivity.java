package com.lece.ex_0719;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandlerActivity extends AppCompatActivity {

    Button btn_start, btn_stop, btn_reset;
    TextView txt_count;
    int count = 0;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);
        btn_reset = findViewById(R.id.btn_reset);
        txt_count = findViewById(R.id.txt_count);

        btn_stop.setOnClickListener(listener);
        btn_start.setOnClickListener(listener);
        btn_reset.setOnClickListener(listener);
    }



    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_start:
                    //백그라운드에서 txt_count 값을 1씩 증가시키는 핸들러 호출

                    //핸들러의 handleMessage() 메서드를 호출하는 방법

                    handler.sendEmptyMessage(0);
                    btn_start.setEnabled(false);

                    break;
                case R.id.btn_stop:
                    //핸들러 정지
                    btn_start.setEnabled(true);
                    handler.removeMessages(0);
                    break;
                case R.id.btn_reset:
                    btn_start.setEnabled(true);
                    handler.removeMessages(0);
                    txt_count.setText(String.valueOf(0));
                    count=0;
                    break;
            }
        }
    };

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //백그라운드에서 코드를 실행하는 영역
                handler.sendEmptyMessageDelayed(0, 100);
                count++;
                txt_count.setText(String.valueOf(count));
        }
    };
}