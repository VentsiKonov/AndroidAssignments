package me.vkonov.classassignmentfive;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Random;


public class ProgramaticalLayout extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programatical_layout);

        TableLayout innerTable, tl = new TableLayout(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 1);
        TextView tv;
        TableRow r;
        Random rand = new Random();
        TableRow innerR;

        tl.setLayoutParams(params);
        for (int i = 0; i < 3; i++) {
            r = new TableRow(this);
            r.setLayoutParams(params);
                tv = new TextView(this);
                tv.setLayoutParams(params);
                tv.setBackgroundColor(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
            r.addView(tv);
                innerTable = new TableLayout(this);
                innerTable.setLayoutParams(params);
                for (int j = 0; j < 2; j++) {
                    innerR = new TableRow(this);
                    innerR.setLayoutParams(params);
                    for (int k = 0; k < 2; k++) {
                        tv = new TextView(this);
                        tv.setLayoutParams(params);
                        tv.setBackgroundColor(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
                        innerR.addView(tv);
                    }
                    innerTable.addView(innerR);
                }
            r.addView(innerTable);
        tl.addView(r);
        }

        setContentView(tl);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_programatical_layout, menu);
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
