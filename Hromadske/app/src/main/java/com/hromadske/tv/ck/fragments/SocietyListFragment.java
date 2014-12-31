package com.hromadske.tv.ck.fragments;

import android.os.Bundle;
import com.hromadske.tv.ck.utils.SystemUtils;
/**
 * Created by cheb on 12/27/14.
 */
public class SocietyListFragment extends BaseListFragment{

    public static SocietyListFragment newInstance(int sectionNumber) {
        SocietyListFragment fragment = new SocietyListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(ARG_URL, SystemUtils.SOCIETY_URL);
        fragment.setArguments(args);
        return fragment;
    }

    public SocietyListFragment() {

    }

}
