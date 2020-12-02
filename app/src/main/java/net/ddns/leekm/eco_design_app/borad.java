package net.ddns.leekm.eco_design_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class borad extends AppCompatActivity {
    Button submit;
    RadioGroup rg;
    TextInputLayout title;
    TextInputLayout story;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borad);
        //db에 담을 제목, 내용
        title = findViewById(R.id.title);
        story = findViewById(R.id.story);

        submit = findViewById(R.id.submit);
        rg = (RadioGroup)findViewById(R.id.bookType);

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
    }
}