package com.example.kuuf_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kuuf_project.Class.User;
import com.example.kuuf_project.DataBase.UserHelper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Profile extends AppCompatActivity {

    TextView p_username, p_gender, p_phone, p_wallet, p_date;
    Button confirm;
    RadioGroup topGroup;
    RadioButton topup;
    EditText p_password;
    int userid;
    UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        p_username = findViewById(R.id.p_username);
        p_gender = findViewById(R.id.p_gender);
        p_phone = findViewById(R.id.p_phone);
        p_wallet = findViewById(R.id.p_wallet);
        p_date = findViewById(R.id.p_date);
        p_password = findViewById(R.id.p_password);
        confirm = findViewById(R.id.confirm);
        topGroup = findViewById(R.id.topGroup);
        userHelper = new UserHelper(this);

        topGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup topGroup, int topupid) {
                topup = topGroup.findViewById(topupid);
            }
        });


        Intent intent = getIntent();
        userid = intent.getIntExtra("userid",0);
        Toast.makeText(this, "" + userid, Toast.LENGTH_SHORT).show();
        User profile = userHelper.getUser(1);
        p_username.setText(profile.getUsername());
        p_gender.setText(profile.getGender());
        p_phone.setText(profile.getPhone_number());
        p_date.setText(profile.getDate_birth());
        String rupiah = (String.format("%,d", profile.getBalance())).replace(',', '.');
        p_wallet.setText("Rp " + rupiah);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wallet = profile.getBalance();
                if (!profile.getPassword().matches(p_password.getText().toString())) {
                    Toast.makeText(Profile.this, "Input password is wrong", Toast.LENGTH_SHORT).show();
                } else if (topup == null) {
                    Toast.makeText(Profile.this, "select the top up nominal", Toast.LENGTH_SHORT).show();
                } else {
                    if (topup.getText().toString().matches("Rp 250.000")) {
                        wallet = wallet + 250000;
                    } else if (topup.getText().toString().matches("Rp 500.000")) {
                        wallet = wallet + 500000;
                    } else {
                        wallet = wallet + 1000000;
                    }
                    userHelper.UpdateNominal(userid, wallet);
                    Intent intent = new Intent(Profile.this, HomeActivity.class);
                    intent.putExtra("p_userid", userid);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}