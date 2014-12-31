package com.hromadske.tv.ck.fragments;

import android.os.Bundle;

import com.hromadske.tv.ck.utils.SystemUtils;

/**
 * Created by cheb on 12/27/14.
 */
public class PhotoesListFragment extends BaseListFragment  {


    public static PhotoesListFragment newInstance(int sectionNumber) {
        PhotoesListFragment fragment = new PhotoesListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(ARG_URL, SystemUtils.PHOTOES_URL);
        fragment.setArguments(args);
        return fragment;
    }

    public PhotoesListFragment() {
    }
}
