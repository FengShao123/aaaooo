package com.gjd.puddingcomic.fargments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gjd.puddingcomic.R;
import com.gjd.puddingcomic.utils.URLUtils;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends Fragment
        implements MaterialTabListener {

    private String[] tabs =
            new String[]{"纯爷们", "爱情", "搞笑", "唯美",
                    "惊恐", "剧情", "平淡", "现实",
                    "生活", "百合", "奇异", "怀古",};
    private String[] showTabs =
            new String[]{"男性", "恋爱", "爆笑", "耽美",
                    "恐怖", "剧情", "日常", "三次元",
                    "治愈", "百合", "奇幻", "古风",};


    public ClassifyFragment() {
        // Required empty public constructor
    }

    private MaterialTabHost tabLayout;
    private ViewPager vp;
    private List<Fragment> fragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_classify, container, false);
        vp = (ViewPager) view.findViewById(R.id.vp_classify);
        tabLayout = ((MaterialTabHost) view.findViewById(R.id.tab_classify_fragment));
        initTab();


//        listView.setAdapter();
        initData();

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//      这里怎么不能用supportManager
        FragmentManager fm = getActivity().getSupportFragmentManager();
        vp.setAdapter(new MyAdapter(fm));
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < tabs.length; i++) {
                    if(position == i){
                        tabLayout.setSelectedNavigationItem(position);
                    }
                }



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        fragments = new ArrayList<>();
        for (int i = 0; i < tabs.length; i++) {

            Fragment ft = AtClassify.newInstance(URLUtils.CLASSIFY_EVERY + showTabs[i]);

            fragments.add(ft);

        }
    }

    private void initTab() {
        for (int i = 0; i < tabs.length; i++) {
            MaterialTab tab = tabLayout.newTab();
            tab.setText(tabs[i]).setTabListener(this);
            tabLayout.addTab(tab);
        }
    }

    //tab的监听
    @Override
    public void onTabSelected(MaterialTab tab) {
//        tabLayout.
        vp.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    //viewpager的适配器
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            System.out.println("在这里---------"+123456789);

            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
