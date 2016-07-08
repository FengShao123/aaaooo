package com.gjd.puddingcomic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gjd.puddingcomic.JavaBean.RandomActivityBean;
import com.gjd.puddingcomic.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class RandomActivityAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater li;
    private int screenWidth;
    private List<RandomActivityBean.DataBean.ComicsBean> comicss;

    public RandomActivityAdapter(Context context
            ,List<RandomActivityBean.DataBean.ComicsBean> comicss){
        li = LayoutInflater.from(context);
        this.context = context;
        this.comicss = comicss;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;

    }


    @Override
    public int getCount() {
        return comicss!=null?comicss.size():0;
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
        ViewHolder holder = null;
        if(convertView == null){
            convertView = li.inflate(R.layout.item_list_random,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        RandomActivityBean.DataBean.ComicsBean comicsBean = comicss.get(position);
        String coverImageUrl = comicsBean.getCover_image_url();
        String title = comicsBean.getTopic().getTitle();
        String nickname = comicsBean.getTopic().getUser().getNickname();

        holder.getTitle().setText(title);
        holder.getNickName().setText(nickname);
        ImageView iv = holder.getIv();
        LinearLayout.LayoutParams params = new
                LinearLayout.LayoutParams(screenWidth, screenWidth*469/750);
        iv.setLayoutParams(params);
        Picasso.with(context).load(coverImageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(iv);

        return convertView;
    }
    public class ViewHolder{
        private ImageView iv;
        private View view;
        private TextView title;
        private TextView nickName;

        protected ViewHolder(View view){
            this.view = view;
        }
        public ImageView getIv(){
            if(iv == null){
                iv = (ImageView) view.findViewById(R.id.iv_random);
            }
            return iv;
        }
        public TextView getTitle(){
            if(title == null){
                title = (TextView) view.findViewById(R.id.title_random);
            }
            return title;
        }
        public TextView getNickName(){
            if(nickName == null){
                nickName = (TextView) view.findViewById(R.id.author_random);
            }
            return nickName;
        }

    }
}
