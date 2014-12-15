package me.vkonov.testone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ProductCatalog extends ActionBarActivity implements View.OnClickListener{
    private static final int ADD_PRODUCT_REQUEST = 90;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_catalog);
        ProductDatabase.generate();
        Button add = (Button) findViewById(R.id.addProduct);
        add.setOnClickListener(this);
        Button showAll = (Button) findViewById(R.id.showAll);
        showAll.setOnClickListener(this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_catalog, menu);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_PRODUCT_REQUEST){
            if(resultCode == RESULT_OK){
                String name = data.getStringExtra("name");
                String desc = data.getStringExtra("description");
                double price = data.getDoubleExtra("price",0);
                Product p = new Product(name, desc, price);

                ProductDatabase.add(p);
                Toast.makeText(this, "Product added!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addProduct:
                Intent add = new Intent(this, AddProduct.class);
                startActivityForResult(add, ADD_PRODUCT_REQUEST);
                break;
            case R.id.showAll:
                Intent show = new Intent(this, AllProducts.class);
                startActivity(show);
                break;

        }
    }
}
