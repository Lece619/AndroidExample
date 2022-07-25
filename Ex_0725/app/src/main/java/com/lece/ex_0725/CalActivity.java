package com.lece.ex_0725;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.evgenii.jsevaluator.JsEvaluator;
import com.evgenii.jsevaluator.interfaces.JsCallback;

public class CalActivity extends AppCompatActivity {

    Button[] numbers;
    Button plus, minus, multi, div, equal, clear;
    TextView text_result;
    String resultStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);
        text_result = findViewById(R.id.text_result);
        numbers = new Button[10];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = findViewById(getResources().getIdentifier("btn_"+i,"id",getPackageName()));
            numbers[i].setOnClickListener(numListener);
        }

        plus = findViewById(R.id.btn_10);
        minus = findViewById(R.id.btn_11);
        multi = findViewById(R.id.btn_12);
        div = findViewById(R.id.btn_13);
        equal = findViewById(R.id.btn_14);
        clear = findViewById(R.id.btn_15);

        plus.setOnClickListener(signClick);
        minus.setOnClickListener(signClick);
        multi.setOnClickListener(signClick);
        div.setOnClickListener(signClick);
        equal.setOnClickListener(signClick);
        clear.setOnClickListener(signClick);

    }

    View.OnClickListener numListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            resultStr += ((Button)view).getText().toString();
            text_result.setText(resultStr);

        }
    };

    View.OnClickListener signClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view != equal){
                resultStr += " " + ((Button)view).getText().toString() + " ";
                text_result.setText(resultStr);

                if(view == clear){
                    resultStr = "";
                    text_result.setText(resultStr);
                }
            }else{
                // = 기호를 클릭 ( 결과 처리 )
                JsEvaluator jsEvaluator = new JsEvaluator(CalActivity.this);
                //evaluate ( 연산하고자 하는 문자열, 수식으로 변경해서 연산결과를 반환 )
                jsEvaluator.evaluate(resultStr, new JsCallback() {
                    @Override
                    public void onResult(String s) {
                        text_result.setText(s);
                    }
                });
            }
        }
    };
}