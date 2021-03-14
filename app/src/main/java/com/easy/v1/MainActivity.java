package com.easy.v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lakue.lakuepopupactivity.PopupActivity;
import com.lakue.lakuepopupactivity.PopupGravity;
import com.lakue.lakuepopupactivity.PopupType;

public class MainActivity extends AppCompatActivity {
    ImageView btn_toreg;
    ImageButton btn_toDevInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setTheme(R.style.AppTheme);

        btn_toreg = findViewById(R.id.btn_toreg);
        btn_toDevInfo = findViewById(R.id.btn_toDevInfo);

        btn_toreg.setOnClickListener(onClickListener);
        btn_toDevInfo.setOnClickListener(onClickListener);
    }
    Button.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()){
                case R.id.btn_toreg:
                    startActivity(new Intent(MainActivity.this, REGSurvey.class));
                    break;
                case R.id.btn_toDevInfo:
                    startActivity(new Intent(MainActivity.this, DevInfo.class));
                    break;
            }
        }
    };
}