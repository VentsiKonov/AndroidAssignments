package me.vkonov.mealcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;


public class MealCalculator extends Activity implements View.OnClickListener{
    TextView dishPrice, dessertPrice, drinkPrice, totalPrice, dishQuantity, dessertQuantity;
    SeekBar drinkQuantity;
    CheckBox homeDelivery;
    float rate;
    String currency;

    void UpdatePrices(){

        String prefix;
        String postfix;
        float currentPrice, totalPrice;
        String format = getString(R.string.priceFormat);

        if(currency.equals(getString(R.string.dollar))){
            prefix = getString(R.string.dollar);
            postfix = "";
        }
        else{
            prefix = "";
            postfix = currency;
        }

        currentPrice = getResources().getInteger(R.integer.dishPriceEUR) * rate;
        totalPrice = currentPrice * Integer.parseInt(dishQuantity.getText().toString());
        dishPrice.setText(
                String.format(format, prefix, currentPrice, postfix)
        );

        currentPrice = getResources().getInteger(R.integer.dessertPriceEUR) * rate;
        totalPrice += currentPrice * Integer.parseInt(dessertQuantity.getText().toString());
        dessertPrice.setText(
                String.format(format, prefix, currentPrice, postfix)
        );

        currentPrice = getResources().getInteger(R.integer.drinkPricePerLiterEUR) * rate;
        totalPrice += currentPrice * drinkQuantity.getProgress() / 10;
        drinkPrice.setText(
                String.format(format, prefix, currentPrice, postfix)
        );

        currentPrice = getResources().getInteger(R.integer.homeDeliveryPrice) * rate;
        if(homeDelivery.isChecked())
            totalPrice += currentPrice;

        homeDelivery.setText(
                getString(R.string.lblHomeDelivery)
                + "\n"
                + String.format(format, prefix, currentPrice, postfix)
        );

        this.totalPrice.setText(
                String.format(format, prefix, totalPrice, postfix)
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_calculator);

        // Init vars
        rate = 1;
        currency = getResources().getString(R.string.euro);

        // Hook labels
        dishPrice = (TextView) findViewById(R.id.dishPrice);
        dessertPrice = (TextView) findViewById(R.id.dessertPrice);
        drinkPrice = (TextView) findViewById(R.id.drinkPrice);
        totalPrice = (TextView) findViewById(R.id.totalPrice);
        dishQuantity = (TextView) findViewById(R.id.dishQuantity);
        dessertQuantity = (TextView) findViewById(R.id.dessertQuantity);
        drinkQuantity = (SeekBar) findViewById(R.id.drinkQuantity);
        homeDelivery = (CheckBox) findViewById(R.id.homeDelivery);

        // Hook buttons
        Button b;
        b = (Button) findViewById(R.id.btnBGN);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.btnDollar);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.btnEuro);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.dishPlus);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.dishMinus);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.dessertMinus);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.dessertPlus);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.btnCalculate);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.btnAboutUs);
        b.setOnClickListener(this);

        // Init Prices
        UpdatePrices();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meal_calculator, menu);
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

    @Override
    public void onClick(View v) {
        int quantity;
        switch(v.getId()){
            case R.id.btnDollar:
                currency = getString(R.string.dollar);
                rate = getResources().getFraction(R.fraction.eurDollarER, 1, 1);
                UpdatePrices();
                break;
            case R.id.btnEuro:
                currency = getString(R.string.euro);
                rate = 1;
                UpdatePrices();
                break;
            case R.id.btnBGN:
                currency = getString(R.string.bgn);
                rate = getResources().getFraction(R.fraction.eurBgnER, 1, 1);
                UpdatePrices();
                break;
            case R.id.dishMinus:
                quantity = Integer.parseInt(dishQuantity.getText().toString());
                if(quantity > 0) {
                    dishQuantity.setText(String.valueOf(quantity - 1));
                }
                break;
            case R.id.dishPlus:
                quantity = Integer.parseInt(dishQuantity.getText().toString());
                if(quantity < 10) {
                    dishQuantity.setText(String.valueOf(quantity + 1));
                }
                break;
            case R.id.dessertPlus:
                quantity = Integer.parseInt(dessertQuantity.getText().toString());
                if(quantity < 10) {
                    dessertQuantity.setText(String.valueOf(quantity + 1));
                }
                break;
            case R.id.dessertMinus:
                quantity = Integer.parseInt(dessertQuantity.getText().toString());
                if(quantity > 0) {
                    dessertQuantity.setText(String.valueOf(quantity - 1));
                }
                break;
            case R.id.btnCalculate:
                UpdatePrices();
                break;
            case R.id.btnAboutUs:
                Intent i = new Intent(this, AboutUs.class);
                startActivity(i);
                break;
        }
    }
}
