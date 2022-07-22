package com.lece.ex_naverapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.lece.vo.BookVO;

import java.io.IOException;
import java.net.URL;

public class SubActivity extends AppCompatActivity {

    ImageView book_image;
    TextView text_title, text_author, text_price;
    Button btn_ok;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        //intent 연결
        intent = getIntent();
        String s_title = intent.getStringExtra("title");
        String s_author = intent.getStringExtra("author");
        String s_price = intent.getStringExtra("price");
        String s_image = intent.getStringExtra("image");

        text_title =findViewById(R.id.text_title);
        text_author =findViewById(R.id.text_author);
        text_price =findViewById(R.id.text_price);
        book_image = findViewById(R.id.book_image);
        btn_ok = findViewById(R.id.btn_ok);
//        Toast.makeText(this, s_title, Toast.LENGTH_SHORT).show();
        text_title.setText(s_title);
        text_author.setText(s_author);
        text_price.setText(s_price);


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        new ImgAsync().execute(s_image);
    }

    class ImgAsync extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            try {
                String img_url = strings[0];
                URL url = new URL(img_url);

                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (bitmap == null) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.no_image);
            }
            //bookVO.setBitmap(bitmap);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            book_image.setImageBitmap(bitmap);
        }
    }
}