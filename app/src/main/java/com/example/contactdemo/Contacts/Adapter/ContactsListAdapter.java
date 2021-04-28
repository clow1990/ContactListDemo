package com.example.contactdemo.Contacts.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.contactdemo.Contacts.Model.ContactsListItems;
import com.example.contactdemo.R;

import java.util.List;

public class ContactsListAdapter extends BaseAdapter {
    private Context _mcont;
    private List<ContactsListItems> itemList;

    public ContactsListAdapter(Context context, List<ContactsListItems> itemList) {
        this._mcont = context;
        this.itemList = itemList;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder v = null;
        if (convertView == null) {
            v = new ViewHolder();
            convertView = LayoutInflater.from(_mcont).inflate(R.layout.row_contact_list_data, null);
            v.contacts_name = (TextView) convertView.findViewById(R.id.txt_contacts_full_name);
            convertView.setTag(v);
        } else {
            v = (ViewHolder) convertView.getTag();
        }
        v.contacts_name.setText(itemList.get(position).getFirstName() + " " + itemList.get(position).getLastName());
        return convertView;
    }

    class ViewHolder {
        TextView contacts_name;
    }

}