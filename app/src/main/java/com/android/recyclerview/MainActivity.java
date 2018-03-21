package com.android.recyclerview;

import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import com.android.recyclerview.base.BaseRvAdapter;
import com.android.recyclerview.base.BaseRvCell;
import com.android.recyclerview.info.Image;
import com.android.recyclerview.info.Info;
import com.android.recyclerview.info.Progress;
import com.android.recyclerview.info.Switch;
import com.android.recyclerview.info.Temperature;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.MemoryCacheAdapter;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private Unbinder unbinder;

    private BaseRvAdapter<BaseRvCell> adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        adapter = new BaseRvAdapter<>();

        initView();
    }

    public void feature1() {
        Log.d(TAG, "developing feature 1");
    }

    public void feature3() {
        Log.d(TAG, "developing feature 3");
    }

    private void initView() {
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                switch (viewType) {
                    case 1:
                    case 4:
                        return 1;
                    default:
                        return 2;
                }
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.top = 20;
                outRect.right = 20;
                outRect.bottom = 20;
                outRect.left = 20;
            }
        });
        recyclerView.setAdapter(adapter);

        requestData();
    }

    private void makeRandomData() {
        adapter.clear();

        int j;
        for (int i = 0; i < 20; i++) {
            j = (int) (Math.random() * 20 + 1);
            if (j < 5) {
                adapter.add(new ImageCell(new Image("https://source.unsplash.com/400x300/?nature,water")));
            } else if (j < 10) {
                adapter.add(new SwitchCell(new Switch("开关 " + i, i % 2 == 0)));
            } else if (j < 15) {
                adapter.add(new SeekBarCell(new Progress(100, i * 4)));
            } else {
                adapter.add(new TextCell(new Temperature("温度 " + i, i * 2 + "°C")));
            }
        }
    }

    private void requestData() {
        adapter.clear();
        String url = "http://www.wanandroid.com/tools/mockapi/3883/info";
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonString = response.body().string();
                Gson gson = new Gson();
                Info info = gson.fromJson(jsonString, Info.class);
                final List<BaseRvCell> switchCells = new ArrayList<>();
                final List<BaseRvCell> seekBarCells = new ArrayList<>();
                final List<BaseRvCell> imageCells = new ArrayList<>();
                final List<BaseRvCell> textCells = new ArrayList<>();
                for (Switch s : info.switchList) {
                    switchCells.add(new SwitchCell(s));
                }
                for (Progress p : info.progressList) {
                    seekBarCells.add(new SeekBarCell(p));
                }
                for (Image i : info.imageList) {
                    imageCells.add(new ImageCell(i));
                }
                for (Temperature t : info.temperatureList) {
                    textCells.add(new TextCell(t));
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addAll(switchCells);
                        adapter.addAll(seekBarCells);
                        adapter.addAll(imageCells);
                        adapter.addAll(textCells);
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simle_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.request_data:
                requestData();
                break;
            case R.id.random_data:
                makeRandomData();
                break;
            case R.id.clear:
                adapter.clear();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
