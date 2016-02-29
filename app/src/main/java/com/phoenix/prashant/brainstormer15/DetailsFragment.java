package com.phoenix.prashant.brainstormer15;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by PRASHANT on 18-08-2015.
 */
public class DetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mview = inflater.inflate(R.layout.details_fragment,container,false);
        return  mview;
    }

}
