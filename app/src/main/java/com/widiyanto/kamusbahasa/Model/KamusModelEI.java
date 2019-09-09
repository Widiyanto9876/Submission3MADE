package com.widiyanto.kamusbahasa.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class KamusModelEI implements Parcelable {

    private int id;
    private String text;
    private String detail;

    public KamusModelEI(){

    }

    public KamusModelEI(String text, String detail){
        this.text = text;
        this.detail = detail;
    }

    public KamusModelEI(int id, String text, String detail){
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

    protected KamusModelEI(Parcel in) {
        this.id = in.readInt();
        this.text = in.readString();
        this.detail = in.readString();
    }

    public static final Parcelable.Creator<KamusModelEI> CREATOR = new Parcelable.Creator<KamusModelEI>() {
        @Override
        public KamusModelEI createFromParcel(Parcel source) {
            return new KamusModelEI(source);
        }

        @Override
        public KamusModelEI[] newArray(int size) {
            return new KamusModelEI[size];
        }
    };

}

