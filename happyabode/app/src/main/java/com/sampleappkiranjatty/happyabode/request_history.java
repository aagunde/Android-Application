package com.sampleappkiranjatty.happyabode;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class request_history extends ListActivity {
    DataBaseHelper helper = new DataBaseHelper(this);
    private  String apt;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_history);
        Bundle bundle = getIntent().getExtras();
        apt = bundle.getString("Apt_no");
        getMyRequests();
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //ItemClicked item = adapter.getItemAtPosition(position);
        super.onListItemClick(l, v, position, id);

        String keyword = (String)this.getListAdapter().getItem(position);

        Intent i = new Intent(request_history.this, description_request_tenant.class);
        Bundle b = new Bundle();
        b.putString("keyword", keyword);
        i.putExtras(b);
        startActivity(i);
    }
    public void getMyRequests() {
        String[] s;
        s = helper.myrequests(apt);
        //if (mAdapter == null) {
        mAdapter = new ArrayAdapter<>(this, R.layout.activity_request_adapter_item, R.id.text_request_item, s);
        this.setListAdapter(mAdapter);
        /*} else {
            mAdapter.clear();
            mAdapter.addAll(s);
            mAdapter.notifyDataSetChanged();
        }*/
        //return names.toArray(new String[names.size()]);
    }
}
