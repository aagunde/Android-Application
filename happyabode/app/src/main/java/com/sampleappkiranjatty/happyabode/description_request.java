package com.sampleappkiranjatty.happyabode;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class description_request extends AppCompatActivity {
    DataBaseHelper helper = new DataBaseHelper(this);
    String str;
    TextView tv;
    NewRequest req;
    byte[] byteArray;
    ImageView image;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_request);
        //setContentView(R.layout.activity_adapter_item);
        Bundle bundle = getIntent().getExtras();
        str = bundle.getString("keyword");
        req = helper.get_Req(str);
        tv = (TextView) findViewById(R.id.text_aptno);
        // set the text to the variable
        tv.setText("ApartmentNo:   " + req.get_apt_no());

        tv = (TextView) findViewById(R.id.text_room);
        // set the text to the variable
        tv.setText("Room:   " + req.get_room());

        tv = (TextView) findViewById(R.id.text_type);
        // set the text to the variable
        tv.setText("Type:   " + req.get_type());

        tv = (TextView) findViewById(R.id.text_descr);
        // set the text to the variable
        tv.setText("Description:   " + req.get_description());

        tv = (TextView) findViewById(R.id.text_time);
        // set the text to the variable
        tv.setText("Available Time:   " + req.get_time());

        byteArray = req.get_byte_stream();
        if (byteArray == null) {
        } else {
        /*tv = (TextView)findViewById(R.id.text_user);
        // set the text to the variable
        tv.setText("User:   "+req.get_user());*/
            bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            image = (ImageView) findViewById(R.id.display_attachment);
            image.setImageBitmap(bmp);
        }
    }


    public void onButtonClick(View v) {
        if(v.getId()==R.id.display_attachment) {
            Intent i = new Intent(this, view_image.class);
            ByteArrayOutputStream _bs = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 50, _bs);
            i.putExtra("byteArray", _bs.toByteArray());
            startActivity(i);
        }
    }
}
