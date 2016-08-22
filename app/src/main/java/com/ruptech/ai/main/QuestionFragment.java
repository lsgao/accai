package com.ruptech.ai.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruptech.ai.MainActivity;
import com.ruptech.ai.R;
import com.ruptech.ai.question.QuestionDetailFragment;
import com.ruptech.ai.question.QuestionListFragment;

import butterknife.ButterKnife;


public class QuestionFragment extends Fragment {
    private static final String TAG = QuestionFragment.class.getName();

    public static final String KIND_LIST = "list";
    public static final String KIND_DETAIL = "detail";

    private String type;
    private int index;
    private String kind;

    public static QuestionFragment newInstance(String type, String index, String kind) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putString(MainActivity.EXTRA_TYPE, type);
        args.putString(MainActivity.EXTRA_INDEX, index);
        args.putString(MainActivity.EXTRA_KIND, kind);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        type = getArguments().getString(MainActivity.EXTRA_TYPE);
        index = new Integer(getArguments().getString(MainActivity.EXTRA_INDEX)).intValue();
        kind = getArguments().getString(MainActivity.EXTRA_KIND);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_question, container, false);
        ButterKnife.inject(this, rootView);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        if(QuestionFragment.KIND_LIST.equals(kind)) {
            ft.replace(R.id.question_main_fragment, QuestionListFragment.newInstance(type));
        } else if(QuestionFragment.KIND_DETAIL.equals(kind)){
            ft.replace(R.id.question_main_fragment, QuestionDetailFragment.newInstance(type, new Integer(index).toString()));
        } else {
            ft.replace(R.id.question_main_fragment, QuestionListFragment.newInstance(MainActivity.TYPE_XZFW));
        }
        ft.commit();
        return rootView;

    }

}

