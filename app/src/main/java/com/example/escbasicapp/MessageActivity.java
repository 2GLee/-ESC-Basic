package com.example.escbasicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MessageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText phoneNum;
    private EditText content;
    private FloatingActionButton send;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        setUpUI();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String getPhoneNum = getIntent().getStringExtra("phone_num");

        phoneNum.setText(getPhoneNum);
        phoneNum.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    private void setUpUI() {
        toolbar = findViewById(R.id.message_toolbar);
        phoneNum = findViewById(R.id.message_et_phone);
        content = findViewById(R.id.message_et_content);
        send = findViewById(R.id.message_fab_send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNum.getText().toString(),null,content.getText().toString(),null,null);
                    finish();
                    Toast.makeText(MessageActivity.this,"Successs",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText(MessageActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();

        }

        return super.onOptionsItemSelected(item);
    }
}