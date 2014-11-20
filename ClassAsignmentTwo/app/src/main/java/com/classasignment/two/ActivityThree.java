package com.classasignment.two;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ActivityThree extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        Button b = (Button) findViewById(R.id.buttonToActOne3);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.buttonToActTwo3);
        b.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_three, menu);
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
            case R.id.buttonToActOne3:
                newIntent = new Intent(ActivityThree.this, ActivityOne.class);
                break;
            case R.id.buttonToActTwo3:
                newIntent = new Intent(ActivityThree.this, ActivityTwo.class);
        }
        if(newIntent != null){
            startActivity(newIntent);
            finish();
        }
    }
}
