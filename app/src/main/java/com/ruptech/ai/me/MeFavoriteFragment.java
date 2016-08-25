package com.ruptech.ai.me;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
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
import com.ruptech.ai.main.MeFragment;
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

    public static final String ITME_TITLE = "TITLE";

    @InjectView(R.id.toolbar_me_favorite)
    TextView toolbar;
    @InjectView(R.id.return_icon_me_favorite)
    ImageView returnIcon;
    @InjectView(R.id.return_text_me_favorite)
    TextView returnText;

    @InjectView(R.id.text_no_data_xzfw_me_favorite)
    TextView text_no_data_xzfw;
    @InjectView(R.id.listView_xzfw_me_favorite)
    ListView listView_xzfw;
    @InjectView(R.id.text_no_data_rlzy_me_favorite)
    TextView text_no_data_rlzy;
    @InjectView(R.id.listView_rlzy_me_favorite)
    ListView listView_rlzy;
    @InjectView(R.id.text_no_data_cwzx_me_favorite)
    TextView text_no_data_cwzx;
    @InjectView(R.id.listView_cwzx_me_favorite)
    ListView listView_cwzx;
    @InjectView(R.id.text_no_data_itzc_me_favorite)
    TextView text_no_data_itzc;
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
        MeFragment.currentMainFragment = MeFragment.KIND_FAVORITE;

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
        returnText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToMeFirstFragment();
            }
        });

        properties = App.loadProperties(getActivity(), App.SAVE_FILE_PATH + "/" + QuestionDetailFragment.PROPERTIES_NAME);

        final List<Map<String, Object>> items_xzfw = initItems(MainActivity.TYPE_XZFW);
        if (null != items_xzfw && items_xzfw.size() > 0) {
            SimpleAdapter adapter_xzfw = new SimpleAdapter(getActivity(), items_xzfw, R.layout.item_favorite,
                    new String[]{ITME_TITLE},
                    new int[]{R.id.item_question_title});
            listView_xzfw.setAdapter(adapter_xzfw);
            listView_xzfw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    String question_tile = (String) items_xzfw.get(arg2).get(ITME_TITLE);
                    int index = findIndexByTitle(question_tile, MainActivity.TYPE_XZFW);
                    onListItemClick(index, MainActivity.TYPE_XZFW);
                }
            });
            setListViewHeight(listView_xzfw);
            text_no_data_xzfw.setVisibility(View.GONE);
            listView_xzfw.setVisibility(View.VISIBLE);
        } else {
            listView_xzfw.setVisibility(View.GONE);
            text_no_data_xzfw.setVisibility(View.VISIBLE);
        }

        final List<Map<String, Object>> items_rlzy = initItems(MainActivity.TYPE_RLZY);
        if (null != items_rlzy && items_rlzy.size() > 0) {
            SimpleAdapter adapter_rlzy = new SimpleAdapter(getActivity(), items_rlzy, R.layout.item_favorite,
                    new String[]{ITME_TITLE},
                    new int[]{R.id.item_question_title});
            listView_rlzy.setAdapter(adapter_rlzy);
            listView_rlzy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    String question_tile = (String) items_rlzy.get(arg2).get(ITME_TITLE);
                    int index = findIndexByTitle(question_tile, MainActivity.TYPE_RLZY);
                    onListItemClick(index, MainActivity.TYPE_RLZY);
                }
            });
            setListViewHeight(listView_rlzy);
            text_no_data_rlzy.setVisibility(View.GONE);
            listView_rlzy.setVisibility(View.VISIBLE);
        } else {
            listView_rlzy.setVisibility(View.GONE);
            text_no_data_rlzy.setVisibility(View.VISIBLE);
        }

        final List<Map<String, Object>> items_cwzx = initItems(MainActivity.TYPE_CWZX);
        if (null != items_cwzx && items_cwzx.size() > 0) {
            SimpleAdapter adapter_cwzx = new SimpleAdapter(getActivity(), items_cwzx, R.layout.item_favorite,
                    new String[]{ITME_TITLE},
                    new int[]{R.id.item_question_title});
            listView_cwzx.setAdapter(adapter_cwzx);
            listView_cwzx.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    String question_tile = (String) items_cwzx.get(arg2).get(ITME_TITLE);
                    int index = findIndexByTitle(question_tile, MainActivity.TYPE_CWZX);
                    onListItemClick(index, MainActivity.TYPE_CWZX);
                }
            });
            setListViewHeight(listView_cwzx);
            text_no_data_cwzx.setVisibility(View.GONE);
            listView_cwzx.setVisibility(View.VISIBLE);
        } else {
            listView_cwzx.setVisibility(View.GONE);
            text_no_data_cwzx.setVisibility(View.VISIBLE);
        }

        final List<Map<String, Object>> items_itzc = initItems(MainActivity.TYPE_ITZC);
        if (null != items_itzc && items_itzc.size() > 0) {
            SimpleAdapter adapter_itzc = new SimpleAdapter(getActivity(), items_itzc, R.layout.item_favorite,
                    new String[]{ITME_TITLE},
                    new int[]{R.id.item_question_title});
            listView_itzc.setAdapter(adapter_itzc);
            listView_itzc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    String question_tile = (String) items_itzc.get(arg2).get(ITME_TITLE);
                    int index = findIndexByTitle(question_tile, MainActivity.TYPE_ITZC);
                    onListItemClick(index, MainActivity.TYPE_ITZC);
                }
            });
            setListViewHeight(listView_itzc);
            text_no_data_itzc.setVisibility(View.GONE);
            listView_itzc.setVisibility(View.VISIBLE);
        } else {
            listView_itzc.setVisibility(View.GONE);
            text_no_data_itzc.setVisibility(View.VISIBLE);
        }

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
                temp.put(ITME_TITLE, Utils.getSubString(titles[i], 17, "..."));
                result.add(temp);
            }
        }
        return result;
    }

    private int findIndexByTitle(String title, String type) {
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
        int index = 0;
        for (int i = 0; i < titles.length; i++) {
            if (Utils.getSubString(titles[i], 17, "...").equals(title)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void onListItemClick(int index, String type) {
        displayQuestion(type, index);
    }

    private void displayQuestion(String type, int index) {
        moveToQuestionLoadingFragment(type, index);
    }

//    private void moveToQuestionDetailFragment(String type, int index) {
//        ((MainActivity) getActivity()).pager.setCurrentItem(1);
//        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//        Fragment target = QuestionDetailFragment.newInstance(type, new Integer(index).toString());
//        ft.replace(R.id.question_main_fragment, target);
//        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        ft.commit();
//    }

    private void moveToQuestionLoadingFragment(String type, int index) {
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // 注册广播
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(activity);
        IntentFilter filter = new IntentFilter();
        filter.addAction(MainActivity.BROADCAST_FAVORITE);
        broadcastManager.registerReceiver(broadcastReceiver, filter);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            properties = App.loadProperties(getActivity(), App.SAVE_FILE_PATH + "/" + QuestionDetailFragment.PROPERTIES_NAME);
            String type = intent.getStringExtra(MainActivity.EXTRA_TYPE);

            if (MainActivity.TYPE_XZFW.equals(type)) {
                final List<Map<String, Object>> items_xzfw = initItems(MainActivity.TYPE_XZFW);
                if (null != items_xzfw && items_xzfw.size() > 0) {
                    SimpleAdapter adapter_xzfw = new SimpleAdapter(getActivity(), items_xzfw, R.layout.item_favorite,
                            new String[]{ITME_TITLE},
                            new int[]{R.id.item_question_title});
                    listView_xzfw.setAdapter(adapter_xzfw);
                    listView_xzfw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                                long arg3) {
                            String question_tile = (String) items_xzfw.get(arg2).get(ITME_TITLE);
                            int index = findIndexByTitle(question_tile, MainActivity.TYPE_XZFW);
                            onListItemClick(index, MainActivity.TYPE_XZFW);
                        }
                    });
                    setListViewHeight(listView_xzfw);
                    text_no_data_xzfw.setVisibility(View.GONE);
                    listView_xzfw.setVisibility(View.VISIBLE);
                } else {
                    listView_xzfw.setVisibility(View.GONE);
                    text_no_data_xzfw.setVisibility(View.VISIBLE);
                }
            } else if (MainActivity.TYPE_RLZY.equals(type)) {
                final List<Map<String, Object>> items_rlzy = initItems(MainActivity.TYPE_RLZY);
                if (null != items_rlzy && items_rlzy.size() > 0) {
                    SimpleAdapter adapter_rlzy = new SimpleAdapter(getActivity(), items_rlzy, R.layout.item_favorite,
                            new String[]{ITME_TITLE},
                            new int[]{R.id.item_question_title});
                    listView_rlzy.setAdapter(adapter_rlzy);
                    listView_rlzy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                                long arg3) {
                            String question_tile = (String) items_rlzy.get(arg2).get(ITME_TITLE);
                            int index = findIndexByTitle(question_tile, MainActivity.TYPE_RLZY);
                            onListItemClick(index, MainActivity.TYPE_RLZY);
                        }
                    });
                    setListViewHeight(listView_rlzy);
                    text_no_data_rlzy.setVisibility(View.GONE);
                    listView_rlzy.setVisibility(View.VISIBLE);
                } else {
                    listView_rlzy.setVisibility(View.GONE);
                    text_no_data_rlzy.setVisibility(View.VISIBLE);
                }
            } else if (MainActivity.TYPE_CWZX.equals(type)) {
                final List<Map<String, Object>> items_cwzx = initItems(MainActivity.TYPE_CWZX);
                if (null != items_cwzx && items_cwzx.size() > 0) {
                    SimpleAdapter adapter_cwzx = new SimpleAdapter(getActivity(), items_cwzx, R.layout.item_favorite,
                            new String[]{ITME_TITLE},
                            new int[]{R.id.item_question_title});
                    listView_cwzx.setAdapter(adapter_cwzx);
                    listView_cwzx.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                                long arg3) {
                            String question_tile = (String) items_cwzx.get(arg2).get(ITME_TITLE);
                            int index = findIndexByTitle(question_tile, MainActivity.TYPE_CWZX);
                            onListItemClick(index, MainActivity.TYPE_CWZX);
                        }
                    });
                    setListViewHeight(listView_cwzx);
                    text_no_data_cwzx.setVisibility(View.GONE);
                    listView_cwzx.setVisibility(View.VISIBLE);
                } else {
                    listView_cwzx.setVisibility(View.GONE);
                    text_no_data_cwzx.setVisibility(View.VISIBLE);
                }
            } else if (MainActivity.TYPE_ITZC.equals(type)) {
                final List<Map<String, Object>> items_itzc = initItems(MainActivity.TYPE_ITZC);
                if (null != items_itzc && items_itzc.size() > 0) {
                    SimpleAdapter adapter_itzc = new SimpleAdapter(getActivity(), items_itzc, R.layout.item_favorite,
                            new String[]{ITME_TITLE},
                            new int[]{R.id.item_question_title});
                    listView_itzc.setAdapter(adapter_itzc);
                    listView_itzc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                                long arg3) {
                            String question_tile = (String) items_itzc.get(arg2).get(ITME_TITLE);
                            int index = findIndexByTitle(question_tile, MainActivity.TYPE_ITZC);
                            onListItemClick(index, MainActivity.TYPE_ITZC);
                        }
                    });
                    setListViewHeight(listView_itzc);
                    text_no_data_itzc.setVisibility(View.GONE);
                    listView_itzc.setVisibility(View.VISIBLE);
                } else {
                    listView_itzc.setVisibility(View.GONE);
                    text_no_data_itzc.setVisibility(View.VISIBLE);
                }
            }

        }
    };
}
