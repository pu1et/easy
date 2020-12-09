package com.easy.v1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lakue.lakuepopupactivity.PopupActivity;
import com.lakue.lakuepopupactivity.PopupGravity;
import com.lakue.lakuepopupactivity.PopupType;

public class MainActivity extends AppCompatActivity {
    Button btn_toreg;
    TextView btn_atod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.AppTheme);

        btn_toreg = findViewById(R.id.btn_toreg);
        btn_atod = findViewById(R.id.btn_atod);

        btn_atod.setClickable(true);
        btn_toreg.setOnClickListener(onClickListener);
        btn_atod.setOnClickListener(onClickListener);
        // 최초 실행 여부를 판단
        SharedPreferences sf = getSharedPreferences("checkFirst", Activity.MODE_PRIVATE);
        boolean checkFirst = sf.getBoolean("checkFirst",false);
        if(!checkFirst){
            SharedPreferences.Editor editor = sf.edit();
            editor.putBoolean("checkFirst",true);
            editor.apply();

            Intent intent = new Intent(MainActivity.this, TutorialActivity.class);
            startActivity(intent);
            finish();
        }else{
            // 최초 실행이 아닐 때 진행할 작업

        }
    }
    Button.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_atod:
                    Intent intent = new Intent(MainActivity.this, PopupActivity.class);
                    intent.putExtra("type", PopupType.NORMAL);
                    intent.putExtra("gravity", PopupGravity.CENTER);
                    intent.putExtra("title","회사소개");
                    intent.putExtra("content","보험접수 전문 회사입니다.");
                    intent.putExtra("buttonCenter","확인");
                    startActivityForResult(intent, 1);
                    break;
                case R.id.btn_toreg:
                    startActivity(new Intent(MainActivity.this, REGStep1.class));
                    break;
            }
        }
    };
}