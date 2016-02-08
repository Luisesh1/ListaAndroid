package com.reser.luisesh1.lista;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.reser.luisesh1.lista.Models.Elements;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    Realm realm;
    ListView lista;
    RealmResults<Elements> results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ExpandableListAdapter listAdapter;
        Vector<String> Header = new Vector<String>();
        Vector<String> Child = new Vector<String>();

        lista = (ListView) findViewById(R.id.expandableListView);
        realm = Realm.getInstance(getApplicationContext());
        recargar();
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) lista.getItemAtPosition(position);
                Intent i = new Intent(getApplicationContext(),AddActivity.class);
                i.putExtra("id",results.get(position).getId());
                i.putExtra("exist",true);
                startActivity(i);
            }

        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                i.putExtra("exist",false);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recargar();
    }

    public void recargar() {
        results = realm.where(Elements.class).findAll();
        Toast.makeText(getApplicationContext(), "" + results.size(), Toast.LENGTH_SHORT).show();
        Vector<String> values = new Vector<String>();
        for (int x = 0; x < results.size(); x++) {
            values.add(results.get(x).getTitulo().toString());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        lista.setAdapter(adapter);
    }
}
