package com.example.notes.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Note implements Parcelable {
    private final int name, description;
    private final Date dateCreated;

    protected Note(int name, int description) {
        this.name = name;
        this.description = description;
        dateCreated = new Date();
    }

    protected Note(Parcel in) {
        name = in.readInt();
        description = in.readInt();
        dateCreated = new Date();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public int getName() {
        return name;
    }

    public int getDescription() {
        return description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(name);
        dest.writeInt(description);
    }
}
