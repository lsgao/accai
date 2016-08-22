package com.ruptech.ai.more;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruptech.ai.R;
import com.ruptech.ai.MainActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MoreAboutFragment extends Fragment {
    private static final String TAG = MoreAboutFragment.class.getName();

    @InjectView(R.id.toolbar_more_about)
    TextView toolbar;
    @InjectView(R.id.return_icon_more_about)
    ImageView returnIcon;

    public static MoreAboutFragment newInstance() {
        MoreAboutFragment fragment = new MoreAboutFragment();
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
        View rootView = inflater.inflate(R.layout.sub_fragment_more_about, container, false);
        ButterKnife.inject(this, rootView);
        toolbar.setText(getString(R.string.about_title));

        returnIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToMoreFirstFragment();
            }
        });

        return rootView;
    }

    private void moveToMoreFirstFragment() {

        MainActivity mainActivity = (MainActivity) this.getActivity();
        FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();

        Fragment target = MoreFirstFragment.newInstance();
        ft.replace(R.id.more_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

    }
}

