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


public class MoreFirstFragment extends Fragment {
    private static final String TAG = MoreFirstFragment.class.getName();

    @InjectView(R.id.toolbar_more_first)
    TextView toolbar;
    @InjectView(R.id.imageView_more_first_about)
    ImageView imageView_more_first_about;
    @InjectView(R.id.textView_more_first_about)
    TextView textView_more_first_about;
    @InjectView(R.id.imageView_more_first_proposal)
    ImageView imageView_more_first_proposal;
    @InjectView(R.id.textView_more_first_proposal)
    TextView textView_more_first_proposal;

    public static MoreFirstFragment newInstance() {
        MoreFirstFragment fragment = new MoreFirstFragment();
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
        View rootView = inflater.inflate(R.layout.sub_fragment_more_first, container, false);
        ButterKnife.inject(this, rootView);

        toolbar.setText(getString(R.string.tab_title_more));
        imageView_more_first_about.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToMoreAboutFragment();
            }
        });
        textView_more_first_about.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToMoreAboutFragment();
            }
        });
        imageView_more_first_proposal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToMoreProposalFragment();
            }
        });
        textView_more_first_proposal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToMoreProposalFragment();
            }
        });
        return rootView;
    }

    private void moveToMoreAboutFragment() {

        MainActivity mainActivity = (MainActivity) this.getActivity();
        FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();

        Fragment target = MoreAboutFragment.newInstance();
        ft.replace(R.id.more_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

    }

    private void moveToMoreProposalFragment() {

        MainActivity mainActivity = (MainActivity) this.getActivity();
        FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();

        Fragment target = MoreProposalFragment.newInstance();
        ft.replace(R.id.more_main_fragment, target);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

    }
}

