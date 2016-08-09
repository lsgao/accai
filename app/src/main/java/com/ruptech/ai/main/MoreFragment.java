package com.ruptech.ai.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ruptech.ai.R;
import com.ruptech.ai.more.MoreAboutFragment;
import com.ruptech.ai.more.MoreFirstFragment;
import com.ruptech.ai.more.MoreProposalFragment;

import butterknife.ButterKnife;


public class MoreFragment extends Fragment {
    private static final String TAG = MoreFragment.class.getName();

    public static final String KIND_FIRST = "first";
    public static final String KIND_ABOUT = "about";
    public static final String KIND_PROPOSAL = "proposal";

    private String kind;

    public static MoreFragment newInstance(String kind) {
        MoreFragment fragment = new MoreFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.inject(this, rootView);
        kind = getArguments().getString(MainActivity.EXTRA_KIND);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        if(MoreFragment.KIND_FIRST.equals(kind)) {
            ft.replace(R.id.more_main_fragment, MoreFirstFragment.newInstance());
        } else if(MoreFragment.KIND_ABOUT.equals(kind)) {
            ft.replace(R.id.more_main_fragment, MoreAboutFragment.newInstance());
        } else if(MoreFragment.KIND_PROPOSAL.equals(kind)) {
            ft.replace(R.id.more_main_fragment, MoreProposalFragment.newInstance());
        } else {
            ft.replace(R.id.more_main_fragment, MoreFirstFragment.newInstance());
        }
        ft.commit();
        return rootView;
    }

}

