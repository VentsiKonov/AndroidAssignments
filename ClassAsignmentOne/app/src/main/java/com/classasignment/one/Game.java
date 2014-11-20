package com.classasignment.one;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Game extends Activity {
    boolean init = false;

    private boolean checkWin(AdapterView v){
        String state = ((TextView) v.getChildAt(0).findViewById(R.id.txt)).getText().toString();
        for (int i = 1; i < 25; i++){
            if(((TextView) v.getChildAt(i).findViewById(R.id.txt)).getText() != state)
                return false;
        }
        return true;
    }

    private void init(AdapterView v){
        final String state1 = getResources().getString(R.string.state_1);
        TextView current;
        for(int i=0; i<v.getChildCount(); ++i){
            current = (TextView) v.getChildAt(i).findViewById(R.id.txt);
            if(current.getText() == state1){
                current.setBackgroundColor(Color.RED);
            }
            else{
                current.setBackgroundColor(Color.GREEN);
            }
        }
    }

    private void flip(TextView t, String state_1, String state_2, int state1_c, int state2_c){
        if (t.getText() == state_2) {
            t.setText(state_1);
            t.setBackgroundColor(state1_c);
        } else {
            t.setText(state_2);
            t.setBackgroundColor(state2_c);
        }
    }

    private void flipNeighbours(AdapterView<?> parent, int position, String state_1, String state_2, int state1_c, int state2_c){
        TextView t;
        if (position % 5 != 0) {
            t = (TextView) parent.getChildAt(position - 1).findViewById(R.id.txt);
            flip(t, state_1, state_2, state1_c, state2_c);
        }

        if (position > 4) {
            t = (TextView) parent.getChildAt(position - 5).findViewById(R.id.txt);
            flip(t, state_1, state_2, state1_c, state2_c);
        }

        if (position < 20) {
            t = (TextView) parent.getChildAt(position + 5).findViewById(R.id.txt);
            flip(t, state_1, state_2, state1_c, state2_c);
        }

        if ((position + 1) % 5 != 0) {
            t = (TextView) parent.getChildAt(position + 1).findViewById(R.id.txt);
            flip(t, state_1, state_2, state1_c, state2_c);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GridView grid = (GridView) findViewById(R.id.gridView);
        final TextView hit_count = (TextView) findViewById(R.id.hit_count);
        final String[] symbols = new String[25];
        final String state_2 = getResources().getString(R.string.state_2);
        final String state_1 =  getResources().getString(R.string.state_1);
        final int state1_c = Color.RED;
        final int state2_c = Color.GREEN;
        Random rand = new Random();
        for (int i = 0; i < 25; i++){
            symbols[i] = rand.nextBoolean() ? state_2 : state_1;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item, symbols);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!init) {
                    init(parent);
                    init = true;
                }
                TextView t;
                t = (TextView) view.findViewById(R.id.txt);
                flip(t, state_1, state_2, state1_c, state2_c);

                flipNeighbours(parent, position, state_1, state_2, state1_c, state2_c);
                hit_count.setText(String.valueOf(Integer.parseInt(hit_count.getText().toString()) + 1));
                if (checkWin(parent)) {
                    Toast toast = Toast.makeText(Game.this, "You win!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        //Toast debug = Toast.makeText(this, "debug", Toast.LENGTH_LONG);
        //debug.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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
