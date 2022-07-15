package com.lece.ex_0715;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //사용을 위한 메뉴 리소스파일을 등록
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //
        switch (item.getItemId()){
            case R.id.menu_1:{
                Toast.makeText(this, "menu_1", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_2:{
                Toast.makeText(this, "menu_2", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.menu_3:
                finish();
                //현재 띄워져있는 액티비티 한 개 종료
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}