package com.easy.v1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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

import java.util.ArrayList;
import java.util.Arrays;

public class REGSurvey extends AppCompatActivity {
    private Toolbar toolbar;
    private Spinner spin_InsComp;
    private TextView accidDate, accidTime;
    // 증권번호, 사업자번호, 보험계약자, 피보험자, 관리담당자, 담당자 연락처, 피해자, 피해자 연락처, 사고 목적물 소재지
    private EditText policyNo, bizNum, insurant, insured, manager, manager_phone, victim, victim_phone, accidLoc;
    private DatePickerDialog.OnDateSetListener callbackMethod_date;
    private TimePickerDialog.OnTimeSetListener callbackMethod_time;
    Button btn_tosuc;
    String[] insCompList = {"선택","현대해상","삼성화재"};
    String insCompStr, insCompManager;
    // 1: 사고장소, 2: 사고유형, 3: 피해현황, 4: 예상손해액, 5: 사고자(배상주체). 6: 영업담당자(GA)
    CheckBox check1_1,check1_2, check2_1, check2_2, check2_3, check2_4, check2_5, check2_6, check2_7, check2_8, check2_9, check2_10, check2_11,
            check3_1, check3_2, check3_3, check3_4, check3_5, check3_6, check3_7, check3_8, check3_9, check3_10, check3_11,
            check4_1, check4_2, check4_3, check4_4, check4_5, check4_6, check5_1, check5_2,
            check6_1, check6_2, check6_3, check6_4, check6_5, check6_6, check6_7, check6_8, check6_9;
    ArrayList<String> check1 = new ArrayList<String>();
    ArrayList<String> check2 = new ArrayList<String>();
    ArrayList<String> check3 = new ArrayList<String>();
    ArrayList<String> check4 = new ArrayList<String>();
    ArrayList<String> check5 = new ArrayList<String>();
    ArrayList<String> check6 = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regstep1);

        String message = "\n\n현재\n삼성화재, 현대해상만\n접수가능합니다.\n\n빠른 시일내\n다른 보험사를\n추가할 예정입니다.\n";
        Intent intent = new Intent(REGSurvey.this, PopupActivity.class);
        intent.putExtra("type", PopupType.NORMAL);
        intent.putExtra("gravity", PopupGravity.CENTER);
        intent.putExtra("title","알림");
        intent.putExtra("content",message);
        intent.putExtra("buttonCenter","확인");
        startActivityForResult(intent, 1);

        spin_InsComp = findViewById(R.id.spin_InsComp);
        editTextInit();
        checkboxInit();
        accidDate = findViewById(R.id.accidDate);
        accidTime = findViewById(R.id.accidTime);
        btn_tosuc = findViewById(R.id.btn_tosuc);
        accidDate.setClickable(true);
        accidTime.setClickable(true);
        accidDate.setOnClickListener(onClickListener);
        accidTime.setOnClickListener(onClickListener);
        btn_tosuc.setOnClickListener(onClickListener);

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, insCompList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_InsComp.setAdapter(adapter);

    }

    public void editTextInit(){
        policyNo = findViewById(R.id.policyNo);
        bizNum = findViewById(R.id.bizNum);
        insurant = findViewById(R.id.insurant);
        insured = findViewById(R.id.insured);
        manager = findViewById(R.id.manager);
        manager_phone = findViewById(R.id.manager_phone);
        victim = findViewById(R.id.victim);
        victim_phone = findViewById(R.id.victim_phone);
        accidLoc = findViewById(R.id.accidLoc);
    }
    
    public void checkboxInit(){
        check1_1 = findViewById(R.id.check1_1);
        check1_2 = findViewById(R.id.check1_2);
        check2_1 = findViewById(R.id.check2_1);
        check2_2 = findViewById(R.id.check2_2);
        check2_3 = findViewById(R.id.check2_3);
        check2_4 = findViewById(R.id.check2_4);
        check2_5 = findViewById(R.id.check2_5);
        check2_6 = findViewById(R.id.check2_6);
        check2_7 = findViewById(R.id.check2_7);
        check2_8 = findViewById(R.id.check2_8);
        check2_9 = findViewById(R.id.check2_9);
        check2_10 = findViewById(R.id.check2_10);
        check2_11 = findViewById(R.id.check2_11);
        check3_1 = findViewById(R.id.check3_1);
        check3_2 = findViewById(R.id.check3_2);
        check3_3 = findViewById(R.id.check3_3);
        check3_4 = findViewById(R.id.check3_4);
        check3_5 = findViewById(R.id.check3_5);
        check3_6 = findViewById(R.id.check3_6);
        check3_7 = findViewById(R.id.check3_7);
        check3_8 = findViewById(R.id.check3_8);
        check3_9 = findViewById(R.id.check3_9);
        check3_10 = findViewById(R.id.check3_10);
        check3_11 = findViewById(R.id.check3_11);
        check4_1 = findViewById(R.id.check4_1);
        check4_2 = findViewById(R.id.check4_2);
        check4_3 = findViewById(R.id.check4_3);
        check4_4 = findViewById(R.id.check4_4);
        check4_5 = findViewById(R.id.check4_5);
        check4_6 = findViewById(R.id.check4_6);
        check5_1 = findViewById(R.id.check5_1);
        check5_2 = findViewById(R.id.check5_2);
        check6_1 = findViewById(R.id.check6_1);
        check6_2 = findViewById(R.id.check6_2);
        check6_3 = findViewById(R.id.check6_3);
        check6_4 = findViewById(R.id.check6_4);
        check6_5 = findViewById(R.id.check6_5);
        check6_6 = findViewById(R.id.check6_6);
        check6_7 = findViewById(R.id.check6_7);
        check6_8 = findViewById(R.id.check6_8);
        check6_9 = findViewById(R.id.check6_9);
    }
    Button.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.accidDate:
                    DatePickerDialog dialog_date = new DatePickerDialog(REGSurvey.this, callbackMethod_date, 2020, 12, 9);
                    dialog_date.show();
                    break;
                case R.id.accidTime:
                    TimePickerDialog dialog_time = new TimePickerDialog(REGSurvey.this, callbackMethod_time, 12,0, true);
                    dialog_time.show();
                    break;
                case R.id.btn_tosuc:
                    String tmp = checkValue();
                    if(tmp == null) {
                        if (insCompStr.equals("현대해상")) {
                            insCompManager = "claim1@hi.co.kr";

                        } else {
                            insCompManager = "sfsonsa@samsungfiresvc.com";
                        }
                        String message = "증권번호 : " + policyNo.getText().toString() + "\n";
                        message += "사업자번호 : " + bizNum.getText().toString() + "\n";
                        message += "보험계약자 : " + insurant.getText().toString() + "\n";
                        message += "피보험자 : " + insured.getText().toString() + "\n";
                        message += "관리담당자 : " + manager.getText().toString() + "\n";
                        message += "담당자 연락처 : " + manager_phone.getText().toString() + "\n";
                        message += "피해자 : " + victim.getText().toString() + "\n";
                        message += "피해자 연락처 : " + victim_phone.getText().toString() + "\n";
                        message += "사고 목적물 소재지 : " + accidLoc.getText().toString() + "\n";
                        message += "사고일시 : " + accidDate.getText().toString()+" "+accidTime.getText().toString() + "\n";
                        message += "사고장소 : " + Arrays.toString(check1.toArray(new String[check1.size()])) + "\n";
                        message += "사고유형 : " + Arrays.toString(check2.toArray(new String[check2.size()])) + "\n";
                        message += "피해현황 : " + Arrays.toString(check3.toArray(new String[check3.size()])) + "\n";
                        message += "예상손해액 : " + Arrays.toString(check4.toArray(new String[check4.size()])) + "\n";
                        message += "사고자(배상주체) : " + Arrays.toString(check5.toArray(new String[check5.size()])) + "\n";
                        message += "영업담당자(GA) : " + Arrays.toString(check6.toArray(new String[check6.size()])) + "\n";
                        Log.v("toInsCompManager",message);
                        try {
                            Intent email = new Intent(Intent.ACTION_SEND);
                            email.setType("text/plain");
                            email.putExtra(Intent.EXTRA_EMAIL, new String[]{insCompManager});
                            email.putExtra(Intent.EXTRA_SUBJECT, "사고 접수 by 이사접app");
                            email.putExtra(Intent.EXTRA_TEXT, message);
                            startActivity(email);

                        }catch (Exception e){
                            e.printStackTrace();
                            message = "\n\n네트워크가 불안정합니다.\n다시 접수하기를 실행해주세요.\n";
                            Intent intent = new Intent(REGSurvey.this, PopupActivity.class);
                            intent.putExtra("type", PopupType.NORMAL);
                            intent.putExtra("gravity", PopupGravity.CENTER);
                            intent.putExtra("title","알림");
                            intent.putExtra("content",message);
                            intent.putExtra("buttonCenter","확인");
                            startActivityForResult(intent, 1);
                        }
                        //Intent intent = new Intent(REGSurvey.this, REGSuccess.class);
                        //startActivity(intent);
                        //break;
                    }else{
                        Intent intent = new Intent(REGSurvey.this, PopupActivity.class);
                        intent.putExtra("type", PopupType.NORMAL);
                        intent.putExtra("gravity", PopupGravity.CENTER);
                        intent.putExtra("title","알림");
                        intent.putExtra("content",tmp);
                        intent.putExtra("buttonCenter","확인");
                        startActivityForResult(intent, 1);
                    }
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
    public String checkValue(){
        insCompStr = spin_InsComp.getSelectedItem().toString();
        if(insCompStr.equals("선택")){
            return "보험사를 선택해주세요.";
        }else if(insurant.getText().toString().equals("")){
            return "보험계약자를 입력해주세요.";
        }else if(manager.getText().toString().equals("")){
            return "관리담당자를 입력해주세요.";
        }else if(manager_phone.getText().toString().equals("")){
            return "담당자 연락처를 입력해주세요.";
        }else if(victim.getText().toString().equals("")){
            return "피해자를 입력해주세요.";
        }else if(victim_phone.getText().toString().equals("")){
            return "피해자 연락처를 입력해주세요.";
        }else{ // 선택 입력사항

            if(check1_1.isChecked()) check1.add(check1_1.getText().toString());
            if(check1_2.isChecked()) check1.add(check1_2.getText().toString());
            if(check2_1.isChecked()) check2.add(check2_1.getText().toString());
            if(check2_2.isChecked()) check2.add(check2_2.getText().toString());
            if(check2_3.isChecked()) check2.add(check2_3.getText().toString());
            if(check2_4.isChecked()) check2.add(check2_4.getText().toString());
            if(check2_5.isChecked()) check2.add(check2_5.getText().toString());
            if(check2_6.isChecked()) check2.add(check2_6.getText().toString());
            if(check2_7.isChecked()) check2.add(check2_7.getText().toString());
            if(check2_8.isChecked()) check2.add(check2_8.getText().toString());
            if(check2_9.isChecked()) check2.add(check2_9.getText().toString());
            if(check2_10.isChecked()) check2.add(check2_10.getText().toString());
            if(check2_11.isChecked()) check2.add(check2_11.getText().toString());
            if(check3_1.isChecked()) check3.add(check3_1.getText().toString());
            if(check3_2.isChecked()) check3.add(check3_2.getText().toString());
            if(check3_3.isChecked()) check3.add(check3_3.getText().toString());
            if(check3_4.isChecked()) check3.add(check3_4.getText().toString());
            if(check3_5.isChecked()) check3.add(check3_5.getText().toString());
            if(check3_6.isChecked()) check3.add(check3_6.getText().toString());
            if(check3_7.isChecked()) check3.add(check3_7.getText().toString());
            if(check3_8.isChecked()) check3.add(check3_8.getText().toString());
            if(check3_9.isChecked()) check3.add(check3_9.getText().toString());
            if(check3_10.isChecked()) check3.add(check3_10.getText().toString());
            if(check3_11.isChecked()) check3.add(check3_11.getText().toString());
            if(check4_1.isChecked()) check4.add(check4_1.getText().toString());
            if(check4_2.isChecked()) check4.add(check4_2.getText().toString());
            if(check4_3.isChecked()) check4.add(check4_3.getText().toString());
            if(check4_4.isChecked()) check4.add(check4_4.getText().toString());
            if(check4_5.isChecked()) check4.add(check4_5.getText().toString());
            if(check4_6.isChecked()) check4.add(check4_6.getText().toString());
            if(check5_1.isChecked()) check5.add(check5_1.getText().toString());
            if(check5_2.isChecked()) check5.add(check5_2.getText().toString());
            if(check6_1.isChecked()) check6.add(check6_1.getText().toString());
            if(check6_2.isChecked()) check6.add(check6_2.getText().toString());
            if(check6_3.isChecked()) check6.add(check6_3.getText().toString());
            if(check6_4.isChecked()) check6.add(check6_4.getText().toString());
            if(check6_5.isChecked()) check6.add(check6_5.getText().toString());
            if(check6_6.isChecked()) check6.add(check6_6.getText().toString());
            if(check6_7.isChecked()) check6.add(check6_7.getText().toString());
            if(check6_8.isChecked()) check6.add(check6_8.getText().toString());
            if(check6_9.isChecked()) check6.add(check6_9.getText().toString());

    }
        return null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
