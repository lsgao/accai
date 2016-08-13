package com.ruptech.ai.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ruptech.ai.R;
import com.ruptech.ai.me.MeFavoriteFragment;
import com.ruptech.ai.me.MeFirstFragment;
import com.ruptech.ai.more.MoreAboutFragment;
import com.ruptech.ai.more.MoreFirstFragment;
import com.ruptech.ai.more.MoreProposalFragment;

import butterknife.ButterKnife;


public class MeFragment extends Fragment {
    private static final String TAG = MeFragment.class.getName();

    public static final String KIND_FIRST = "first";
    public static final String KIND_FAVORITE = "favorite";

    private String kind;

    public static MeFragment newInstance(String kind) {
        MeFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        args.putString(MainActivity.EXTRA_KIND, kind);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        kind = getArguments().getString(MainActivity.EXTRA_KIND);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.inject(this, rootView);
        kind = getArguments().getString(MainActivity.EXTRA_KIND);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        if (MeFragment.KIND_FIRST.equals(kind)) {
            ft.replace(R.id.me_main_fragment, MeFirstFragment.newInstance());
        } else if (MeFragment.KIND_FAVORITE.equals(kind)) {
            ft.replace(R.id.me_main_fragment, MeFavoriteFragment.newInstance());
        } else {
            ft.replace(R.id.me_main_fragment, MeFirstFragment.newInstance());
        }
        ft.commit();
        return rootView;
    }

}

