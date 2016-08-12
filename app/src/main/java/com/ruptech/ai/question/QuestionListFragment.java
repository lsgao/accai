package com.ruptech.ai.question;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruptech.ai.R;
import com.ruptech.ai.main.MainActivity;
import com.ruptech.ai.utils.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QuestionListFragment extends Fragment {
    private static final String TAG = QuestionListFragment.class.getName();

    private String type;
    private List<Map<String, Object>> items;
    @InjectView(R.id.toolbar_question_list)
    TextView toolbar;
    @InjectView(R.id.listView_question_list)
    ListView listView;

    public static QuestionListFragment newInstance(String type) {
        QuestionListFragment fragment = new QuestionListFragment();
        Bundle args = new Bundle();
        args.putString(MainActivity.EXTRA_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        type = getArguments().getString(MainActivity.EXTRA_TYPE);
        items = initItems();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.sub_fragment_question_list, container, false);
        ButterKnife.inject(this, rootView);

        if (MainActivity.TYPE_XZFW.equals(type)) {
            String title = getString(R.string.title_item_xzfw);
            toolbar.setText(title);
        } else if (MainActivity.TYPE_RLZY.equals(type)) {
            String title = getString(R.string.title_item_rlzy);
            toolbar.setText(title);
        } else if (MainActivity.TYPE_CWZX.equals(type)) {
            String title = getString(R.string.title_item_cwzx);
            toolbar.setText(title);
        } else if (MainActivity.TYPE_ITZC.equals(type)) {
            String title = getString(R.string.title_item_itzc);
            toolbar.setText(title);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), items, R.layout.item_question,
                new String[]{"TITLE"},
                new int[]{R.id.item_question_title});
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                onListItemClick(arg0, arg1, arg2, arg3);
            }
        });
        return rootView;

    }

    private List<Map<String, Object>> initItems() {
        String[] titles = {};
        if (MainActivity.TYPE_XZFW.equals(type)) {
            titles = MainActivity.xzfw_titles;
        } else if (MainActivity.TYPE_RLZY.equals(type)) {
            titles = MainActivity.rlzy_titles;
        } else if (MainActivity.TYPE_CWZX.equals(type)) {
            titles = MainActivity.cwzx_titles;
        } else if (MainActivity.TYPE_ITZC.equals(type)) {
            titles = MainActivity.itzc_titles;
        }
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < titles.length; i++) {
            Map<String, Object> temp = new HashMap<String, Object>();
            temp.put("TITLE", Utils.getSubString(titles[i], 17, "..."));
            result.add(temp);
        }

        return result;
    }

    public void onListItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        moveToQuestionDetailFragment(arg2);
    }

    private void moveToQuestionDetailFragment(int index) {
        MainActivity mainActivity = (MainActivity) this.getActivity();
        FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();

        Fragment target = QuestionDetailFragment.newInstance(type, new Integer(index).toString());
        ft.replace(R.id.question_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

}

