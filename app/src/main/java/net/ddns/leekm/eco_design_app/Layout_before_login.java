package net.ddns.leekm.eco_design_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// 앱 처음 실행시 보이는 화면
public class Layout_before_login extends AppCompatActivity {
    Button info1_btn;
    Button ba_btn;
    Button login_btn;
    Button add_log_btn;
    TextView version;
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_before_login);
        info1_btn = findViewById(R.id.information);
        ba_btn = findViewById(R.id.ba);
        login_btn = findViewById(R.id.login_btn);
        add_log_btn = findViewById(R.id.add_log);
        version = findViewById(R.id.version);

        new InternetCheck(internet -> { // 서버 연결 체크
            if(internet) {
                login_btn.setEnabled(true);
                add_log_btn.setEnabled(true);
            }else{
                Toast.makeText(this, "서버off", Toast.LENGTH_LONG).show();
            }
        });
        version.setText("version : "+getVersionInfo(this));
        info1_btn.setOnClickListener(onClickListener);
        ba_btn.setOnClickListener(onClickListener);
        login_btn.setOnClickListener(onClickListener);
        add_log_btn.setOnClickListener(onClickListener);
    }

    // gradle에서 버젼정보를 불러옴
    public String getVersionInfo(Context context){
        String version = null;
        try {
            PackageInfo i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = i.versionName;
        } catch(PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    android.view.View.OnClickListener onClickListener = v -> {
        Intent intent;
        switch (v.getId()){
            //버튼별 옮겨지는 위치 지정
            case R.id.information:
                intent = new Intent(Layout_before_login.this, Information.class);
                startActivity(intent);
                break;
            case R.id.ba:
                intent = new Intent(Layout_before_login.this, Before_after.class);
                startActivity(intent);
                break;
            case R.id.login_btn:
                intent = new Intent(Layout_before_login.this, Login.class);
                startActivity(intent);
                break;
            case R.id.add_log:
                intent = new Intent(Layout_before_login.this, SignUp.class);
                startActivity(intent);
                break;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        // 화면이 다시 보이면 서버연결체크 다시. 일단 버튼 비활성화 시킴.
        login_btn.setEnabled(false);
        add_log_btn.setEnabled(false);
        new InternetCheck(internet -> { // 서버 연결 체크
            if(internet) {
                login_btn.setEnabled(true);
                add_log_btn.setEnabled(true);
            }else{
                Toast.makeText(this, "서버off", Toast.LENGTH_LONG).show();
            }
        });
    }
}
