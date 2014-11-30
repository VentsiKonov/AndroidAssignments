package me.vkonov.gamebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class OpenChest extends Activity implements View.OnClickListener {

    int heroFavour;
    ArrayList<String> heroItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_chest);
        Intent data = getIntent();

        Button back = (Button) findViewById(R.id.leaveChest);
        back.setOnClickListener(this);
        ImageView chest = (ImageView) findViewById(R.id.imageView);
        chest.setOnClickListener(this);
        heroFavour = data.getIntExtra(Constants.varHeroFavour, 0);
        heroItems = data.getStringArrayListExtra(Constants.varItems);
        //setResult(RESULT_CANCELED, data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leaveChest:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.imageView:
                Intent data = getIntent();
                if (heroItems.contains(Constants.varGameItems.Key.toString())) {
                    heroItems.remove(Constants.varGameItems.Key.toString());

                    Constants.varGameItems[] items = Constants.varGameItems.values();
                    Random r = new Random();
                    String newItem = items[r.nextInt(items.length)].toString();
                    heroItems.add(newItem);

                    Toast.makeText(this, "You have a new item in your inventory: \n" + newItem, Toast.LENGTH_LONG).show();
                } else {
                    --heroFavour;
                    Toast.makeText(this, "You didn't have a key! Your favour decreased!", Toast.LENGTH_LONG).show();

                }
                data.putExtra(Constants.varItems, heroItems);
                data.putExtra(Constants.varHeroFavour, heroFavour);
                setResult(RESULT_OK, data);
                finish();

                break;
        }
    }
}
