package com.lece.ex_naverapi;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.lece.vo.BookVO;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.transform.URIResolver;

public class ViewModelAdapter extends ArrayAdapter<BookVO> {

    Context context;
    int resource;
    BookVO bookVO;
    ArrayList<BookVO> list;
    TextView book_title, book_author, book_price;
    ImageView book_image;
    ImageAsync imageAsync;

    public ViewModelAdapter(Context context, int resource, ArrayList<BookVO> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //myListView.setAdapter(adapter) 했을 때 호출되는 메서드(getView()) 
        //생성자의 파라미터를 받은 사이즈만큼 getView() 메서드가 반복 호출
        imageAsync = new ImageAsync();

        //인플레터로 layout을 view로 바꿔주는 작업도 여기서 수행해 준다.
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //book_item.xml 파일을 view 형태로 변경해준다.
        convertView = layoutInflater.inflate(resource, null);

        BookVO bookVO = list.get(position);
        book_title = convertView.findViewById(R.id.book_title);
        book_author = convertView.findViewById(R.id.book_author);
        book_price = convertView.findViewById(R.id.book_price);
        book_image = convertView.findViewById(R.id.book_image);

        book_title.setText(bookVO.getB_title());
        book_author.setText(bookVO.getB_author());
        book_price.setText(bookVO.getB_price() + "원");
        imageAsync.execute(bookVO.getB_image());



        return convertView;
    }

    class ImageAsync extends AsyncTask<String, Void, Bitmap>{

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

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            book_image.setImageBitmap(bitmap);
        }
    }
}
