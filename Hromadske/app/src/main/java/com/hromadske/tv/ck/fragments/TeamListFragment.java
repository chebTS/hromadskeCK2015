package com.hromadske.tv.ck.fragments;

import android.os.Bundle;

import com.hromadske.tv.ck.utils.SystemUtils;

/**
 * Created by cheb on 12/27/14.
 */
public class TeamListFragment extends BaseListFragment  {

    public static TeamListFragment newInstance(int sectionNumber) {
        TeamListFragment fragment = new TeamListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(ARG_URL, SystemUtils.TEAM_URL);
        fragment.setArguments(args);
        return fragment;
    }

    public TeamListFragment() {
    }
}