package com.ruptech.ai.main;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ruptech.ai.R;
import com.ruptech.ai.me.MeFirstFragment;
import com.ruptech.ai.question.QuestionDetailFragment;
import com.ruptech.ai.question.QuestionListFragment;
import com.ruptech.ai.view.PagerItem;
import com.ruptech.ai.view.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends ActionBarActivity implements MaterialTabListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    public static final String TYPE_XZFW = "XZFW";
    public static final String TYPE_RLZY = "RLZY";
    public static final String TYPE_CWZX = "CWZX";
    public static final String TYPE_ITZC = "ITZC";

    public static final String EXTRA_TYPE = "EXTRA_TYPE";
    public static final String EXTRA_INDEX = "EXTRA_INDEX";
    public static final String EXTRA_KIND = "EXTRA_KIND";

    public static final int TAKE_PHOTO = 1;

    public static MainActivity instance = null;
    @InjectView(R.id.tabHost)
    MaterialTabHost tabHost;
    @InjectView(R.id.pager)
    ViewPager pager;

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
    public void onTabSelected(MaterialTab tab) {
        if(1 == tab.getPosition()) {
            popupCategoriesMenu();
        } else {
            pager.setCurrentItem(tab.getPosition());
        }
    }

    @Override
    public void onTabReselected(MaterialTab tab) {
    }

    @Override
    public void onTabUnselected(MaterialTab tab) {
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

        // init view pager
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), setupTabs());
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);
            }
        });
        setTabHosts();
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
                //
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

    protected List<PagerItem> setupTabs() {
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
                return QuestionFragment.newInstance(MainActivity.TYPE_CWZX,"0",QuestionFragment.KIND_LIST);
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
    protected void setTabHosts() {
        MaterialTab homeTab = new MaterialTab(this, true);
        homeTab.setIcon(getResources().getDrawable(R.drawable.tab_home));
//        homeTab.setText(getString(R.string.tab_title_home));
        homeTab.setTabListener(this);
        tabHost.addTab(homeTab);

//        MaterialTab homeTab_text = new MaterialTab(this, false);
//        homeTab_text.setText(getString(R.string.tab_title_home));
//        homeTab_text.setTabListener(this);
//        tabHost_text.addTab(homeTab_text);

        MaterialTab categoryTab = new MaterialTab(this, true);
        categoryTab.setIcon(getResources().getDrawable(R.drawable.tab_category));
//        categoryTab.setText(getString(R.string.tab_title_category));
        categoryTab.setTabListener(this);
        tabHost.addTab(categoryTab);

//        MaterialTab categoryTab_text = new MaterialTab(this, false);
//        categoryTab_text.setText(getString(R.string.tab_title_category));
//        categoryTab_text.setTabListener(this);
//        tabHost_text.addTab(categoryTab_text);

        MaterialTab meTab = new MaterialTab(this, true);
        meTab.setIcon(getResources().getDrawable(R.drawable.tab_me));
//        meTab.setText(getString(R.string.tab_title_me));
        meTab.setTabListener(this);
        tabHost.addTab(meTab);

//        MaterialTab meTab_text = new MaterialTab(this, false);
//        meTab_text.setText(getString(R.string.tab_title_me));
//        meTab_text.setTabListener(this);
//        tabHost_text.addTab(meTab_text);

        MaterialTab moreTab = new MaterialTab(this, true);
        moreTab.setIcon(getResources().getDrawable(R.drawable.tab_more));
//        moreTab.setText(getString(R.string.tab_title_more));
        moreTab.setTabListener(this);
        tabHost.addTab(moreTab);

//        MaterialTab moreTab_text = new MaterialTab(this, false);
//        moreTab_text.setText(getString(R.string.tab_title_more));
//        moreTab_text.setTabListener(this);
//        tabHost_text.addTab(moreTab_text);
    }

    private void popupCategoriesMenu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setItems(MainActivity.category_menu, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface arg0, int arg1)
            {
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
                        type = MainActivity.TYPE_CWZX;
                        moveToQuestionListFragment(type);
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

}
