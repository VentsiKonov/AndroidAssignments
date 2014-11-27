package me.vkonov.classassignmentfour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends Activity implements View.OnClickListener {

    private EditText email;
    private EditText password;
    private EditText name;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);


        Button register = (Button) this.findViewById(R.id.register);
        register.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
    public void onClick(View view) {
//        EditText email = (EditText) findViewById(R.id.email);
//        EditText password = (EditText) findViewById(R.id.password);
//        EditText name = (EditText) findViewById(R.id.name);
//        EditText phone = (EditText) findViewById(R.id.phone);

        if(email.getText().toString().isEmpty()
                || password.getText().toString().isEmpty()){
            Toast.makeText(RegisterActivity.this, "Email and Password cannot be empty!", Toast.LENGTH_LONG).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra("email", email.getText().toString());
        data.putExtra("password", password.getText().toString());
        data.putExtra("name", name.getText().toString());
        data.putExtra("phone", phone.getText().toString());

        setResult(RESULT_OK, data);

        //RegisterActivity.this.getParent().setResult(RESULT_OK, data);

        finish();
    }
}
