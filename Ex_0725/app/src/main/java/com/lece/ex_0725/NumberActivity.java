package com.lece.ex_0725;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NumberActivity extends AppCompatActivity {

    /*
     * 04 05 06 07 12 13 14 15 20 21 22 23 28 29 30
     * 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30
     * 01 03 05 07 09 11 13 15 17 19 21 23 25 27 29
     * 08 09 10 11 12 13 14 15 24 25 26 27 28 29 30
     * 02 03 06 07 10 11 14 15 18 19 22 23 26 27 30
     *
     * */

    LinearLayout layout_select;
    Button btn_yes, btn_no, btn_reset;
    TextView text_num;

    String numSet1 = ("04 05 06 07 12\n13 14 15 20 21\n22 23 28 29 30");
    String numSet2 = ("16 17 18 19 20\n21 22 23 24 25\n26 27 28 29 30");
    String numSet3 = ("01 03 05 07 09\n11 13 15 17 19\n21 23 25 27 29");
    String numSet4 = ("08 09 10 11 12\n13 14 15 24 25\n26 27 28 29 30");
    String numSet5 = ("02 03 06 07 10\n11 14 15 18 19\n22 23 26 27 30");
    String[] numSet = {numSet1, numSet2, numSet3, numSet4, numSet5};
    int[] numStart = {4, 16, 1, 8, 2};
    boolean[] check = new boolean[5];
    int qNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        layout_select = findViewById(R.id.layout_select);
        btn_no = findViewById(R.id.btn_no);
        btn_yes = findViewById(R.id.btn_yes);
        btn_reset = findViewById(R.id.btn_reset);
        text_num = findViewById(R.id.text_num);

        text_num.setText(numSet[0]);
        btn_yes.setOnClickListener(listener);
        btn_no.setOnClickListener(listener);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_num.setText(numSet[qNum]);
                layout_select.setVisibility(View.VISIBLE);
                btn_reset.setVisibility(View.GONE);
            }
        });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btn_yes:
                    check[qNum] = true;
                    break;
                case R.id.btn_no:
                    check[qNum] = false;
                    break;
            }
            qNum++;
            if(qNum==5){
                qNum = 0;
                int result= 0;
                for (int i = 0; i < numSet.length; i++) {
                    result += check[i] ? numStart[i] : 0 ;
                }
                String resultString = "당신이 생각한 숫자는\n\"" + result + "\"입니다.";
                text_num.setText(resultString);
                check = new boolean[5];
                layout_select.setVisibility(View.GONE);
                btn_reset.setVisibility(View.VISIBLE);
            }else{
                text_num.setText(numSet[qNum]);
            }
        }
    };



}