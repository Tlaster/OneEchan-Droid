package moe.tlaster.oneechan.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import moe.tlaster.oneechan.R;
import moe.tlaster.oneechan.listener.ItemClickedListener;
import moe.tlaster.oneechan.model.ListResult;
import moe.tlaster.oneechan.viewholder.ViewHolderBase;

/**
 * Created by Tlaster on 2016/8/19.
 */
public class MainListAdapter extends RecyclerView.Adapter<ViewHolderBase> {

    private List<ItemClickedListener> listeners = new ArrayList<>();

    private List<ListResult> mItems;

    public List<ListResult> getItems() {
        return mItems;
    }

    public MainListAdapter(List<ListResult> items) {
        mItems = items;
    }

    public void add(List<ListResult> items){
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_main, parent, false);
        ViewHolderBase vh = new ViewHolderBase(itemView, R.id.name_list_main, R.id.update_at_list_main);
        itemView.setOnClickListener(v -> {
            for (ItemClickedListener listener : listeners) {
                listener.itemClicked(vh.getLayoutPosition());
            }
        });
        return vh;
    }

    public void addItemClickedListener(ItemClickedListener toAdd) {
        listeners.add(toAdd);
    }

    @Override
    public void onBindViewHolder(ViewHolderBase holder, int position) {
        holder.setText(R.id.name_list_main,getItems().get(position).getName())
                .setText(R.id.update_at_list_main,getItems().get(position).getUpdated_At().toString());
    }

    @Override
    public int getItemCount() {
        return getItems() == null ? 0 : getItems().size();
    }
}
