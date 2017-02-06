package com.sampleappkiranjatty.happyabode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    DataBaseHelper helper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.button_register) {
            EditText username = (EditText)findViewById (R.id.text_username_register);
            EditText password = (EditText)findViewById(R.id.text_pass_register);
            EditText re_password = (EditText)findViewById(R.id.text_confirm_password);
            EditText apartment = (EditText)findViewById(R.id.text_apartment_register);
            //convert to strings
            String username_str = username.getText().toString();
            String password_str = password.getText().toString();
            String re_password_str = re_password.getText().toString();
            String apartment_str = apartment.getText().toString();

            if(!password_str.equals(re_password_str)){
                //pop up mesaage
                Toast t = Toast.makeText(register.this, "Sorry, Passwords did not match!!", Toast.LENGTH_SHORT);
                t.show();
            }
            else{
                //insert it in the databse
                apartment a = new apartment();
                a.set_apartment(apartment_str);
                a.set_password(password_str);
                a.set_username(username_str);
                helper.register(a);
                //Toast t = Toast.makeText(register.this, "Register successful!!", Toast.LENGTH_SHORT);
                //t.show();

            }
         }
        }
    }
