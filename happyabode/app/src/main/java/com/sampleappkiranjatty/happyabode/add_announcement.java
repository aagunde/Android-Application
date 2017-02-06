package com.sampleappkiranjatty.happyabode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class add_announcement extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup rg2;
    private EditText description, choose_apt;
    private String apart,desc;
    announcement anno = new announcement();
    DataBaseHelper helper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcement);
        rg2 = (RadioGroup) findViewById(R.id.radioGroup2);
        rg2.setOnCheckedChangeListener(this);
        choose_apt = (EditText) findViewById(R.id.content_apartment);
        //actv(false);
        choose_apt.setVisibility(View.INVISIBLE);

    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId){
        switch (checkedId)
        {
            case R.id.radio_enter_apartment:
                choose_apt.setVisibility(View.VISIBLE);
                choose_apt = (EditText) findViewById(R.id.content_apartment);
                apart = choose_apt.getText().toString();
                break;
        }
    }
    public void OnButtonClick(View v) {
        if (v.getId() == R.id.button_add_announcement) {
            description = (EditText) findViewById(R.id.editText_description);
            //desc = "default";
            desc = description.getText().toString();
            //apart ="all";
            choose_apt = (EditText) findViewById(R.id.content_apartment);
            apart = choose_apt.getText().toString();
            //Toast tt = Toast.makeText(add_announcement.this, desc, Toast.LENGTH_SHORT);
            //tt.show();
            //Toast t0 = Toast.makeText(add_announcement.this, apart, Toast.LENGTH_SHORT);
           // t0.show();
            anno.set_announcement(desc);
            anno.set_apt(apart);
            helper.add_announcement(anno);
            //Toast t = Toast.makeText(add_announcement.this, "Announcement added!!" + desc + apart, Toast.LENGTH_SHORT);
            //Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            //startActivityForResult(cameraIntent, CAMERA_REQUEST);

            //t.show();
        }
    }
}
