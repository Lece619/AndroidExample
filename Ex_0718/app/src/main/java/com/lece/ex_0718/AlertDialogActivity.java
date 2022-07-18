package com.lece.ex_0718;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlertDialogActivity extends AppCompatActivity {

    Button btn_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);

        btn_show = findViewById(R.id.btn_show);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(AlertDialogActivity.this);
                dialog.setTitle("카카오톡");
                dialog.setMessage("평가좀 해주세요 ");

                dialog.setNegativeButton("아니오", null);
                dialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(AlertDialogActivity.this, "Yes", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNeutralButton("Netural",null);
                //다이얼로그가 꺼지는 것을 방지하는 코드
                dialog.setCancelable(false);

                dialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(AlertDialogActivity.this);
        alert.setTitle("종료하시겠습니까?");
        alert.setNegativeButton("종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alert.setPositiveButton("아니오",null);
        alert.setCancelable(false);
        alert.show();
    }
}