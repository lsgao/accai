package com.ruptech.ai.main;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ruptech.ai.App;
import com.ruptech.ai.R;
import com.ruptech.ai.me.MeFirstFragment;
import com.ruptech.ai.me.UploadPhotoActivity;
import com.ruptech.ai.question.QuestionListFragment;
import com.ruptech.ai.view.PagerItem;
import com.ruptech.ai.view.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    public static final String TYPE_XZFW = "XZFW";
    public static final String TYPE_RLZY = "RLZY";
    public static final String TYPE_CWZX = "CWZX";
    public static final String TYPE_ITZC = "ITZC";

    public static final String EXTRA_TYPE = "EXTRA_TYPE";
    public static final String EXTRA_INDEX = "EXTRA_INDEX";
    public static final String EXTRA_KIND = "EXTRA_KIND";

    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_CONTNET = "EXTRA_CONTNET";

    public static final int TAKE_PHOTO = 1;

    public static MainActivity instance = null;

    @InjectView(R.id.pager)
    public ViewPager pager;

    @InjectView(R.id.tab_home)
    LinearLayout tab_home;
    @InjectView(R.id.tab_home_title)
    TextView tab_home_title;
    @InjectView(R.id.tab_home_icon)
    ImageView tab_home_icon;

    @InjectView(R.id.tab_category)
    LinearLayout tab_category;
    @InjectView(R.id.tab_category_title)
    TextView tab_category_title;
    @InjectView(R.id.tab_category_icon)
    ImageView tab_category_icon;

    @InjectView(R.id.tab_me)
    LinearLayout tab_me;
    @InjectView(R.id.tab_me_title)
    TextView tab_me_title;
    @InjectView(R.id.tab_me_icon)
    ImageView tab_me_icon;

    @InjectView(R.id.tab_more)
    LinearLayout tab_more;
    @InjectView(R.id.tab_more_title)
    TextView tab_more_title;
    @InjectView(R.id.tab_more_icon)
    ImageView tab_more_icon;

    @InjectView(R.id.tab_bottom_line)
    ImageView tabBottomLine;

    public static String[] xzfw_titles = {};
    public static String[] xzfw_contents = {};
    public static String[] rlzy_titles = {};
    public static String[] rlzy_contents = {};
    public static String[] cwzx_titles = {};
    public static String[] cwzx_contents = {};
    public static String[] itzc_titles = {};
    public static String[] itzc_contents = {};
    public static String[] category_menu = {};

    long back_pressed;

    public static void close() {
        if (instance != null) {
            instance.finish();
            instance = null;
        }
    }

    @Override
    public void onBackPressed() {

        if (back_pressed + 2000 > System.currentTimeMillis()) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.confirm_exit), Toast.LENGTH_SHORT)
                    .show();
            back_pressed = System.currentTimeMillis();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        instance = this;

        xzfw_titles = getResources().getStringArray(R.array.xzfw_title);
        xzfw_contents = getResources().getStringArray(R.array.xzfw_content);
        rlzy_titles = getResources().getStringArray(R.array.rlzy_title);
        rlzy_contents = getResources().getStringArray(R.array.rlzy_content);
        cwzx_titles = getResources().getStringArray(R.array.cwzx_title);
        cwzx_contents = getResources().getStringArray(R.array.cwzx_content);
        itzc_titles = getResources().getStringArray(R.array.itzc_title);
        itzc_contents = getResources().getStringArray(R.array.itzc_content);

        category_menu = getResources().getStringArray(R.array.categories);

        InitWidth();

        // init view pager
        initViewPager();

        initTabHost();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_PHOTO) {
            try {
                ImageView photo = getUsePhoto();
                if (null != photo) {
                    Bitmap bm = BitmapFactory.decodeFile(App.SAVE_PIC_PATH + "/" + UploadPhotoActivity.PIC_NAME);
                    if (null != bm) {
                        photo.setImageBitmap(bm);
                    }
                }
            } catch (Exception e) {
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void initViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), setupPagerItems());
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new PagerOnPageChangeListener());
    }

    private List<PagerItem> setupPagerItems() {
        List<PagerItem> mTabs = new ArrayList<PagerItem>();

        /**
         * Populate our tab list with tabs. Each item contains a title, indicator color and divider
         * color, which are used by {@link com.ruptech.ai.view.SlidingTabLayout}.
         */
        mTabs.add(new PagerItem(
                getString(R.string.tab_title_home)
        ) {
            public Fragment createFragment() {
                return HomeFragment.newInstance();
            }
        });

        mTabs.add(new PagerItem(
                getString(R.string.tab_title_category) // Title
        ) {
            public Fragment createFragment() {
                return QuestionFragment.newInstance(MainActivity.TYPE_XZFW, "0", QuestionFragment.KIND_LIST);
            }
        });

        mTabs.add(new PagerItem(
                getString(R.string.tab_title_me) // Title
        ) {
            public Fragment createFragment() {
                return MeFragment.newInstance(MeFragment.KIND_FIRST);
            }
        });

        mTabs.add(new PagerItem(
                getString(R.string.tab_title_more) // Title
        ) {
            public Fragment createFragment() {
                return MoreFragment.newInstance(MoreFragment.KIND_FIRST);
            }
        });
        return mTabs;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    protected void initTabHost() {
        tab_home.setOnClickListener(new TabOnClickListener(0));
        tab_category.setOnClickListener(new TabOnClickListener(1));
        tab_me.setOnClickListener(new TabOnClickListener(2));
        tab_more.setOnClickListener(new TabOnClickListener(3));
    }

    private void popupCategoriesMenu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setItems(MainActivity.category_menu, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                String type;
                switch (arg1) {
                    case 0:
                        type = MainActivity.TYPE_XZFW;
                        moveToQuestionListFragment(type);
                        break;
                    case 1:
                        type = MainActivity.TYPE_RLZY;
                        moveToQuestionListFragment(type);
                        break;
                    case 2:
                        //type = MainActivity.TYPE_CWZX;
                        //moveToQuestionListFragment(type);
                        moveToQuestionBuildingFragment("财务咨询", "[财务咨询内容更新中...]");
                        break;
                    case 3:
                        type = MainActivity.TYPE_ITZC;
                        moveToQuestionListFragment(type);
                        break;
                    default:
                        type = MainActivity.TYPE_XZFW;
                        moveToQuestionListFragment(type);
                }
                arg0.dismiss();
            }
        });
        builder.show();
    }

    public void moveToQuestionListFragment(String type) {
        pager.setCurrentItem(1);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        Fragment target = QuestionListFragment.newInstance(type);
        ft.replace(R.id.question_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    public void moveToQuestionBuildingFragment(String title, String content) {
        pager.setCurrentItem(1);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        Fragment target = BuildingFragment.newInstance(title, content);
        ft.replace(R.id.question_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    /**
     * 自定义监听类
     *
     * @author Administrator
     */
    public class TabOnClickListener implements View.OnClickListener {
        private int index = 0;

        public TabOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            if (1 == index) {
                popupCategoriesMenu();
            } else {
                //设置ViewPager的当前view
                pager.setCurrentItem(index);
            }
        }
    }

    /**
     * 页面滑动监听
     *
     * @author Administrator
     */
    public class PagerOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int index) {
            //动画
            Animation animation = null;
            switch (index) {
                case 0:
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(position_one, 0, 0, 0);
                        tab_category_icon.setImageResource(R.drawable.tab_category);
                        tab_category_title.setTextColor(getResources().getColor(R.color.font_color));
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(position_two, 0, 0, 0);
                        tab_me_icon.setImageResource(R.drawable.tab_me);
                        tab_me_title.setTextColor(getResources().getColor(R.color.font_color));
                    } else if (currIndex == 3) {
                        animation = new TranslateAnimation(position_three, 0, 0, 0);
                        tab_more_icon.setImageResource(R.drawable.tab_more);
                        tab_more_title.setTextColor(getResources().getColor(R.color.font_color));
                    }
                    tab_home_icon.setImageResource(R.drawable.tab_home_selected);
                    tab_home_title.setTextColor(getResources().getColor(R.color.editor_pressed_color));
                    break;
                case 1:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(0, position_one, 0, 0);
                        tab_home_icon.setImageResource(R.drawable.tab_home);
                        tab_home_title.setTextColor(getResources().getColor(R.color.font_color));
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(position_two, position_one, 0, 0);
                        tab_me_icon.setImageResource(R.drawable.tab_me);
                        tab_me_title.setTextColor(getResources().getColor(R.color.font_color));
                    } else if (currIndex == 3) {
                        animation = new TranslateAnimation(position_three, position_one, 0, 0);
                        tab_more_icon.setImageResource(R.drawable.tab_more);
                        tab_more_title.setTextColor(getResources().getColor(R.color.font_color));
                    }
                    tab_category_icon.setImageResource(R.drawable.tab_category_selected);
                    tab_category_title.setTextColor(getResources().getColor(R.color.editor_pressed_color));
                    break;
                case 2:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(0, position_two, 0, 0);
                        tab_home_icon.setImageResource(R.drawable.tab_home);
                        tab_home_title.setTextColor(getResources().getColor(R.color.font_color));
                    } else if (currIndex == 1) {
                        animation = new TranslateAnimation(position_one, position_two, 0, 0);
                        tab_category_icon.setImageResource(R.drawable.tab_category);
                        tab_category_title.setTextColor(getResources().getColor(R.color.font_color));
                    } else if (currIndex == 3) {
                        animation = new TranslateAnimation(position_three, position_two, 0, 0);
                        tab_more_icon.setImageResource(R.drawable.tab_more);
                        tab_more_title.setTextColor(getResources().getColor(R.color.font_color));
                    }
                    tab_me_icon.setImageResource(R.drawable.tab_me_selected);
                    tab_me_title.setTextColor(getResources().getColor(R.color.editor_pressed_color));
                    break;
                case 3:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(0, position_three, 0, 0);
                        tab_home_icon.setImageResource(R.drawable.tab_home);
                        tab_home_title.setTextColor(getResources().getColor(R.color.font_color));
                    } else if (currIndex == 1) {
                        animation = new TranslateAnimation(position_one, position_three, 0, 0);
                        tab_category_icon.setImageResource(R.drawable.tab_category);
                        tab_category_title.setTextColor(getResources().getColor(R.color.font_color));
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(position_two, position_three, 0, 0);
                        tab_me_icon.setImageResource(R.drawable.tab_me);
                        tab_me_title.setTextColor(getResources().getColor(R.color.font_color));
                    }
                    tab_more_icon.setImageResource(R.drawable.tab_more_selected);
                    tab_more_title.setTextColor(getResources().getColor(R.color.editor_pressed_color));
                    break;
            }
            //记录当前的页面位置
            currIndex = index;
            //动画播放完后，保持结束时的状态
            animation.setFillAfter(true);
            //动画持续时间
            animation.setDuration(300);
            //底栏滑动白线开始动画
            tabBottomLine.startAnimation(animation);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    }

    public ImageView getUsePhoto() {
        if (2 == currIndex) {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.me_main_fragment);
            if (currentFragment instanceof MeFirstFragment) {
                return ((MeFirstFragment) currentFragment).getUserPhoto();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 初始化底栏，获取相应宽度信息
     */
    private void InitWidth() {

        //获取底栏白色滑动线的宽度
        bottomLineWidth = tabBottomLine.getLayoutParams().width;

        //获取屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        //屏幕分4份，计算出每份中白色滑条外的间隔距离
        offset = (int) ((screenW / 4.0 - bottomLineWidth) / 2);

        //计算出底栏的位置
        position_one = (int) (screenW / 4.0);
        position_two = position_one * 2;
        position_three = position_one * 3;
    }

    private int currIndex = 0;
    private int bottomLineWidth;
    private int offset = 0;
    private int position_one;
    private int position_two;
    private int position_three;
}
