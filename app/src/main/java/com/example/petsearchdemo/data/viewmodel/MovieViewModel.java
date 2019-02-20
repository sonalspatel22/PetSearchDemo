package com.example.petsearchdemo.data.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieViewModel implements Parcelable {

    private long mId;
    private String mTitle;
    private String mPosterUrl;
    private String mPlotSynopsis;
    private double mUserRating;
    private String mReleaseDate;
    private String laungage;
    private int budget;
    private String overview;
    private int revenue;
    private int totaltime;

    public String getGeners() {
        return geners;
    }

    private String geners;

    protected MovieViewModel(Parcel in) {
        this.mId = in.readLong();
        this.mTitle = in.readString();
        this.mPosterUrl = in.readString();
        this.mPlotSynopsis = in.readString();
        this.mUserRating = in.readDouble();
        this.mReleaseDate = in.readString();
        this.laungage = in.readString();
        this.overview = in.readString();
        this.budget = in.readInt();
        this.revenue = in.readInt();
        this.totaltime = in.readInt();
        this.geners = in.readString();
    }

    public MovieViewModel(long id, String title, String posterUrl, String plotSynopsis, double userRating, String releaseDate, String lang, String overView, int budget, int revenue, int time, String geners) {
        this.mId = id;
        this.mTitle = title;
        this.mPosterUrl = posterUrl;
        this.mPlotSynopsis = plotSynopsis;
        this.mUserRating = userRating;
        this.mReleaseDate = releaseDate;
        this.laungage = lang;
        this.budget = budget;
        this.overview = overView;
        this.revenue = revenue;
        this.totaltime = time;
        this.geners = geners;
    }

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPosterUrl() {
        return mPosterUrl;
    }

    public String getPlotSynopsis() {
        return mPlotSynopsis;
    }

    public double getUserRating() {
        return mUserRating;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getLaungage() {
        return laungage;
    }

    public int getBudget() {
        return budget;
    }

    public String getOverview() {
        return overview;
    }

    public int getRevenue() {
        return revenue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mId);
        dest.writeString(this.mTitle);
        dest.writeString(this.mPosterUrl);
        dest.writeString(this.mPlotSynopsis);
        dest.writeDouble(this.mUserRating);
        dest.writeString(this.mReleaseDate);
        dest.writeString(this.laungage);
        dest.writeString(this.overview);
        dest.writeInt(this.budget);
        dest.writeInt(this.revenue);
        dest.writeInt(this.totaltime);
        dest.writeString(this.geners);

    }

    public static final Creator<MovieViewModel> CREATOR = new Creator<MovieViewModel>() {
        @Override
        public MovieViewModel createFromParcel(Parcel source) {
            return new MovieViewModel(source);
        }

        @Override
        public MovieViewModel[] newArray(int size) {
            return new MovieViewModel[size];
        }
    };

    public int getTotaltime() {
        return totaltime;
    }

}
