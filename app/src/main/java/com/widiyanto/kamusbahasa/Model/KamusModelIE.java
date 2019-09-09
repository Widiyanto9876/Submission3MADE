package com.widiyanto.kamusbahasa.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class KamusModelIE implements Parcelable {
    private int id;
    private String text;
    private String detail;

    public KamusModelIE(){

    }

    public KamusModelIE(String text, String detail){
        this.text = text;
        this.detail = detail;
    }

    public KamusModelIE(int id, String text, String detail){
        this.id = id;
        this.text = text;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.text);
        dest.writeString(this.detail);
    }

    protected KamusModelIE(Parcel in) {
        this.id = in.readInt();
        this.text = in.readString();
        this.detail = in.readString();
    }

    public static final Parcelable.Creator<KamusModelIE> CREATOR = new Parcelable.Creator<KamusModelIE>() {
        @Override
        public KamusModelIE createFromParcel(Parcel source) {
            return new KamusModelIE(source);
        }

        @Override
        public KamusModelIE[] newArray(int size) {
            return new KamusModelIE[size];
        }
    };

}

