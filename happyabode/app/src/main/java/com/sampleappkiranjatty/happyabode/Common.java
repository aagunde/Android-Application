package com.sampleappkiranjatty.happyabode;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Common extends AppCompatActivity {

    String str, apt;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        Bundle bundle = getIntent().getExtras();
        str = bundle.getString("Username");
        apt = bundle.getString("Apt_no");
        //tv = (TextView)findViewById(R.id.test_text);
        // set the text to the variable
        //tv.setText(apt);
    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
               Intent i = new Intent(Common.this, Common.class);
                startActivity(i);

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    public void OnButtonClick(View v){
        if(v.getId()==R.id.button_request){
                // intent to call other activity from present activity(current, next)
                Intent i = new Intent(Common.this, Request.class);
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
        if(v.getId()==R.id.button_hist)
        {
            Intent i = new Intent(Common.this, request_history.class);
            i.putExtra("Apt_no", apt);
            // send the string value of username to next activity using put extra
            // the one in quotation can be any name but it should be the same in the next activity where we want it to display
            startActivity(i);
        }
        if(v.getId()==R.id.button_change_password)
        {
            Intent i = new Intent(Common.this, change_password.class);
            i.putExtra("Username", str);
            //i.putExtra("Apt_no", apt);
            // send the string value of username to next activity using put extra
            // the one in quotation can be any name but it should be the same in the next activity where we want it to display
            startActivity(i);
        }
        if(v.getId()==R.id.button_view_announcements)
        {
            Intent i = new Intent(Common.this, get_announcement.class);
            i.putExtra("Apt_no", apt);
            //i.putExtra("Apt_no", apt);
            // send the string value of username to next activity using put extra
            // the one in quotation can be any name but it should be the same in the next activity where we want it to display
            startActivity(i);
        }

    }
}
