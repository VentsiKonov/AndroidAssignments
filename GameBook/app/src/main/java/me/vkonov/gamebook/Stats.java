package me.vkonov.gamebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Stats extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        // TODO: Make name changeable
        EditText nameField = (EditText) findViewById(R.id.nameField);
        TextView favourField = (TextView) findViewById(R.id.favourField);
        TextView energyField = (TextView) findViewById(R.id.energyField);
        TextView strengthField = (TextView) findViewById(R.id.strengthField);

        Intent i = getIntent();
        Bundle data = i.getExtras();
        if (data != null) {
            Log.d(getLocalClassName(), data.toString());
            nameField.setText(data.getString(Constants.varHeroName));
            favourField.setText(String.valueOf(data.getInt(Constants.varHeroFavour)));
            energyField.setText(String.valueOf(data.getInt(Constants.varHeroEnergy)));
            strengthField.setText(String.valueOf(data.getInt(Constants.varHeroStrength)));
        }

        Button close = (Button) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
