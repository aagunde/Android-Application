package com.sampleappkiranjatty.happyabode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class change_password extends AppCompatActivity {
    String str;
    DataBaseHelper helper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Bundle bundle = getIntent().getExtras();
        str = bundle.getString("Username");
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.button_change) {
            EditText old_password = (EditText) findViewById(R.id.text_old_password);
            EditText new_password = (EditText) findViewById(R.id.text_new_password);
            EditText confirm_password = (EditText) findViewById(R.id.text_confirm_new_password);
            //convert to string
            String old_password_str = old_password.getText().toString();
            String new_password_str = new_password.getText().toString();
            String confirm_password_str = confirm_password.getText().toString();

            if (!new_password_str.equals(confirm_password_str)) {
                //pop up mesaage
                Toast t = Toast.makeText(change_password.this, "Sorry, Passwords did not match!!", Toast.LENGTH_SHORT);
                t.show();
            } else {
                Login match = helper.searchPass(str);
                if(match.getName().equals(old_password_str)){
                apartment a = new apartment();
                    a.set_username(str);
                    a.set_password(new_password_str);
                    helper.changePassword(a);
                    // set new password
                }
                else{
                    Toast t = Toast.makeText(change_password.this, "Invalid password", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        }

    }
}
