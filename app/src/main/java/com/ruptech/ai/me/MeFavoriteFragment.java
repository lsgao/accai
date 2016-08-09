package com.ruptech.ai.me;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruptech.ai.App;
import com.ruptech.ai.R;
import com.ruptech.ai.main.MainActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MeFavoriteFragment extends Fragment {
    private static final String TAG = MeFavoriteFragment.class.getName();
    @InjectView(R.id.toolbar_me_favorite)
    TextView toolbar;
    @InjectView(R.id.return_icon_me_favorite)
    ImageView returnIcon;

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

        return rootView;
    }

    private void moveToMeFirstFragment() {

        MainActivity mainActivity = (MainActivity) this.getActivity();
        FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();

        Fragment target = MeFirstFragment.newInstance();
        ft.replace(R.id.me_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

    }
}

