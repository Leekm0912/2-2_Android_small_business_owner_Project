package net.ddns.leekm.eco_design_app;

import android.content.ContentValues;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

// 회원가입창
public class SignUp extends AppCompatActivity {
    TextInputEditText name;
    TextInputEditText id;
    TextInputEditText pw;
    TextInputEditText address;
    TextInputEditText birthday;
    TextInputEditText phone;
    boolean isError = false;
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // 위젯에 대한 참조.
        id = findViewById(R.id.id);
        pw = findViewById(R.id.pw);
        address = findViewById(R.id.address);
        name = findViewById(R.id.name);
        birthday = findViewById(R.id.birthday);
        phone = findViewById(R.id.phone);

        TextInputEditText editTextID = textwatcher_idpw(id);
        TextInputEditText editTextPW = textwatcher_idpw(pw);
        TextInputEditText editTextPhone = textwatcher_phone(phone);
        TextInputEditText editTextName = textwatcher_name(name);

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener((v)->{
            if(!isError) { // 에러가 없으면
                // URL 설정.
                String url = AppData.SERVER_FULL_URL + "/eco_design/eco_design/signUp.jsp";
                String parse_data = null;

                String id_str = editTextID.getText().toString();
                String pw_str = editTextPW.getText().toString();
                String phone_str = editTextPhone.getText().toString();

                // AsyncTask를 통해 HttpURLConnection 수행.
                ContentValues contentValues = new ContentValues();
                contentValues.put("ID", id_str);
                contentValues.put("PW", pw_str);
                contentValues.put("이름", editTextName.getText().toString());
                contentValues.put("전화번호", phone_str);
                contentValues.put("주소", address.getText().toString());
                contentValues.put("생년월일", birthday.getText().toString());
                NetworkTask networkTask = new NetworkTask(this, url, contentValues, (AppData) getApplication());
                try {
                    parse_data = networkTask.execute().get(); // get()함수를 이용해 작업결과를 불러올 수 있음.
                    Log.i("1", parse_data);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Parse p = new Parse((AppData) getApplication(), parse_data);
                if (p.getNotice().equals("success")) { // 작업이 정상적으로 완료되면 서버에서 success를 보냄.
                    Toast.makeText(this, "계정 생성 완료", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }else{
                Toast.makeText(this,"입력이 잘못되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public TextInputEditText textwatcher_idpw(TextInputEditText textInputEditText) {
        //geteditText는 TextInputLayout를 다시 EditText로 바꿔주는 역할입니다.
        TextInputEditText editText = textInputEditText;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
                    isError = true;
                    editText.setError("영문자, 특수문자만 사용 가능합니다.");
                    Log.i("==============id,pw================",s.toString());
                } else {
                    isError = false;
                    editText.setError(null);
                }
            }
        });
        return editText;
    }

    public TextInputEditText textwatcher_phone(TextInputEditText textInputEditText) {
        //geteditText는 TextInputLayout를 다시 EditText로 바꿔주는 역할입니다.
        TextInputEditText editText = textInputEditText;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().matches("^[0-9]+$") | s.toString().length() != 11){ // 전화번호 형식 정규식으로 확인
                    isError = true;
                    Log.i("==============전화번호================",s.toString());
                    editText.setError("전화번호 형식에 맞게 적어주세요(- 제외 11자리)");
                }else{
                    // 에러 메시지 삭제
                    isError = false;
                    editText.setError(null);
                }
            }
        });
        return editText;
    }

    public TextInputEditText textwatcher_name(TextInputEditText textInputEditText) {
        //geteditText는 TextInputLayout를 다시 EditText로 바꿔주는 역할입니다.
        TextInputEditText editText = textInputEditText;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
                    isError = true;
                    Log.i("==============이름================",s.toString());
                    editText.setError("한글로 작성해 주세요");
                }else{
                    // 에러 메시지 삭제
                    isError = false;
                    editText.setError(null);
                }
            }
        });
        return editText;
    }
}
