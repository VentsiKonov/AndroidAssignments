package com.classasignment.one;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Game extends Activity {
    String[] symbols;
    String state_2;
    String state_1;

    private boolean checkWin(){
        for (int i = 1; i < symbols.length; i++){
            if(!symbols[i].equals(symbols[0]))
                return false;
        }
        return true;
    }

    private void flip(int position){
        symbols[position] = symbols[position].equals(state_1) ? state_2 : state_1;
    }

    private void flipNeighbours(int position){
        if (position % 5 != 0) {
            flip(position - 1);
        }

        if (position > 4) {
            flip(position - 5);
        }

        if (position < 20) {
            flip(position + 5);
        }

        if ((position + 1) % 5 != 0) {
            flip(position + 1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GridView grid = (GridView) findViewById(R.id.gridView);
        final TextView hit_count = (TextView) findViewById(R.id.hit_count);
        symbols = new String[25];
        state_1 = getResources().getString(R.string.state_1);
        state_2 = getResources().getString(R.string.state_2);
        Random rand = new Random();
        for (int i = 0; i < symbols.length; i++){
            symbols[i] = rand.nextBoolean() ? state_2 : state_1;
        }
        final TileAdapter adapter = new TileAdapter(this, symbols);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                flip(position);
                flipNeighbours(position);
                adapter.notifyDataSetChanged();
                hit_count.setText(String.valueOf(Integer.parseInt(hit_count.getText().toString()) + 1));

                if (checkWin()) {
                    Toast toast = Toast.makeText(Game.this, "You win!", Toast.LENGTH_LONG);
                    toast.show();
                    finish();
                }
            }
        });

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
