package com.ruptech.ai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by ls_gao on 2016/8/17.
 */
public class StartActivity extends Activity {
    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final View view = View.inflate(this, R.layout.activity_start, null);
        setContentView(view);

        //渐变展示启动屏

        AlphaAnimation start = new AlphaAnimation(0.3f, 1.0f);

        start.setDuration(2000);

        view.startAnimation(start);

        start.setAnimationListener(new Animation.AnimationListener() {

            @Override

            public void onAnimationEnd(Animation arg0) {

                redirectTo();

            }

            @Override

            public void onAnimationRepeat(Animation animation) {
            }

            @Override

            public void onAnimationStart(Animation animation) {
            }


        });


    }


    /**
     * 跳转到...
     */

    private void redirectTo() {

        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);

        finish();

    }

}
