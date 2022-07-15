package com.lece.ex_0715;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class VisibleActivity extends AppCompatActivity {

    Button btn_back1, btn_back2, btn_back3, btn_bot;
    ImageView img_back1, img_back2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visible);

        btn_back1 = findViewById(R.id.btn_back1);
        btn_back2 = findViewById(R.id.btn_back2);
        btn_back3 = findViewById(R.id.btn_back3);
        btn_bot = findViewById(R.id.bot);
        img_back1 = findViewById(R.id.img_back1);
        img_back2 = findViewById(R.id.img_back2);

        btn_back1.setOnClickListener(listener);
        btn_back2.setOnClickListener(listener);
        btn_back3.setOnClickListener(listener);


    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (btn_back1.equals(view)) {
                img_back1.setVisibility(View.INVISIBLE);
                img_back2.setVisibility(View.VISIBLE);

            } else if (btn_back2.equals(view)) {
                img_back2.setVisibility(View.INVISIBLE);
                img_back1.setVisibility(View.VISIBLE);

            } else if (btn_back3 == view) {
                if (btn_bot.getVisibility() == View.VISIBLE) {
                    btn_bot.setVisibility(View.GONE);
                } else {
                    btn_bot.setVisibility(View.VISIBLE);
                }
            }
        }
    };
}