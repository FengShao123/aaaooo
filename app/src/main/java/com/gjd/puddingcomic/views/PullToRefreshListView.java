package com.gjd.puddingcomic.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/7/7.
 */
public class PullToRefreshListView extends ListView{
    public PullToRefreshListView(Context context) {
        super(context);
    }

    public PullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        getParent().requestDisallowInterceptTouchEvent(true);//listview的滑动和下拉刷新的滑动冲突，到这步拦截，一直运行onTouchEvent，直到onTouchEvent把时间给外层布局，才运行外层的拦截(onInterceptTouchEvent)-消费(onTouchEvent)

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (getChildAt(0) == null) {// 获取view的position防止为null
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        if (getCount() == 0) {
            // 没有item的时候也可以下拉刷新
            getParent().requestDisallowInterceptTouchEvent(false);
        } else if (getFirstVisiblePosition() == 0
                && getChildAt(0).getTop() >= 0) {
            // 滑到ListView的顶部了
            getParent().requestDisallowInterceptTouchEvent(false);
        } else {
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        return super.onTouchEvent(ev);
    }
}
