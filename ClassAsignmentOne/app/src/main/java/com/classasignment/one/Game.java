package com.classasignment.one;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Game extends Activity {
    int[] matrix;
    int size;
    TileAdapter adapter;
    GridView grid;
    TextView hit_count;
    static Random rand = new Random();

    private boolean checkWin(){
        for (int i = 1; i < matrix.length; i++){
            if(matrix[0] == matrix[i])
                return false;
        }
        return true;
    }

    private void flip(int position){
        matrix[position] = matrix[position] == 0 ? 1 : 0;
    }

    private void flipNeighbours(int position){
        if (position % size != 0) {
            flip(position - 1);
        }

        if (position > (size - 1)) {
            flip(position - size);
        }

        if (position < size*size - size) {
            flip(position + size);
        }

        if ((position + 1) % size != 0) {
            flip(position + 1);
        }
    }

    private void init(){
        matrix = new int[size*size];
        for (int i = 0; i < matrix.length; i++){
            matrix[i] = rand.nextBoolean() ? 1 : 0;
        }
    }

    private void reset(){
        init();
        adapter.reinit(matrix);
        grid.setNumColumns(size);
        grid.invalidateViews();
        hit_count.setText("0");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        grid = (GridView) findViewById(R.id.gridView);
        hit_count = (TextView) findViewById(R.id.hit_count);
        size = getResources().getInteger(R.integer.size);

        init();

        adapter = new TileAdapter(this, matrix);
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
                }
            }
        });

        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
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
        switch (id) {
            case R.id.size_5:
                size = 5;
                break;
            case R.id.size_6:
                size = 6;
                break;
            case R.id.size_7:
                size = 7;
                break;
            case R.id.size_8:
                size = 8;
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        reset();

        return true;

    }
}
