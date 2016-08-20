package moe.tlaster.oneechan;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.support.v4.widget.*;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.litesuits.http.LiteHttp;
import com.litesuits.http.listener.HttpListener;
import com.litesuits.http.parser.DataParser;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.JsonAbsRequest;
import com.litesuits.http.request.JsonRequest;
import com.litesuits.http.response.Response;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.logging.Logger;

import moe.tlaster.oneechan.Adapter.MainListAdapter;
import moe.tlaster.oneechan.model.*;
import moe.tlaster.oneechan.helper.*;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean mIsLoading;
    private boolean mHasMore;
    private int mPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(this).build());
        HttpHelper.Initialize(this);

        setContentView(R.layout.activity_main);
        setTitle("OneEchan");
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_main_activity);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(1)){
                    loadMore();
                }
            }
        });
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresher_main_activity);
        assert mSwipeRefreshLayout != null;
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(this::refresh);
        mSwipeRefreshLayout.post(()->mSwipeRefreshLayout.setRefreshing(true));
        refresh();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        return super.onCreateOptionsMenu(menu);
    }


    private void refresh(){
        mPage = 0;
        String url = String.format("https://oneechan.moe/%s/api/list?page=%d",LanguageHelper.prefLang(), mPage++);
        JsonAbsRequest<PagedListResult> jsonAbsRequest = new JsonRequest<>(url,PagedListResult.class);
        jsonAbsRequest.setHttpListener(new HttpListener<PagedListResult>() {
            @Override
            public void onSuccess(PagedListResult pagedListResult, Response<PagedListResult> response) {
                mHasMore = mPage < pagedListResult.getMaxPage();
                MainListAdapter adapter = new MainListAdapter(pagedListResult.getList());
                adapter.addItemClickedListener(position -> itemClicked(position));
                mRecyclerView.setAdapter(adapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        HttpHelper.getLiteHttp().executeAsync(jsonAbsRequest);
    }

    private void itemClicked(int position){
        ListResult item = ((MainListAdapter)mRecyclerView.getAdapter()).getItems().get(position);
        Intent intent = new Intent(this,DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("name", item.getName());
        bundle.putInt("id", item.getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void loadMore(){
        if (mIsLoading || !mHasMore) return;
        mIsLoading = true;


        String url = String.format("https://oneechan.moe/%s/api/list?page=%d",LanguageHelper.prefLang(), mPage++);
        JsonAbsRequest<PagedListResult> jsonAbsRequest = new JsonRequest<>(url,PagedListResult.class);
        jsonAbsRequest.setHttpListener(new HttpListener<PagedListResult>() {
            @Override
            public void onSuccess(PagedListResult pagedListResult, Response<PagedListResult> response) {
                mHasMore = mPage < pagedListResult.getMaxPage();
                ((MainListAdapter)mRecyclerView.getAdapter()).add(pagedListResult.getList());
                mIsLoading = false;
            }
        });
        HttpHelper.getLiteHttp().executeAsync(jsonAbsRequest);
    }

}
