package com.phoenix.prashant.brainstormer15;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by PRASHANT on 02-08-2015.
 */
public class Customview2 extends View {
    int position;
    RowItem rowItem;
    Context context;
    TextView tv;

    public Customview2(Context context) {
        super(context);

        this.context=context;

    }

    public View getView(){

        final Store store = new Store();
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View convertView = mInflater.inflate(R.layout.login, null);
      tv= (TextView) convertView.findViewById(R.id.em);
        return convertView;
    }

    public String gtext(){
        String st = String.valueOf(tv.getText());
        return st;
    }
}
