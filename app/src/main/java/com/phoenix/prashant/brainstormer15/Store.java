package com.phoenix.prashant.brainstormer15;

import android.app.Application;
import android.content.Context;

import java.util.List;

/**
 * Created by PRASHANT on 14-07-2015.
 */
public class Store extends QuizFragment {
    public static int[] select= new int[100];
    public static int[] ans = new int[100];




    public static void setSelect(int position, int select) {
        Store.select[position] = select;
    }

    public static int getSelect(int position) {
        return select[position];
    }
}
