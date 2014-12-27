package com.hromadske.tv.ck.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.hromadske.tv.ck.activities.MainActivity;

/**
 * Created by cheb on 12/27/14.
 */
public class BaseMenuFragment extends Fragment {
    protected static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
