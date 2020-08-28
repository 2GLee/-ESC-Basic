package com.example.escbasicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.textfield.TextInputLayout;

public class AddEditActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextInputLayout name;
    private TextInputLayout phone;
    private TextInputLayout email;
    private String addEdit;
    private int position = -1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        setUpUI();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String phoneNum = getIntent().getStringExtra("phone_num");
        position = getIntent().getIntExtra("position",-1);
        addEdit = getIntent().getStringExtra("add_edit");

        if(addEdit.equals("add")){
            phone.getEditText().setText(phoneNum);
        } else if (addEdit.equals("edit")){
            name.getEditText().setText(DummyData.contacts.get(position).getName());
            phone.getEditText().setText(DummyData.contacts.get(position).getPhone());
            email.getEditText().setText(DummyData.contacts.get(position).getEmail());
        }

    }

    private void setUpUI(){
        toolbar = findViewById(R.id.addedit_toolbar);
        name = findViewById(R.id.addedit_til_name);
        phone = findViewById(R.id.addedit_til_phone);
        email = findViewById(R.id.addedit_til_email);

        phone.getEditText().addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_save:
                String inputName = name.getEditText().getText().toString();
                String inputPhone = phone.getEditText().getText().toString();
                String inputEmail = email.getEditText().getText().toString();

                if (inputName.trim().equals("")){
                    name.setError("필수 입력란입니다.");
                }
                else{
                    name.setError(null);
                }if (inputPhone.trim().equals("")){
                phone.setError("필수 입력란입니다.");
            }
            else{
                phone.setError(null);
            }

                if (addEdit.equals("add")){
                     if (!inputName.trim().equals("")&&!inputPhone.trim().equals("")){
                         Contact newContact = new Contact(inputName,inputPhone,inputEmail);
                         DummyData.contacts.add(newContact);
                         finish();
                     }
                }

                else if (addEdit.equals("edit")){
                    Contact editContact = DummyData.contacts.get(position);

                    if (!inputName.trim().equals("")&&!inputPhone.trim().equals("")){
                        editContact.setName(inputName);
                        editContact.setPhone(inputPhone);
                        editContact.setEmail(inputEmail);

                        finish();
                    }

                }
        }

        return super.onOptionsItemSelected(item);
    }
}