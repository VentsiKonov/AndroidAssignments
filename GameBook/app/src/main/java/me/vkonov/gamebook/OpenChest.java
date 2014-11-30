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
    boolean specialKey;

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
        specialKey = data.getBooleanExtra(Constants.varSpecialItem, false);
        //setResult(RESULT_CANCELED, data);
    }

    @Override
    public void onClick(View view) {
        // TODO: Extract string resources
        switch (view.getId()) {
            case R.id.leaveChest:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.imageView:
                Intent data = getIntent();
                String newItem;
                Random r = new Random();

                if (specialKey) {
                    newItem = Constants.varSpecialItem;
                } else {
                    Constants.varGameItems[] items = Constants.varGameItems.values();
                    newItem = items[r.nextInt(items.length)].toString();
                }
                if (!specialKey) {
                    if (heroItems.contains(Constants.varGameItems.Key.toString())) {
                        heroItems.remove(Constants.varGameItems.Key.toString());

                        heroItems.add(newItem);

                        Toast.makeText(this, "You opened the chest and found: \n" + newItem, Toast.LENGTH_LONG).show();
                    } else {
                        --heroFavour;
                        if (r.nextBoolean()) {
                            heroItems.add(newItem);
                            Toast.makeText(this, "You managed to break the chest and found: \n" + newItem, Toast.LENGTH_LONG).show();
                        }
                        Toast.makeText(this, "You didn't have a key! Your favour decreased to " + String.valueOf(heroFavour) + "!", Toast.LENGTH_LONG).show();

                    }
                } else {
                    int keysCount = 0;
                    for (String heroItem : heroItems) {
                        if (heroItem.equals(Constants.varGameItems.Key.toString())) {
                            ++keysCount;
                        }
                    }
                    if (keysCount >= 2) {
                        heroItems.remove(Constants.varGameItems.Key.toString());
                        heroItems.remove(Constants.varGameItems.Key.toString());

                        heroItems.add(newItem);

                        Toast.makeText(this, "You opened the chest and found: \n" + newItem, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "You didn't have enough keys! Your favour decreased to " + String.valueOf(heroFavour) + "!", Toast.LENGTH_LONG).show();
                    }
                }
                data.putExtra(Constants.varItems, heroItems);
                data.putExtra(Constants.varHeroFavour, heroFavour);
                setResult(RESULT_OK, data);
                finish();

                break;
        }
    }
}
