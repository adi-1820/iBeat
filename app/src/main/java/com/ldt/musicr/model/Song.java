package com.ldt.musicr.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ldt.musicr.model.core.Entity;

import java.util.ArrayList;
import java.util.List;

public class Song extends Media implements Parcelable, Entity {
    public static final Song EMPTY_SONG = new Song(-1, "", -1, -1, -1, "", -1, -1, "", -1, "");

    public final int id;
    public String title;
    public final int trackNumber;
    public final int year;
    public final long duration;
    public final String data;
    public final long dateModified;
    public final int albumId;
    public final String albumName;
    public final int artistId;
    public final String artistName;

    private final String uid;
    private float searchScore = 0f;
    private final List<Integer> spanPosArray = new ArrayList<>();
    private String logMessage = null;

    public Song(int id, String title, int trackNumber, int year, long duration, String data, long dateModified, int albumId, String albumName, int artistId, String artistName) {
        this.id = id;
        this.title = title;
        this.trackNumber = trackNumber;
        this.year = year;
        this.duration = duration;
        this.data = data;
        this.dateModified = dateModified;
        this.albumId = albumId;
        this.albumName = albumName;
        this.artistId = artistId;
        this.artistName = artistName;
        this.uid = "song_" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (id != song.id) return false;
        if (trackNumber != song.trackNumber) return false;
        if (year != song.year) return false;
        if (duration != song.duration) return false;
        if (dateModified != song.dateModified) return false;
        if (albumId != song.albumId) return false;
        if (artistId != song.artistId) return false;
        if (title != null ? !title.equals(song.title) : song.title != null) return false;
        if (data != null ? !data.equals(song.data) : song.data != null) return false;
        if (albumName != null ? !albumName.equals(song.albumName) : song.albumName != null)
            return false;
        return artistName != null ? artistName.equals(song.artistName) : song.artistName == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + trackNumber;
        result = 31 * result + year;
        result = 31 * result + (int) (duration ^ (duration >>> 32));
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (int) (dateModified ^ (dateModified >>> 32));
        result = 31 * result + albumId;
        result = 31 * result + (albumName != null ? albumName.hashCode() : 0);
        result = 31 * result + artistId;
        result = 31 * result + (artistName != null ? artistName.hashCode() : 0);
        return result;
    }

    @NonNull
    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", trackNumber=" + trackNumber +
                ", year=" + year +
                ", duration=" + duration +
                ", data='" + data + '\'' +
                ", dateModified=" + dateModified +
                ", albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeInt(this.trackNumber);
        dest.writeInt(this.year);
        dest.writeLong(this.duration);
        dest.writeString(this.data);
        dest.writeLong(this.dateModified);
        dest.writeInt(this.albumId);
        dest.writeString(this.albumName);
        dest.writeInt(this.artistId);
        dest.writeString(this.artistName);
    }

    protected Song(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.trackNumber = in.readInt();
        this.year = in.readInt();
        this.duration = in.readLong();
        this.data = in.readString();
        this.dateModified = in.readLong();
        this.albumId = in.readInt();
        this.albumName = in.readString();
        this.artistId = in.readInt();
        this.artistName = in.readString();
        this.uid = "song_"+ this.id;
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        public Song createFromParcel(Parcel source) {
            return new Song(source);
        }

        public Song[] newArray(int size) {
            return new Song[size];
        }
    };


    @NonNull
    @Override
    public String getUid() {
        return uid;
    }

    @NonNull
    @Override
    public String getDisplayName() {
        return title;
    }

    @Override
    public float getSearchScore() {
        return searchScore;
    }

    @Override
    public void setSearchScore(float searchScore) {
        this.searchScore = searchScore;
    }

    @Nullable
    @Override
    public String getLogMessage() {
        return logMessage;
    }

    @Override
    public void setLogMessage(@Nullable String logMessage) {
        this.logMessage = logMessage;
    }

    @NonNull
    @Override
    public List<Integer> getSpanPosList() {
        return spanPosArray;
    }
}
