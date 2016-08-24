package com.ruptech.ai.loading;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.Toast;

import com.ruptech.ai.LoginActivity;
import com.ruptech.ai.MainActivity;
import com.ruptech.ai.R;

import butterknife.ButterKnife;

/**
 * Created by ls_gao on 2016/8/18.
 */
public class LoadingByeActivity extends Activity {

    MediaPlayer mMediaMusic = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_loading_bye);
        ButterKnife.inject(this);

        // 创建MediaPlayer 这里用的音频格式是mp3
        mMediaMusic = MediaPlayer.create(this, R.raw.robot_bye);
        // 设置音频循环播放
        mMediaMusic.setLooping(false);
        // 播放声音
        mMediaMusic.start();

        initThread();
        mHandler.postDelayed(timeOutTask, 30000);//30秒超时
    }

    int MSG_INIT_OK = 1;
    int MSG_INIT_INFO = 2;
    int MSG_INIT_TIMEOUT = 9;

    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == MSG_INIT_TIMEOUT) {

                if (mHandler != null && timeOutTask != null) {
                    mHandler.removeCallbacks(timeOutTask);
                }

                Toast.makeText(LoadingByeActivity.this, getString(R.string.timeout), Toast.LENGTH_LONG).show();
                LoadingByeActivity.this.finish();

            } else if (msg.what == MSG_INIT_OK) {

                if (mHandler != null && timeOutTask != null) {
                    mHandler.removeCallbacks(timeOutTask);
                }

                startActivity(new Intent(getApplication(), LoginActivity.class));
                LoadingByeActivity.this.finish();
            } else if (msg.what == MSG_INIT_INFO) {


            }
        }
    };

    Runnable timeOutTask = new Runnable() {
        public void run() {

            isTimeout = true;

            Message msg = Message.obtain();
            msg.what = MSG_INIT_TIMEOUT;
            mHandler.sendMessage(msg);
        }
    };
    boolean isTimeout = false;

    private void initThread() {
        new Thread() {
            public void run() {
                try {
                    if (!isTimeout) {
                        // 初始化
                        try {
                            Thread.currentThread().sleep(3000);
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                        sendInitInfo("");
                    }

                    if (!isTimeout) {
                        //初始化完成
                        Message msg = Message.obtain();
                        msg.what = MSG_INIT_OK;
                        mHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void sendInitInfo(String info) {
        Message msg = Message.obtain();
        msg.what = MSG_INIT_INFO;
        msg.obj = info;
        mHandler.sendMessage(msg);
    }
}
