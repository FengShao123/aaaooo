package com.gjd.puddingcomic.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gjd.puddingcomic.JavaBean.RandomActivityBean;
import com.gjd.puddingcomic.adapters.RandomActivityAdapter;
import com.google.gson.Gson;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gjd.puddingcomic.JavaBean.ShowComicsListActivityBean;
import com.gjd.puddingcomic.R;
import com.gjd.puddingcomic.adapters.ShowComicsListActivityAdapter;
import com.gjd.puddingcomic.utils.URLUtils;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class RandomActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private PtrClassicFrameLayout ptr;
    private boolean isNeedLoadMore = true;
    private boolean canAddFootView = true;
    private View footView;
    private LayoutInflater mInflater;
    private int[] urls = new int[]{1466352000, 1466438400, 1466524800
            , 1466611200, 1466697600};
    private int currentPager = 0;
    private boolean isBottom = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        toolbar = (Toolbar) findViewById(R.id.toolbar_random);
        toolbar.setNavigationIcon(R.mipmap.ic_search_back_normal);
        setSupportActionBar(toolbar);
        ptr = (PtrClassicFrameLayout) findViewById(
                R.id.ptr_random);

        mInflater = getLayoutInflater();
        footView = mInflater.inflate(R.layout.list_footview, null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = ((ListView) findViewById(R.id.lv_randomList));
        initViews();
        initData();
        mAdapter = new RandomActivityAdapter(
                RandomActivity.this, comicss);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = ids.get(position);
                System.out.println("urlllllllllllllllllll"+url);
                Intent intent = new Intent(RandomActivity.this, ShowTotleTalkActivity.class);
                intent.putExtra("url", URLUtils.INTO_EVERY + url + URLUtils.INTO_EVERY_AFTER_URL);
                startActivity(intent);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(isBottom && scrollState == SCROLL_STATE_IDLE && isNeedLoadMore){
                    // 11+10  = 21 拖动滚动条的时候发生了好多次,当然只要要执行一次加载更多
                    //加载更多数据
                    Log.e("TAG", "123");
                    loadMore();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem
                    , int visibleItemCount, int totalItemCount) {
                if(totalItemCount != 0){
					//反复添加底部(都是底部视图了?)
					//解决方案:通过标志变量(boolean类型的值,就像开关一样)只让底部添加1次
					if(canAddFootView){
						listView.addFooterView(footView);
						canAddFootView = false;  //添加完毕以后修改标志变量就可以了
					}
				}
				//底部条件的判断,屏蔽 0+0 = 0 (这是数据就没有的情况)
                isBottom = totalItemCount != 0 && firstVisibleItem + visibleItemCount == totalItemCount;

            }
        });
    }

    public void initViews() {
        //ptr.refreshComplete();
        ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptr.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
//                        myAdapter.notifyDataSetChanged();
                    }
                }, 1800);

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, content, header);
            }
        });
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

    private RandomActivityBean rab;
    private List<RandomActivityBean.DataBean.ComicsBean> comicss
            = new ArrayList<>();
    private RandomActivityAdapter mAdapter;

    List<Integer> alsoAddUrl = new ArrayList<>();
//  初始化数据
    public void initData() {

        StringRequest request = new StringRequest(URLUtils.RANDOM_LIST
                + urls[0] + URLUtils.RANDOM_LIST_AFTER
                , new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        rab = new Gson().fromJson(response, RandomActivityBean.class);
                        System.out.println("sclabbbbbbbbbbbbbb" + rab.toString());
//                        comicss = rab.getData().getComics();
                        comicss.addAll(rab.getData().getComics());
                        getData();
                        listView.post(new Runnable() {
                            @Override
                            public void run() {
                                //为什么在这里不能刷新适配器,为什么必须要在这里绑定
                                ptr.refreshComplete();
                                mAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }).start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ShowComicsListActivity下载失败");
                Toast.makeText(RandomActivity.this, "网络不给力，请稍后再试", Toast.LENGTH_SHORT).show();
                ptr.refreshComplete();
            }
        });


        MyApp.getQueue().add(request);

    }

    private List<String> ids;
    private List<String> totleIds = new ArrayList<>();

    private void getData() {
        ids = new ArrayList<>();

        for (int i = 0; i < comicss.size(); i++) {
            RandomActivityBean.DataBean.ComicsBean.TopicBean topic = comicss.get(i).getTopic();
            System.out.println("topic.getId()"+topic.getId());
            ids.add(topic.getId() + "");
        }
    }
    //加载更多
    public void loadMore(){
        //异步任务(包名 + 自定义的接口名字和系统的CallBack进行区分)
        currentPager++;
        isNeedLoadMore = false; //页数变了,就不需要加载更多
        if(currentPager >= urls.length){ //不需要加载数据了
            //可以移除footView
            listView.removeFooterView(footView);  //就不加载数据了
            return;
        }
        StringRequest request = new StringRequest(URLUtils.RANDOM_LIST
                + urls[currentPager] + URLUtils.RANDOM_LIST_AFTER
                , new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        rab = new Gson().fromJson(response, RandomActivityBean.class);
                        System.out.println("sclabbbbbbbbbbbbbb" + rab.toString());
//                        comicss = rab.getData().getComics();
                        comicss.addAll(rab.getData().getComics());
//                        isNeedLoadMore = false;
                        getData();
                        listView.post(new Runnable() {
                            @Override
                            public void run() {

                                mAdapter.notifyDataSetChanged();
                                ptr.refreshComplete();
                                isNeedLoadMore = true;
                            }
                        });
                    }
                }).start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("RandomActivityloadMore下载失败");
                Toast.makeText(RandomActivity.this, "网络不给力，请稍后再试", Toast.LENGTH_SHORT).show();
                ptr.refreshComplete();
                currentPager--;
                isNeedLoadMore = true;
            }
        });
        MyApp.getQueue().add(request);
    }


}
