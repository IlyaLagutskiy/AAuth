package com.example.aauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.file.Files;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText login;
    EditText password;
    Button button;
    int remindAttempts = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.editLogin);
        password = findViewById(R.id.editPassword);
        button = findViewById(R.id.btnLogin);
        button.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        login.setText("");
        password.setText("");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin)
        {
            String loginText = login.getText().toString();
            String passwordText = password.getText().toString();
            Log.d("MLOG", "login: " + loginText);
            Log.d("MLOG", "password: " + loginText);
            if (loginText.equals("admin") && passwordText.equals("admin")){
                Intent intent = new Intent(this, Second.class);
                startActivity(intent);
            }
            else {
                remindAttempts--;
                Toast.makeText(getApplicationContext(), "Неправильные данные!\n Попыток осталось: " + remindAttempts,Toast.LENGTH_SHORT).show();
                password.setText("");
            }
            if(remindAttempts == 0){
                button.setEnabled(false);
                login.setEnabled(false);
                password.setEnabled(false);
                login.setText("");
                login.setHint("You Are Blocked");
                login.setHintTextColor(Color.RED);
                remindAttempts = 3;
            }

        }
    }
}
