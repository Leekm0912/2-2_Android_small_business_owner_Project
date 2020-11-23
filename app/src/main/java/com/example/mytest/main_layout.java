package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main_layout extends AppCompatActivity {
    Button info1_btn;
    Button info2_btn;
    Button ba_btn;
    Button login_btn;
    Button add_log_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        info1_btn = findViewById(R.id.info1);
        info2_btn = findViewById(R.id.info2);
        ba_btn = findViewById(R.id.ba);
        login_btn = findViewById(R.id.login);
        add_log_btn = findViewById(R.id.add_log);

        info1_btn.setOnClickListener(onClickListener);
        info2_btn.setOnClickListener(onClickListener);
        ba_btn.setOnClickListener(onClickListener);
        login_btn.setOnClickListener(onClickListener);
        add_log_btn.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.info1:
                    break;
                case R.id.ba:
                    Bundle bundle = new Bundle();
                    bundle.putString("ba","인테리어이야기");
                    Intent intent = new Intent(main_layout.this,MainActivity.class);
                    intent.putExtra("baBundle",bundle);
                    startActivity(intent);
                    break;
                case R.id.info2:
                    break;
                case R.id.login:
                    break;
                case R.id.add_log:
                    break;
            }
        }
    };
}
