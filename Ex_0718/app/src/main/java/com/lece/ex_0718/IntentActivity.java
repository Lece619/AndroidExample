package com.lece.ex_0718;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class IntentActivity extends AppCompatActivity {

    Button btn_call, btn_sms, btn_camera, btn_gallery, btn_link, btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        btn_call =  findViewById(R.id.btn_call);
        btn_sms =  findViewById(R.id.btn_sms);
        btn_camera =  findViewById(R.id.btn_camera);
        btn_gallery =  findViewById(R.id.btn_gallery);
        btn_link =  findViewById(R.id.btn_link);
        btn_next =  findViewById(R.id.btn_next);

        btn_call.setOnClickListener(click);
        btn_sms.setOnClickListener(click);
        btn_camera.setOnClickListener(click);
        btn_gallery.setOnClickListener(click);
        btn_link.setOnClickListener(click);
        btn_next.setOnClickListener(click);

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = null;

            switch (view.getId()){
                //전화 화면 걸기
                case R.id.btn_call:
                    intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:010-1111-1111"));
                    //통화를 거는데 필요한 권한을 주는 작업
                    intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:010-1111-1111"));
                    startActivity(intent);
                    break;
                //문자로 보내기
                case R.id.btn_sms:
                    intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("sms:010-1111-1111"));
                    intent.putExtra("sms_body","보낼 문자입니다.");
                    startActivity(intent);
                    break;
                //카메라로 연결
                case R.id.btn_camera:
                    //내장 카메라
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //동영상 연결
                    intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    startActivity(intent);
                    break;
                //갤러리 접근
                case R.id.btn_gallery:
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivity(intent);
                    break;
                //외부 링크로 이동
                case R.id.btn_link:
                    //특정 링크 이동
                    intent = new Intent(Intent.ACTION_VIEW); //Action_View가 없어도 동작
                                                            //낮은버전에서 무조건 구글로 이동 버그있음
                    intent.setData(Uri.parse("https://naver.com"));
                    //플레이스토어로 이동하는법
                    intent.setData(Uri.parse("market://details?id=com.lece.ex_0718"));
                    startActivity(intent);
                    break;
                //다른 액티비티로 이동
                case R.id.btn_next:
                    intent = new Intent(IntentActivity.this,IntentSubActivity.class);
                    //중복된 페이지를 걸러내는 플래그 추가
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    break;
            }

        }
    };
}