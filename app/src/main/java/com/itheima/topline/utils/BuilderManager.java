package com.itheima.topline.utils;

import com.itheima.topline.R;

public class BuilderManager {
    private static int[] imageResources = new int[]{
            R.drawable.bat,
            R.drawable.bear,
            R.drawable.bee,
            R.drawable.butterfly,
            R.drawable.cat,
            R.drawable.deer,
            R.drawable.dolphin,
            R.drawable.eagle,
            R.drawable.elephant,
            R.drawable.owl,
            R.drawable.peacock,
            R.drawable.pig,
            R.drawable.rat,
            R.drawable.snake,
            R.drawable.squirrel
    }; // 9个菜单的随机选择的图片

    private static int[] textResources = new int[]{
            R.string.android,
            R.string.java,
            R.string.python,
            R.string.php,
            R.string.c,
            R.string.ios,
            R.string.fore_end,
            R.string.ui,
            R.string.network
    }; // 9个菜单中的文本

    private static int imageResourceIndex = 0;
    private static int textResourceIndex = 0;

    public static int getImageResource() {
        if (imageResourceIndex >= imageResources.length)
            imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    public static int getTextResource() {
        if (textResourceIndex >= textResources.length)
            textResourceIndex = 0;
        return textResources[textResourceIndex++];
    }

    private static BuilderManager ourInstance = new BuilderManager();

    public static BuilderManager getInstance() {
        return ourInstance;
    }

    private BuilderManager() {
    }
}