package net.ddns.leekm.eco_design_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class borad extends AppCompatActivity {
    Button submit;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borad);

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