package com.gjd.puddingcomic.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gjd.puddingcomic.JavaBean.ShowComicsListActivityBean;
import com.gjd.puddingcomic.R;
import com.gjd.puddingcomic.adapters.ShowComicsListActivityAdapter;
import com.gjd.puddingcomic.utils.URLUtils;
import com.google.gson.Gson;

import java.util.List;

public class ShowComicsListActivity extends AppCompatActivity {

    private ListView listView;
    private String url;
    private List<ShowComicsListActivityBean.DataBean.TopicsBean> tbs;
    private ShowComicsListActivityBean sclab;
    private ShowComicsListActivityAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_comics_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar_comics);
        toolbar.setNavigationIcon(R.mipmap.ic_search_back_normal);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        url = getIntent().getStringExtra("url");
        listView = ((ListView) findViewById(R.id.lv_showList));

        initData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int url = tbs.get(position).getId();
                Intent intent = new Intent(ShowComicsListActivity.this,ShowTotleTalkActivity.class);
                intent.putExtra("url", URLUtils.INTO_EVERY+url+URLUtils.INTO_EVERY_AFTER_URL);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initData() {
        System.out.println("33333333333333333333");
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sclab = new Gson().fromJson(response,ShowComicsListActivityBean.class);
                        System.out.println("sclabbbbbbbbbbbbbb"+sclab.toString());
                        tbs = sclab.getData().getTopics();
                        listView.post(new Runnable() {
                            @Override
                            public void run() {
                                //为什么在这里不能刷新适配器,为什么必须要在这里绑定
                                adapter = new ShowComicsListActivityAdapter(ShowComicsListActivity.this,tbs);
                                listView.setAdapter(adapter);
//                                adapter.notifyDataSetChanged();
                            }
                        });

                    }
                }).start();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ShowComicsListActivity下载失败");
                Toast.makeText(ShowComicsListActivity.this, "网络不给力，请稍后再试", Toast.LENGTH_SHORT).show();
            }
        });


        MyApp.getQueue().add(request);

    }


}
