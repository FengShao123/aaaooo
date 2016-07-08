package com.gjd.puddingcomic.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gjd.puddingcomic.R;

import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
public class ShowTotleTalkActivityAdapter extends BaseAdapter{
    private LayoutInflater li;
    private Context context;
    private List<String> everyTitles;

    public ShowTotleTalkActivityAdapter(Context context
            ,List<String> everyTitles){
        this.context = context;
        li = LayoutInflater.from(context);
        this.everyTitles = everyTitles;
    }

    @Override
    public int getCount() {
        return everyTitles!=null?everyTitles.size():0;
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
    public View getView(int position, View convertView
            , ViewGroup parent) {
//        TextView text = null;
//        if(convertView==null){
//            convertView = li.inflate(R.layout.item_totle_talk, null);
//            text = (TextView) convertView.findViewById(R.id.tv_item);
//            convertView.setTag(text);
//        }else {
//            text = (TextView) convertView.getTag();
//        }
        View view = li.inflate(R.layout.item_totle_talk, null);
        TextView text = (TextView) view.findViewById(R.id.tv_item);

        String title = everyTitles.get(position);

//        text.setTextColor(Color.BLACK);
        System.out.println("titleeeeeeeeeeeeeeeeee+"+title);


        text.setText(title);
        LinearLayout.LayoutParams params = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                220);
//        params.weight = 1;
        text.setLayoutParams(params);
        return view;
    }


}
