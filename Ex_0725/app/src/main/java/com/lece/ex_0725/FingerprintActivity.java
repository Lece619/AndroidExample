package com.lece.ex_0725;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ajalt.reprint.core.AuthenticationFailureReason;
import com.github.ajalt.reprint.core.AuthenticationListener;
import com.github.ajalt.reprint.core.Reprint;

public class FingerprintActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint);

        text = findViewById(R.id.text);

        //지문 사용을 위한 초기화
        Reprint.initialize(FingerprintActivity.this);

        if(checkSpac()){
            //지문인식이 가능한 경우
            Reprint.authenticate(new AuthenticationListener() {
                @Override
                public void onSuccess(int moduleTag) {
                    //보통은 화면을 전환한다.
                    text.setText("인증 성공");
                }

                @Override
                public void onFailure(AuthenticationFailureReason failureReason, boolean fatal, CharSequence errorMessage, int moduleTag, int errorCode) {
                    text.setText("인증 실패. 다시 시도하세요.");
                }
            });
        }

    }

    //지문 인식이 가능한지 판단
    public boolean checkSpac(){
        //지문 인식센서 존재 유무
        boolean hardware = Reprint.isHardwarePresent();

        //센서가 있어도 지문이 등록이 되어있지 않으면 사용할 수 없으니
        // 등록되어있는지 확인
        boolean register = Reprint.hasFingerprintRegistered();
        
        if(!hardware){
            Toast.makeText(FingerprintActivity.this, "지문인식을 지원하지 않는 기기입니다.", Toast.LENGTH_SHORT).show();
        }else if(!register){
            Toast.makeText(FingerprintActivity.this, "등록된 지문이 없습니다.", Toast.LENGTH_SHORT).show();
        }
        
        return hardware && register;
    }
}