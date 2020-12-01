package net.ddns.leekm.eco_design_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class DIY_booking extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_i_y_booking);


        button = findViewById(R.id.btn1);
        button.setOnClickListener((v) ->{
            Intent intent = new Intent(this,borad.class);
            startActivity(intent);
        });
    }
}