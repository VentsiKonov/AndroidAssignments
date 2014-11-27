package me.vkonov.classassignmentthree;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Stack;


public class Calculator extends Activity implements View.OnClickListener {

    String TAG = getClass().getSimpleName();
    EditText field;
    boolean lock = false;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, plus, equals, minus, divide, multiply;
    Stack<String> operations;
    Stack<Long> numbers;

    // Calculates operations.top() for two arguments from numbers
    private void calculate() throws Exception{
        if(numbers.size() < 2){
            return;
        }
        String op = operations.pop();
        Long rhs = numbers.pop();
        Long lhs = numbers.pop();
        Log.d(TAG, lhs.toString() + op + rhs.toString());
        if(op.equals("+")){
            numbers.push(lhs + rhs);
        }
        else if(op.equals("-")){
            numbers.push(lhs - rhs);
        }
        else if(op.equals("*")){
            numbers.push(lhs * rhs);
        }
        else if(op.equals("/")){
            if(rhs == 0){
                numbers.push(lhs);
                numbers.push(rhs);
                operations.push(op);
                throw new Exception("Division by zero!");
            }
            numbers.push(lhs / rhs);
        }

        lock = true;
    }

    private void handleOperation(String operation){

        if(!field.getText().toString().isEmpty())
            numbers.push(Long.parseLong(field.getText().toString()));

        if(!operations.empty()){
            try{
                calculate();
                field.setText(String.valueOf(numbers.peek()));
            }catch (Exception ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
        }

        if(!operation.equals("=")){
            if(!lock)
                operations.push(operation);
            else lock = true;
            field.setText("");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        operations = new Stack<String>();
        numbers = new Stack<Long>();
        Log.wtf(TAG, "onCreate");

        field = (EditText) findViewById(R.id.field);
        field.setTypeface(Typeface.createFromAsset(getAssets(), "digital-7.ttf"));

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        b0 = (Button) findViewById(R.id.b0);
        minus = (Button) findViewById(R.id.minus);
        plus = (Button) findViewById(R.id.plus);
        equals = (Button) findViewById(R.id.equals);
        multiply = (Button) findViewById(R.id.multiply);
        divide = (Button) findViewById(R.id.divide);
        Button clear = (Button) findViewById(R.id.clear);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b0.setOnClickListener(this);
        minus.setOnClickListener(this);
        plus.setOnClickListener(this);
        equals.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        clear.setOnClickListener(this);


    }

    @Override
    protected void onPause() {
        Log.wtf(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.wtf(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {

        Log.wtf(TAG, "onDestroy");
        super.onDestroy();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.wtf(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.wtf(TAG, "onResume");
    }

    @Override

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        Log.wtf(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        Log.wtf(TAG, "onRestoreInstanceState");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        //if(del) {
        //    field.setText("");
        //    del = false;
        //}else{
            if(!field.getText().toString().isEmpty()){
                if(field.getText().toString().startsWith("0")){
                    field.setText(field.getText().toString().substring(1));
                }
            }
        //}
        switch (v.getId()) {
            case R.id.b0:
                field.append("0");
                break;
            case R.id.b1:
                field.append("1");
                break;
            case R.id.b2:
                field.append("2");
                break;
            case R.id.b3:
                field.append("3");
                break;
            case R.id.b4:
                field.append("4");
                break;
            case R.id.b5:
                field.append("5");
                break;
            case R.id.b6:
                field.append("6");
                break;
            case R.id.b7:
                field.append("7");
                break;
            case R.id.b8:
                field.append("8");
                break;
            case R.id.b9:
                field.append("9");
                break;
            case R.id.plus:
                handleOperation("+");
                break;
            case R.id.minus:
                handleOperation("-");
                break;
            case R.id.multiply:
                handleOperation("*");
                break;
            case R.id.divide:
                handleOperation("/");
                break;
            case R.id.equals:
                handleOperation("=");
                break;
            case R.id.clear:
                field.setText("");
                operations.clear();
                numbers.clear();
                break;
        }
    }
}
