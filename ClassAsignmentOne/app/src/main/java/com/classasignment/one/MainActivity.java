package com.classasignment.one;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button game_btn = (Button) findViewById(R.id.game_btn);
        game_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this,Game.class);
                startActivity(i);
            }
        });

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText userName = (EditText) findViewById(R.id.userName);
                String toastText = getResources().getText(R.string.hello).toString();
                if(userName.length() > 0){
                    toastText += userName.getText();
                }else{
                    toastText += getResources().getText(R.string.anonymous).toString();
                }
                EditText birthYear = (EditText) findViewById(R.id.birthYear);
                if(birthYear.length() > 0){
                    int age = 2014 - Integer.parseInt(birthYear.getText().toString());
                    toastText += getResources().getText(R.string.age);
                    toastText += age;
                }
                Toast t = Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_LONG);
                t.show();
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
}
