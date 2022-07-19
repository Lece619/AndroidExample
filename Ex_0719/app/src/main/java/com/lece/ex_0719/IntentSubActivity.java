package com.lece.ex_0719;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

public class IntentSubActivity extends AppCompatActivity {

    TextView txt_name, txt_age, txt_tel, txt_birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_sub);

        txt_name = findViewById(R.id.txt_name);
        txt_age = findViewById(R.id.txt_age);
        txt_tel = findViewById(R.id.txt_tel);
        txt_birth = findViewById(R.id.txt_b_day);

        Intent intent = getIntent();
        txt_name.append(intent.getStringExtra("name"));
        txt_age.append(intent.getStringExtra("age"));
        txt_tel.append(intent.getStringExtra("tel"));
        txt_birth.append(intent.getStringExtra("birth"));
    }


}