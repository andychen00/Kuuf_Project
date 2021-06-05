package com.example.kuuf_project;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kuuf_project.Class.User;
import com.example.kuuf_project.DataBase.UserHelper;

public class regis_activity extends AppCompatActivity {

    EditText username, password, confirm_password, phone;
    Button login, register, date_birth;
    RadioGroup genderGroup;
    RadioButton gender;
    CheckBox checkBox;
    DatePicker picker;
    UserHelper userHelper;

    public static Boolean isAlphaNumeric(String password)
    {
        for (int i = 0; i < password.length(); i++)
        {
            char test = password.charAt(i);
            if (!(test >= 'A' && test <= 'Z') && !(test >= 'a' && test <= 'z') && !(test >= '0' && test <= '9')) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public static Boolean isNumberic(String phone)
    {
        for (int i = 0; i < phone.length(); i++)
        {
            char test = phone.charAt(i);
            if (!(test >= '0' && test <= '9')) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regis_page);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm);
        phone = findViewById(R.id.number);
        register = findViewById(R.id.register);
        genderGroup = findViewById(R.id.radio);
        checkBox = findViewById(R.id.checkbox);
        date_birth = findViewById(R.id.Btndate);
        userHelper = new UserHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.length() < 6 || username.length() > 12) {
                    Toast.makeText(regis_activity.this, "username must be between 6 and 12 characters", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 9 || isAlphaNumeric(password.getText().toString()).equals(false)) {
                    Toast.makeText(regis_activity.this, "password must be more than 8 characters and contain alphanumeric", Toast.LENGTH_SHORT).show();
                } else if (!confirm_password.getText().toString().matches(password.getText().toString())) {
                    Toast.makeText(regis_activity.this, "confirm password must be the same with password", Toast.LENGTH_SHORT).show();
                } else if (phone.length() < 10 || phone.length() > 12 || isNumberic(phone.getText().toString()).equals(false)) {
                    Toast.makeText(regis_activity.this, "phone number must be between 10 and 12 digits and contain only numbers", Toast.LENGTH_SHORT).show();
                } else if (picker == null) {
                    Toast.makeText(regis_activity.this, "Date of Birth must be filled", Toast.LENGTH_SHORT).show();
                } else if (gender == null) {
                    Toast.makeText(regis_activity.this, "gender must be selected", Toast.LENGTH_SHORT).show();
                } else if (!checkBox.isChecked()) {
                    Toast.makeText(regis_activity.this, "please agree for terms and condition", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(1,username.getText().toString(), password.getText().toString(), phone.getText().toString(), date_birth.getText().toString(), gender.getText().toString(),0);

                    userHelper.insertUser(user);
                    Intent intent = new Intent(regis_activity.this, Login_activity.class);
                    finish();
                }
            }
        });

        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int genderid) {
                gender = radioGroup.findViewById(genderid);
            }
        });

        date_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(regis_activity.this);
                View view = getLayoutInflater().inflate(R.layout.date_picker, null);
                picker = view.findViewById(R.id.date);
                builder.setView(view);
                builder.setTitle("Date of Birth");
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        date_birth.setText(picker.getDayOfMonth() + "/" + (picker.getMonth() + 1) + "/" + picker.getYear());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create();
                builder.show();
            }
        });

//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    Intent intent = new Intent(regis_activity.this, Login_activity.class);
//                    startActivity(intent);
//                } finally {
//                    finish();
//                }
//            }
//        });

    }
}
