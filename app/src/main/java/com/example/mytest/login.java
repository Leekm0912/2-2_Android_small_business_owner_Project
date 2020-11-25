package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void mClick(View v){
        Intent intent;
        switch(v.getId()){
            case R.id.login:
                intent = new Intent(login.this,login_layout.class);
                startActivity(intent);
                break;
        }

    }
}
