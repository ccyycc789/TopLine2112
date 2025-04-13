package com.itheima.topline.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.itheima.topline.R;
import com.itheima.topline.view.SwipeBackLayout;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

public class AndroidCountActivity extends AppCompatActivity {
    private TextView tv_main_title, tv_back, tv_intro;
    private RelativeLayout rl_title_bar;
    private SwipeBackLayout layout;
    private PieChartView chart;
    private PieChartData data;
    private boolean hasLabels = false;
    private boolean hasLabelsOutside = false;
    private boolean isExploded = false;
    private boolean hasLabelForSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 使用自定义布局初始化 SwipeBackLayout
        layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
                R.layout.base, null);
        layout.attachToActivity(this);
        // 设置当前 Activity 的布局
        setContentView(R.layout.activity_android_count);
        // 初始化 UI 和数据
        init();
    }

    private void init() {
        // 初始化标题栏
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("Android统计"); // 设置标题文字

        // 初始化标题栏背景
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.rdTextColorPress));

        // 初始化返回按钮
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setVisibility(View.VISIBLE); // 显示返回按钮
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidCountActivity.this.finish(); // 点击返回按钮时关闭 Activity
            }
        });

        // 初始化介绍文本
        tv_intro = (TextView) findViewById(R.id.tv_intro);
        tv_intro.setText(R.string.android_count_text);

        // 初始化饼图
        chart = (PieChartView) findViewById(R.id.chart);
        toggleLabels(); // 切换标签显示
        chart.startDataAnimation(); // 开始数据动画
    }

    private void generateData() {
        int numValues = 4; // 设置饼状图扇形的数量
        List<SliceValue> values = new ArrayList<>(); // 存储饼状图的扇形数据

        for (int i = 0; i < numValues; ++i) {
            switch (i + 1) {
                case 1: // 第一个扇形
                    SliceValue sliceValue1 = new SliceValue(i + 1, ChartUtils.COLOR_GREEN);
                    sliceValue1.setTarget(4); // 设置扇形的大小
                    sliceValue1.setLabel("月薪8-15k"); // 设置扇形的标签
                    values.add(sliceValue1);
                    break;

                case 2: // 第二个扇形
                    SliceValue sliceValue2 = new SliceValue(i + 1, ChartUtils.COLOR_VIOLET);
                    sliceValue2.setTarget(3);
                    sliceValue2.setLabel("月薪15-20k");
                    values.add(sliceValue2);
                    break;

                case 3: // 第三个扇形
                    SliceValue sliceValue3 = new SliceValue(i + 1, ChartUtils.COLOR_BLUE);
                    sliceValue3.setTarget(2);
                    sliceValue3.setLabel("月薪20-30k");
                    values.add(sliceValue3);
                    break;

                case 4: // 第四个扇形
                    SliceValue sliceValue4 = new SliceValue(i + 1, ChartUtils.COLOR_ORANGE);
                    sliceValue4.setTarget(1);
                    sliceValue4.setLabel("月薪30k+");
                    values.add(sliceValue4);
                    break;
            }
        }

        data = new PieChartData(values); // 创建饼图数据
        data.setHasLabels(hasLabels); // 设置是否显示标签
        data.setHasLabelsOnlyForSelected(hasLabelForSelected); // 设置是否只显示选中项的标签
        data.setHasLabelsOutside(hasLabelsOutside); // 设置标签是否显示在外部

        if (isExploded) {
            data.setSlicesSpacing(24); // 设置扇形间距（爆炸效果）
        }
        chart.setPieChartData(data); // 将数据设置到饼图中
    }

    private void toggleLabels() {
        hasLabels = !hasLabels; // 切换标签显示状态
        if (hasLabels) {
            hasLabelForSelected = false; // 如果显示标签，则不只显示选中项的标签
            chart.setValueSelectionEnabled(hasLabelForSelected); // 禁用值选择
            if (hasLabelsOutside) {
                chart.setCircleFillRatio(0.7f); // 设置饼图填充比例（标签在外部时）
            } else {
                chart.setCircleFillRatio(1.0f); // 设置饼图填充比例（标签在内部时）
            }
        }
        generateData(); // 重新生成数据以应用新的标签设置
    }
}