package com.gjd.puddingcomic.EventBusJavaBean;

import android.view.View;

/**
 * Created by Administrator on 2016/6/27.
 */
public class LeftClickBackBean {
    private View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public LeftClickBackBean(View view) {
        this.view = view;
    }

    public LeftClickBackBean() {
    }
}
