package com.honmichael.esutputme.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Subjects implements Parcelable {

    private String name;
    private Boolean isCompulsory;

    public Subjects(String name, Boolean isCompulsory) {
        this.name = name;
        this.isCompulsory = isCompulsory;
    }

    protected Subjects(Parcel in) {
        name = in.readString();
        byte tmpIsCompulsory = in.readByte();
        isCompulsory = tmpIsCompulsory == 0 ? null : tmpIsCompulsory == 1;
    }

    public static final Creator<Subjects> CREATOR = new Creator<Subjects>() {
        @Override
        public Subjects createFromParcel(Parcel in) {
            return new Subjects(in);
        }

        @Override
        public Subjects[] newArray(int size) {
            return new Subjects[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCompulsory() {
        return isCompulsory;
    }

    public void setCompulsory(Boolean compulsory) {
        isCompulsory = compulsory;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeByte((byte) (isCompulsory == null ? 0 : isCompulsory ? 1 : 2));
    }
}
