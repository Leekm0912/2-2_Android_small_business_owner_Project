package net.ddns.leekm.eco_design_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.TextView;

public class login_layout extends AppCompatActivity {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        AppData appData = (AppData) getApplication();
        TextView test = findViewById(R.id.test);
        test.setText("이름 : "+appData.getUser().get이름()+"\nID : "+appData.getUser().getID()+"\n전화번호 : "+appData.getUser().get전화번호());
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
            case R.id.booking:
                intent = new Intent(login_layout.this,booktype.class);
                startActivity(intent);
                break;
        }
    }
}
