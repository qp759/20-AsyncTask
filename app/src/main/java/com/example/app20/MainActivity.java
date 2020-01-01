package com.example.app20;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hw20.R;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new GetImage().execute("http://www.hklyclub.com/data/files/store_34/goods_38/small_201501071420382990.jpg");
            }
        });

    }
    private class GetImage extends AsyncTask<String , Integer , Bitmap> {
        ProgressDialogUtil progressDialogUtil;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialogUtil = new ProgressDialogUtil();
            progressDialogUtil.showProgressDialog(MainActivity.this,"載入中");
        }
        @Override
        protected Bitmap doInBackground(String... params) {
            String urlStr = params[0];
            try {
                URL url = new URL(urlStr);
                return BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            progressDialogUtil.dismiss();
            imageView.setImageBitmap(bitmap);
        }
    }

    }
