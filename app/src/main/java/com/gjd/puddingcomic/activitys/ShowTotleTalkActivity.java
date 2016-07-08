package com.gjd.puddingcomic.activitys;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gjd.puddingcomic.adapters.ShowTotleTalkActivityAdapter;
import com.google.gson.Gson;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gjd.puddingcomic.JavaBean.ShowTotleTalkActivityBean;
import com.gjd.puddingcomic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShowTotleTalkActivity extends AppCompatActivity {

    private String url;
    private ImageView iv;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        initData();

    }

    private TextView globaiTitle;
    private TextView describe;
    private TextView author;
    private GridView gridView;
    private ShowTotleTalkActivityAdapter adapter;
    private Toolbar toolbar;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void initViews() {
        setContentView(R.layout.activity_show_totle_talk);
//        toolbar.setNavigationIcon(R.mipmap.nagaicon);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar = (Toolbar) findViewById(R.id.toolbar_show_talk);
        toolbar.setNavigationIcon(R.mipmap.ic_search_back_normal);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        url = getIntent().getStringExtra("url");
        System.out.println("ShowTotleTalkActivity+Url"+url);
//        getActionBar().setHomeAsUpIndicator(R.mipmap.ic_search_back_normal);
        iv = ((ImageView) findViewById(R.id.iv_showActivity));
        globaiTitle = (TextView) findViewById(R.id.tv_title_talk);
        describe = (TextView) findViewById(R.id.tv_describe);
        author = (TextView) findViewById(R.id.tv_author_talk);
        gridView = (GridView) findViewById(R.id.gv_showActivity);
//        gridView.seto
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer urlId = everyIds.get(position);
                Intent intent = new Intent(ShowTotleTalkActivity.this,
                        ReadCartoonActivity.class);

                intent.putExtra("imageUrl", urlId + "");
                startActivity(intent);
//                finish();
            }
        });

    }
    //对toorbar上的返回键进行监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ShowTotleTalkActivityBean bean = new
                                Gson().fromJson(response, ShowTotleTalkActivityBean.class);
                        //通过json数据构建List集合
                        initListData(bean);

                    }
                }).start();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShowTotleTalkActivity.this, "网络不给力，请稍后再试", Toast.LENGTH_SHORT).show();

            }
        });
        MyApp.getQueue().add(request);
    }

    //初始化list集合

    private List<String> everyTitles;
    private List<Integer> everyIds;

    private void initListData(ShowTotleTalkActivityBean bean) {
        everyTitles = new ArrayList<>();
        everyIds = new ArrayList<>();
        int size = bean.getData().getComics().size();
        final String nickname = bean.getData().getUser().getNickname();
        int id = bean.getData().getId();
        final String imageUrl = bean.getData().getVertical_image_url();
        final String title = bean.getData().getTitle();
        final String description = bean.getData().getDescription();
        for (int i = 0; i < size; i++) {
            String everyTitle = bean.getData().getComics().get(i).getTitle();
            int everyId = bean.getData().getComics().get(i).getId();
            everyTitles.add(everyTitle);
            everyIds.add(everyId);
        }
        gridView.post(new Runnable() {
            @Override
            public void run() {
                Picasso.with(ShowTotleTalkActivity.this)
                        .load(imageUrl).config(Bitmap.Config.ARGB_8888)
                        .into(iv);
                describe.setText(description);
                System.out.println("titleeeeeeeeeeeeeeee"+title);
                globaiTitle.setText(title);
                author.setText(nickname);
                adapter = new ShowTotleTalkActivityAdapter(
                        ShowTotleTalkActivity.this,everyTitles);
                gridView.setAdapter(adapter);
//                setListViewHeight();
            }
        });
    }
    /**
     * 测量listView的高度
     * 必须在setAdapter后调用
     * @param listView
     */
    public void setListViewHeight(ListView listView) {

        // 获取ListView对应的Adapter

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
