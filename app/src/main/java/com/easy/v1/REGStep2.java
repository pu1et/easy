package com.easy.v1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class REGStep2 extends AppCompatActivity {
    private Button btn_tomain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regstep2);

        btn_tomain = findViewById(R.id.btn_tomain);
        btn_tomain.setOnClickListener(onClickListener);
    }


    Button.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_tomain:
                    Intent intent = new Intent(REGStep2.this, MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
