package moe.tlaster.oneechan.Adapter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import moe.tlaster.oneechan.R;
import moe.tlaster.oneechan.listener.ItemClickedListener;
import moe.tlaster.oneechan.model.DetailList;
import moe.tlaster.oneechan.viewholder.ViewHolderBase;

/**
 * Created by Tlaster on 2016/8/20.
 */
public class DetailListAdapter extends RecyclerView.Adapter<ViewHolderBase> {

    private List<ItemClickedListener> listeners = new ArrayList<>();

    private List<DetailList> mItems;

    public List<DetailList> getItems() {
        return mItems;
    }

    public DetailListAdapter(List<DetailList> items) {
        super();
        mItems = items;
    }

    public void addItemClickedListener(ItemClickedListener toAdd) {
        listeners.add(toAdd);
    }

    @Override
    public ViewHolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_detail, parent, false);
        ViewHolderBase vh = new ViewHolderBase(itemView, R.id.name_list_detail, R.id.clickcount_list_detail, R.id.image_list_detail);
        itemView.setOnClickListener(v->{
            for (ItemClickedListener listener : listeners) {
                listener.itemClicked(vh.getLayoutPosition());
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolderBase holder, int position) {
        holder.setText(R.id.name_list_detail, String.valueOf(getItems().get(position).getSet())).setText(R.id.clickcount_list_detail, getItems().get(position).getClickCount() + " Views");
        if (getItems().get(position).getFileThumb() == null || getItems().get(position).getFileThumb().length() == 0)
            holder.setBackgroundColor(R.id.image_list_detail, Color.parseColor("#c71585")).setImageResource(R.id.image_list_detail, R.drawable.splashscreen);
        else
            ImageLoader.getInstance().loadImage(getItems().get(position).getFileThumb(), new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    holder.setImageBitmap(R.id.image_list_detail, loadedImage);
                }
            });
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }
}
