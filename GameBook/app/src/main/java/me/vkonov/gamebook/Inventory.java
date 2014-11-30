package me.vkonov.gamebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Inventory extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        TextView itemsView = (TextView) findViewById(R.id.items);
        Intent data = getIntent();
        ArrayList<String> items = data.getStringArrayListExtra(Constants.varItems);
        String viewContent = "";
        if (items != null)
            for (String item : items) {
                viewContent += item + "\n";
            }
        itemsView.setText(viewContent);

        Button goBack = (Button) findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inventory.this.finish();
            }
        });

        Button usePotion = (Button) findViewById(R.id.usePotion);
        usePotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Inventory.this, "Not implemented", Toast.LENGTH_SHORT).show();
                // TODO: Implement potions
            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.d(getLocalClassName(), "onDestroy()");
        super.onDestroy();
    }
}
