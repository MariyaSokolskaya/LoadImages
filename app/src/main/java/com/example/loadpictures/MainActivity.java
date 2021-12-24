package com.example.loadpictures;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableRow;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TableRow tableRow;
    ProgressBar progressBar;
    ArrayList imagesView = new ArrayList();
    //адреса картинок
    public static final String[] imageUrls = {
            "http://srd-maestro.ru/content/catalog/75_south-landscape/1/fo3127.jpg",
            "http://img1.postila.ru/storage/6368000/6341379/48491bc488f1a3158b64913acd14a559.jpg",
            "http://is5.mzstatic.com/image/thumb/Music6/v4/1e/e4/57/1ee457b0-e296-a959-a424-56e2c5502ee0/source/100x100bb.jpg",
            "http://wallpaperscraft.ru/image/oblaka_pasmurno_nebo_tuchi_hmuroe_seroe_kusty_leto_zelen_55744_300x175.jpg"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableRow = findViewById(R.id.table_images);
        progressBar = findViewById(R.id.load_progress);
    }

    public void loadImages(View view) {
        for (int i = 0; i < tableRow.getChildCount(); i++) {
            imagesView.add((ImageView)tableRow.getChildAt(i));
        }

        try {
            URL url = new URL(imageUrls[0]);
            Bitmap image = BitmapFactory.decodeStream(url.openStream());
            ((ImageView)imagesView.get(0)).setImageBitmap(image);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}