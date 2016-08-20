package moe.tlaster.oneechan;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.litesuits.http.LiteHttp;
import com.litesuits.http.listener.HttpListener;
import com.litesuits.http.request.JsonAbsRequest;
import com.litesuits.http.request.JsonRequest;
import com.litesuits.http.response.Response;

import moe.tlaster.oneechan.Adapter.DetailListAdapter;
import moe.tlaster.oneechan.Adapter.MainListAdapter;
import moe.tlaster.oneechan.helper.HttpHelper;
import moe.tlaster.oneechan.helper.LanguageHelper;
import moe.tlaster.oneechan.model.DetailResult;
import moe.tlaster.oneechan.model.PagedListResult;

public class DetailActivity extends AppCompatActivity {

    private int mID;
    private String mName;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mName = getIntent().getExtras().getString("name");
        mID = getIntent().getExtras().getInt("id");
        setContentView(R.layout.activity_main);
        setTitle(mName);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_activity);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, displayMetrics.heightPixels > displayMetrics.widthPixels ? 2 : 3));
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresher_main_activity);
        assert mSwipeRefreshLayout != null;
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(this::refresh);
        mSwipeRefreshLayout.post(()->mSwipeRefreshLayout.setRefreshing(true));
        refresh();
    }

    private void refresh(){
        String url = String.format("https://oneechan.moe/%s/api/detail?id=%d", LanguageHelper.prefLang(), mID);
        JsonAbsRequest<DetailResult> jsonAbsRequest = new JsonRequest<>(url,DetailResult.class);
        jsonAbsRequest.setHttpListener(new HttpListener<DetailResult>() {
            @Override
            public void onSuccess(DetailResult pagedListResult, Response<DetailResult> response) {
                DetailListAdapter adapter = new DetailListAdapter(pagedListResult.getList());
                adapter.addItemClickedListener(position -> itemClicked(position));
                mRecyclerView.setAdapter(adapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        HttpHelper.getLiteHttp().executeAsync(jsonAbsRequest);
    }

    private void itemClicked(int position) {

    }
}
