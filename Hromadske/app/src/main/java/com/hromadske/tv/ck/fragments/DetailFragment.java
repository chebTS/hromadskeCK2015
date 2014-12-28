package com.hromadske.tv.ck.fragments;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.entities.BaseEntity;
import com.hromadske.tv.ck.utils.SystemUtils;

/**
 * Created by cheb on 28.12.2014.
 */
public class DetailFragment extends Fragment {

    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BaseEntity entity = (BaseEntity)getArguments().getSerializable(SystemUtils.EXTRA_ENTITY);
        ((TextView)view.findViewById(R.id.txt_title)).setText(entity.getTitle());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
