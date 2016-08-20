package moe.tlaster.oneechan.model;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by Tlaster on 2016/8/20.
 */
public class DetailList {
    @SerializedName("set")
    private double mSet;
    @SerializedName("clickCount")
    private int mClickCount;
    @SerializedName("fileThumb")
    private String mFileThumb;
    @SerializedName("created_At")
    private String mCreated_At;

    public void setSet(double set) {
        mSet = set;
    }

    public void setClickCount(int clickCount) {
        mClickCount = clickCount;
    }

    public void setFileThumb(String fileThumb) {
        mFileThumb = fileThumb;
    }

    public void setCreated_At(String created_At) {
        mCreated_At = created_At;
    }

    public double getSet() {
        return mSet;
    }

    public int getClickCount() {
        return mClickCount;
    }

    public String getFileThumb() {
        return mFileThumb;
    }

    public String getCreated_At() {
        return mCreated_At;
    }
}
