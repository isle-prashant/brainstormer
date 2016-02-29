package com.phoenix.prashant.brainstormer15;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.special.ResideMenu.ResideMenu;

import java.util.zip.Inflater;

/**
 * Created by PRASHANT on 26-06-2015.
 */
public class RulesFragment extends Fragment {
    View mview;
    private ResideMenu resideMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.rules_fragment,container,false);

        return mview;
    }

 /*   private void setUpViews() {
        MainActivity parentActivity = (MainActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();

        FrameLayout ignored_view = (FrameLayout) mview.findViewById(R.id.ignored_view);
        resideMenu.addIgnoredView(ignored_view);
        // add gesture operation's ignored view
    }*/

}
