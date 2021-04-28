package com.example.contactdemo.Contacts.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.contactdemo.Contacts.Adapter.ContactsListAdapter;
import com.example.contactdemo.Contacts.Model.ContactsListItems;
import com.example.contactdemo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.example.contactdemo.Utils.Util.loadJSONFromAsset;

public class ContactsDetailsActivity extends AppCompatActivity {
    ContactsListItems contactsObj;
    EditText et_value_firstName, et_value_lastName, et_value_email, et_value_phone;
    TextView tb_txt1, tb_txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_details);
        setID();
        loadData();
        setEvent();
    }

    void loadData() {
        contactsObj = getIntent().getParcelableExtra("contactsObj");
        et_value_firstName.setText(contactsObj.getFirstName());
        et_value_lastName.setText(contactsObj.getLastName());
        et_value_email.setText(contactsObj.getEmail());
        et_value_phone.setText(contactsObj.getPhone());
    }

    void setID() {
        et_value_firstName = findViewById(R.id.et_value_firstName);
        et_value_lastName = findViewById(R.id.et_value_lastName);
        et_value_email = findViewById(R.id.et_value_email);
        et_value_phone = findViewById(R.id.et_value_phone);
        tb_txt1 = findViewById(R.id.tb_txt1);
        tb_txt2 = findViewById(R.id.tb_txt2);
    }

    void setEvent() {
        tb_txt1.setOnClickListener(v -> {
            if (checkIsValid())
                finish();
        });
        tb_txt2.setOnClickListener(v -> {
            if (checkIsValid()){
                Intent intent = new Intent();
                intent.putExtra("returnContactsObj", new ContactsListItems(
                        contactsObj.getId(),
                        et_value_firstName.getText().toString(),
                        et_value_lastName.getText().toString(),
                        et_value_email.getText().toString(),
                        et_value_phone.getText().toString()));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    boolean checkIsValid() {
        Boolean hasError = false;
        if (et_value_firstName.length() <= 0) {
            et_value_firstName.setError(getText(R.string.required));
            hasError = true;
        }
        if (et_value_lastName.length() <= 0) {
            et_value_lastName.setError(getText(R.string.required));
            hasError = true;
        }
        if (hasError) {
            return false;
        } else return true;
    }


}