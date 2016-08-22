package com.ruptech.ai.me;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ruptech.ai.App;
import com.ruptech.ai.R;
import com.ruptech.ai.MainActivity;
import com.ruptech.ai.question.QuestionDetailFragment;
import com.ruptech.ai.question.QuestionLoadingFragment;
import com.ruptech.ai.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MeFavoriteFragment extends Fragment {
    private static final String TAG = MeFavoriteFragment.class.getName();
    @InjectView(R.id.toolbar_me_favorite)
    TextView toolbar;
    @InjectView(R.id.return_icon_me_favorite)
    ImageView returnIcon;

    @InjectView(R.id.listView_xzfw_me_favorite)
    ListView listView_xzfw;
    @InjectView(R.id.listView_rlzy_me_favorite)
    ListView listView_rlzy;
    @InjectView(R.id.listView_cwzx_me_favorite)
    ListView listView_cwzx;
    @InjectView(R.id.listView_itzc_me_favorite)
    ListView listView_itzc;

    Properties properties;

    public static MeFavoriteFragment newInstance() {
        MeFavoriteFragment fragment = new MeFavoriteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        String subForder = App.SAVE_FILE_PATH;
        File folder = new File(subForder);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(subForder, QuestionDetailFragment.PROPERTIES_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        properties = App.loadProperties(getActivity(), App.SAVE_FILE_PATH + "/" + QuestionDetailFragment.PROPERTIES_NAME);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sub_fragment_me_favorite, container, false);
        ButterKnife.inject(this, rootView);

        returnIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToMeFirstFragment();
            }
        });


        List<Map<String, Object>> items_xzfw = initItems(MainActivity.TYPE_XZFW);
        SimpleAdapter adapter_xzfw = new SimpleAdapter(getActivity(), items_xzfw, R.layout.item_favorite,
                new String[]{"TITLE"},
                new int[]{R.id.item_question_title});
        listView_xzfw.setAdapter(adapter_xzfw);
        listView_xzfw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                onListItemClick(arg0, arg1, arg2, arg3, MainActivity.TYPE_XZFW);
            }
        });
        setListViewHeight(listView_xzfw);

        List<Map<String, Object>> items_rlzy = initItems(MainActivity.TYPE_RLZY);
        SimpleAdapter adapter_rlzy = new SimpleAdapter(getActivity(), items_rlzy, R.layout.item_favorite,
                new String[]{"TITLE"},
                new int[]{R.id.item_question_title});
        listView_rlzy.setAdapter(adapter_rlzy);
        listView_rlzy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                onListItemClick(arg0, arg1, arg2, arg3, MainActivity.TYPE_RLZY);
            }
        });
        setListViewHeight(listView_rlzy);

        List<Map<String, Object>> items_cwzx = initItems(MainActivity.TYPE_CWZX);
        SimpleAdapter adapter_cwzx = new SimpleAdapter(getActivity(), items_cwzx, R.layout.item_favorite,
                new String[]{"TITLE"},
                new int[]{R.id.item_question_title});
        listView_cwzx.setAdapter(adapter_cwzx);
        listView_cwzx.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                onListItemClick(arg0, arg1, arg2, arg3, MainActivity.TYPE_CWZX);
            }
        });
        setListViewHeight(listView_cwzx);

        List<Map<String, Object>> items_itzc = initItems(MainActivity.TYPE_ITZC);
        SimpleAdapter adapter_itzc = new SimpleAdapter(getActivity(), items_itzc, R.layout.item_favorite,
                new String[]{"TITLE"},
                new int[]{R.id.item_question_title});
        listView_itzc.setAdapter(adapter_itzc);
        listView_itzc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                onListItemClick(arg0, arg1, arg2, arg3, MainActivity.TYPE_ITZC);
            }
        });
        setListViewHeight(listView_itzc);

        return rootView;
    }

    private List<Map<String, Object>> initItems(String type) {
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

            String favorite = ((String) properties.get("favorite_" + type + "_" + i));
            if ("1".equals(favorite)) {
                temp.put("TITLE", Utils.getSubString(titles[i], 17, "..."));
                result.add(temp);
            }
        }
        return result;
    }

    public void onListItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3, String category) {
        displayQuestion(category, arg2);
    }

    public void displayQuestion(String type, int index) {
        moveToQuestionLoadingFragment(type, index);
    }

//    public void moveToQuestionDetailFragment(String type, int index) {
//        ((MainActivity) getActivity()).pager.setCurrentItem(1);
//        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//        Fragment target = QuestionDetailFragment.newInstance(type, new Integer(index).toString());
//        ft.replace(R.id.question_main_fragment, target);
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        ft.commit();
//    }

    public void moveToQuestionLoadingFragment(String type, int index) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment target = QuestionLoadingFragment.newInstance(type, new Integer(index).toString());
        ft.replace(R.id.question_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

        ((MainActivity) getActivity()).pager.setCurrentItem(1);
    }

    private void moveToMeFirstFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment target = MeFirstFragment.newInstance();
        ft.replace(R.id.me_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

    }


    private void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        listView.setLayoutParams(params);
    }
}

