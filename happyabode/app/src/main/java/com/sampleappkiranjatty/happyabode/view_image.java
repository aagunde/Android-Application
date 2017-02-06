package com.sampleappkiranjatty.happyabode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.InputStream;

public class view_image extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
       // ImageView myimage = (ImageView) findViewById(R.id.display_attachment);

        if(getIntent().hasExtra("byteArray")) {
            //ImageView _imv= new ImageView(this);
            ImageView image = (ImageView) findViewById(R.id.display_image);
            Bitmap _bitmap = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
            image.setImageBitmap(_bitmap);
        }
    }
}
