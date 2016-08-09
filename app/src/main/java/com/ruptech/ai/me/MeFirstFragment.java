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
import com.ruptech.ai.more.MoreAboutFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MeFirstFragment extends Fragment {
    private static final String TAG = MeFirstFragment.class.getName();

    @InjectView(R.id.textView_me_name)
    TextView user_name;
    @InjectView(R.id.imageView_favorite)
    ImageView imageView_favorite;
    @InjectView(R.id.textView_favorite)
    TextView textView_favorite;

    public static MeFirstFragment newInstance() {
        MeFirstFragment fragment = new MeFirstFragment();
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
        View rootView = inflater.inflate(R.layout.sub_fragment_me_first, container, false);
        ButterKnife.inject(this, rootView);

        user_name.setText(App.readUser().getUsername());

        imageView_favorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToMeFavoriteFragment();
            }
        });
        textView_favorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToMeFavoriteFragment();
            }
        });

        return rootView;
    }

    private void moveToMeFavoriteFragment() {

        MainActivity mainActivity = (MainActivity) this.getActivity();
        FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();

        Fragment target = MeFavoriteFragment.newInstance();
        ft.replace(R.id.me_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

    }
}

