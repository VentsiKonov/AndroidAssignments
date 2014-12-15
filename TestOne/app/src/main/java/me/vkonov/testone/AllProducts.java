package me.vkonov.testone;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class AllProducts extends ActionBarActivity {
    private MyListViewAdapter adapter;
    final ArrayList<Product> all = ProductDatabase.getAll();
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        lv = ((ListView) findViewById(R.id.listView));

        adapter = new MyListViewAdapter(this, all);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AllProducts.this, all.get(position).getDescription(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_all_products, menu);
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
        if(id == R.id.searchAction){
            return onSearchRequested();
        }
        if(id == R.id.clear){
           lv.setAdapter(new MyListViewAdapter(this, all));
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Product> getResults(String query){
        ArrayList<Product> list = ProductDatabase.getAll();
        for (int i = 0; i < list.size(); i++) {
            if(!list.get(i).getName().contains(query)){
                list.remove(i);
            }
        }

        return list;
    }


    @Override
    protected void onNewIntent(Intent intent) {
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){

            String query = intent.getStringExtra(SearchManager.QUERY);

            adapter = new MyListViewAdapter(this, getResults(query));

        }
        else{
            adapter =  new MyListViewAdapter(this, ProductDatabase.getAll());
        }
        lv.setAdapter(adapter);
    }
}
