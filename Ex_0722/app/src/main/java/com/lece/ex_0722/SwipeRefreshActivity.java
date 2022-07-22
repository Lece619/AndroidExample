package com.lece.ex_0722;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SwipeRefreshActivity extends AppCompatActivity {

    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);

        swipe = findViewById(R.id.swipe);
        swipe.setProgressBackgroundColorSchemeColor(Color.parseColor("#aaaaff"));
        swipe.setSize(SwipeRefreshLayout.LARGE);

        //스케일을 바꿀건지, 어느 시점까지 당겨지게 할껀지 결정
        swipe.setProgressViewEndTarget(true, 700);

        swipe.setColorSchemeResources(R.color.coler_1, R.color.coler_2, R.color.coler_3, R.color.coler_4);

        swipe.setOnRefreshListener(swipeListener);
    }

    SwipeRefreshLayout.OnRefreshListener swipeListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            //당겼다가 손은 떼는 순간 호출되는 메서드
            handler.sendEmptyMessageDelayed(0, 3000);
        }
    };

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //로딩이 완료된 시점에서 디스크 제거
            swipe.setRefreshing(false);
        }
    };
}