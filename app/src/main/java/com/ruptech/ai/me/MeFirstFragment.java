package com.ruptech.ai.me;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruptech.ai.App;
import com.ruptech.ai.LoginActivity;
import com.ruptech.ai.R;
import com.ruptech.ai.main.MainActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MeFirstFragment extends Fragment {
    private static final String TAG = MeFirstFragment.class.getName();

    @InjectView(R.id.logout_icon_me_first)
    TextView logout_icon;
    @InjectView(R.id.imageView_me_first_photo)
    ImageView user_photo;
    @InjectView(R.id.textView_me_name)
    TextView user_name;

    // 头像上传
    @InjectView(R.id.item_me_first_photo)
    LinearLayout item_photo;
    @InjectView(R.id.imageView_me_first_photo_logo)
    ImageView imageView_photo_logo;
    @InjectView(R.id.textView_me_first_photo_title)
    TextView textView_photo_title;

    // 我的收藏
    @InjectView(R.id.imageView_me_first_favorite_logo)
    ImageView imageView_favorite_logo;
    @InjectView(R.id.textView_me_first_favorite_title)
    TextView textView_favorite_title;

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

        logout_icon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                logout();
            }
        });

        Bitmap bm = BitmapFactory.decodeFile(UploadPhotoActivity.SAVE_REAL_PATH + "/" + UploadPhotoActivity.PIC_NAME);
        if(null != bm) {
            user_photo.setImageBitmap(bm);
        }
        user_name.setText(App.readUser().getUsername());

        // 头像上传
        item_photo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                goToUploadPhotoActivity();
            }
        });
        imageView_photo_logo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                goToUploadPhotoActivity();
            }
        });
        textView_photo_title.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                goToUploadPhotoActivity();
            }
        });

        // 我的收藏
        imageView_favorite_logo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                moveToMeFavoriteFragment();
            }
        });
        textView_favorite_title.setOnClickListener(new View.OnClickListener() {

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

    private void goToUploadPhotoActivity() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), UploadPhotoActivity.class);
        getActivity().startActivityForResult(intent, MainActivity.TAKE_PHOTO);
    }

    private void logout() {
        App.logout();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}

