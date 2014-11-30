package me.vkonov.gamebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;


public class Battle extends Activity implements View.OnClickListener {

    int banditStrength = new Random().nextInt(3) + 1;
    int heroEnergy;
    ArrayList<String> heroItems;
    Button loot, skip;
    Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        loot = (Button) findViewById(R.id.fight);
        loot.setOnClickListener(this);
        loot = (Button) findViewById(R.id.loot);
        loot.setOnClickListener(this);
        skip = (Button) findViewById(R.id.skip);
        skip.setOnClickListener(this);

        data = getIntent();
        heroItems = data.getStringArrayListExtra(Constants.varItems);
        heroEnergy = data.getIntExtra(Constants.varHeroEnergy, 10);
        if (heroItems.contains(Constants.varGameItems.Sword.toString())) {
            banditStrength -= 1;
        }


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.loot:
                Constants.varGameItems[] allItems = Constants.varGameItems.values();
                String newItem = allItems[new Random().nextInt(allItems.length)].toString();
                heroItems.add(newItem);
                data.putStringArrayListExtra(Constants.varItems, heroItems);
                setResult(RESULT_OK, data);
                finish();
                break;
            case R.id.fight:
                heroEnergy -= banditStrength;
                data.putExtra(Constants.varHeroEnergy, heroEnergy);
                loot.setVisibility(View.VISIBLE);
                skip.setVisibility(View.VISIBLE);
                break;
            case R.id.skip:
                setResult(RESULT_OK, data);
                finish();
                break;
        }
    }
}
