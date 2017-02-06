package com.sampleappkiranjatty.happyabode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }
    public void OnButtonClick(View v){
        if(v.getId()== R.id.button_myrequest){
            Intent i = new Intent(Admin.this, admin_requests.class);
            startActivity(i);
        }
        if(v.getId()== R.id.button_register_main){
            Intent i = new Intent(Admin.this, register.class);
            startActivity(i);
        }
        if(v.getId()== R.id.button_announcement){
            Intent i = new Intent(Admin.this, add_announcement.class);
            startActivity(i);
        }
    }
}
