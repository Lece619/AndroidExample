package com.lece.ex_0727;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SqliteActivity extends AppCompatActivity {

    SQLiteDatabase mDatabase;

    boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        //앱이 최초에 실행되었을때
        copyAccects();
    }

    private void copyAccects() {
        //inputStream -> output
        AssetManager assetManager = getAssets();

        String[] files = null;
        String mkdir = "";
        try{
            files = assetManager.list("");
            InputStream in = null;
            OutputStream out = null;

            in = assetManager.open(files[1]);

            String str = "" + Environment.getExternalStorageDirectory();
            mkdir = str + "/database";

            File mpath = new File(mkdir);

            if(!mpath.exists()){
                isFirst =true;
            }
            if(isFirst){
                mpath.mkdirs();

                out = new FileOutputStream(mkdir + "/" + files[1]);
                byte[] buffer = new byte[2048];
                int read = 0;

                while((read = in.read(buffer)) != -1){
                    out.write(buffer, 0, read);
                }

                out.close();
                in.close();

                isFirst = false;
            }
        }catch (Exception e){

        }


    }
}