package com.ruptech.ai.question;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ruptech.ai.MainActivity;
import com.ruptech.ai.R;
import com.ruptech.ai.utils.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ls_gao on 2016/8/19.
 */
public class QuestionLoadingFragment extends Fragment {

    private String type;
    private int index;

    @InjectView(R.id.toolbar_question_loading)
    TextView toolbar;
    @InjectView(R.id.return_icon_question_loading)
    ImageView returnIcon;

    MediaPlayer mMediaMusic = null;

    public static QuestionLoadingFragment newInstance(String type, String index) {
        QuestionLoadingFragment fragment = new QuestionLoadingFragment();
        Bundle args = new Bundle();
        args.putString(MainActivity.EXTRA_TYPE, type);
        args.putString(MainActivity.EXTRA_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        type = getArguments().getString(MainActivity.EXTRA_TYPE);
        index = new Integer(getArguments().getString(MainActivity.EXTRA_INDEX)).intValue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.sub_fragment_question_loading, container, false);
        ButterKnife.inject(this, rootView);

        if (MainActivity.TYPE_XZFW.equals(type)) {
            String title = Utils.getSubString(MainActivity.xzfw_titles[index], 15, "...");
            toolbar.setText(title);
        } else if (MainActivity.TYPE_RLZY.equals(type)) {
            String title = Utils.getSubString(MainActivity.rlzy_titles[index], 15, "...");
            toolbar.setText(title);
        } else if (MainActivity.TYPE_CWZX.equals(type)) {
            String title = Utils.getSubString(MainActivity.cwzx_titles[index], 15, "...");
            toolbar.setText(title);
        } else if (MainActivity.TYPE_ITZC.equals(type)) {
            String title = Utils.getSubString(MainActivity.itzc_titles[index], 15, "...");
            toolbar.setText(title);
        }

        returnIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionListFragment();
            }
        });

        // 创建MediaPlayer 这里用的音频格式是mp3
        mMediaMusic = MediaPlayer.create(getActivity(), R.raw.robot_waiting);
        // 设置音频循环播放
        mMediaMusic.setLooping(false);
        // 播放声音
        mMediaMusic.start();

        initThread();
        mHandler.postDelayed(timeOutTask, 30000);//30秒超时

        return rootView;
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

                Toast.makeText(getActivity(), "timeout", Toast.LENGTH_LONG).show();

            } else if (msg.what == MSG_INIT_OK) {

                if (mHandler != null && timeOutTask != null) {
                    mHandler.removeCallbacks(timeOutTask);
                }

                moveToQuestionDetailFragment();
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
                            Thread.currentThread().sleep(2500);
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

    private void moveToQuestionListFragment() {

        MainActivity mainActivity = (MainActivity) this.getActivity();
        FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();

        Fragment target = QuestionListFragment.newInstance(type);
        ft.replace(R.id.question_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

    }

    private void moveToQuestionDetailFragment() {

        MainActivity mainActivity = (MainActivity) this.getActivity();
        FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();

        Fragment target = QuestionDetailFragment.newInstance(type, new Integer(index).toString());
        ft.replace(R.id.question_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

    }
}
