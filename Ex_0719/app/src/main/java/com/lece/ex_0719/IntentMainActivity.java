package com.lece.ex_0719;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class IntentMainActivity extends AppCompatActivity {

    EditText edit_name, edit_age, edit_tel, edit_b_day;
    Button btn_date, btn_send;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_main);
        edit_name = findViewById(R.id.edit_name);
        edit_age = findViewById(R.id.edit_age);
        edit_tel = findViewById(R.id.edit_tel);
        edit_b_day = findViewById(R.id.edit_b_day);
        btn_date = findViewById(R.id.btn_date);
        btn_send = findViewById(R.id.btn_send);


        btn_send.setOnClickListener(sendClick);
        btn_date.setOnClickListener(dateClick);
    }

    View.OnClickListener sendClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = edit_name.getText().toString();
            if(name.trim().length()==0){
                Toast.makeText(IntentMainActivity.this, "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            String age = edit_age.getText().toString();
            String tel = edit_tel.getText().toString();
            String birth = edit_b_day.getText().toString();

            Intent intent = new Intent(IntentMainActivity.this,IntentSubActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("age", age);
            intent.putExtra("tel", tel);
            intent.putExtra("birth", birth);

            startActivity(intent);
            
        }
    };

    View.OnClickListener dateClick = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            //달력에 최초로 표기될 날짜를 구한다.
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH); //달은 0부터 시작
            int day = now.get(Calendar.DAY_OF_MONTH);

            dialog = new DatePickerDialog(IntentMainActivity.this,
                    dateSetListener, year, month, day);
            dialog.show();

        }
    };

    //달력의 변경사항을 감지하는 이벤트 감지자
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            String result = String.format("%d-%02d-%02d",year, month+1, day);
            edit_b_day.setText(result);
        }
    };
}