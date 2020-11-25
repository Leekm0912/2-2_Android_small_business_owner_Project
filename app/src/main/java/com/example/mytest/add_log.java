package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class add_log extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_log);
    }

    public void mClick(View v){
        Intent intent;
        switch(v.getId()){
            case R.id.add_log:
                intent = new Intent(add_log.this,main_layout.class);
                startActivity(intent);
                break;
        }
    }
}
