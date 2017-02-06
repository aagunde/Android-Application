package com.sampleappkiranjatty.happyabode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Request extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg1;
    private EditText description, choosetime;
    byte[] byteArray;
    private String time_inp, choose_time, str, apt, desc, text_room, text_type;
    NewRequest req = new NewRequest();
    DataBaseHelper helper = new DataBaseHelper(this);
    private static final int CAMERA_REQUEST = 1337;
    public ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
        rg1.setOnCheckedChangeListener(this);
        //actv(false);
        choosetime = (EditText) findViewById(R.id.editText_time);
        choosetime.setVisibility(View.INVISIBLE);
        Bundle bundle = getIntent().getExtras();
        str = bundle.getString("Username");
        apt = bundle.getString("Apt_no");
        //tv = (TextView)findViewById(R.id.test_text);
        // set the text to the variable
        //tv.setText(apt);
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        switch (checkedId)
        {
            case R.id.radio_choosetime:
                //choosetime = (EditText) findViewById(R.id.editText_time);
                choosetime.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void OnButtonClick(View v){
        if(v.getId()==R.id.button_attachment) {
            //Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            //startActivityForResult(cameraIntent, CAMERA_REQUEST);
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent,CAMERA_REQUEST);
            //Toast t = Toast.makeText(Request.this, "Request filed!!", Toast.LENGTH_SHORT);
            //t.show();
        }
        if(v.getId()==R.id.button_send) {
            Spinner spin_room = (Spinner) findViewById(R.id.spinner_room);
            text_room = spin_room.getSelectedItem().toString();
            choosetime = (EditText) findViewById(R.id.editText_time);
            choose_time = choosetime.getText().toString();
            time_inp=choose_time;
            Spinner spin_type = (Spinner) findViewById(R.id.spinner_type);
            text_type = spin_type.getSelectedItem().toString();
            description = (EditText) findViewById(R.id.text_desc);
            desc = description.getText().toString();
            req.set_apt_no(apt);
            req.set_description(desc);
            req.set_time(time_inp);
            req.set_room(text_room);
            req.set_type(text_type);
            req.set_user(str);
            req.set_byte_stream(byteArray);
            helper.insert(req);
            //Toast t = Toast.makeText(Request.this, "Request filed!!", Toast.LENGTH_SHORT);
            //t.show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            //imageView.setImageBitmap(photo);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();
            //helper.insert_photo(byteArray);

        }
    }

}
