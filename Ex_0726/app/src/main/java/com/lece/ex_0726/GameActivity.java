package com.lece.ex_0726;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    ImageView img_com, img_user;
    Button btn_r, btn_s, btn_p, btn_start, btn_exit;
    boolean flage;
    TextView text_result;
    int imgNum;
    int[] imgs = {R.drawable.cr, R.drawable.cs, R.drawable.cp};
    int[] imgUser = {R.drawable.ur, R.drawable.us, R.drawable.up};
    int[] imgResult = {R.drawable.rabbit,R.drawable.rabbit2};
    int userNum, resultNum;
    ImageView img_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        img_com = findViewById(R.id.img_com);
        img_user = findViewById(R.id.img_user);
        btn_r = findViewById(R.id.btn_r);
        btn_s = findViewById(R.id.btn_s);
        btn_p = findViewById(R.id.btn_p);
        btn_start = findViewById(R.id.btn_start);
        btn_exit = findViewById(R.id.btn_exit);
        text_result = findViewById(R.id.text_result);
        img_result = findViewById(R.id.result_img);
        enableBtn(false);


        img_com.setImageResource(R.drawable.cp);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_result.setVisibility(View.GONE);
                img_result.setVisibility(View.GONE);
                handler2.removeMessages(1);
                flage = true;
                imgNum = 0;
                btn_start.setEnabled(false);
                enableBtn(true);
                handler.sendEmptyMessage(0);
            }
        });
        btn_r.setOnClickListener(listener);
        btn_s.setOnClickListener(listener);
        btn_p.setOnClickListener(listener);
        btn_exit.setOnClickListener(listener);


    }

    private void enableBtn(boolean check) {
        btn_s.setEnabled(check);
        btn_p.setEnabled(check);
        btn_r.setEnabled(check);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            btn_start.setEnabled(true);
            flage = false;
            imgNum = new Random().nextInt(3);
            img_com.setImageResource(imgs[imgNum]);
            enableBtn(false);

            switch (view.getId()){
                case R.id.btn_r:
                    userNum=0;
                    makeResult();
                    break;
                case R.id.btn_s:
                    userNum=1;
                    makeResult();
                    break;
                case R.id.btn_p:
                    userNum=2;
                    makeResult();
                    break;
                case R.id.btn_exit:
                    finish();
                    break;
            }
        }
    };

    public void makeResult(){
        img_user.setImageResource(imgUser[userNum]);
        //묵 찌 빠 
        if(userNum == imgNum){
            text_result.setText("DRAW!!!");
            text_result.setVisibility(View.VISIBLE);
        }else if((userNum+1)%3 == imgNum){
            text_result.setText("WIN!!!");
            text_result.setVisibility(View.VISIBLE);
        }else{
            img_result.setVisibility(View.VISIBLE);
            text_result.setText("Defeat!!!");
            handler2.sendEmptyMessage(1);
            text_result.setVisibility(View.VISIBLE);
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (flage) {
                sendEmptyMessageDelayed(0, 300);
                imgNum = (imgNum+1)%3;
                img_com.setImageResource(imgs[imgNum]);
            }
        }
    };

    Handler handler2 = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            sendEmptyMessageDelayed(1,200);
            resultNum++;
            img_result.setImageResource(imgResult[resultNum%2]);
        }
    };

}