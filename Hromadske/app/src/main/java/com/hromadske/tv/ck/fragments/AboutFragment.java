package com.hromadske.tv.ck.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        return v;
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
                    .replace(R.id.tablet_container, finReportFragment)
                    .commit();
        }

    }

}