package net.ddns.leekm.eco_design_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

public class login_layout extends AppCompatActivity {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
    }

    public void mClick(android.view.View v){
        Intent intent;
        switch(v.getId()){
            case R.id.logout:
                intent = new Intent(login_layout.this,main_layout.class);
                startActivity(intent);
                break;
            case R.id.info1:
                intent = new Intent(login_layout.this,info1.class);
                startActivity(intent);
                break;
            case R.id.ba:
                intent = new Intent(login_layout.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.info2:
                intent = new Intent(login_layout.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.booking:
                intent = new Intent(login_layout.this,booktype.class);
                startActivity(intent);
                break;
            case R.id.board:
                intent = new Intent(login_layout.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
