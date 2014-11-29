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


public class Story extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Hero hero;
    int currentChapter;

    TextView chapterTitle;
    TextView chapterText;
    Spinner userChoice;

    private void loadChapter(int id) {
        ArrayAdapter adapter = null;
        switch (id) {
            case Constants.idChapterOne:
                chapterTitle.setText(R.string.chapterOneTitle);
                chapterText.setText(R.string.chapterOneText);
                adapter = ArrayAdapter.createFromResource(this, R.array.chapterOneChoices, android.R.layout.simple_spinner_item);
                break;

            case Constants.idChapterTwo:
                chapterTitle.setText(R.string.chapterTwoTitle);
                chapterText.setText(R.string.chapterTwoText);
                adapter = ArrayAdapter.createFromResource(this, R.array.chapterTwoChoices, android.R.layout.simple_spinner_item);
                break;
        }
        if (adapter != null) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            userChoice.setAdapter(adapter);
            userChoice.setOnItemSelectedListener(this);
        }
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
        int nextChapter = currentChapter;
        switch (currentChapter) {
            case Constants.idChapterOne:
                switch (heroDecision) {
                    case 1:
                        nextChapter = Constants.idChapterTwo;

                        break;
                    case 2:
                        nextChapter = Constants.idChapterFive;

                        break;
                }
                break;
            case Constants.idChapterTwo:
                switch (heroDecision) {
                    case 1:
                        nextChapter = Constants.idChapterFour;

                        break;
                    case 2:
                        nextChapter = Constants.idChapterFive;

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

        switch (id) {
            case R.id.inventory:
                Intent inventory = new Intent(this, Inventory.class);
                inventory.putStringArrayListExtra(Constants.varItems, hero.getItems());
                startActivity(inventory);
                break;
            case R.id.stats:
                break;

        }

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
