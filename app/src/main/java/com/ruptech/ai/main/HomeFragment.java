package com.ruptech.ai.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruptech.ai.R;
import com.ruptech.ai.question.QuestionDetailFragment;
import com.ruptech.ai.question.QuestionListFragment;
import com.ruptech.ai.utils.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getName();

    // TOOLBAR
    @InjectView(R.id.toolbar_home)
    ImageView toolbar;

    // 中间部分的分类图标
    @InjectView(R.id.imageView_category_xzfw)
    ImageView category_xzfw_image;
    @InjectView(R.id.textView_category_xzfw)
    TextView category_xzfw_text;

    @InjectView(R.id.imageView_category_rlzy)
    ImageView category_rlzy_image;
    @InjectView(R.id.textView_category_rlzy)
    TextView category_rlzy_text;

    @InjectView(R.id.imageView_category_cwzx)
    ImageView category_cwzx_image;
    @InjectView(R.id.textView_category_cwzx)
    TextView category_cwzx_text;

    @InjectView(R.id.imageView_category_itzc)
    ImageView category_itzc_image;
    @InjectView(R.id.textView_category_itzc)
    TextView category_itzc_text;

    // 热门问答
    @InjectView(R.id.layout_RMWD_1)
    RelativeLayout rmwd_item_1;
    @InjectView(R.id.imageView_RMWD_LOGO_1)
    ImageView rmwd_logo_1;
    @InjectView(R.id.textView_RMWD_1)
    TextView rmwd_1;
    @InjectView(R.id.imageView_RMWD_NEXT_1)
    ImageView rmwd_next_1;

    @InjectView(R.id.layout_RMWD_2)
    RelativeLayout rmwd_item_2;
    @InjectView(R.id.imageView_RMWD_LOGO_2)
    ImageView rmwd_logo_2;
    @InjectView(R.id.textView_RMWD_2)
    TextView rmwd_2;
    @InjectView(R.id.imageView_RMWD_NEXT_2)
    ImageView rmwd_next_2;

    @InjectView(R.id.layout_RMWD_3)
    RelativeLayout rmwd_item_3;
    @InjectView(R.id.imageView_RMWD_LOGO_3)
    ImageView rmwd_logo_3;
    @InjectView(R.id.textView_RMWD_3)
    TextView rmwd_3;
    @InjectView(R.id.imageView_RMWD_NEXT_3)
    ImageView rmwd_next_3;

    // 最新更新
    @InjectView(R.id.layout_ZXGX_1)
    RelativeLayout zxgx_item_1;
    @InjectView(R.id.imageView_ZXGX_LOGO_1)
    ImageView zxgx_logo_1;
    @InjectView(R.id.textView_ZXGX_1)
    TextView zxgx_1;
    @InjectView(R.id.imageView_ZXGX_NEXT_1)
    ImageView zxgx_next_1;

    @InjectView(R.id.layout_ZXGX_2)
    RelativeLayout zxgx_item_2;
    @InjectView(R.id.imageView_ZXGX_LOGO_2)
    ImageView zxgx_logo_2;
    @InjectView(R.id.textView_ZXGX_2)
    TextView zxgx_2;
    @InjectView(R.id.imageView_ZXGX_NEXT_2)
    ImageView zxgx_next_2;

    @InjectView(R.id.layout_ZXGX_3)
    RelativeLayout zxgx_item_3;
    @InjectView(R.id.imageView_ZXGX_LOGO_3)
    ImageView zxgx_logo_3;
    @InjectView(R.id.textView_ZXGX_3)
    TextView zxgx_3;
    @InjectView(R.id.imageView_ZXGX_NEXT_3)
    ImageView zxgx_next_3;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, rootView);

        rmwd_1.setText(Utils.getSubString(MainActivity.xzfw_titles[0], 15, "..."));
        rmwd_2.setText(Utils.getSubString(MainActivity.rlzy_titles[0], 15, "..."));
        rmwd_3.setText(Utils.getSubString(MainActivity.itzc_titles[0], 15, "..."));
        zxgx_1.setText(Utils.getSubString(MainActivity.xzfw_titles[1], 15, "..."));
        zxgx_2.setText(Utils.getSubString(MainActivity.rlzy_titles[1], 15, "..."));
        zxgx_3.setText(Utils.getSubString(MainActivity.itzc_titles[1], 15, "..."));

        category_xzfw_image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionListFragment(MainActivity.TYPE_XZFW);
            }
        });
        category_xzfw_text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionListFragment(MainActivity.TYPE_XZFW);
            }
        });
        category_rlzy_image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionListFragment(MainActivity.TYPE_RLZY);
            }
        });
        category_rlzy_text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionListFragment(MainActivity.TYPE_RLZY);
            }
        });
        category_cwzx_image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionBuildingFragment("财务咨询", "[财务咨询内容更新中...]");
            }
        });
        category_cwzx_text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionBuildingFragment("财务咨询", "[财务咨询内容更新中...]");
            }
        });
        category_itzc_image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionListFragment(MainActivity.TYPE_ITZC);
            }
        });
        category_itzc_text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionListFragment(MainActivity.TYPE_ITZC);
            }
        });

        rmwd_item_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_XZFW, 0);
            }
        });
        rmwd_logo_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_XZFW, 0);
            }
        });
        rmwd_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_XZFW, 0);
            }
        });
        rmwd_next_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_XZFW, 0);
            }
        });

        rmwd_item_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_RLZY, 0);
            }
        });
        rmwd_logo_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_RLZY, 0);
            }
        });
        rmwd_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_RLZY, 0);
            }
        });
        rmwd_next_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_RLZY, 0);
            }
        });

        rmwd_item_3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_ITZC, 0);
            }
        });
        rmwd_logo_3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_ITZC, 0);
            }
        });
        rmwd_3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_ITZC, 0);
            }
        });
        rmwd_next_3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_ITZC, 0);
            }
        });

        zxgx_item_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_XZFW, 1);
            }
        });
        zxgx_logo_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_XZFW, 1);
            }
        });
        zxgx_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_XZFW, 1);
            }
        });
        zxgx_next_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_XZFW, 1);
            }
        });

        zxgx_item_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_RLZY, 1);
            }
        });
        zxgx_logo_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_RLZY, 1);
            }
        });
        zxgx_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_RLZY, 1);
            }
        });
        zxgx_next_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_RLZY, 1);
            }
        });

        zxgx_item_3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_ITZC, 1);
            }
        });
        zxgx_logo_3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_ITZC, 1);
            }
        });
        zxgx_3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_ITZC, 1);
            }
        });
        zxgx_next_3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_ITZC, 1);
            }
        });

        return rootView;
    }

    public void moveToQuestionDetailFragment(String type, int index) {
        ((MainActivity) getActivity()).pager.setCurrentItem(1);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

        Fragment target = QuestionDetailFragment.newInstance(type, new Integer(index).toString());
        ft.replace(R.id.question_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    public void moveToQuestionListFragment(String type) {
        ((MainActivity) getActivity()).pager.setCurrentItem(1);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

        Fragment target = QuestionListFragment.newInstance(type);
        ft.replace(R.id.question_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    public void moveToQuestionBuildingFragment(String title, String content) {
        ((MainActivity) getActivity()).pager.setCurrentItem(1);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

        Fragment target = BuildingFragment.newInstance(title, content);
        ft.replace(R.id.question_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}

