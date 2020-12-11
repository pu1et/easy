package com.easy.v1;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.lakue.lakuepopupactivity.PopupActivity;
import com.lakue.lakuepopupactivity.PopupResult;

public class AtoDPopupActivity extends PopupActivity {

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("message",data+","+resultCode);
        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                PopupResult result = (PopupResult)data.getSerializableExtra("result");
                if(result == PopupResult.CENTER){
                    Toast.makeText(this, "CENTER", Toast.LENGTH_LONG).show();
                }
            }
            if(requestCode == 2){
                PopupResult result = (PopupResult)data.getSerializableExtra("result");
                if(result == PopupResult.LEFT){
                    Toast.makeText(this, "LEFT", Toast.LENGTH_SHORT).show();
                }else if(result == PopupResult.RIGHT){
                    Toast.makeText(this, "RIGHT", Toast.LENGTH_SHORT).show();
                }
            }
            if(requestCode == 3){
                PopupResult result = (PopupResult)data.getSerializableExtra("result");
                if(result == PopupResult.CENTER){
                    Toast.makeText(this, "CENTER", Toast.LENGTH_SHORT).show();
                }
            }
            if(requestCode == 4){
                PopupResult result = (PopupResult)data.getSerializableExtra("result");
                if(result == PopupResult.LEFT){
                    Toast.makeText(this, "LEFT", Toast.LENGTH_SHORT).show();
                }else if(result == PopupResult.RIGHT){
                    Toast.makeText(this, "RIGHT", Toast.LENGTH_SHORT).show();
                }else if(result == PopupResult.IMAGE){
                    Toast.makeText(this, "IMAGE", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
