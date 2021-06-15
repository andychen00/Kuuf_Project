package com.example.kuuf_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.kuuf_project.Class.User;
import com.example.kuuf_project.DataBase.UserHelper;

public class Login_activity extends AppCompatActivity {

    public static final int REQUEST_CODE_REPLY = 1;
    EditText username, password;
    Button login, register;
    UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        username = findViewById(R.id.user);
        password = findViewById(R.id.pass);
        login = findViewById(R.id.loginbtn);
        register = findViewById(R.id.regisbtn);
        userHelper = new UserHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_activity.this, regis_activity.class);
                startActivityForResult(intent, REQUEST_CODE_REPLY);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().isEmpty()) {
                    Toast.makeText(Login_activity.this, "username must be filled", Toast.LENGTH_SHORT).show();

                } else if (password.getText().toString().isEmpty()) {
                    Toast.makeText(Login_activity.this, "password must be filled", Toast.LENGTH_SHORT).show();

                } else {
                    int test = userHelper.checkUsers(username.getText().toString(), password.getText().toString());
                    if (test == 0) {
                        Toast.makeText(Login_activity.this, "username and password must be registered ", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(Login_activity.this, HomeActivity.class);
                        intent.putExtra("userid", test);
                        startActivity(intent);
                        finish();
                    }
                }
            }

        });
    }

}