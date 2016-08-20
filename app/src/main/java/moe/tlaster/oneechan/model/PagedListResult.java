package moe.tlaster.oneechan.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tlaster on 2016/8/19.
 */
public class PagedListResult {

    @SerializedName("currentPage")
    private int mCurrentPage;
    @SerializedName("maxPage")
    private int mMaxPage;
    @SerializedName("itemCount")
    private int mItemCount;
    @SerializedName("maxCount")
    private int mMaxCount;
    @SerializedName("list")
    private List<ListResult> mList;

    public void setList(List<ListResult> list) {
        mList = list;
    }

    public List<ListResult> getList() {
        return mList;
    }

    public void setMaxCount(int maxCount) {
        mMaxCount = maxCount;
    }

    public int getMaxCount() {
        return mMaxCount;
    }

    public void setItemCount(int itemCount) {
        mItemCount = itemCount;
    }

    public int getItemCount() {
        return mItemCount;
    }

    public void setMaxPage(int maxPage) {
        mMaxPage = maxPage;
    }

    public int getMaxPage() {
        return mMaxPage;
    }

    public void setCurrentPage(int currentPage) {
        mCurrentPage = currentPage;
    }

    public int getCurrentPage() {
        return mCurrentPage;
    }
}