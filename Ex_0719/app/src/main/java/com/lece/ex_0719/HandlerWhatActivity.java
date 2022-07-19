package com.lece.ex_0719;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HandlerWhatActivity extends AppCompatActivity {

    Button btn0, btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_what);
        
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);

        btn0.setOnClickListener(listener);
        btn1.setOnClickListener(listener);

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                    
            switch (view.getId()){
                case R.id.btn0:
                        handler.sendEmptyMessage(0);
                    break;

                case R.id.btn1:
                        handler.sendEmptyMessage(1);
                    break;
            }
        }
    };

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    Toast.makeText(HandlerWhatActivity.this, "0", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(HandlerWhatActivity.this, "1", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}