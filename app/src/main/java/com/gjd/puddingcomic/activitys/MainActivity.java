package com.gjd.puddingcomic.activitys;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gjd.puddingcomic.EventBusJavaBean.LeftClickBackBean;
import com.gjd.puddingcomic.R;
import com.gjd.puddingcomic.fargments.ClassifyFragment;
import com.gjd.puddingcomic.fargments.HomePageFragment;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subcriber;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
        EventBus.getDefault().register(this);
        initFragment();
    }
    private android.support.v4.app.FragmentTransaction ft;
    private android.support.v4.app.FragmentManager manager;
    private  HomePageFragment home;

    private void initFragment() {
        home = new HomePageFragment();
        manager = getSupportFragmentManager();
        ft = manager.beginTransaction();
        ft.replace(R.id.relative_main, home);
        ft.commit();

    }

    private void initViews() {
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setNavigationIcon(R.mipmap.nagaicon);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative_main);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subcriber
    public void onEventMainThread(LeftClickBackBean lcbb) {
        View v = lcbb.getView();
        drawerLayout.closeDrawer(Gravity.LEFT);
        switch (v.getId()) {
            case R.id.homePage_fragment_left:
                home = new HomePageFragment();
                manager = getSupportFragmentManager();
                ft = manager.beginTransaction();
                ft.replace(R.id.relative_main, home);
                ft.commit();
                Toast.makeText(MainActivity.this, "0101010101010101010101010101", Toast.LENGTH_SHORT).show();

                break;
            case R.id.random_fragment_left:
                Intent intent = new Intent(this,RandomActivity.class);

                startActivity(intent);
                Toast.makeText(MainActivity.this, "0101010101010101010101010101", Toast.LENGTH_SHORT).show();

                break;
            case R.id.update_fragment_left:
                Toast.makeText(MainActivity.this, "1111111111111111111111111111111111111111111", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rankLink_fragment_left:
                Toast.makeText(MainActivity.this, "2222", Toast.LENGTH_SHORT).show();

                break;
            case R.id.end_fragment_left:
                Toast.makeText(MainActivity.this, "3333", Toast.LENGTH_SHORT).show();

                break;
            case R.id.classify_fragment_left:
                ClassifyFragment classifyFragment = new ClassifyFragment();
                ft = manager.beginTransaction();
                ft.replace(R.id.relative_main,classifyFragment);
                ft.commit();

                Toast.makeText(MainActivity.this, "4444", Toast.LENGTH_SHORT).show();

                break;
            case R.id.image_fragment_left:
                Toast.makeText(MainActivity.this, "5555", Toast.LENGTH_SHORT).show();

                break;
            case R.id.history_fragment_left:
                Toast.makeText(MainActivity.this, "6666", Toast.LENGTH_SHORT).show();

                break;
            case R.id.setting_fragment_left:
                Toast.makeText(MainActivity.this, "7777", Toast.LENGTH_SHORT).show();

                break;


        }
    }
}
