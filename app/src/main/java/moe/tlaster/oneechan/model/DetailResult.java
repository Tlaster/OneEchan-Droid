package moe.tlaster.oneechan.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tlaster on 2016/8/20.
 */
public class DetailResult {
    @SerializedName("name")
    private String mName;
    @SerializedName("id")
    private int mID;
    @SerializedName("list")
    private List<DetailList> mList;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getId() {
        return mID;
    }

    public void setId(int ID) {
        mID = ID;
    }

    public List<DetailList> getList() {
        return mList;
    }

    public void setList(List<DetailList> list) {
        mList = list;
    }
}
