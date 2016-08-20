package moe.tlaster.oneechan.model;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by Tlaster on 2016/8/19.
 */
public class ListResult {

    @SerializedName("name")
    private String mName;
    @SerializedName("id")
    private int mID;
    @SerializedName("updated_At")
    private String mUpdated_At;

    public String getUpdated_At() {
        return mUpdated_At;
    }

    public void setUpdated_At(String updated_At) {
        mUpdated_At = updated_At;
    }

    public int getId() {
        return mID;
    }

    public void setId(int ID) {
        mID = ID;
    }

    public String getName() {
        return mName;
    }
    public void setName(String name) {
        mName = name;
    }

}
