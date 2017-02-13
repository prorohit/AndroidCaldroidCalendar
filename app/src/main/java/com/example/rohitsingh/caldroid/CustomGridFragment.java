package com.example.rohitsingh.caldroid;

import android.graphics.Color;
import android.widget.Button;

import com.roomorama.caldroid.CaldroidFragment;

/**
 * Created by rohit.singh on 10/02/17.
 */

public class CustomGridFragment extends CaldroidFragment {
    @Override
    protected int getGridViewRes() {
        return R.layout.custom_grid_fragment;
    }

    @Override
    public Button getLeftArrowButton() {
        return super.getRightArrowButton();
    }

    @Override
    public Button getRightArrowButton() {
        return super.getRightArrowButton();
    }
}
