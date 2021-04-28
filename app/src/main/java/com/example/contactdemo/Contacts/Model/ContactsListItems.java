package com.example.contactdemo.Contacts.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactsListItems implements Parcelable {
    String id;
    String firstName;
    String lastName;
    String email;
    String phone;

    public ContactsListItems(){}

    public ContactsListItems(String id, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static final Creator<ContactsListItems> CREATOR = new Creator<ContactsListItems>() {
        @Override
        public ContactsListItems createFromParcel(Parcel in) {
            return new ContactsListItems(in);
        }

        @Override
        public ContactsListItems[] newArray(int size) {
            return new ContactsListItems[size];
        }
    };

    public ContactsListItems(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in){
        this.id = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.email);
        dest.writeString(this.phone);
    }
}
