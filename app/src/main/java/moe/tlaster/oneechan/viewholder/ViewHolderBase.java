package moe.tlaster.oneechan.viewholder;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tlaster on 2016/8/19.
 */
public class ViewHolderBase extends RecyclerView.ViewHolder {

    private final SparseArray<View> mViews = new SparseArray<>();

    public ViewHolderBase(View itemView, int... ids) {
        super(itemView);
        for (int id: ids) {
            mViews.append(id, itemView.findViewById(id));
        }
    }

    public ViewHolderBase setText(int id, String text){
        ((TextView)mViews.get(id)).setText(text);
        return this;
    }

    public ViewHolderBase setBackgroundResource(int id, int res){
        mViews.get(id).setBackgroundResource(res);
        return this;
    }

    public ViewHolderBase setBackgroundColor(int id, int color){
        mViews.get(id).setBackgroundColor(color);
        return this;
    }

    public ViewHolderBase setImageBitmap(int id, Bitmap bm){
        ((ImageView)mViews.get(id)).setImageBitmap(bm);
        return this;
    }

    public ViewHolderBase setImageResource(int id, int res){
        ((ImageView)mViews.get(id)).setImageResource(res);
        return this;
    }
}
