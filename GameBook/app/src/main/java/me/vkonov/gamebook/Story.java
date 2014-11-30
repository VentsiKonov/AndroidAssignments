package me.vkonov.gamebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;


public class Story extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Hero hero;
    int currentChapter;

    TextView chapterTitle;
    TextView chapterText;
    Spinner userChoice;

    private void loadChapter(int id) {

        chapterTitle.setText(Constants.chapters[id - 1].titleResource);
        chapterText.setText(Constants.chapters[id - 1].textResource);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, Constants.chapters[id - 1].choicesResource, android.R.layout.simple_spinner_item);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userChoice.setAdapter(adapter);
        userChoice.setOnItemSelectedListener(this);

        currentChapter = id;
    }

    private void setView(int id) {
        setContentView(id);
        Button b;
        switch (id) {
            case R.layout.welcome_screen:
                b = ((Button) findViewById(R.id.beginGame));
                b.setOnClickListener(this);
                break;

            case R.layout.activity_story:
                chapterTitle = ((TextView) findViewById(R.id.chapterTitle));
                chapterText = ((TextView) findViewById(R.id.chapterText));
                userChoice = ((Spinner) findViewById(R.id.userChoice));

                break;

        }
    }

    private void handleHeroDecision(int heroDecision) {
        // TODO: This method needs a lot more thought, these switches are ridiculous
        Random random = new Random();

        Intent i = new Intent(this, OpenChest.class);
        i.putStringArrayListExtra(Constants.varItems, hero.getItems());
        i.putExtra(Constants.varHeroFavour, hero.getStats().get(Constants.varHeroFavour));

        if (random.nextInt(10) < hero.getStats().get(Constants.varHeroFavour)) {
            startActivityForResult(i, Constants.varChestRequestCode);
        }
        int nextChapter = currentChapter;

        switch (currentChapter) {
            case 1: // Constants.chapters[0].ID:
                switch (heroDecision) {
                    case 1:
                        nextChapter = Constants.chapters[1].ID;
                        break;
                    case 2:
                        nextChapter = Constants.chapters[2].ID;
                        break;
                }
                break;
            case 2:
                switch (heroDecision) {
                    case 1:
                        Intent battle = new Intent(this, Battle.class);
                        battle.putStringArrayListExtra(Constants.varItems, hero.getItems());
                        battle.putExtra(Constants.varHeroEnergy, hero.getStats().get(Constants.varHeroEnergy));
                        startActivityForResult(battle, Constants.varBattleRequestCode);
                        nextChapter = Constants.chapters[6].ID;
                        break;
                    case 2:
                        if (random.nextInt(10) < hero.getStats().get(Constants.varHeroFavour)) {
                            nextChapter = 0;
                            startActivity(new Intent(this, Death.class));
                            finish();
                        } else {
                            nextChapter = Constants.chapters[5].ID;
                        }
                        break;
                }
                break;
            case 3:
                switch (heroDecision) {
                    case 1:
                        ArrayList<String> heroItems = hero.getItems();
                        Constants.varGameItems[] items = Constants.varGameItems.values();
                        String newItem = items[random.nextInt(items.length)].toString();
                        heroItems.add(newItem);
                        hero.setItems(heroItems);
                        Toast.makeText(this, "You gained a new item!", Toast.LENGTH_SHORT).show();
                        nextChapter = Constants.chapters[7].ID;
                        break;
                    case 2:
                        hero.setStrength(hero.getStats().get(Constants.varHeroStrength) - 1);
                        nextChapter = Constants.chapters[3].ID;
                        break;
                }
                break;
            case 4:
                startActivityForResult(i, Constants.varChestRequestCode);
                switch (heroDecision) {
                    case 1:
                        nextChapter = Constants.chapters[1].ID;
                        break;
                    case 2:
                        nextChapter = Constants.chapters[7].ID;
                        break;
                }
                break;
            case 5:
                switch (heroDecision) {
                    // Merchant? some day
                    case 1:
                        nextChapter = Constants.chapters[1].ID;
                        break;
                    case 2:
                        nextChapter = Constants.chapters[2].ID;
                        break;
                }
                break;
            case 6:
                startActivityForResult(i, Constants.varChestRequestCode);
                switch (heroDecision) {
                    case 1:
                        nextChapter = Constants.chapters[6].ID;
                        break;
                }
                break;
            case 7:
                switch (heroDecision) {
                    case 1:
                        nextChapter = Constants.chapters[7].ID;
                        break;
                    case 2:
                        if (hero.getItems().contains(Constants.varSpecialItem))
                            nextChapter = Constants.chapters[8].ID;
                        else
                            Toast.makeText(this, "You don\'t have the special key!", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            case 8:
                switch (heroDecision) {
                    case 1:
                        nextChapter = Constants.chapters[4].ID;
                        break;
                    case 2:
                        if (random.nextInt(7) < hero.getStats().get(Constants.varHeroFavour)) {
                            i.putExtra(Constants.varSpecialItem, true);
                            startActivityForResult(i, Constants.varChestRequestCode);
                        }
                        nextChapter = currentChapter;
                        break;
                }
                break;
            case 9:
                switch (heroDecision) {
                    case 1:
                        // Reinitialize game
                        nextChapter = Constants.chapters[0].ID;
                        hero.initialize();

                        break;
                }
                break;
        }

        loadChapter(nextChapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init
        hero = Hero.getInstance();
        currentChapter = 0;
        setView(R.layout.welcome_screen);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (hero.getName().isEmpty())
            return false;

        Intent i = null;
        switch (id) {
            case R.id.inventory:
                i = new Intent(this, Inventory.class);
                i.putStringArrayListExtra(Constants.varItems, hero.getItems());

                break;
            case R.id.stats:
                i = new Intent(this, Stats.class);
                i.putExtra(Constants.varHeroName, hero.getName());
                for (Map.Entry<String, Integer> stat : hero.getStats().entrySet()) {
                    i.putExtra(stat.getKey(), stat.getValue());
                }
//                HashMap<String, Integer> stats = hero.getStats();
//                i.putExtra(Constants.varHeroFavour, stats.get(Constants.varHeroFavour));
//                i.putExtra(Constants.varHeroEnergy, stats.get(Constants.varHeroEnergy));
//                i.putExtra(Constants.varHeroStrength, stats.get(Constants.varHeroStrength));
                break;

        }
        if (i != null)
            startActivity(i);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.beginGame:
                EditText nameField = ((EditText) findViewById(R.id.nameField));
                String name = nameField.getText().toString().trim();
                if (!name.isEmpty()) {
                    hero.setName(name);
                    setView(R.layout.activity_story);
                    currentChapter = 1;
                    loadChapter(currentChapter);

                    Log.d(getLocalClassName(), "Player name: " + hero.getName());
                } else {
                    Toast.makeText(this, R.string.userInputNameToast, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // TODO: Extract string resources
        if (i != 0) {

            String selection = ((TextView) view).getText().toString();
            Log.d(getLocalClassName(), "Selected id: " + String.valueOf(i));
            Log.d(getLocalClassName(), "Selected: " + selection);

            Toast.makeText(this, "You decided: " + selection, Toast.LENGTH_SHORT).show();
            handleHeroDecision(i);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Do nothing
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO: Extract string resources
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "You decided to leave the chest behind!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (resultCode != RESULT_OK) {
            Log.d(getLocalClassName(), "Activity for result is not ok!\nIntent data: \n" + (data != null ? data.toString() : "null"));
            Toast.makeText(this, "Sorry, something is wrong!", Toast.LENGTH_SHORT).show();
        }

        switch (requestCode) {
            case Constants.varChestRequestCode:
                if (data != null) {
                    ArrayList<String> heroItems = data.getStringArrayListExtra(Constants.varItems);
                    hero.setItems(heroItems);
                    hero.setFavour(data.getIntExtra(Constants.varHeroFavour, 3));
                }
                break;
            case Constants.varBattleRequestCode:
                if (data != null) {
                    ArrayList<String> heroItems = data.getStringArrayListExtra(Constants.varItems);
                    hero.setItems(heroItems);
                    hero.setEnergy(data.getIntExtra(Constants.varHeroEnergy, 3));
                }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.varCurrentChapter, currentChapter);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.varCurrentChapter)) {
            currentChapter = savedInstanceState.getInt(Constants.varCurrentChapter);
            if (currentChapter != 0) {
                setView(R.layout.activity_story);
                loadChapter(currentChapter);
            } else {
                setView(R.layout.welcome_screen);
            }
        }
        super.onRestoreInstanceState(savedInstanceState);

    }
}
