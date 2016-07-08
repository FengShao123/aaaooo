package com.gjd.puddingcomic.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.gjd.puddingcomic.R;

/**
 * Created by Administrator on 2016/6/24.
 * 导航界面
 */
public class WelcomeActivity extends AppCompatActivity {

    private Button btn;
    private final static int TIME_WHAT = 1;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        btn = ((Button) findViewById(R.id.btn_welcome));
        timeCount();
    }
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what ){
                case TIME_WHAT:
                    --count;
                    btn.post(new Runnable() {
                        @Override
                        public void run() {
                            btn.setText(count + "s后跳过");
                        }
                    });

                    if(count<=0){

                        Intent intent =
                                new Intent(WelcomeActivity.this,MainActivity.class);

                        startActivity(intent);
                        finish();
                    }else{
                        handler.sendEmptyMessageDelayed(TIME_WHAT,1000);
                    }
                    break;
            }
            return true;
        }
    });

    public void timeCount(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    handler.sendEmptyMessageDelayed(TIME_WHAT, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
