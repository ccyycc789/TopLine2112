package com.itheima.topline.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.itheima.topline.R;
import com.itheima.topline.activity.AndroidCountActivity;
import com.itheima.topline.activity.JavaCountActivity;
import com.itheima.topline.utils.BuilderManager;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;


public class CountFragment extends Fragment {
    private BoomMenuButton bmb;

    public CountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_count, container, false);
        bmb = (BoomMenuButton) view.findViewById(R.id.bmb);
        assert bmb != null;
        //设置点击圆形菜单后显示的多个按钮为圆形且带文本的
        bmb.setButtonEnum(ButtonEnum.TextInsideCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_9_1);//设置右下角圆形菜单中有9个圆形
        //设置点击右下角圆形菜单后显示的按钮为9个圆形Button
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_9_1);
        
        // 添加9个按钮的构建器
        for (int i = 0; i < 9; i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .normalImageRes(BuilderManager.getImageResource())
                    .normalTextRes(BuilderManager.getTextResource());
            final int buttonIndex = i;
            builder.listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                    switch (buttonIndex) {
                        case 0: //跳转到Android统计详情界面
                            Intent android = new Intent(getActivity(), AndroidCountActivity.class);
                            startActivity(android);
                            break;
                        case 1: //跳转到Java统计详情界面
                            Intent java = new Intent(getActivity(), JavaCountActivity.class);
                            startActivity(java);
                            break;
                    }
                }
            });
            bmb.addBuilder(builder);
        }
        return view;
    }
}