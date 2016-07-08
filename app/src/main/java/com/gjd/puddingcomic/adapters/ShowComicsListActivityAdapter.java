package com.gjd.puddingcomic.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gjd.puddingcomic.JavaBean.ShowComicsListActivityBean;
import com.gjd.puddingcomic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class ShowComicsListActivityAdapter extends BaseAdapter {

    private int size;
    private LayoutInflater li;
    private List<ShowComicsListActivityBean.DataBean.TopicsBean> tbs;

    public ShowComicsListActivityAdapter(Context context
            ,List<ShowComicsListActivityBean.DataBean.TopicsBean> tbs){
        li = LayoutInflater.from(context);
        this.tbs = tbs;
        System.out.println("tbsssssssssssss"+tbs);
        this.context = context;
        if(this.tbs != null){
            initData();
        }

    }
    //初始化数据
    private List<String> imageUrls;
    private List<String> titles;
    private List<String> nicknames;
    private List<Integer> ids;
    private Context context;


    public void initData(){
        imageUrls = new ArrayList<>();
        titles = new ArrayList<>();
        nicknames = new ArrayList<>();
        ids = new ArrayList<>();

        for (int i = 0; i < tbs.size(); i++) {
            String imageUrl = tbs.get(i).getCover_image_url();
            int id = tbs.get(i).getId();
            String title = tbs.get(i).getTitle();
            String nickname = tbs.get(i).getUser().getNickname();
            ids.add(id);
            imageUrls.add(imageUrl);
            nicknames.add(nickname);
            titles.add(title);

        }
    }
    @Override
    public int getCount() {
        return tbs==null?0:tbs.size();
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
        ViewHolder viewHolder;
        if(convertView==null){
            convertView =  li.inflate(R.layout.show_comics_item_list, null);
//            convertView.findViewById(R.id.tv_title);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        System.out.println("11111111111"+titles.get(position));
        viewHolder.getTitle().setText(titles.get(position));
        viewHolder.getNickName().setText(nicknames.get(position));
        System.out.println("222222222222" + nicknames.get(position));
        Picasso.with(context).load(imageUrls.get(position))
                .config(Bitmap.Config.ARGB_8888)
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.getIv());

        return convertView;
    }
    protected class ViewHolder{
        View view;
        ImageView iv;
        TextView title;
        TextView nickName;
        public ViewHolder(View view){
            this.view = view;
        }
        public ImageView getIv(){
            if(iv == null){
                iv = (ImageView) view.findViewById(
                        R.id.iv_item);

            }
            return iv;
        }
        public TextView getNickName(){
            if( nickName== null){
                nickName = (TextView) view.findViewById(
                        R.id.tv_nickName);

            }
            return nickName;
        }
        public TextView getTitle(){
            if( title == null){
                title = (TextView) view.findViewById(
                        R.id.tv_title);

            }
            return title;
        }

    }
}
