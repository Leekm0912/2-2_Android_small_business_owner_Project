package net.ddns.leekm.eco_design_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;

public class main_layout extends AppCompatActivity {
    Button info1_btn;
    Button ba_btn;
    Button login_btn;
    Button add_log_btn;
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        info1_btn = findViewById(R.id.info1);
        ba_btn = findViewById(R.id.ba);
        login_btn = findViewById(R.id.login_btn);
        add_log_btn = findViewById(R.id.add_log);

        info1_btn.setOnClickListener(onClickListener);
        ba_btn.setOnClickListener(onClickListener);
        login_btn.setOnClickListener(onClickListener);
        add_log_btn.setOnClickListener(onClickListener);
    }

    android.view.View.OnClickListener onClickListener = new android.view.View.OnClickListener(){

        @Override
        public void onClick(android.view.View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.info1:
                    intent = new Intent(main_layout.this,info1.class);
                    startActivity(intent);
                    break;
                case R.id.ba:
                    intent = new Intent(main_layout.this,MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.login_btn:
                    intent = new Intent(main_layout.this,login.class);
                    startActivity(intent);
                    break;
                case R.id.add_log:
                    intent = new Intent(main_layout.this,add_log.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
