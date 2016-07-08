package com.gjd.puddingcomic.activitys;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.gjd.puddingcomic.JavaBean.BeginReadCartoon;
import com.gjd.puddingcomic.adapters.ReadCartoonImageAdapter;
import com.gjd.puddingcomic.utils.URLUtils;
import com.google.gson.Gson;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gjd.puddingcomic.JavaBean.ShowTotleTalkActivityBean;
import com.gjd.puddingcomic.R;
import com.gjd.puddingcomic.views.MyListView;

import java.util.ArrayList;
import java.util.List;

public class ReadCartoonActivity extends AppCompatActivity {

//    private String urlId;
    private MyListView myList;
    private Toolbar toolbar;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_cartoon);
        String urlId = getIntent().getStringExtra("imageUrl");

        toolbar = (Toolbar) findViewById(R.id.toolbar_read_cartoon);
        toolbar.setNavigationIcon(R.mipmap.ic_search_back_normal);
        setSupportActionBar(toolbar);
//        getActionBar().setHomeAsUpIndicator(R.mipmap.ic_search_back_normal);
        initData(urlId);
        myList = ((MyListView) findViewById(R.id.myListView_read));
//        myList.setAdapter();
//        initListener();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
//    public void initListener(){
//
//
//    }


    private List<String> urls;
    private BeginReadCartoon bean;
    private boolean isFirst = true;
    private boolean isLast = true;

    private void initData(String urlId) {
        StringRequest request = new StringRequest(URLUtils.BEGIN_READ +
                urlId
                , new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        bean = new
                                Gson().fromJson(response, BeginReadCartoon.class);
                        getData(bean);
                        //通过json数据构建List集合
                        myList.post(new Runnable() {
                            private ReadCartoonImageAdapter readCartoonImageAdapter;

                            @Override
                            public void run() {
                                readCartoonImageAdapter
                                        = new ReadCartoonImageAdapter(ReadCartoonActivity.this
                                ,urls);
                                myList.setAdapter(readCartoonImageAdapter);

                            }
                        });


                    }
                }).start();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ReadCartoonActivity.this, "网络不给力，请稍后再试", Toast.LENGTH_SHORT).show();

            }
        });
        MyApp.getQueue().add(request);

    }
    private String title;
    private String previousComicId;
    private String nextComicId;

    private void getData(BeginReadCartoon bean) {
        urls = new ArrayList<>();

        title = bean.getData().getTitle();
        for (int i = 0; i < bean.getData().getImages().size(); i++) {

            urls.add(bean.getData().getImages().get(i));
        }

        if (!"null".equals(bean.getData().getPrevious_comic_id())) {
            previousComicId = ((int)bean.getData().getPrevious_comic_id()) + "";
            isFirst = false;
        } else {
            isFirst = true;
        }
        if (!"null".equals(bean.getData().getNext_comic_id())) {
            nextComicId = ((int)bean.getData().getNext_comic_id()) + "";
            isLast = false;
        } else {
            isLast = true;
        }
    }

    public void btuClick(View view) {
        switch (view.getId()){
            case R.id.previous_btn_read:
                if(!isFirst){
                    System.out.println("previousComicIddddddd"+previousComicId);
                    initData(previousComicId);
                }else{
                    Toast.makeText(ReadCartoonActivity.this
                            , "已经是第一页了", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.next_btn_read:
                if (!isLast){
                    System.out.println("nextComicIddddddd"+nextComicId);
                    initData(nextComicId);
                }else{
                    Toast.makeText(ReadCartoonActivity.this
                            , "已经是最后一页了", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
