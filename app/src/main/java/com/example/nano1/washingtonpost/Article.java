package com.example.nano1.washingtonpost;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by nano1 on 1/29/2016.
 */
public class Article implements Parcelable {
    String articleTitle;
    String articleContent;
    Date articleDate;


    public Article(String title, String content, Date date){
        this.articleTitle = title;
        this.articleContent = content;
        this.articleDate = date;
    }


    protected Article(Parcel in) {
        articleTitle = in.readString();
        articleContent = in.readString();
        long tmpPostDate = in.readLong();
        articleDate = tmpPostDate != -1 ? new Date(tmpPostDate) : null;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(articleTitle);
        dest.writeString(articleContent);
        dest.writeLong(articleDate != null ? articleDate.getTime() : -1L);
    }
}
