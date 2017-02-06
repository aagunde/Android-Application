package com.sampleappkiranjatty.happyabode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper helper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onButtonClick(View v){
        if(v.getId()==R.id.button_login){
            try {

                helper.createDataBase();

            } catch (IOException ioe) {

                throw new Error("Unable to create database");

            }
            // get the text from username text edit
            EditText e = (EditText)findViewById(R.id.text_user);
            // get password
            EditText p = (EditText)findViewById(R.id.text_pass);
            // convert to string
            String pass = p.getText().toString();
            // convert text edit to string
            String str = e.getText().toString();
            // check if username and password match
            Login match = helper.searchPass(str);
            if(match.getName().equals(pass)) {
                if (match.getRole() == 0) {
                    String apt = helper.query_db(str);
                    // intent to call other activity from present activity(current, next)
                    Intent i = new Intent(MainActivity.this, Common.class);
                    i.putExtra("Username", str);
                    i.putExtra("Apt_no", apt);
                    // send the string value of username to next activity using put extra
                    // the one in quotation can be any name but it should be the same in the next activity where we want it to display
                    //i.putExtra("Username", str);
                    // start new activity
                    startActivity(i);
                    //Toast t = Toast.makeText(MainActivity.this, "Congrats!!!", Toast.LENGTH_SHORT);
                    //t.show();
                }
                if (match.getRole() == 1) {
                    // intent to call other activity from present activity(current, next)
                    Intent i = new Intent(MainActivity.this, Admin.class);
                    //i.putExtra("Username", str);
                    //i.putExtra("Apt_no", apt);
                    // send the string value of username to next activity using put extra
                    // the one in quotation can be any name but it should be the same in the next activity where we want it to display
                    //i.putExtra("Username", str);
                    // start new activity
                    startActivity(i);
                    //Toast t = Toast.makeText(MainActivity.this, "Congrats!!!", Toast.LENGTH_SHORT);
                    //t.show();
                }
            }
            else{
                Toast t = Toast.makeText(MainActivity.this, "Invalid password", Toast.LENGTH_SHORT);
                t.show();
            }

        }
    }
}
