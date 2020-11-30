package net.ddns.leekm.eco_design_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private EditText insertID;
    private EditText insertPW;
    private Button button;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        insertID = findViewById(R.id.insertID);
        insertPW = findViewById(R.id.insertPW);
        button = findViewById(R.id.login_btn);
        button.setOnClickListener((v)->{
            String url = "http://220.66.111.200:8889/eco_design/eco_design/signIn.jsp";
            String parse_data = null;

            String id = insertID.getText().toString();
            String pw = insertPW.getText().toString();
            if(id.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*") || pw.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) { // 한글이 포함되면.
                Toast.makeText(this,"영문자, 특수문자만 사용 가능합니다.",Toast.LENGTH_SHORT).show();
                return;
            }else if(id.equals("")){
                Toast.makeText(this,"아이디를 입력해 주세요",Toast.LENGTH_SHORT).show();
                return;
            }else if(pw.equals("")){
                Toast.makeText(this,"패스워드를 입력해 주세요",Toast.LENGTH_SHORT).show();
                return;
            }
            // AsyncTask를 통해 HttpURLConnection 수행.
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID",id);
            contentValues.put("PW",pw);

            NetworkTask networkTask = new NetworkTask(this, url, contentValues, (AppData)getApplication());
            try {
                parse_data =  networkTask.execute().get(); // get()함수를 이용해 작업결과를 불러올 수 있음.
                Log.i("1",parse_data);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Parse p = new Parse((AppData)getApplication() ,parse_data);
            if(p.getNotice().equals("success")){
                p.setUser();
                Intent intent = new Intent(login.this ,login_layout.class);
                startActivityForResult(intent,0);//액티비티 띄우기
            }
        });
    }
}
