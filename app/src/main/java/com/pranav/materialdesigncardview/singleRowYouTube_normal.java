package com.pranav.materialdesigncardview;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pranav on 9/7/2016.
 */
public class singleRowYouTube_normal implements Parcelable {
    String id;
    String title;
    String thumbnail;

    public singleRowYouTube_normal(String id, String title, String thumbnail) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    protected singleRowYouTube_normal(Parcel in) {
        id = in.readString();
        title = in.readString();
        thumbnail = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(thumbnail);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<singleRowYouTube_normal> CREATOR = new Parcelable.Creator<singleRowYouTube_normal>() {
        @Override
        public singleRowYouTube_normal createFromParcel(Parcel in) {
            return new singleRowYouTube_normal(in);
        }

        @Override
        public singleRowYouTube_normal[] newArray(int size) {
            return new singleRowYouTube_normal[size];
        }
    };
}