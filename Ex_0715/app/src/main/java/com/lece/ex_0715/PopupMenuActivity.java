package com.lece.ex_0715;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class PopupMenuActivity extends AppCompatActivity {

    Button btn_show, anchor;
    PopupMenu popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu);

        btn_show = findViewById(R.id.btn_show);
        anchor = findViewById(R.id.anchor);
        createPopup();
        
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.show();
            }
        });
    }

    //메뉴 만들어주기
    private void createPopup(){
        popup = new PopupMenu(PopupMenuActivity.this,anchor);
        getMenuInflater().inflate(R.menu.my_menu2,popup.getMenu());
        //팝업메뉴에 띄워줄 메뉴 xml 등록
        //inflate() : xml을 view 형태로 바꿔준다.
        //popup.getMenu() : 메뉴가 들어갈 공간을 만들어준다.
        popup.setOnMenuItemClickListener(menu_click);
    }

    PopupMenu.OnMenuItemClickListener menu_click = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {

            switch (menuItem.getItemId()){
                case R.id.menu_4:
                    Toast.makeText(PopupMenuActivity.this, "menu1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu_5:
                    Toast.makeText(PopupMenuActivity.this, "menu2", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu_6:
                    Toast.makeText(PopupMenuActivity.this, "menu3", Toast.LENGTH_SHORT).show();
                    break;
            }

            return false;
        }
    };

}