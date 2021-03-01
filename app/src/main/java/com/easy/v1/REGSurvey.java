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
    // 보험사, 사고장소, 사고유형, 예상손해액, 사고자, 영업담당자
    private Spinner spin_InsComp, spin_accPlace, spin_accType, spin_expLoss, spin_accSuspect, spin_PICOfSales;
    private TextView accidDate, accidTime;
    // 증권번호, 사업자번호, 보험계약자, 피보험자, 관리담당자, 담당자 연락처, 피해자, 피해자 연락처, 사고 목적물 소재지
    private EditText policyNo, bizNum, insurant, insured, manager, manager_phone, victim, victim_phone, accidLoc;
    private DatePickerDialog.OnDateSetListener callbackMethod_date;
    private TimePickerDialog.OnTimeSetListener callbackMethod_time;
    Button btn_tosuc;
    String[] insCompList = {"선택","현대해상","삼성화재"};
    String[] accPlaceList = {"선택","건물 내", "건물 외"};
    String[] accTypeList = {"선택","화재","풍수해","파손","폭발/파열","지진","급배수 누수/방수","스프링쿨러 누수","전기 누전","대물(제3자)배상","대인(제3자)배상","기타"};
    String[] expLossList = {"선택","300만원 미만","300만원 이상 ~ 1천만원 미만","1천만원 이상 ~ 3천만원 미만","3천만원 이상 ~ 5천만원 미만","5천만원 이상 ~ 1억원 미만","1억원 이상"};
    String[] accSuspectList = {"선택","피보험자","기타"};
    String[] PICOfSalesList = {"선택","밸류어블인","밸류테크","넥솔","에드","사랑모아","영진에드","가이드","피플라이프"};

    String insCompStr, insCompManager;
    // 1: 피해현황
    CheckBox check1_1, check1_2, check1_3, check1_4, check1_5, check1_6, check1_7, check1_8, check1_9, check1_10, check1_11;
    ArrayList<String> check1 = new ArrayList<String>();

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


        spinInit();
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

     

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, insCompList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_InsComp.setAdapter(adapter);

    }

    public void spinInit(){
        ArrayAdapter<String> adapter1, adapter2, adapter3, adapter4, adapter5, adapter6;
        spin_InsComp = findViewById(R.id.spin_InsComp);
        spin_accPlace = findViewById(R.id.spin_accPlace);
        spin_accType = findViewById(R.id.spin_accType);
        spin_expLoss = findViewById(R.id.spin_expLoss);
        spin_accSuspect = findViewById(R.id.spin_accSuspect);
        spin_PICOfSales= findViewById(R.id.spin_PICOfSales);

        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, insCompList);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, accPlaceList);
        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, accTypeList);
        adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, expLossList);
        adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, accSuspectList);
        adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, PICOfSalesList);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin_InsComp.setAdapter(adapter1);
        spin_accPlace.setAdapter(adapter2);
        spin_accType.setAdapter(adapter3);
        spin_expLoss.setAdapter(adapter4);
        spin_accSuspect.setAdapter(adapter5);
        spin_PICOfSales.setAdapter(adapter6);

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
        check1_3 = findViewById(R.id.check1_3);
        check1_4 = findViewById(R.id.check1_4);
        check1_5 = findViewById(R.id.check1_5);
        check1_6 = findViewById(R.id.check1_6);
        check1_7 = findViewById(R.id.check1_7);
        check1_8 = findViewById(R.id.check1_8);
        check1_9 = findViewById(R.id.check1_9);
        check1_10 = findViewById(R.id.check1_10);
        check1_11 = findViewById(R.id.check1_11);
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
                        message += "사고장소 : " + spin_accPlace.getSelectedItem().toString() + "\n";
                        message += "사고유형 : " + spin_accType.getSelectedItem().toString() + "\n";
                        message += "피해현황 : " + Arrays.toString(check1.toArray(new String[check1.size()])) + "\n";
                        message += "예상손해액 : " + spin_expLoss.getSelectedItem().toString() + "\n";
                        message += "사고자(배상주체) : " + spin_accSuspect.getSelectedItem().toString() + "\n";
                        message += "영업담당자(GA) : " + spin_PICOfSales.getSelectedItem().toString() + "\n";
                        Log.v("toInsCompManager",message);
                        try {
                            Intent email = new Intent(Intent.ACTION_SEND);
                            email.setType("text/plain");
                            email.putExtra(Intent.EXTRA_EMAIL, new String[]{insCompManager});
                            email.putExtra(Intent.EXTRA_CC, new String[]{"cs.iatod.inc@gmail.com"});
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
            if(check1_3.isChecked()) check1.add(check1_3.getText().toString());
            if(check1_4.isChecked()) check1.add(check1_4.getText().toString());
            if(check1_5.isChecked()) check1.add(check1_5.getText().toString());
            if(check1_6.isChecked()) check1.add(check1_6.getText().toString());
            if(check1_7.isChecked()) check1.add(check1_7.getText().toString());
            if(check1_8.isChecked()) check1.add(check1_8.getText().toString());
            if(check1_9.isChecked()) check1.add(check1_9.getText().toString());
            if(check1_10.isChecked()) check1.add(check1_10.getText().toString());
            if(check1_11.isChecked()) check1.add(check1_11.getText().toString());

    }
        return null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
