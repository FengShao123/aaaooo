package com.gjd.puddingcomic.fargments;


import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.test.PerformanceTestCase;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageRequest;
import com.gjd.puddingcomic.JavaBean.ClassifyUnder;
import com.gjd.puddingcomic.activitys.MainActivity;
import com.gjd.puddingcomic.activitys.MyApp;
import com.gjd.puddingcomic.activitys.ShowComicsListActivity;
import com.gjd.puddingcomic.activitys.ShowTotleTalkActivity;
import com.google.gson.Gson;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gjd.puddingcomic.JavaBean.HotViewPager;
import com.gjd.puddingcomic.R;
import com.gjd.puddingcomic.utils.URLUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends Fragment
        implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ViewPager vp;
    private PtrClassicFrameLayout ptr;

    private LinearLayout linearDot;

    private RelativeLayout heatRelative;

    private RelativeLayout recommendRelative;
    private LinearLayout linearHeatOne;
    private LinearLayout linearHeatTwo;
    private LinearLayout linearHeatThree;
    //下边的六个linear的
    private LinearLayout linearRecommendOne;
    private LinearLayout linearRecommendTwo;
    private LinearLayout linearRecommendThree;
    private LinearLayout linearRecommendFour;
    private LinearLayout linearRecommendFive;
    private LinearLayout linearRecommendSix;
    private LinearLayout totleLinear;


    public HomePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearDot = ((LinearLayout) view.findViewById(R.id.linear_dot_hot));
        vp = ((ViewPager) view.findViewById(R.id.viewpager_home));
        ptr = ((PtrClassicFrameLayout) view.findViewById(R.id.ptr_framelayout));
        ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptr.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        myAdapter.notifyDataSetChanged();
//                        myAdapter.notifyDataSetChanged();
//                        ptr.refreshComplete();
                    }
                }, 1800);

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, content, header);
            }
        });

        myAdapter = new MyViewPagerAdapter();
        vp.setAdapter(myAdapter);
        //初始化数据
        initData();
        //viewpager设置监听
        myAdapter.notifyDataSetChanged();
        setVPListener();
        //找到布局文件中各个控件，并设置监听
        findView(view);

    }

    private List<String> viewPagerUrl = new ArrayList<>();
    private List<String> values;
    private HotViewPager viewPagerBean;

    //初始化数据并解析
    private void initData() {
        StringRequest request = new StringRequest(URLUtils.HOT_VIEWPAGER
                , new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        viewPagerBean
                                = new Gson().fromJson(response, HotViewPager.class);
                        //加载下面数据
                        initUnderData();
                        System.out.println("bean--------->" + viewPagerBean.getData()
                                .getBanner_group().get(0).getPic());
                        final int count = viewPagerBean.getData().getBanner_group().size();
                        values = new ArrayList<>();
                        for (int i = 0; i < count; i++) {
                            final int num = i;
                            String imageUrl = viewPagerBean.getData().getBanner_group().get(i).getPic();
                            System.out.println("imageUrl-----" + imageUrl);
                            String value = viewPagerBean.getData().getBanner_group().get(i).getValue();
                            viewPagerUrl.add(imageUrl);
                            values.add(value);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    //初始化小圆点

//                                    System.out.println("values.size()----" + values.size());
                                    ImageView icon = new ImageView(getActivity());

                                    LinearLayout.LayoutParams params = new LinearLayout
                                            .LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                            LinearLayout.LayoutParams.WRAP_CONTENT);
                                    params.gravity = Gravity.CENTER_VERTICAL;
                                    icon.setLayoutParams(params);

                                    if (num == 0) {
                                        icon.setImageResource(R.mipmap.dot_0);
                                    } else {
                                        icon.setImageResource(R.mipmap.dot_1);
                                    }
                                    if(linearDot.getChildCount() == count){
                                        linearDot.removeAllViews();
                                    }

                                    linearDot.addView(icon);
                                    myAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }
                }).start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("json数据下载失败" + error.getMessage());
                ptr.refreshComplete();
                Toast.makeText(getActivity(),"网速不给力，请重新检查网络", Toast.LENGTH_SHORT).show();
            }
        });
        MyApp.getQueue().add(request);


    }
    //加载下面数据
    //点击进入的连接

    private List<String> classifyUnderImageUrl = new ArrayList<>();
    private List<String> classifyUnderTextUrl = new ArrayList<>();
    private List<Integer> classifyUnderId = new ArrayList<>();
    private ClassifyUnder classifyUnderBean;
    //ClassifyUnder
    public void initUnderData() {
        StringRequest request = new StringRequest(URLUtils.CLASSITY_UNDER
                , new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        classifyUnderBean
                                = new Gson().fromJson(response, ClassifyUnder.class);
//                        System.out.println("bean--------->" + bean.getData()
//                                .getBanner_group().get(0).getPic());

                        int count = classifyUnderBean.getData().getInfos().get(0).getTopics().size();

                        for (int i = 0; i < count - 1; i++) {
                            int id = classifyUnderBean.getData().getInfos()
                                    .get(0).getTopics().get(i).getId();
                            System.out.println("id++++++++++shang" + id);

//                            final int num = i;
                            String imageUrl = classifyUnderBean.getData().getInfos()
                                    .get(0).getTopics().get(i).getVertical_image_url();

                            int size = classifyUnderBean.getData().getInfos()
                                    .get(0).getTopics().size()+classifyUnderBean.getData().getInfos()
                                    .get(3).getTopics().size() - 1;
                            System.out.println("imageUrl-----" + imageUrl);
                            String text = classifyUnderBean.getData().getInfos()
                                    .get(0).getTopics().get(i).getTitle();
                            //判断原集合是否已经有值，防止刷新时候重复添加
                            if (classifyUnderId.size() == size){
                                classifyUnderId.clear();
                            }

                            if (classifyUnderImageUrl.size()== size){
                                classifyUnderImageUrl.clear();
                            }

                            if (classifyUnderTextUrl.size()== size){
                                classifyUnderTextUrl.clear();
                            }
                            classifyUnderId.add(id);
                            classifyUnderImageUrl.add(imageUrl);
                            classifyUnderTextUrl.add(text);

                        }
                        int underCount = classifyUnderBean.getData().getInfos().get(3).getTopics().size();

                        for (int j = 0; j < underCount; j++) {
                            int id = classifyUnderBean.getData().getInfos()
                                    .get(3).getTopics().get(j).getId();
                            System.out.println("id++++++++++"+id);
                            classifyUnderId.add(id);
                            String underImageUrl = classifyUnderBean.getData().getInfos()
                                    .get(3).getTopics().get(j).getVertical_image_url();
                            classifyUnderImageUrl.add(underImageUrl);
                            System.out.println("underImageUrl-----" + underImageUrl);
                            String underText = classifyUnderBean.getData().getInfos()
                                    .get(3).getTopics().get(j).getTitle();
                            classifyUnderTextUrl.add(underText);
                        }
                        LinearLayout[] linears = new LinearLayout[]{
                                linearHeatOne, linearHeatTwo, linearHeatThree
                                , linearRecommendOne, linearRecommendTwo, linearRecommendThree
                                , linearRecommendFour, linearRecommendFive, linearRecommendSix
                        };
//                        System.out.println("classifyUnderImageUrlsizeeeeeeee"+classifyUnderImageUrl.size());

                        //设置各个图片和text
                        for (int a = 0; a < linears.length; a++) {
                            setImageFromBitmapAndText(linears[a]
                                    , classifyUnderImageUrl.get(a)
                                    , classifyUnderTextUrl.get(a)
                            ,linears.length);
                        }

                    }

                }).start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("json数据下载失败309" + error.getMessage());
                Toast.makeText(getActivity(), "网速不给力，请重新检查网络", Toast.LENGTH_SHORT).show();
                ptr.refreshComplete();
            }
        });
        MyApp.getQueue().add(request);

    }
//  定义一个全局的flag 判断是否是第一次刷新
    private boolean flag = true;
//    一个计数器记录进入第几次 九次的话 就不在添加
    private int intoCount = 0;


    public void setImageFromBitmapAndText(final LinearLayout linear
            , final String imageUrl, String text, final int size) {


        final ImageView imageView = new ImageView(getActivity());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        final TextView textView = new TextView(getActivity());
        textView.setText(text);
        textView.setSingleLine();
        textView.setTextSize(13);
        textView.setTextColor(Color.DKGRAY);
        //imageView params
        LinearLayout.LayoutParams imageParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0
                );
//        imageParams.height = 170;
        imageParams.weight = 7;
        imageParams.setMargins(10, 0, 10, 5);
//        imageParams.height =
        imageView.setLayoutParams(imageParams);
        imageView.setImageResource(R.mipmap.progress_bar_icon);
        //textView params
        LinearLayout.LayoutParams textParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0
                );
        textParams.weight = 1;
//        textParams.gravity = Gravity.CENTER;
        textView.setLayoutParams(textParams);
        textView.setGravity(Gravity.CENTER);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                intoCount++;
                System.out.println("intoCount < size" + intoCount + ";;" + size);
                if (flag && (intoCount < size+1)) {

                    linear.addView(imageView);
                    linear.addView(textView);
//                    intoCount = 0;
//                    flag = false;
                } else if (intoCount == size +1 && flag) {
                    intoCount = 0;
                    flag = false;
                }
//                else if(intoCount == size){
//                    ptr.refreshComplete();
//                }
                Picasso.with(getActivity())
                        .load(imageUrl)
                        .placeholder(R.mipmap.progress_bar_icon)
                        .into(imageView);


            }
        });
        vp.post(new Runnable() {
            @Override
            public void run() {
                ptr.refreshComplete();
            }
        });

//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    private MyViewPagerAdapter myAdapter;
//    com.gjd.puddingcomic.views.MyTouchViewPager


    private void findView(View view) {
        totleLinear = ((LinearLayout) view.findViewById(R.id.linear_totle));

        heatRelative = ((RelativeLayout) view.findViewById(R.id.heat_relative));
        heatRelative.setOnClickListener(this);
        recommendRelative = ((RelativeLayout) view.findViewById(R.id.recommend_relative));
        recommendRelative.setOnClickListener(this);
        linearHeatOne = ((LinearLayout) view.findViewById(R.id.linear_heat_one));
        linearHeatOne.setOnClickListener(this);
        linearHeatTwo = ((LinearLayout) view.findViewById(R.id.linear_heat_two));
        linearHeatTwo.setOnClickListener(this);
        linearHeatThree = ((LinearLayout) view.findViewById(R.id.linear_heat_three));
        linearHeatThree.setOnClickListener(this);

        //
        linearRecommendOne = ((LinearLayout) view.findViewById(R.id.linear_recommend_one));
        linearRecommendOne.setOnClickListener(this);
        linearRecommendTwo = ((LinearLayout) view.findViewById(R.id.linear_recommend_two));
        linearRecommendTwo.setOnClickListener(this);
        linearRecommendThree = ((LinearLayout) view.findViewById(R.id.linear_recommend_three));
        linearRecommendThree.setOnClickListener(this);
        linearRecommendFour = ((LinearLayout) view.findViewById(R.id.linear_recommend_four));
        linearRecommendFour.setOnClickListener(this);
        linearRecommendFive = ((LinearLayout) view.findViewById(R.id.linear_recommend_five));
        linearRecommendFive.setOnClickListener(this);
        linearRecommendSix = ((LinearLayout) view.findViewById(R.id.linear_recommend_six));
        linearRecommendSix.setOnClickListener(this);
    }

    //对热门里的点击事件,点击图片下载

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();
//        int id =
        if (classifyUnderId.size()!=0){
            switch (v.getId()){
                case R.id.heat_relative:
                    intent.setClass(getActivity(), ShowComicsListActivity.class);

                    intent.putExtra("url", URLUtils.INTO_HOT);
                    startActivity(intent);
                    break;
                case R.id.recommend_relative:
                    intent.setClass(getActivity(), ShowComicsListActivity.class);
                    intent.putExtra("url",URLUtils.INTO_RECOMMEND);
                    startActivity(intent);
                    break;
                case R.id.linear_heat_one:
                    intent.setClass(getActivity(), ShowTotleTalkActivity.class);
                    intent.putExtra("url",URLUtils.INTO_EVERY + classifyUnderId.get(0)+"?sort=0");
                    startActivity(intent);
                    break;
                case R.id.linear_heat_two:
                    intent.setClass(getActivity(), ShowTotleTalkActivity.class);
                    intent.putExtra("url",URLUtils.INTO_EVERY+classifyUnderId.get(1)+"?sort=0");
                    startActivity(intent);
                    break;
                case R.id.linear_heat_three:
                    intent.setClass(getActivity(), ShowTotleTalkActivity.class);
                    intent.putExtra("url",URLUtils.INTO_EVERY+classifyUnderId.get(2)+"?sort=0");
                    startActivity(intent);
                    break;
                case R.id.linear_recommend_one:
                    intent.setClass(getActivity(), ShowTotleTalkActivity.class);
                    intent.putExtra("url",URLUtils.INTO_EVERY+classifyUnderId.get(3)+"?sort=0");
                    startActivity(intent);
                    break;
                case R.id.linear_recommend_two:
                    intent.setClass(getActivity(), ShowTotleTalkActivity.class);
                    intent.putExtra("url",URLUtils.INTO_EVERY+classifyUnderId.get(4)+"?sort=0");
                    startActivity(intent);
                    break;
                case R.id.linear_recommend_three:
                    intent.setClass(getActivity(), ShowTotleTalkActivity.class);
                    intent.putExtra("url",URLUtils.INTO_EVERY+classifyUnderId.get(5)+"?sort=0");
                    startActivity(intent);
                    break;
                case R.id.linear_recommend_four:
                    intent.setClass(getActivity(), ShowTotleTalkActivity.class);
                    intent.putExtra("url",URLUtils.INTO_EVERY+classifyUnderId.get(6)+"?sort=0");
                    startActivity(intent);
                    break;
                case R.id.linear_recommend_five:
                    intent.setClass(getActivity(), ShowTotleTalkActivity.class);
                    intent.putExtra("url",URLUtils.INTO_EVERY+classifyUnderId.get(7)+"?sort=0");
                    startActivity(intent);
                    break;
                case R.id.linear_recommend_six:
                    intent.setClass(getActivity(), ShowTotleTalkActivity.class);
                    intent.putExtra("url",URLUtils.INTO_EVERY+classifyUnderId.get(8)+"?sort=0");
                    startActivity(intent);
                    break;
            }
        }else {
            return;
        }

    }

    //对viewpager进行监听
    private void setVPListener() {
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset
                    , int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                System.out.println("事实上是事实是事实是事实是事" + values.size());
                for (int i = 0; i < values.size(); i++) {
                    ImageView iv = (ImageView) linearDot.getChildAt(i);
                    System.out.println("positionpositionpositionposition"+position);
                    System.out.println("ivvvvvvvvvvvvvvvvvvvvvv"+iv);

                    if (i == position) {
//                        System.out.println("事实上是事实是事实是事实是事" + iv);
                        iv.setImageResource(R.mipmap.dot_0);

                    } else {
                        System.out.println("qqqqqqqqqqqqqqqqqqqqq" + iv);
                        iv.setImageResource(R.mipmap.dot_1);

                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    //给viewpager设置适配器
    private class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return values==null?0:values.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        private int screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {
            //viewpager里添加图片
            final ImageView imageView = new ImageView(getActivity());
            RelativeLayout.LayoutParams params =
                    new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.MATCH_PARENT
                            , RelativeLayout.LayoutParams.MATCH_PARENT);

            imageView.setLayoutParams(params);
//            imageView.setBackgroundResource(imagesId[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            ImageRequest image = new ImageRequest(viewPagerUrl.get(position), new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    System.out.println("Bitmap response" + response);
                    imageView.setImageBitmap(response);
                    container.addView(imageView);
                }
            }, screenWidth, 200, ImageView.ScaleType.CENTER_CROP
                    , Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("ImageRequestviewPager下载失败");
                    Toast.makeText(getActivity(),"网络不给力，请重新检查网络",Toast.LENGTH_SHORT).show();
                }
            });
            MyApp.getQueue().add(image);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }
    }
}
