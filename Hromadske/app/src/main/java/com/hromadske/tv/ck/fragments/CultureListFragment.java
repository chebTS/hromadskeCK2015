package com.hromadske.tv.ck.fragments;

import android.os.Bundle;

import com.hromadske.tv.ck.utils.SystemUtils;

/**
 * Created by cheb on 12/27/14.
 */
public class CultureListFragment extends BaseListFragment {


    public static CultureListFragment newInstance(int sectionNumber) {
        CultureListFragment fragment = new CultureListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(ARG_URL, SystemUtils.CULTURE_URL);
        fragment.setArguments(args);
        return fragment;
    }

    public CultureListFragment() {
    }
}
