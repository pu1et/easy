package com.easy.v1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.lakue.lakuepopupactivity.PopupActivity;
import com.lakue.lakuepopupactivity.PopupGravity;
import com.lakue.lakuepopupactivity.PopupType;

import java.net.DatagramPacket;

public class REGStep1 extends AppCompatActivity {
    private Toolbar toolbar;
    private Spinner spin_InsComp;
    private TextView insCompEmail, accidDate, accidTime;
    // 증권번호, 사업자번호, 보험계약자, 피보험자, 관리담당자, 담당자 연락처, 피해자, 피해자 연락처, 사고 목적물 소재지, 사고일시,
    private EditText policyNo, bizNum, insurant, insured, manager, manager_phone, victim, victim_phone, accidLoc;
    private DatePickerDialog.OnDateSetListener callbackMethod_date;
    private TimePickerDialog.OnTimeSetListener callbackMethod_time;
    Button btn_tostep2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regstep1);

        spin_InsComp = findViewById(R.id.spin_InsComp);
        policyNo = findViewById(R.id.policyNo);
        bizNum = findViewById(R.id.bizNum);
        insurant = findViewById(R.id.insurant);
        insured = findViewById(R.id.insured);
        manager = findViewById(R.id.manager);
        manager_phone = findViewById(R.id.manager_phone);
        victim = findViewById(R.id.victim);
        victim_phone = findViewById(R.id.victim_phone);
        accidLoc = findViewById(R.id.accidLoc);
        btn_tostep2 = findViewById(R.id.btn_tostep2);
        accidDate = findViewById(R.id.accidDate);
        accidTime = findViewById(R.id.accidTime);
        accidDate.setClickable(true);
        accidTime.setClickable(true);
        accidDate.setOnClickListener(onClickListener);
        accidTime.setOnClickListener(onClickListener);
        btn_tostep2.setOnClickListener(onClickListener);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        callbackMethod_date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                accidDate.setText(year+"."+month+"."+dayOfMonth);
            }
        };

        callbackMethod_time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                accidTime.setText(hourOfDay+":"+minute);
            }
        };

    }

    Button.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.accidDate:
                    DatePickerDialog dialog_date = new DatePickerDialog(REGStep1.this, callbackMethod_date, 2020, 12, 9);
                    dialog_date.show();
                    break;
                case R.id.accidTime:
                    TimePickerDialog dialog_time = new TimePickerDialog(REGStep1.this, callbackMethod_time, 12,0, true);
                    dialog_time.show();
                    break;
                case R.id.btn_tostep2:
                    Intent intent = new Intent(REGStep1.this, REGStep2.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
