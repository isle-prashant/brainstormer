package com.phoenix.prashant.brainstormer15;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by PRASHANT on 02-08-2015.
 */
public class Customview extends View {
    int position;
    RowItem rowItem;
    Context context;

    public Customview(Context context, int position) {
        super(context);
        this.position=position;
        this.context=context;
          rowItem = QuizFragment.item_arr[position];
    }

    public View getView(){

        final Store store = new Store();
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View convertView = mInflater.inflate(R.layout.options, null);
        RadioGroup RG= (RadioGroup) convertView.findViewById(R.id.RG);
        RadioButton A = (RadioButton) convertView.findViewById(R.id.A1);
        RadioButton B = (RadioButton) convertView.findViewById(R.id.A2);
        RadioButton C = (RadioButton) convertView.findViewById(R.id.A3);
        RadioButton D = (RadioButton) convertView.findViewById(R.id.A4);
        RadioButton Def = (RadioButton) convertView.findViewById(R.id.A5);
        A.setText(rowItem.getOptA());
        B.setText(rowItem.getOptB());
        C.setText(rowItem.getOptC());
        D.setText(rowItem.getOptD());
        int ans=store.getSelect(position);
        switch (ans){
            case 1 :RG.setOnCheckedChangeListener(null);
                A.setChecked(true);
                B.setChecked(false);
                C.setChecked(false);
                D.setChecked(false);
                break;
            case 2:
                RG.setOnCheckedChangeListener(null);
                B.setChecked(true);
                A.setChecked(false);
                C.setChecked(false);
                D.setChecked(false);
                break;
            case 3:RG.setOnCheckedChangeListener(null);
                C.setChecked(true);
                A.setChecked(false);
                B.setChecked(false);
                D.setChecked(false);
                break;
            case 4:RG.setOnCheckedChangeListener(null);
                D.setChecked(true);
                A.setChecked(false);
                B.setChecked(false);
                C.setChecked(false);

                break;
            default:
                RG.setOnCheckedChangeListener(null);
                A.setChecked(false);
                B.setChecked(false);
                C.setChecked(false);
                D.setChecked(false);
                Def.setChecked(true);
        }
        RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.A1 : store.setSelect(position,1);
                        break;
                    case R.id.A2 : store.setSelect(position,2);
                        break;
                    case R.id.A3 : store.setSelect(position,3);
                        break;
                    case R.id.A4 : store.setSelect(position,4);
                        break;
                    default: store.setSelect(position,0);
                        break;
                }
            }
        });
        return convertView;
    }
}
