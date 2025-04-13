package com.itheima.topline.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.itheima.PullToRefreshView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.itheima.topline.R;
import com.itheima.topline.adapter.PythonListAdapter;
import com.itheima.topline.bean.PythonBean;
import com.itheima.topline.utils.Constant;
import com.itheima.topline.utils.JsonParse;
import com.itheima.topline.view.SwipeBackLayout;
import com.itheima.topline.view.WrapRecyclerView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class PythonActivity extends AppCompatActivity {

    private SwipeBackLayout layout;
    //    private PullToRefreshView mPullToRefreshView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private WrapRecyclerView recycleView;
    public static final int REFRESH_DELAY = 1000;
    public static final int MSG_PYTHON_OK = 1;
    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;
    private MHandler mHandler;
    private PythonListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
                R.layout.base, null);
        layout.attachToActivity(this);
        setContentView(R.layout.fragment_home);
        mHandler = new MHandler();
        initData();
        initView();
    }

    private void initView() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("Python学科");
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(
                R.color.rdTextColorPress));
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setVisibility(View.VISIBLE);
        recycleView = (WrapRecyclerView) findViewById(R.id.recycler_view);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PythonListAdapter();
        recycleView.setAdapter(adapter);

//        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
//        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mPullToRefreshView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mPullToRefreshView.setRefreshing(false);
//                        initData();
//                    }
//                }, REFRESH_DELAY);
//            }
//        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        initData();
                    }
                }, REFRESH_DELAY);
            }
        });

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PythonActivity.this.finish();
            }
        });
    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Constant.WEB_SITE + Constant.REQUEST_PYTHON_URL).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String res = response.body().string();
                Message msg = new Message();
                msg.what = MSG_PYTHON_OK;
                msg.obj = res;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

        });
    }

    class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case MSG_PYTHON_OK:
                    if (msg.obj != null) {
                        String vlResult = (String) msg.obj;
                        List<PythonBean> pythonList = JsonParse.getInstance().getPythonList(vlResult);
                        adapter.setData(pythonList);
                    }
                    break;
            }
        }
    }

}