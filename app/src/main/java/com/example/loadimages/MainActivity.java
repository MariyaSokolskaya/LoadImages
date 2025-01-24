package com.example.loadimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button loadButton;
    TableRow imagesRow;
    String [] imageAddresses =
            {"https://www.fonstola.ru/pic/202003/1920x1080/fonstola.ru_376107.jpg",
                    "https://i.ytimg.com/vi/4PUhM01PZf8/maxresdefault.jpg",
                    "https://i.etsystatic.com/24419106/r/il/e571b0/2440722344/il_1588xN.2440722344_gi2c.jpg"};
    Bitmap image;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //super.handleMessage(msg);
            if(msg.arg1 == 1){
                //TODO показать картинку
                ((ImageView)imagesRow.getChildAt(0)).setImageBitmap(image);
            }else{
                Toast.makeText(getApplicationContext(), "Картинка не загрузилась", Toast.LENGTH_LONG)
                        .show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        loadButton = findViewById(R.id.load);
        imagesRow = findViewById(R.id.images_row);

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadImageThread loadImageThread = new LoadImageThread(handler, 0);

                loadImageThread.start();
            }
        });

    }

    class LoadImageThread extends Thread{
        Handler handler;
        int numImage;
        public LoadImageThread(Handler handler, int numImage){
            this.handler = handler;
            this.numImage = numImage;
        }

        @Override
        public void run() {
            super.run();
            Message message = new Message();
            try {
                URL imageAddress = new URL(imageAddresses[numImage]);
                image = BitmapFactory.decodeStream(imageAddress.openStream());
                message.arg1 = 1;
            } catch (MalformedURLException e) {
                message.arg1 = 0;
            } catch (IOException e) {
                message.arg1 = 0;
            }
            handler.sendMessage(message);
        }
    }
}