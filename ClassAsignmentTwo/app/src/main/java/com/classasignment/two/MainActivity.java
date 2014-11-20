package com.classasignment.two;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button) findViewById(R.id.buttonToActTwo);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.buttonToActOne);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.buttonToActThree);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.buttonCustomColor);
        b.setOnClickListener(this);
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
    public void onClick(View v) {
        Intent newIntent = null;
        switch(v.getId()){
            case R.id.buttonToActOne:
                newIntent = new Intent(MainActivity.this, ActivityOne.class);
                break;
            case R.id.buttonToActTwo:
                newIntent = new Intent(MainActivity.this, ActivityTwo.class);
                break;
            case R.id.buttonToActThree:
                newIntent = new Intent(MainActivity.this, ActivityThree.class);
                break;
            case R.id.buttonCustomColor:
                newIntent = new Intent(MainActivity.this, CustomColor.class);
                break;
        }
        if(newIntent != null) startActivity(newIntent);
    }
}
