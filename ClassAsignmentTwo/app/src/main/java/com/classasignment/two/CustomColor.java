package com.classasignment.two;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;


public class CustomColor extends Activity implements SeekBar.OnSeekBarChangeListener{

    TextView red_val, green_val, blue_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_color);

        SeekBar red = (SeekBar) findViewById(R.id.color_red);
        SeekBar green = (SeekBar) findViewById(R.id.color_green);
        SeekBar blue = (SeekBar) findViewById(R.id.color_blue);

        red_val = (TextView) findViewById(R.id.value_red);
        green_val = (TextView) findViewById(R.id.value_green);
        blue_val = (TextView) findViewById(R.id.value_blue);

        red.setOnSeekBarChangeListener(this);
        green.setOnSeekBarChangeListener(this);
        blue.setOnSeekBarChangeListener(this);

        Button b = (Button) findViewById(R.id.buttonSetColor);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);
                int red, green, blue;
                red = Integer.parseInt(red_val.getText().toString());
                green = Integer.parseInt(green_val.getText().toString());
                blue = Integer.parseInt(blue_val.getText().toString());
                String red_s, green_s, blue_s;
                red_s = Integer.toHexString(red).toUpperCase();
                green_s = Integer.toHexString(green).toUpperCase();
                blue_s = Integer.toHexString(blue).toUpperCase();
                if(red_s.length() < 2)
                    red_s = "0"+red_s;
                if(green_s.length() < 2)
                    green_s = "0"+green_s;
                if(blue_s.length() < 2)
                    blue_s = "0"+blue_s;

                String color = "#" + red_s+ green_s + blue_s;

                //Toast.makeText(CustomColor.this, color, Toast.LENGTH_LONG).show();
                layout.setBackgroundColor(Color.parseColor(color));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom_color, menu);
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
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch(seekBar.getId()){
            case R.id.color_red:
                red_val.setText(String.valueOf(progress));
                break;
            case R.id.color_blue:
                blue_val.setText(String.valueOf(seekBar.getProgress()));
                break;
            case R.id.color_green:
                green_val.setText(String.valueOf(seekBar.getProgress()));
                break;
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
