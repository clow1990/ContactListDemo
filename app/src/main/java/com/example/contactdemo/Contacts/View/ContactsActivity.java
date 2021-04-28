package com.example.contactdemo.Contacts.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
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

public class ContactsActivity extends AppCompatActivity {
    long mLastClickTime = 0;
    ListView listView;
    ArrayList<ContactsListItems> arrayList;
    ContactsListAdapter adapter;
    SwipeRefreshLayout refreshLayout;
    TextView txt_no_contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        setID();
        setEvent();
        assignArrayList();
        setAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    void setID() {
        listView = (ListView) findViewById(R.id.lst_contacts);
        refreshLayout = findViewById(R.id.refreshLayout);
        txt_no_contacts = findViewById(R.id.txt_no_contacts);
    }

    void setEvent() {
        refreshLayout.setOnRefreshListener(() -> {
            assignArrayList();
            setAdapter();
            reload();
            refreshLayout.setRefreshing(false);
        });

        listView.setOnItemClickListener((AdapterView.OnItemClickListener) (adapterView, view, i, l) -> {
            // Avoid double click
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            Intent intent = new Intent(ContactsActivity.this, ContactsDetailsActivity.class);
            intent.putExtra("contactsObj", arrayList.get(i));
            startActivityForResult(intent, 101);

        });
    }

    void setAdapter() {
        if (arrayList != null) {
            adapter = new ContactsListAdapter(this, arrayList);
            listView.setAdapter(adapter);
        }
    }

    void assignArrayList() {
        arrayList = new ArrayList<>();
        arrayList = new Gson().fromJson(loadJSONFromAsset(ContactsActivity.this, "data.json"), new TypeToken<List<ContactsListItems>>() {
        }.getType());
        if (arrayList == null) txt_no_contacts.setVisibility(View.VISIBLE);
        else {
            if (arrayList.size() < 1)
                txt_no_contacts.setVisibility(View.VISIBLE);
            else
                txt_no_contacts.setVisibility(View.GONE);
        }
    }

    void reload() {
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    replaceObj((ContactsListItems) extras.get("returnContactsObj"));
                    reload();
                }
            }
        }
    }

    void replaceObj(ContactsListItems contactsObj) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getId().equalsIgnoreCase(contactsObj.getId())) {
                arrayList.get(i).setFirstName(contactsObj.getFirstName());
                arrayList.get(i).setLastName(contactsObj.getLastName());
                arrayList.get(i).setEmail(contactsObj.getEmail());
                arrayList.get(i).setPhone(contactsObj.getPhone());
            }
        }
    }
}