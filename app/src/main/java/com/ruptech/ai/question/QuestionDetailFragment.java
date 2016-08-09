package com.ruptech.ai.question;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruptech.ai.R;
import com.ruptech.ai.main.MainActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;


public class QuestionDetailFragment extends Fragment {
    private static final String TAG = QuestionDetailFragment.class.getName();

    private String type;
    private int index;
    private List<Map<String, Object>> items;
    @InjectView(R.id.toolbar_question_detail)
    TextView toolbar;
    @InjectView(R.id.textView_question_detail)
    TextView content;
    @InjectView(R.id.return_icon_question_detail)
    ImageView returnIcon;

    public static QuestionDetailFragment newInstance(String type, String index) {
        QuestionDetailFragment fragment = new QuestionDetailFragment();
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

        View rootView = inflater.inflate(R.layout.sub_fragment_question_detail, container, false);
        ButterKnife.inject(this, rootView);

        if (MainActivity.TYPE_XZFW.equals(type)) {
            String title = getString(R.string.title_item_xzfw);
            toolbar.setText(title);
            String[] contents = MainActivity.xzfw_contents;
            content.setText(contents[index]);
        } else if (MainActivity.TYPE_RLZY.equals(type)) {
            String title = getString(R.string.title_item_rlzy);
            toolbar.setText(title);
            String[] contents = MainActivity.rlzy_contents;
            content.setText(contents[index]);
        } else if (MainActivity.TYPE_CWZX.equals(type)) {
            String title = getString(R.string.title_item_cwzx);
            toolbar.setText(title);
            String[] contents = MainActivity.cwzx_contents;
            content.setText(contents[index]);
        } else if (MainActivity.TYPE_ITZC.equals(type)) {
            String title = getString(R.string.title_item_itzc);
            toolbar.setText(title);
            String[] contents = MainActivity.itzc_contents;
            content.setText(contents[index]);
        }

        returnIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionListFragment(index);
            }
        });

        return rootView;

    }

    private void moveToQuestionListFragment(int index) {

        MainActivity mainActivity = (MainActivity) this.getActivity();
        FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();

        Fragment target = QuestionListFragment.newInstance(type);
        ft.replace(R.id.question_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

    }
}

