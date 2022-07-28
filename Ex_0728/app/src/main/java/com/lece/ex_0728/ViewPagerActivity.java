package com.lece.ex_0728;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.UnderlinePageIndicator;

public class ViewPagerActivity extends FragmentActivity {

    Button menu1, menu2, menu3, menu4;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        menu1 = findViewById(R.id.menu1);
        menu2 = findViewById(R.id.menu2);
        menu3 = findViewById(R.id.menu3);
        menu4 = findViewById(R.id.menu4);

        pager = findViewById(R.id.pager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        //밑줄을 그려주는 인디케이터
//        UnderlinePageIndicator under = findViewById(R.id.indicator);
//        under.setFades(false);
//        under.setViewPager(pager);

        CirclePageIndicator circle = findViewById(R.id.circle);
        circle.setViewPager(pager);

        menu1.setOnClickListener(listener);
        menu2.setOnClickListener(listener);
        menu3.setOnClickListener(listener);
        menu4.setOnClickListener(listener);

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Button button = (Button) view;
            pager.setCurrentItem(Integer.parseInt(button.getText().toString().substring(4))-1
            );
        }
    };
}