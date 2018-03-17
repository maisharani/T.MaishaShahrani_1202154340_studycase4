package com.andro.maisha.tmaishashahrani_1202154340_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class CariGambarActivity extends AppCompatActivity {

    private EditText edCari;
    private Button btnCari;
    private ImageView ivCari;
    private ProgressDialog progressDialog;
    private String text;

    //Deklarasi semua komponen yang akan digunakan


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_gambar);

        edCari = (EditText) findViewById(R.id.ed_cari_gambar);
        btnCari = (Button) findViewById(R.id.btn_cari);
        ivCari = (ImageView) findViewById(R.id.iv_cari);

        //Inisialisasi semua komponen yang akan digunakan

    }

    public void cariGambar(View view) {
        text = edCari.getText().toString();
        //Mengubah EditText ke dalam bentuk String
        if (text.isEmpty()) {
            Toast.makeText(this, "Masukkan URL gambar terlebih dahulu", Toast.LENGTH_LONG).show();
            //Jika EditText kosong akan memunculkan Toast
        } else {
            new DownloadGambar().execute(text);
            //Jika EditText berisi String maka akan di eksekusi
        }
    }

    private class DownloadGambar extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CariGambarActivity.this);
            progressDialog.setTitle("Cari Gambar");
            progressDialog.setMessage("Loading Gambar");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

            //Method ini digunakan untuk melakukan eksekusi progress dialog sebelum method onPostExecute dijalankan
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;

            //Method ini digunakan untuk melakukan aktivitas dibackground menggunakan AsyncTask
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ivCari.setImageBitmap(bitmap);
            progressDialog.dismiss();

            //Method ini digunakan untuk melakukan eksekusi setImageBitmap setelah method doInBackground dijalankan
        }
    }
}