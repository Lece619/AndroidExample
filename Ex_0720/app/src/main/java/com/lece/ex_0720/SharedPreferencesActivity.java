package com.lece.ex_0720;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SharedPreferencesActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView text_value;
    Button btn_plus, btn_minus, btn_reset;
    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);


        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_reset = findViewById(R.id.btn_reset);
        text_value = findViewById(R.id.text_value);

        btn_plus.setOnClickListener(listener);
        btn_minus.setOnClickListener(listener);
        btn_reset.setOnClickListener(listener);

        sharedPreferences = getSharedPreferences("SHARE",MODE_PRIVATE);
        value = sharedPreferences.getInt("save",0);
        text_value.setText(String.valueOf(value));
        //default parameter i ;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            value = Integer.parseInt(text_value.getText().toString());
            switch (view.getId()){
                case R.id.btn_plus:
                    text_value.setText(String.valueOf(++value));
                    break;
                case R.id.btn_minus:
                    text_value.setText(String.valueOf(--value));
                    break;
                case R.id.btn_reset:
                    text_value.setText(String.valueOf(0));
                    break;
            }
        }
    };

    //앱이 정지될때 실행되는 메서드 값을 확인
    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("save",value);
        editor.commit(); //물리적으로 세이브 데이터를 저장하는 작업

    }
}