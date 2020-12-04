package net.ddns.leekm.eco_design_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class borad extends AppCompatActivity {
    Button submit;
    RadioGroup rg;
    TextInputEditText title;
    TextInputEditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borad);
        //db에 담을 제목, 내용
        title = findViewById(R.id.title);
        text = findViewById(R.id.text);

        submit = findViewById(R.id.submit);
        rg = (RadioGroup)findViewById(R.id.bookType);
        /*
        submit.setOnClickListener((v)->{
            Intent intent;
            Toast.makeText(this,"등록되었습니다.",Toast.LENGTH_SHORT).show();
            switch (rg.getCheckedRadioButtonId()){
                case R.id.Consulting:
                    intent = new Intent(this,ConsultingBooking.class);
                    startActivity(intent);
                    break;
                case R.id.Diy:
                    intent = new Intent(this,DIY_booking.class);
                    startActivity(intent);
                    break;
            }
        });
        */
    }

    public void postSubmit(View v){
        Map<String, String> param = new HashMap<>();
        RadioButton rb = findViewById(rg.getCheckedRadioButtonId());
        String url = AppData.SERVER_FULL_URL+"/eco_design/eco_design/postUpload.jsp";
        String parse_data = null;
        ContentValues contentValues = new ContentValues();
        // AsyncTask를 통해 HttpURLConnection 수행.
        try {
            AppData appData = (AppData) getApplication();

            String title_str = title.getText().toString();
            String text_str = text.getText().toString();
            Pattern pattern = Pattern.compile("[<>+%]");
            if(pattern.matcher(title_str).find() || pattern.matcher(text_str).find()){ // 특수문자 들어있으면 true 리턴
                Toast.makeText(this,"사용 불가능한 특수문자가 포함되어 있습니다.",Toast.LENGTH_SHORT).show();
                return;
            }
            param.put("제목", URLEncoder.encode(title_str, "utf-8"));
            param.put("내용", URLEncoder.encode(text_str, "utf-8"));
            param.put("분류", rb.getText().toString());
            param.put("사용자_ID", appData.getUser().getID());

            contentValues.put("제목", URLEncoder.encode(title_str, "utf-8"));
            contentValues.put("내용", URLEncoder.encode(text_str, "utf-8"));
            contentValues.put("분류", rb.getText().toString());
            contentValues.put("사용자_ID", appData.getUser().getID());
        }
        /*catch (NumberFormatException ne){ //숫자가 아닌값을 price에 입력했을때.
            ne.printStackTrace();
            Toast.makeText(this, "가격은 숫자만 가능합니다.", Toast.LENGTH_SHORT).show();
        }*/
        catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, "빈칸이 있거나 잘못된 가격입니다.",Toast.LENGTH_SHORT).show();
            return;
        }
        /* 이미지 전송부분
        try{
            //File이 널이 아니면 이미지 전송
            if(tempSelectFile != null) {
                String result = DoFileUpload(AppData.SERVER_FULL_URL+"/yonam-market/market/img_upload/uploadAction.jsp",tempSelectFile, param);  //해당 함수를 통해 이미지 전송.
                Parse p = new Parse((AppData)getApplication() ,result);
                if(p.getNotice().equals("success")){
                    //Intent intent = new Intent(this,MainMenu.class);
                    //startActivityForResult(intent,0);//액티비티 띄우기
                    Log.i("삽입완료","삽입완료");
                    Toast.makeText(this,"그림+게시물 작성 완료",Toast.LENGTH_SHORT).show();
                }else{
                    return;
                }
            }else{

         */
        NetworkTask networkTask = new NetworkTask(this, url, contentValues, (AppData)getApplication());
        try {
            parse_data =  networkTask.execute().get(); // get()함수를 이용해 작업결과를 불러올 수 있음.
            Log.i("1",parse_data);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Parse p = new Parse((AppData)getApplication() ,parse_data);
        if(p.getNotice().equals("success")){
            //Intent intent = new Intent(this,MainMenu.class);
            //startActivityForResult(intent,0);//액티비티 띄우기
            Log.i("삽입완료","삽입완료");
            Toast.makeText(this,"게시물 작성 완료",Toast.LENGTH_SHORT).show();
        }
        else {
            return;
        }
        /* 이미지 전송부분
        catch(Exception e){
            e.printStackTrace();
        }
        */
        finish();
    }
}