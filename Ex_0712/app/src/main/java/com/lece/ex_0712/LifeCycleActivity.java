package com.lece.ex_0712;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class LifeCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        Log.i("MY","--onCreate--");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MY","--onDestroy--");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MY","--onPause--");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MY","--onStart--");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(LifeCycleActivity.this, "재실행", Toast.LENGTH_SHORT).show();
        Log.i("MY","--onReStart--");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MY","--onResume--");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MY","--onStop--");
    }
}