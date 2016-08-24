package com.ruptech.ai.question;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruptech.ai.App;
import com.ruptech.ai.R;
import com.ruptech.ai.MainActivity;
import com.ruptech.ai.main.MeFragment;
import com.ruptech.ai.main.QuestionFragment;
import com.ruptech.ai.utils.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;

import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.Properties;


public class QuestionDetailFragment extends Fragment {
    private static final String TAG = QuestionDetailFragment.class.getName();

    public static final String PROPERTIES_NAME = "my.properties";

    private String type;
    private int index;

    @InjectView(R.id.toolbar_question_detail)
    TextView toolbar;
    @InjectView(R.id.textView_question_detail)
    TextView content;
    @InjectView(R.id.return_icon_question_detail)
    ImageView returnIcon;
    @InjectView(R.id.return_text_question_detail)
    TextView returnText;

    @InjectView(R.id.button_praise_question_detail)
    LinearLayout button_praise;
    @InjectView(R.id.imageView_question_detail_praise)
    ImageView icon_praise;
    @InjectView(R.id.button_favorite_question_detail)
    LinearLayout button_favorite;
    @InjectView(R.id.imageView_question_detail_favorite)
    ImageView icon_favorite;

    @InjectView(R.id.layout_relation_1)
    RelativeLayout layout_relation_1;
    @InjectView(R.id.textView_relation_1)
    TextView textView_relation_1;
    @InjectView(R.id.layout_relation_2)
    RelativeLayout layout_relation_2;
    @InjectView(R.id.textView_relation_2)
    TextView textView_relation_2;
    @InjectView(R.id.layout_relation_3)
    RelativeLayout layout_relation_3;
    @InjectView(R.id.textView_relation_3)
    TextView textView_relation_3;

    @InjectView(R.id.image_xzfw_1)
    ImageView image_xzfw_1;
    @InjectView(R.id.table_rlzy_14)
    TableLayout table_rlzy_14;
    @InjectView(R.id.table_itzc_3)
    TableLayout table_itzc_3;
    @InjectView(R.id.table_itzc_6)
    TableLayout table_itzc_6;
    @InjectView(R.id.table_itzc_13)
    TableLayout table_itzc_13;
    @InjectView(R.id.text_itzc_13_cell11)
    TextView text_itzc_13_cell11;
    @InjectView(R.id.text_itzc_13_cell12)
    TextView text_itzc_13_cell12;
    @InjectView(R.id.text_itzc_13_cell13)
    TextView text_itzc_13_cell13;
    @InjectView(R.id.text_itzc_13_cell21)
    TextView text_itzc_13_cell21;
    @InjectView(R.id.text_itzc_13_cell22)
    TextView text_itzc_13_cell22;
    @InjectView(R.id.text_itzc_13_cell23)
    TextView text_itzc_13_cell23;
    @InjectView(R.id.text_itzc_13_cell31)
    TextView text_itzc_13_cell31;
    @InjectView(R.id.text_itzc_13_cell32)
    TextView text_itzc_13_cell32;
    @InjectView(R.id.text_itzc_13_cell33)
    TextView text_itzc_13_cell33;

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
        QuestionFragment.currentMainFragment = QuestionFragment.KIND_DETAIL;
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
            setContent(contents[index]);

            textView_relation_1.setText(Utils.getSubString(MainActivity.xzfw_titles[0], 15, "..."));
            textView_relation_2.setText(Utils.getSubString(MainActivity.xzfw_titles[1], 15, "..."));
            textView_relation_3.setText(Utils.getSubString(MainActivity.xzfw_titles[2], 15, "..."));

        } else if (MainActivity.TYPE_RLZY.equals(type)) {
            String title = Utils.getSubString(MainActivity.rlzy_titles[index], 15, "...");
            toolbar.setText(title);
            String[] contents = MainActivity.rlzy_contents;
            setContent(contents[index]);

            textView_relation_1.setText(Utils.getSubString(MainActivity.rlzy_titles[0], 15, "..."));
            textView_relation_2.setText(Utils.getSubString(MainActivity.rlzy_titles[1], 15, "..."));
            textView_relation_3.setText(Utils.getSubString(MainActivity.rlzy_titles[2], 15, "..."));

        } else if (MainActivity.TYPE_CWZX.equals(type)) {
            String title = Utils.getSubString(MainActivity.cwzx_titles[index], 15, "...");
            toolbar.setText(title);
            String[] contents = MainActivity.cwzx_contents;
            setContent(contents[index]);

            textView_relation_1.setText(Utils.getSubString(MainActivity.cwzx_titles[0], 15, "..."));
            textView_relation_2.setText(Utils.getSubString(MainActivity.cwzx_titles[1], 15, "..."));
            textView_relation_3.setText(Utils.getSubString(MainActivity.cwzx_titles[2], 15, "..."));

        } else if (MainActivity.TYPE_ITZC.equals(type)) {
            String title = Utils.getSubString(MainActivity.itzc_titles[index], 15, "...");
            toolbar.setText(title);
            String[] contents = MainActivity.itzc_contents;
            setContent(contents[index]);

            textView_relation_1.setText(Utils.getSubString(MainActivity.itzc_titles[0], 15, "..."));
            textView_relation_2.setText(Utils.getSubString(MainActivity.itzc_titles[1], 15, "..."));
            textView_relation_3.setText(Utils.getSubString(MainActivity.itzc_titles[2], 15, "..."));

        }

        returnIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionListFragment();
            }
        });
        returnText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToQuestionListFragment();
            }
        });

        if (isPraise(type, index)) {
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


        if (isFavorite(type, index)) {
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

        layout_relation_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(0);
            }
        });

        layout_relation_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(1);
            }
        });

        layout_relation_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                moveToQuestionDetailFragment(2);
            }
        });

        if (MainActivity.TYPE_XZFW.equals(type) && 0 == index) {
            image_xzfw_1.setVisibility(View.VISIBLE);
            image_xzfw_1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View arg0) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse("http://120.24.250.86/app/link");
                    intent.setData(content_url);
                    startActivity(intent);
                    return true;
                }
            });
        } else {
            image_xzfw_1.setVisibility(View.GONE);
        }
        if (MainActivity.TYPE_RLZY.equals(type) && 13 == index) {
            table_rlzy_14.setVisibility(View.VISIBLE);
        } else {
            table_rlzy_14.setVisibility(View.GONE);
        }
        if (MainActivity.TYPE_ITZC.equals(type) && 2 == index) {
            table_itzc_3.setVisibility(View.VISIBLE);
        } else {
            table_itzc_3.setVisibility(View.GONE);
        }

        if (MainActivity.TYPE_ITZC.equals(type) && 5 == index) {
            table_itzc_6.setVisibility(View.VISIBLE);
        } else {
            table_itzc_6.setVisibility(View.GONE);
        }

        if (MainActivity.TYPE_ITZC.equals(type) && 12 == index) {
            table_itzc_13.setVisibility(View.VISIBLE);
            String text;

            text = text_itzc_13_cell11.getText().toString();
            text_itzc_13_cell11.setText(Html.fromHtml(text));
            text_itzc_13_cell11.setMovementMethod(LinkMovementMethod.getInstance());

            text = text_itzc_13_cell12.getText().toString();
            text_itzc_13_cell12.setText(Html.fromHtml(text));
            text_itzc_13_cell12.setMovementMethod(LinkMovementMethod.getInstance());

            text = text_itzc_13_cell13.getText().toString();
            text_itzc_13_cell13.setText(Html.fromHtml(text));
            text_itzc_13_cell13.setMovementMethod(LinkMovementMethod.getInstance());

            text = text_itzc_13_cell21.getText().toString();
            text_itzc_13_cell21.setText(Html.fromHtml(text));
            text_itzc_13_cell21.setMovementMethod(LinkMovementMethod.getInstance());

            text = text_itzc_13_cell22.getText().toString();
            text_itzc_13_cell22.setText(Html.fromHtml(text));
            text_itzc_13_cell22.setMovementMethod(LinkMovementMethod.getInstance());

            text = text_itzc_13_cell23.getText().toString();
            text_itzc_13_cell23.setText(Html.fromHtml(text));
            text_itzc_13_cell23.setMovementMethod(LinkMovementMethod.getInstance());

            text = text_itzc_13_cell31.getText().toString();
            text_itzc_13_cell31.setText(Html.fromHtml(text));
            text_itzc_13_cell31.setMovementMethod(LinkMovementMethod.getInstance());

            text = text_itzc_13_cell32.getText().toString();
            text_itzc_13_cell32.setText(Html.fromHtml(text));
            text_itzc_13_cell32.setMovementMethod(LinkMovementMethod.getInstance());

            text = text_itzc_13_cell33.getText().toString();
            text_itzc_13_cell33.setText(Html.fromHtml(text));
            text_itzc_13_cell33.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            table_itzc_13.setVisibility(View.GONE);
        }

        return rootView;

    }

    private void setContent(String str) {
        str = str.replace("\n", "<br/>");
        str = str.replace("&gt;", ">");
        str = str.replace("&lt;", "<");
        CharSequence text_charSequence = Html.fromHtml(str);
        content.setText(text_charSequence);
    }

    private void favorite(String type, int index) {
        String pro_name = "favorite_" + type + "_" + index;
        if (isFavorite(type, index)) {
            Properties properties = loadProPerties();
            saveProPerties(properties, pro_name, new String("0"));
            icon_favorite.setImageResource(R.drawable.icon_favorite);
        } else {
            Properties properties = loadProPerties();
            saveProPerties(properties, pro_name, new String("1"));
            icon_favorite.setImageResource(R.drawable.icon_favorite_light);
        }
        if(MeFragment.KIND_FAVORITE.equals(MeFragment.currentMainFragment)) {
            sendBroadcast(type);
        }
    }

    private boolean isFavorite(String type, int index) {
        String pro_name = "favorite_" + type + "_" + index;
        Properties properties = loadProPerties();
        String favorite = ((String) properties.get(pro_name));
        if (("1").equals(favorite)) {
            return true;
        } else {
            return false;
        }
    }

    private void praise(String type, int index) {
        String pro_name = "praise_" + type + "_" + index;
        if (isPraise(type, index)) {
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
        String favorite = ((String) properties.get(pro_name));
        if (("1").equals(favorite)) {
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
        Properties properties = App.loadProperties(getActivity(), App.SAVE_FILE_PATH + "/" + PROPERTIES_NAME);
        return properties;
    }

    private void saveProPerties(Properties properties, String key, String value) {
        properties.put(key, value);
        App.saveConfig(getActivity(), App.SAVE_FILE_PATH + "/" + PROPERTIES_NAME, properties);
    }

    private void moveToQuestionListFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment target = QuestionListFragment.newInstance(type);
        ft.replace(R.id.question_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    private void displayQuestion(int index) {
        moveToQuestionLoadingFragment(index);
    }

    private void moveToQuestionDetailFragment(int index) {
        MainActivity mainActivity = (MainActivity) this.getActivity();
        FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();

        Fragment target = QuestionDetailFragment.newInstance(type, new Integer(index).toString());
        ft.replace(R.id.question_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    private void moveToQuestionLoadingFragment(int index) {
        MainActivity mainActivity = (MainActivity) this.getActivity();
        FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();

        Fragment target = QuestionLoadingFragment.newInstance(type, new Integer(index).toString());
        ft.replace(R.id.question_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    private void sendBroadcast(String type) {
        Intent intent = new Intent(MainActivity.BROADCAST_FAVORITE);
        intent.putExtra(MainActivity.EXTRA_TYPE, type);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }
}

