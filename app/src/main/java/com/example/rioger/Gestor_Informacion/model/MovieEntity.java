package com.example.rioger.Gestor_Informacion.model;

/**
 * Created by Rioger on 06/11/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class MovieEntity implements Parcelable {

    private Integer _id;
    private String _title;
    private String _overview;
    private String _releaseDate;
    private Double _voteAverage;

    public MovieEntity(Integer id, String title, String overview, String releaseDate, Double voteAverage) {
        this._id = id;
        this._title = title;
        this._overview = overview;
        this._releaseDate = releaseDate;
        this._voteAverage = voteAverage;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_overview() {
        return _overview;
    }

    public void set_overview(String _overview) {
        this._overview = _overview;
    }

    public String get_releaseDate() {
        return _releaseDate;
    }

    public void set_releaseDate(String _releaseDate) {
        this._releaseDate = _releaseDate;
    }

    public Double get_voteAverage() {
        return _voteAverage;
    }

    public void set_voteAverage(Double _voteAverage) {
        this._voteAverage = _voteAverage;
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "_id=" + _id +
                ", _title='" + _title + '\'' +
                ", _overview='" + _overview + '\'' +
                ", _releaseDate='" + _releaseDate + '\'' +
                ", _voteAverage=" + _voteAverage +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this._id);
        dest.writeString(this._title);
        dest.writeString(this._overview);
        dest.writeString(this._releaseDate);
        dest.writeDouble(this._voteAverage);
    }

    protected MovieEntity(Parcel in) {
        this._id = in.readInt();
        this._title = in.readString();
        this._overview = in.readString();
        this._releaseDate = in.readString();
        this._voteAverage = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<MovieEntity> CREATOR = new Parcelable.Creator<MovieEntity>() {
        @Override
        public MovieEntity createFromParcel(Parcel source) {
            return new MovieEntity(source);
        }

        @Override
        public MovieEntity[] newArray(int size) {
            return new MovieEntity[size];
        }
    };
}
