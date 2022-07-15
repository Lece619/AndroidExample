package com.lece.ex_0714;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Exam2Activity extends AppCompatActivity {

    Button btn_1, btn_2, btn_3, btn_4, btn_re;
    TextView result;
    int ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam2);

        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_re = findViewById(R.id.btn_re);
        result = findViewById(R.id.result);

        ok = new Random().nextInt(3)+1;
        Log.i("My",ok+"");
        btn_1.setOnClickListener(listener);
        btn_2.setOnClickListener(listener);
        btn_3.setOnClickListener(listener);
        btn_4.setOnClickListener(listener);
        btn_re.setOnClickListener(listener);

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            /*switch (view.getId()){
                case R.id.btn_1:
                    check(1);
                    break;
                case R.id.btn_2:
                    check(2);
                    break;
                case R.id.btn_3:
                    check(3);
                    break;
                case R.id.btn_4:
                    check(4);
                    break;
                case R.id.btn_re:
                    ok = new Random().nextInt(3)+1;
                    result.setText("Result");
                    break;
            }*/
            //클릭된 버튼의 text를 가져오는 방법을 버튼을 판별
            if(((Button)view).getText().toString().equals("다시하기")){
                ok = new Random().nextInt(3)+1;
                result.setText("Result");
                return ;
            }

            int num = Integer.parseInt(((Button)view).getText().toString());
            check(num);

        }
    };

    public void check(int num){
        if(num==ok){
            result.setText("당첨");
        }else{
            result.setText("꽝");
        }
    }
}