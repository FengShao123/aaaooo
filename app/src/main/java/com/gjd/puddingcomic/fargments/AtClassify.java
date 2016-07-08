package com.gjd.puddingcomic.fargments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gjd.puddingcomic.activitys.MyApp;
import com.gjd.puddingcomic.activitys.ReadCartoonActivity;
import com.gjd.puddingcomic.activitys.ShowComicsListActivity;
import com.gjd.puddingcomic.activitys.ShowTotleTalkActivity;
import com.gjd.puddingcomic.utils.URLUtils;
import com.google.gson.Gson;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gjd.puddingcomic.JavaBean.ShowComicsListActivityBean;
import com.gjd.puddingcomic.R;
import com.gjd.puddingcomic.adapters.MyAtClassifyListAdapter;
import com.gjd.puddingcomic.adapters.ShowComicsListActivityAdapter;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AtClassify#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AtClassify extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "url";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView lv;


    public AtClassify() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param url Parameter 1.
     * @return A new instance of fragment AtClassify.
     */
    // TODO: Rename and change types and number of parameters
    public static AtClassify newInstance(String url) {
        AtClassify fragment = new AtClassify();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_at_classify, container, false);
    }

    private List<ShowComicsListActivityBean.DataBean.TopicsBean> tbs;
    private PtrClassicFrameLayout ptr;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = ((ListView) view.findViewById(R.id.lv_at_classify));
        ptr = (PtrClassicFrameLayout) view.findViewById(R.id.ptr_framelayout_classify);
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
        initData();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int urlId = tbs.get(position).getId();
                Intent intent = new Intent(getActivity(), ShowTotleTalkActivity.class);
                intent.putExtra("url", URLUtils.INTO_EVERY+urlId+URLUtils.INTO_EVERY_AFTER_URL);
                System.out.println("imageUrllllllllllllll" + urlId);
                startActivity(intent);

            }
        });
    }
    private ShowComicsListActivityBean sclab;
    private ShowComicsListActivityAdapter mAdapter;

    public void initData() {
//        System.out.println("33333333333333333333");
        StringRequest request = new StringRequest(mParam1, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sclab = new Gson().fromJson(response,ShowComicsListActivityBean.class);
                        System.out.println("sclabbbbbbbbbbbbbb"+sclab.toString());
                        tbs = sclab.getData().getTopics();
                        lv.post(new Runnable() {
                            @Override
                            public void run() {
                                //为什么在这里不能刷新适配器,为什么必须要在这里绑定
                                mAdapter = new ShowComicsListActivityAdapter(getActivity(),tbs);
                                lv.setAdapter(mAdapter);
//                                adapter.notifyDataSetChanged();
                                ptr.refreshComplete();
                            }
                        });

                    }
                }).start();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ShowComicsListActivity下载失败");
                Toast.makeText(getActivity(), "网速不给力，请重新检查网络", Toast.LENGTH_SHORT).show();
                ptr.refreshComplete();
            }
        });


        MyApp.getQueue().add(request);

    }
}
