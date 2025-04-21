package com.itheima.topline.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.itheima.topline.R;
import com.itheima.topline.adapter.VideoListAdapter;
import com.itheima.topline.bean.VideoBean;
import com.itheima.topline.utils.Constant;
import com.itheima.topline.utils.JsonParse;
import com.itheima.topline.view.WrapRecyclerView;
import com.refresh.PullToRefreshView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class VideoFragment extends Fragment {
    private SwipeRefreshLayout mPullToRefreshView;
    private WrapRecyclerView recycleView;
    public static final int REFRESH_DELAY = 1000;
    public static final int MSG_VIDEO_OK = 1; //获取数据
    private MHandler mHandler;    //事件捕获
    private VideoListAdapter adapter;

    public VideoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mHandler = new MHandler();
        initData();
        View view = initView(inflater, container);
        return view;
    }

    @SuppressLint("MissingInflatedId")
    private View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        recycleView = (WrapRecyclerView) view.findViewById(R.id.recycler_view);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new VideoListAdapter(getActivity());
        recycleView.setAdapter(adapter);
        mPullToRefreshView = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mPullToRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                        initData();
                    }
                }, REFRESH_DELAY);
            }
        });
        return view;
    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Constant.WEB_SITE + Constant.REQUEST_VIDEO_URL).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Message msg = new Message();
                msg.what = MSG_VIDEO_OK;
                msg.obj = res;
                mHandler.sendMessage(msg);
            }
        });
    }

    class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case MSG_VIDEO_OK:
                    if (msg.obj != null) {
                        String vlResult = (String) msg.obj;
                        List<VideoBean> videolist = JsonParse.getInstance().getVideoList(vlResult);
                        adapter.setData(videolist);
                    }
                    break;
            }
        }
    }
}