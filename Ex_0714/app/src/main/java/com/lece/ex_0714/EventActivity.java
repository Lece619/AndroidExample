package com.lece.ex_0714;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EventActivity extends AppCompatActivity {

    //레이아웃에서 객체로 사용하는 모든 것들은 액티비티에서 클래스로 존재한다.
    Button b_red, b_green, b_blue;
    TextView t_view;
    Button btn_send, btn_reset;
    EditText e_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        //이벤트 처리에 사용할 객체를 ' 검색 '
        b_red = findViewById(R.id.btn_red);
        b_green = findViewById(R.id.btn_green);
        b_blue = findViewById(R.id.btn_blue);
        t_view = findViewById(R.id.text_view);
        btn_reset = findViewById(R.id.btn_reset);
        btn_send = findViewById(R.id.btn_send);
        e_text = findViewById(R.id.edit_text);


        b_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                t_view.setBackgroundColor(Color.RED);
                if(t_view.getText().equals("RED")){
                    
                }

                t_view.setBackgroundColor(Color.parseColor("#ff0000"));
                //액티비티 영역에서는 생략이 안되므로 6글자 다 적어줘야한다.
                t_view.setText("RED");


            }
        });

        b_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t_view.setBackgroundColor(Color.GREEN);
                t_view.setText("GREEN");
            }
        });

        b_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t_view.setBackgroundColor(Color.BLUE);
                t_view.setText("BLUE");
            }
        });

        t_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(EventActivity.this)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        t_view.setText("결 과");
                        t_view.setBackgroundColor(Color.BLACK);
                    }
                })
                        .setTitle("초기화면으로")
                        .show();
            }
        });
//
//        btn_send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String msg = e_text.getText().toString();
//                if(msg.equals("")){
//                    Toast.makeText(EventActivity.this, "글자를 입력해 주세요", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    t_view.setText(msg);
//                }
//            }
//        });
//
//
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (view.getId()){
//                    case R.id.btn_reset:
//                        t_view.setBackgroundColor(Color.BLACK);
//                        t_view.setText("결과");
//                        break;
//                    case R.id.btn_send:
//                        break;
//                }
//            }
//        };
//        
//         btn_reset.setOnClickListener(listener);
        btn_send.setOnClickListener(click);
        btn_reset.setOnClickListener(click);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){
                    case R.id.btn_send:
                        String msg = e_text.getText().toString();
                        if(msg.equals("")){
                            Toast.makeText(EventActivity.this, "글자를 입력해 주세요", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            t_view.setText(msg);
                        }

                        break;
                    case R.id.btn_reset:
                        t_view.setBackgroundColor(Color.BLACK);
                        t_view.setText("결과");
                        break;
                }
        }
    };
}

