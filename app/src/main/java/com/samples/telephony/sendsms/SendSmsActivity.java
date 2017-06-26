package com.samples.telephony.sendsms;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendSmsActivity extends Activity
            implements View.OnClickListener{

    private EditText textSms;
    private EditText textNumber;
    private Button button;
    private SmsManager manager;
    private PendingIntent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        textSms = (EditText)findViewById(R.id.text);
        textNumber = (EditText)findViewById(R.id.number);
        button = (Button)findViewById(R.id.bSend);
        manager = SmsManager.getDefault();

        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String dest = textNumber.getText().toString();
        try {
            if (PhoneNumberUtils.isWellFormedSmsAddress(dest)){
                intent = PendingIntent.getActivity(this, 0, new Intent(this, SendSmsActivity.class), 0);

                manager.sendTextMessage(dest, null, textSms.getText().toString(), intent, null);

                Toast.makeText(SendSmsActivity.this, "Sms send", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(SendSmsActivity.this, "Error formed sms adress", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(SendSmsActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
