package com.lece.ex_0719;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class BackFinishActivity extends AppCompatActivity {

    int num = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_finish);
    }

    @Override
    public void onBackPressed() {
        if(num!=2){
            finish();
        } else{
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessage(0);
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //2초 카운팅을 위한 핸들러
            sendEmptyMessageDelayed(0,1000);
            if(num>0){
                --num;
            }else{
                num = 2;
                handler.removeMessages(0);
            }
        }
    };

}