package com.lece.ex_0725;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

public class SweetAlertActivity extends AppCompatActivity {

    SweetAlertDialog sweetAlertDialog;
    Button btn_warning, btn_error, btn_success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweet_alert);

        btn_warning = findViewById(R.id.btn_waring);
        btn_error = findViewById(R.id.btn_error);
        btn_success = findViewById(R.id.btn_success);

        btn_warning.setOnClickListener(listener);
        btn_error.setOnClickListener(listener);
        btn_success.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_waring:
                    sweetAlert("warning", getString(R.string.alert_waring),SweetAlertDialog.WARNING_TYPE,SweetAlertActivity.this);
                    break;

                case R.id.btn_error:
                    sweetAlert("error", getString(R.string.alert_error),SweetAlertDialog.ERROR_TYPE,SweetAlertActivity.this);
                    break;

                case R.id.btn_success:
                    sweetAlert("success", getString(R.string.alert_success),SweetAlertDialog.SUCCESS_TYPE,SweetAlertActivity.this);
                    break;

            }
        }
    };

    //타입별 다이얼로그 호출 메소드
    public void sweetAlert(String msg, String title, int type, Context context){
        sweetAlertDialog = new SweetAlertDialog(context, type);
        sweetAlertDialog.setTitleText(title);
        sweetAlertDialog.setConfirmText(msg);
        sweetAlertDialog.setConfirmText("ok");
        sweetAlertDialog.show();

    }
}