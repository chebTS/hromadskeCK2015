package com.hromadske.tv.ck.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hromadske.tv.ck.R;
import com.hromadske.tv.ck.activities.DetailActivity;
import com.hromadske.tv.ck.activities.MainActivity;

/**
 * Created by cheb on 12/27/14.
 */
public class AboutFragment extends BaseMenuFragment {

    public static AboutFragment newInstance(int sectionNumber) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public AboutFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (((MainActivity)getActivity()).getTabletContainer() == null) {
            view.findViewById(R.id.txt_report).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), DetailActivity.class));
                }
            });
        }else{
            FinReportFragment finReportFragment = new FinReportFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.tablet_container, finReportFragment)
                    .commit();
        }
    }
}