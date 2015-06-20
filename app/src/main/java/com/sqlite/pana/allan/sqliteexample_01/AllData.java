package com.sqlite.pana.allan.sqliteexample_01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class AllData extends AppCompatActivity {

    ListView listView;
    List<MyData> listData = new ArrayList<>();
    List<String> namelist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_data);
        MyDataBaseAdapter helper = new MyDataBaseAdapter(this);

        listData = helper.viewallData();

        listView = (ListView) findViewById(R.id.listView_Data);
        for(MyData myData : listData){

            namelist.add( myData.getName());

        }
        ArrayAdapter<String>  adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,namelist);
        listView.setAdapter(adapter);
    }


}
