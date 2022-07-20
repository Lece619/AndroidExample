package com.lece.ex_0720;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class InflaterActivity extends AppCompatActivity {

    LinearLayout layout;
    LayoutInflater inflater;
    View inner;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflater);

        layout = findViewById(R.id.layout);
        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        inner = inflater.inflate(R.layout.my_inflater, layout);
        //LinearLayout 안쪽에 inner View를 추가 해준다.
        //layout.addView(inner);

        btn1 = findViewById(R.id.btn_1);
        btn1 = inner.findViewById(R.id.btn_1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InflaterActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}