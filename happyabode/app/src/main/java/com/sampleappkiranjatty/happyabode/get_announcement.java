package com.sampleappkiranjatty.happyabode;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class get_announcement extends ListActivity {

    DataBaseHelper helper = new DataBaseHelper(this);
    private ArrayAdapter<String> mAdapter;
    ListView view;
    String apt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_announcement);
        Bundle bundle = getIntent().getExtras();
        apt = bundle.getString("Apt_no");
        getActive();
    }

    public void getActive() {
        String[] s;
        //Toast.makeText(get_announcement.this, "get active", Toast.LENGTH_SHORT).show();
        s = helper.get_active_announcements(apt);
        //if (mAdapter == null) {
        mAdapter = new ArrayAdapter<>(this, R.layout.activity_anno_adapter, R.id.text_announcement, s);
        this.setListAdapter(mAdapter);
        /*} else {
            mAdapter.clear();
            mAdapter.addAll(s);
            mAdapter.notifyDataSetChanged();
        }*/
        //return names.toArray(new String[names.size()]);
    }

}
