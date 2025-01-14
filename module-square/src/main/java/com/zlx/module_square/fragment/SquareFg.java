package com.zlx.module_square.fragment;

import android.view.MotionEvent;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.p2m.core.P2M;
import com.p2m.annotation.api.Launcher;
import com.p2m.module.api.Square;
import com.zlx.module_base.base_fg.BaseFg;
import com.zlx.module_square.R;
import com.zlx.module_square.R2;
import com.zlx.module_square.adapters.TabNavigatorAdapter;
import com.zlx.module_square.adapters.VpAdapterSquare;
import com.zlx.module_square.impl.TabPagerListener;
import com.zlx.widget.viewpager.animviewpager.LiquidSwipeViewPager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Copyright (C)
 * FileName: SquareFg
 * Author: Zlx
 * Email: 1170762202@qq.com
 * Date: 2020/9/17 11:27
 * Description: 广场
 */
@Launcher
public class SquareFg extends BaseFg implements TabPagerListener {

    @BindView(R2.id.magicIndicator)
    MagicIndicator magicIndicator;
    @BindView(R2.id.viewPager)
    LiquidSwipeViewPager viewPager;


    @Override
    protected int getLayoutId() {
        return R.layout.fg_square;
    }

    @Override
    protected boolean immersionBar() {
        return true;
    }

    @Override
    protected void initViews() {
        super.initViews();
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        String[] stringArray = getResources().getStringArray(R.array.square_title);
        TabNavigatorAdapter tabNavigatorAdapter = new TabNavigatorAdapter(Arrays.asList(stringArray));
        tabNavigatorAdapter.setOnTabClickListener((view, index) -> viewPager.setCurrentItem(index));
        commonNavigator.setAdapter(tabNavigatorAdapter);
        magicIndicator.setNavigator(commonNavigator);
        VpAdapterSquare vpAdapterSquare = new VpAdapterSquare(getChildFragmentManager(), 2);
        vpAdapterSquare.setListener(this);
        viewPager.setOffscreenPageLimit(stringArray.length);
        viewPager.setAdapter(vpAdapterSquare);
        ViewPagerHelper.bind(magicIndicator, viewPager);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
    }

    @Override
    public Fragment getFragment(int position) {
        if (position == 0) {
            return P2M.moduleOf(Square.class).getLauncher().newFragmentForSystemFg();
        } else if (position == 1) {
            return P2M.moduleOf(Square.class).getLauncher().newFragmentForNavigationFg();
        }
        return null;
    }
}
