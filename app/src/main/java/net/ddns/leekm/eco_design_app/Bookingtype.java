package net.ddns.leekm.eco_design_app;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

// 게시판 선택
public class Bookingtype extends AppCompatActivity {
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingtype);

    }

    public void mClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.Consulting_booking:
                intent = new Intent(Bookingtype.this,ConsultingBooking.class);
                startActivity(intent);
                break;
            case R.id.DIY_booking:
                intent = new Intent(Bookingtype.this,DIY_booking.class);
                startActivity(intent);
                break;
        }
    }
}
