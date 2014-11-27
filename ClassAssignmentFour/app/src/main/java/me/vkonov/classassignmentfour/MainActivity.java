package me.vkonov.classassignmentfour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;


public class MainActivity extends Activity implements View.OnClickListener {

    private LinkedList<User> users;

    private User login(String email, String password){
        try{
            int index = users.indexOf(new User(email, password));
            if(index >= 0){
               return users.get(index);
            }
            else{
                throw new Exception("No such user!");
            }
        } catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private void setView(int id){
        Button b;
        setContentView(id);
        switch (id){
            case R.layout.login:
                b = (Button) findViewById(R.id.login);
                b.setOnClickListener(this);
                b = (Button) findViewById(R.id.register);
                b.setOnClickListener(this);
                break;

            case R.layout.main_user_screen:
                b = (Button) findViewById(R.id.logout);
                b.setOnClickListener(this);
                break;

            case R.layout.main_admin_screen:
                ListView lv = (ListView) findViewById(R.id.users);
                ArrayAdapter<User> adapter = new ArrayAdapter<User>(this, R.layout.list_item, users);
                lv.setAdapter(adapter);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            String email = data.getStringExtra("email");
            String password = data.getStringExtra("password");
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Email and password cannot be empty!", Toast.LENGTH_SHORT).show();
            }

            User newUser = createUser(email, password, User.Role.User);

            if(newUser != null){
                if(!name.isEmpty()){
                    newUser.setName(name);
                }
                if(!phone.isEmpty()){
                    newUser.setPhone(phone);
                }
                users.add(newUser);
                Toast.makeText(this, "Success! You can now login!", Toast.LENGTH_SHORT).show();
            }

            else{
                Toast.makeText(this, "Something is not OK!", Toast.LENGTH_SHORT).show();
            }

    }

    private User createUser(String email, String password, User.Role role){
        try {
            User newUser = new User(email, password, role);
            if(users.contains(newUser))
                throw new Exception("Such user already exists!");

            //users.add(new User(email, password, role));
            return newUser;
        }
        catch (Exception e){

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize users
        users = new LinkedList<User>();
        users.add(createUser("admin", "123", User.Role.Admin));

        setView(R.layout.login);
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
    public void onClick(View view) {
        EditText email = (EditText) findViewById(R.id.email);
        EditText pass = (EditText) findViewById(R.id.password);

        switch (view.getId()){

            case R.id.login:
                User u = login(email.getText().toString(), pass.getText().toString());
                if(u != null){
                    if(u.getRole() == User.Role.User) {
                        setView(R.layout.main_user_screen);
                    }
                    else if(u.getRole() == User.Role.Admin){
                        setView(R.layout.main_admin_screen);
                    }
                }
                break;

            case R.id.register:
                // Show register form
                Intent reg = new Intent(this, RegisterActivity.class);
                startActivityForResult(reg, 90);
                break;

            case R.id.logout:
                setView(R.layout.login);
                break;
        }
    }
}
