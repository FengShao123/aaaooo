package com.gjd.puddingcomic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gjd.puddingcomic.JavaBean.BeginReadCartoon;
import com.gjd.puddingcomic.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class ReadCartoonImageAdapter extends BaseAdapter{

    private List<String> urls;
    private LayoutInflater li;
    private Context context;
    private int screenWidth;

    public ReadCartoonImageAdapter(Context context
            ,List<String> urls){
        li = LayoutInflater.from(context);
        this.urls = urls;
        this.context = context;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public int getCount() {
        return urls==null?0:urls.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv = null;
        if(convertView == null ){
            convertView = li.inflate(R.layout.list_item_read_cartoon,null);
            iv = (ImageView) convertView.findViewById(R.id.iv_list_item_read);
            convertView.setTag(iv);
        }else{
            iv = (ImageView) convertView.getTag();
        }

        String url = urls.get(position);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                screenWidth,
                screenWidth*500/640
        );
        //640*500
//        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setLayoutParams(params);
        Picasso.with(context).load(url)
                .placeholder(R.mipmap.progress_bar_icon)
                .into(iv);

        return convertView;
    }
}
