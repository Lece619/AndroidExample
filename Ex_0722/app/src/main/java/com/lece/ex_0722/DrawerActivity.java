package com.lece.ex_0722;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DrawerActivity extends AppCompatActivity {

    DrawerLayout drawer_layout;
    Button btn_open, btn_close, btn_close2;
    LinearLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        drawer_layout = findViewById(R.id.drawer_layout);
        btn_open = findViewById(R.id.btn_open);
        btn_close = findViewById(R.id.btn_close);
        btn_close2 = findViewById(R.id.btn_close2);
        drawer = findViewById(R.id.drawer);

        btn_open.setOnClickListener( listener );
        btn_close.setOnClickListener( listener );
        btn_close2.setOnClickListener( listener );

        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_open:
                    drawer_layout.openDrawer(drawer);
                    break;
                case R.id.btn_close: case R.id.btn_close2:
//                        drawer_layout.closeDrawer(drawer);
                    drawer_layout.closeDrawers();
                    break;
            }
        }
    };
}