package com.gjd.puddingcomic.activitys;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gjd.puddingcomic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 * 三大欢迎界面
 */
public class SplashActivity extends AppCompatActivity {

    private ViewPager vp;

    private int[] imagesId ;
    private MySplashAdapter mySplashAdapter;
    private  Button button;
    private LinearLayout linearLayout;
    //,R.mipmap.splash2,R.mipmap.splash3
    private SharedPreferences spfc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spfc = getSharedPreferences("config",MODE_PRIVATE);

        if(!MyApp.isFirstLogin()){
            Intent intent = new Intent(this,WelcomeActivity.class);
            startActivity(intent);
            spfc.edit().putBoolean("isFristLogin", false).apply();
            finish();
            return;
        }

        setContentView(R.layout.splash_activity);
        initViews();

    }

    private List<View> ivs;

    public void initViews(){
        vp = ((ViewPager) findViewById(R.id.splash_vp));
        linearLayout = (LinearLayout) findViewById(R.id.ll_splash);


        initData();
        mySplashAdapter = new MySplashAdapter();
        button = (Button) findViewById(R.id.btu_splash);

        vp.setAdapter(mySplashAdapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position!=2){
                    button.setVisibility(View.INVISIBLE);
                }else{
                    button.setVisibility(ImageView.VISIBLE);
                }

                for (int i = 0; i < imagesId.length; i++) {
                    ImageView iv = (ImageView)linearLayout.getChildAt(i);

                    if(i==position){
                        iv.setImageResource(R.mipmap.dot_0);

                    }else{
                        iv.setImageResource(R.mipmap.dot_1);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {

        ivs = new ArrayList<>();
        imagesId = new int[]{R.mipmap.splash1
                , R.mipmap.splash2, R.mipmap.splash3};
        for (int i = 0; i < imagesId.length; i++) {
            ImageView imageView = new ImageView(this);
            LayoutParams params =
                    new LayoutParams(200
                            , 200);

            imageView.setLayoutParams(params);
//            imageView.setBackgroundResource(imagesId[i]);
            imageView.setImageResource(imagesId[i]);
            imageView.setScaleType(ScaleType.CENTER_CROP);

            ivs.add(imageView);
        }

        for (int j = 0; j < imagesId.length; j++) {
            ImageView icon = new ImageView(this);

            LinearLayout.LayoutParams params = new LinearLayout
                    .LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_VERTICAL;
            icon.setLayoutParams(params);

            if(j==0){
                icon.setImageResource(R.mipmap.dot_0);
            }else{
                icon.setImageResource(R.mipmap.dot_1);
            }
            linearLayout.addView(icon);
        }


    }

    public void btuClick(View view) {
        Intent intent = new Intent(this,WelcomeActivity.class);
        startActivity(intent);
        spfc.edit().putBoolean("isFristLogin",false).apply();
        finish();
    }

    /**
     * splash页面的数据适配器
     */
    private class MySplashAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return ivs.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            View view = getLayoutInflater().inflate(R.layout.splash_images, null);
//            ImageView iv = (ImageView) view.findViewById(R.id.iv_splash_images);
//            iv.setImageResource(imagesId[position]);
//            ImageView imageView = ;
            container.addView(ivs.get(position));

            return ivs.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }
    }
}
