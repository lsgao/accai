package com.ruptech.ai.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruptech.ai.App;
import com.ruptech.ai.R;
import com.ruptech.ai.question.QuestionDetailFragment;
import com.ruptech.ai.question.QuestionListFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

import android.widget.ImageView;
import android.widget.TextView;


public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getName();

    @InjectView(R.id.toolbar_home)
    TextView toolbar;
    @InjectView(R.id.imageView_category_xzfw)
    ImageView category_xzfw;
    @InjectView(R.id.imageView_category_rlzy)
    ImageView category_rlzy;
    @InjectView(R.id.imageView_category_cwzx)
    ImageView category_cwzx;
    @InjectView(R.id.imageView_category_itzc)
    ImageView category_itzc;

    @InjectView(R.id.textView_RMWD_1)
    TextView rmwd_1;
    @InjectView(R.id.textView_RMWD_2)
    TextView rmwd_2;
    @InjectView(R.id.textView_ZXGX_1)
    TextView zxgx_1;
    @InjectView(R.id.textView_ZXGX_2)
    TextView zxgx_2;

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
        String title_home = getString(R.string.title_activity_actionbar) + " - " + App.readUser().getUsername();
        toolbar.setText(title_home);
        rmwd_1.setText(getString(R.string.ask_title) + " " + MainActivity.xzfw_titles[0]);
        rmwd_2.setText(getString(R.string.ask_title) + " " + MainActivity.xzfw_titles[1]);
        zxgx_1.setText(getString(R.string.ask_title) + " " + MainActivity.rlzy_titles[0]);
        zxgx_2.setText(getString(R.string.ask_title) + " " + MainActivity.rlzy_titles[1]);

        category_xzfw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionListFragment(MainActivity.TYPE_XZFW);
            }
        });
        category_rlzy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionListFragment(MainActivity.TYPE_RLZY);
            }
        });
        category_cwzx.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionListFragment(MainActivity.TYPE_CWZX);
            }
        });
        category_itzc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionListFragment(MainActivity.TYPE_ITZC);
            }
        });

        rmwd_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_XZFW, 0);
            }
        });
        rmwd_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_XZFW, 1);
            }
        });
        zxgx_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_RLZY, 0);
            }
        });
        zxgx_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(MainActivity.TYPE_RLZY, 1);
            }
        });
        return rootView;
    }

    public void moveToQuestionDetailFragment(String type, int index) {
        ((MainActivity)getActivity()).pager.setCurrentItem(1);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

        Fragment target = QuestionDetailFragment.newInstance(type, new Integer(index).toString());
        ft.replace(R.id.question_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    public void moveToQuestionListFragment(String type) {
        ((MainActivity)getActivity()).pager.setCurrentItem(1);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

        Fragment target = QuestionListFragment.newInstance(type);
        ft.replace(R.id.question_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}

