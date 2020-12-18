package net.ddns.leekm.eco_design_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.TextView;

// 로그인 후 메인 화면
public class Layout_after_login extends AppCompatActivity {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_after_login);
        AppData appData = (AppData) getApplication();
        TextView test = findViewById(R.id.test);
        test.setText(appData.getUser().get이름()+"님");
    }

    public void mClick(android.view.View v){
        Intent intent;
        //버튼별 옮겨지는 위치 지정
        switch(v.getId()){
            case R.id.logout:
                intent = new Intent(Layout_after_login.this, Layout_before_login.class);
                startActivity(intent);
                break;
            case R.id.information:
                intent = new Intent(Layout_after_login.this, Information.class);
                startActivity(intent);
                break;
            case R.id.notice:
                intent = new Intent(Layout_after_login.this,Notice_Board.class);
                startActivity(intent);
                break;
            case R.id.ba:
                intent = new Intent(Layout_after_login.this, Before_after.class);
                startActivity(intent);
                break;
            case R.id.booking:
                intent = new Intent(Layout_after_login.this, Bookingtype.class);
                startActivity(intent);
                break;
        }
    }
}
