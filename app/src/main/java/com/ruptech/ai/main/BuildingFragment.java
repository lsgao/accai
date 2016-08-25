package com.ruptech.ai.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruptech.ai.MainActivity;
import com.ruptech.ai.R;


import butterknife.ButterKnife;
import butterknife.InjectView;


public class BuildingFragment extends Fragment {
    private static final String TAG = BuildingFragment.class.getName();

    private String title;
    private String content;

    @InjectView(R.id.toolbar_building)
    TextView toolbar_building;
    @InjectView(R.id.return_icon_building)
    ImageView returnIcon;
    @InjectView(R.id.return_text_building)
    TextView returnText;
    @InjectView(R.id.content_building)
    TextView content_building;


    public static BuildingFragment newInstance() {
        BuildingFragment fragment = new BuildingFragment();
        return fragment;
    }

    public static BuildingFragment newInstance(String title, String content) {
        BuildingFragment fragment = new BuildingFragment();
        Bundle args = new Bundle();
        args.putString(MainActivity.EXTRA_TITLE, title);
        args.putString(MainActivity.EXTRA_CONTNET, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        title = getArguments().getString(MainActivity.EXTRA_TITLE);
        content = getArguments().getString(MainActivity.EXTRA_CONTNET);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.sub_fragment_buiding, container, false);
        ButterKnife.inject(this, rootView);

        if (null != title && !("").equals(title)) {
            toolbar_building.setText(title);
        }
        if (null != content && !("").equals(content)) {
            content_building.setText(content);
        }

        returnIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToHomeFragment();
            }
        });
        returnText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToHomeFragment();
            }
        });
        return rootView;
    }

    private void moveToHomeFragment() {
        ((MainActivity) getActivity()).pager.setCurrentItem(0);
    }
}

