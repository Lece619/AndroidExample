package com.lece.ex_0714;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ExamActivity extends AppCompatActivity {

    Button btn_ok;
    EditText numText;
    TextView result_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        btn_ok = findViewById(R.id.btn_ok);
        numText = findViewById(R.id.numText);
        result_view = findViewById(R.id.result_view);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String num = numText.getText().toString();
                String num2 = "";
                for (int i = num.length()-1; i >= 0; i--) {
                    num2 += num.charAt(i);
                }
                String num3 = new StringBuilder(num).reverse().toString();
                if(num.equals(num3) && !num.equals("")){
                    result_view.setText("ok");
                }
                else{
                    result_view.setText("false");
                }
            }
        });
    }
}