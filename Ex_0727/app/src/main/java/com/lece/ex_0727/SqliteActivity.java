package com.lece.ex_0727;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SqliteActivity extends AppCompatActivity {

    SQLiteDatabase mDatabase;
    boolean isFirst = true;
    SharedPreferences pref;
    Button btn_all, btn_search, btn_insert, btn_delete;
    TextView result_txt;
    EditText edit_name, edit_phone, edit_age;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        pref = PreferenceManager.getDefaultSharedPreferences(SqliteActivity.this);

        load();
        //앱이 최초에 실행되었을때
        copyAccects();
        save();
        Toast.makeText(this, isFirst+"", Toast.LENGTH_SHORT).show();

        mDatabase =
        openOrCreateDatabase(Environment.getExternalStorageDirectory()+"/database/myDB.db",SQLiteDatabase.CREATE_IF_NECESSARY,null);


        btn_all = findViewById(R.id.btn_all);
        btn_search = findViewById(R.id.btn_search);
        btn_insert = findViewById(R.id.btn_insert);
        btn_delete = findViewById(R.id.btn_delete);
        result_txt = findViewById(R.id.result_txt);
        edit_name = findViewById(R.id.edit_name);
        edit_phone = findViewById(R.id.edit_phone);
        edit_age = findViewById(R.id.edit_age);

        btn_all.setOnClickListener(listener);
        btn_delete.setOnClickListener(listener);
        btn_insert.setOnClickListener(listener);
        btn_search.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            switch (view.getId()) {
                case R.id.btn_all: // 전체조회
                    searchQuery("select * from friend");
                    break;
                case R.id.btn_search: //상세조회
                    String name = edit_name.getText().toString().trim();
                    if(name.length()==0){
                        Toast.makeText(SqliteActivity.this, "검색할 이름을 입력하세요", Toast.LENGTH_SHORT).show();
                    }else {
                        searchQuery(String.format("select * from friend where name='%s'",name));
                    }
                    break;
                case R.id.btn_insert: // 정보추가
                    name = edit_name.getText().toString().trim();
                    String phone = edit_phone.getText().toString().trim();
                    String age = edit_age.getText().toString().trim();
                    if(name.length() == 0 ){
                        Toast.makeText(SqliteActivity.this, "이름을 입력해 주세요", Toast.LENGTH_SHORT).show();
                    }else if(phone.length() == 0){
                        Toast.makeText(SqliteActivity.this, "핸드폰 번호를 입력해 주세요", Toast.LENGTH_SHORT).show();
                    }else if (age.length() == 0){
                        Toast.makeText(SqliteActivity.this, "나이를 입력해 주세요", Toast.LENGTH_SHORT).show();
                    }else{
                        insertQuery(name, phone, age);
                    }
                    searchQuery("select * from friend");
                    break;

                case R.id.btn_delete: // 정보삭제
                    name = edit_name.getText().toString().trim();
                    if(name.length()!=0) {
                        if (mDatabase.delete("friend", "name = ? ", new String[]{name}) == 0) {
                            Toast.makeText(SqliteActivity.this, "이름이 없습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SqliteActivity.this, "삭제완료", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SqliteActivity.this, "이름을 입력하세요", Toast.LENGTH_SHORT).show();
                    }
                    edit_name.setText("");
                    break;
            }


        }
    };
    //쿼리문
    public void searchQuery(String query){
        Cursor c = mDatabase.rawQuery(query,null);
        
        String[] col = c.getColumnNames();
        
        String[] str = new String[c.getColumnCount()];
        String result="";
        while(c.moveToNext()){
//            str[index] = c.getString(0) + "\t:::\t"
//                        + c.getString(1) + "\t:::\t"
//                        + c.getString(2);
//            index++;
            for (int i = 0; i < col.length; i++) {
                str[i]="";
                str[i] += c.getString(i);

                result += col[i] + " : " + str[i] + "\n";
            }

            result += "\n";
        }


        result_txt.setText(result);
    }

    public void insertQuery(String name, String phone, String age){

        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("phone", phone);
        cv.put("age", age);

        if(mDatabase.insert("friend", null, cv) == 0){
            Toast.makeText(this, "실패했습니다.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "추가했습니다.", Toast.LENGTH_SHORT).show();
        }

        edit_age.setText("");
        edit_phone.setText("");
        edit_name.setText("");

    }

    private void copyAccects() {
        //inputStream -> output
        AssetManager assetManager = getAssets();

        String[] files = null;
        String mkdir = "";
        try {
            files = assetManager.list("");
            InputStream in = null;
            OutputStream out = null;

            in = assetManager.open(files[1]);

            String str = "" + Environment.getExternalStorageDirectory();
            mkdir = str + "/database";

            File mpath = new File(mkdir);

            if (!mpath.exists()) {
                isFirst = true;
            }
            if (isFirst) {
                mpath.mkdirs();

                out = new FileOutputStream(mkdir + "/" + files[1]);
                byte[] buffer = new byte[2048];
                int read = 0;
        
                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }

                out.close();
                in.close();
                isFirst = false;
            }
        } catch (Exception e) {

        }
    }

    public void save() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("save", isFirst);
    }

    public void load() {
        isFirst = pref.getBoolean("save", true);
        //default true로 가져온다.
    }
}