package com.sampleappkiranjatty.happyabode;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class admin_requests extends ListActivity {
    DataBaseHelper helper = new DataBaseHelper(this);
    private ArrayAdapter<String> mAdapter;
    ListView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_requests);
        //view = (ListView) findViewById(R.id.list);
        getActive();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //ItemClicked item = adapter.getItemAtPosition(position);
        super.onListItemClick(l, v, position, id);

        String keyword = (String)this.getListAdapter().getItem(position);

        Intent i = new Intent(admin_requests.this, description_request.class);
        Bundle b = new Bundle();
        b.putString("keyword", keyword);
        i.putExtras(b);
        startActivity(i);
    }
    public void getActive() {
        String[] s;
        s = helper.get_active();
        //if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this, R.layout.activity_adapter_item, R.id.text_apartment, s);
            this.setListAdapter(mAdapter);
        /*} else {
            mAdapter.clear();
            mAdapter.addAll(s);
            mAdapter.notifyDataSetChanged();
        }*/
        //return names.toArray(new String[names.size()]);
    }
    public void onDoneClick(View v){
        View parent = (View)v.getParent();
        TextView task = (TextView)parent.findViewById(R.id.text_apartment);
        String str = task.getText().toString();
        helper.delete_request(str);
        getActive();
    }
}
