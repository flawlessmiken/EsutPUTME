package com.honmichael.esutputme.model;

import android.os.Parcel;
import android.os.Parcelable;

public class HomeItemModel implements Parcelable  {


    private String model_image;
    private String name;
    private String sub_title;

    public HomeItemModel(String sub_title){
        this.sub_title = sub_title;
    }


    public HomeItemModel(String model_image, String name, String sub_title) {
        this.model_image = model_image;
        this.name = name;
        this.sub_title = sub_title;
    }

    protected HomeItemModel(Parcel in) {
        model_image = in.readString();
        name = in.readString();
        sub_title = in.readString();
    }

    public static final Creator<HomeItemModel> CREATOR = new Creator<HomeItemModel>() {
        @Override
        public HomeItemModel createFromParcel(Parcel in) {
            return new HomeItemModel(in);
        }

        @Override
        public HomeItemModel[] newArray(int size) {
            return new HomeItemModel[size];
        }
    };

    public String getModel_image() {
        return model_image;
    }

    public void setModel_image(String model_image) {
        this.model_image = model_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(model_image);
        parcel.writeString(name);
        parcel.writeString(sub_title);
    }
}
