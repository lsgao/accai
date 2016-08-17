package com.ruptech.ai.question;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruptech.ai.App;
import com.ruptech.ai.R;
import com.ruptech.ai.main.MainActivity;
import com.ruptech.ai.utils.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class QuestionDetailFragment extends Fragment {
    private static final String TAG = QuestionDetailFragment.class.getName();

    public static final String PROPERTIES_NAME = "my.properties";

    private String type;
    private int index;
    private List<Map<String, Object>> items;
    @InjectView(R.id.toolbar_question_detail)
    TextView toolbar;
    @InjectView(R.id.textView_question_detail)
    TextView content;
    @InjectView(R.id.return_icon_question_detail)
    ImageView returnIcon;

    @InjectView(R.id.button_praise_question_detail)
    LinearLayout button_praise;
    @InjectView(R.id.imageView_question_detail_praise)
    ImageView icon_praise;
    @InjectView(R.id.button_favorite_question_detail)
    LinearLayout button_favorite;
    @InjectView(R.id.imageView_question_detail_favorite)
    ImageView icon_favorite;


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
            String title = Utils.getSubString(MainActivity.xzfw_titles[index], 15, "...");
            toolbar.setText(title);
            String[] contents = MainActivity.xzfw_contents;
            content.setText(contents[index]);
        } else if (MainActivity.TYPE_RLZY.equals(type)) {
            String title = Utils.getSubString(MainActivity.rlzy_titles[index], 15, "...");
            toolbar.setText(title);
            String[] contents = MainActivity.rlzy_contents;
            content.setText(contents[index]);
        } else if (MainActivity.TYPE_CWZX.equals(type)) {
            String title = Utils.getSubString(MainActivity.cwzx_titles[index], 15, "...");
            toolbar.setText(title);
            String[] contents = MainActivity.cwzx_contents;
            content.setText(contents[index]);
        } else if (MainActivity.TYPE_ITZC.equals(type)) {
            String title = Utils.getSubString(MainActivity.itzc_titles[index], 15, "...");
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

        if(isPraise(type, index)) {
            icon_praise.setImageResource(R.drawable.icon_praise_light);
        } else {
            icon_praise.setImageResource(R.drawable.icon_praise);
        }

        button_praise.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                praise(type, index);
            }
        });


        if(isFavorite(type, index)) {
            icon_favorite.setImageResource(R.drawable.icon_favorite_light);
        } else {
            icon_favorite.setImageResource(R.drawable.icon_favorite);
        }

        button_favorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                favorite(type, index);
            }
        });

        return rootView;

    }

    private void favorite(String type, int index) {
        String pro_name = "favorite_" + type + "_" + index;
        if(isFavorite(type, index)) {
            Properties properties = loadProPerties();
            saveProPerties(properties, pro_name, new String("0"));
            icon_favorite.setImageResource(R.drawable.icon_favorite);
        } else {
            Properties properties = loadProPerties();
            saveProPerties(properties, pro_name, new String("1"));
            icon_favorite.setImageResource(R.drawable.icon_favorite_light);
        }
    }

    private boolean isFavorite(String type, int index) {
        String pro_name = "favorite_" + type + "_" + index;
        Properties properties = loadProPerties();
        String favorite = ((String)properties.get(pro_name));
        if(("1").equals(favorite)) {
            return true;
        } else {
            return false;
        }
    }

    private void praise(String type, int index) {
        String pro_name = "praise_" + type + "_" + index;
        if(isPraise(type, index)) {
            Properties properties = loadProPerties();
            saveProPerties(properties, pro_name, new String("0"));
            icon_praise.setImageResource(R.drawable.icon_praise);
        } else {
            Properties properties = loadProPerties();
            saveProPerties(properties, pro_name, new String("1"));
            icon_praise.setImageResource(R.drawable.icon_praise_light);
        }
    }

    private boolean isPraise(String type, int index) {
        String pro_name = "praise_" + type + "_" + index;
        Properties properties = loadProPerties();
        String favorite = ((String)properties.get(pro_name));
        if(("1").equals(favorite)) {
            return true;
        } else {
            return false;
        }
    }

    private Properties loadProPerties() {
        String subForder = App.SAVE_FILE_PATH;
        File folder = new File(subForder);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(subForder, PROPERTIES_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Properties properties = App.loadProperties(getActivity(),App.SAVE_FILE_PATH + "/" + PROPERTIES_NAME );
        return properties;
    }

    private void saveProPerties(Properties properties, String key, String value) {
        properties.put(key, value);
        App.saveConfig(getActivity(), App.SAVE_FILE_PATH + "/" + PROPERTIES_NAME, properties);
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

