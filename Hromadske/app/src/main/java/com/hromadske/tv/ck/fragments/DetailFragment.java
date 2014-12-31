package com.hromadske.tv.ck.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.entities.BaseEntity;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

import static com.hromadske.tv.ck.utils.SystemUtils.EXTRA_ENTITY;
import static com.hromadske.tv.ck.utils.SystemUtils.IMAGELOADER;
import static com.hromadske.tv.ck.utils.SystemUtils.watchYoutubeVideo;

/**
 * Created by cheb on 28.12.2014.
 */
public class DetailFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = DetailFragment.class.getSimpleName();
    private BaseEntity entity;

    public DetailFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        entity = (BaseEntity)getArguments().getSerializable(EXTRA_ENTITY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView)view.findViewById(R.id.txt_title)).setText(entity.getTitle());
        ImageView imgYoutube = (ImageView)view.findViewById(R.id.img_youtube);
        if(entity.getVideo() == null || entity.getVideo().length() == 0){
            imgYoutube.setVisibility(View.GONE);
        }else{
            imgYoutube.setOnClickListener(this);
        }
        if (entity.getImage() != null){
            ImageAware imageAware = new ImageViewAware((ImageView)view.findViewById(R.id.img_photo));
            IMAGELOADER.displayImage(entity.getImage(), imageAware);
        }
        ((TextView)view.findViewById(R.id.txt_fulltext)).setText(entity.getFullText());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((ActionBarActivity)getActivity()).getSupportActionBar().setTitle(entity.getTitle());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_youtube:
                watchYoutubeVideo(getActivity(), entity.getVideo());
                break;
        }
    }
}