package com.phoenix.prashant.brainstormer15;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PRASHANT on 22-02-2015.
 */


 class CustomAdapter extends ArrayAdapter{


    SharedPreferences marks;

    Context context;

    public CustomAdapter(Context context, List<RowItem> items) {
        super(context, R.layout.question_listitem, items);
        this.context = context;
    }


    /*private view holder class*/
    private class ViewHolder {
        RadioButton A, B, C, D;
        RadioGroup RG;
        TextView txtTitle,answer,Qn;
        int Ans;
        Double marks;
        Button sub;

    }

    public View getView( int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final int[] FLAG = new int[1];
        final QuizFragment QF = new QuizFragment();
        Log.e("pos",""+ position);
        position %= 10;
        marks = context.getSharedPreferences(QF.filename, 0);
        final RowItem rowItem = QuizFragment.item_arr[position];
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);


        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.question_listitem, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.Question);
            holder.answer = (TextView) convertView.findViewById(R.id.Answer);
            holder.Qn= (TextView) convertView.findViewById(R.id.Qn);
            holder.Ans = Store.select[position];
/*            holder.A = (RadioButton) convertView.findViewById(R.id.A1);
            holder.B = (RadioButton) convertView.findViewById(R.id.A2);
            holder.C = (RadioButton) convertView.findViewById(R.id.A3);
            holder.D = (RadioButton) convertView.findViewById(R.id.A4);
            holder.RG = (RadioGroup) convertView.findViewById(R.id.RG);*/
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        final int a = Store.getSelect(position);
        holder.txtTitle.setText(rowItem.getTitle());
        Store.ans[position]= Integer.parseInt(rowItem.getCorrect())+1;
        holder.Qn.setText(String.valueOf(position+1));
        switch (a){
            case 1: holder.answer.setText(rowItem.getOptA());
                break;
            case 2: holder.answer.setText(rowItem.getOptB());
                break;
            case 3: holder.answer.setText(rowItem.getOptC());
                break;
            case 4: holder.answer.setText(rowItem.getOptD());
                break;
            default: holder.answer.setText("none");
                break;
        }






       /* holder.A.setText(rowItem.getOptA());
        holder.B.setText(rowItem.getOptB());
        holder.C.setText(rowItem.getOptC());
        holder.D.setText(rowItem.getOptD());

        holder.Ans = Integer.parseInt(rowItem.getCorrect());

        int id = Store.select[position];
        switch (id){
            case 0:holder.RG.setOnCheckedChangeListener(null);
                holder.A.setChecked(false);
                holder.B.setChecked(false);
                holder.C.setChecked(false);
                holder.D.setChecked(false);

                break;
            case 1: holder.RG.setOnCheckedChangeListener(null);
                holder.A.setChecked(true);

                break;
            case 2:
                holder.RG.setOnCheckedChangeListener(null);
                holder.B.setChecked(true);

                break;
            case 3:
                holder.RG.setOnCheckedChangeListener(null);

                holder.C.setChecked(true);

                break;
            case 4:

                holder.RG.setOnCheckedChangeListener(null);

                holder.D.setChecked(true);
                break;
        }
        final ViewHolder finalHolder = holder;
        final int finalposition = position;
        holder.RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                SharedPreferences.Editor editor = marks.edit();
                float m = marks.getFloat("marks", 0.0f);
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                int id = group.indexOfChild(radioButton);
                switch (checkedId){
                    case R.id.A1:
                        if (!firstRow.containsKey(position)){
                            firstRow.put(position,true);
                            secondRow.remove(position);
                            thirdRow.remove(position);
                            forthRow.remove(position);
                            Store.select[position]=1;
                            rowItem.setSelected("1");
                        }break;
                    case R.id.A2:
                        if (!secondRow.containsKey(position)){
                            secondRow.put(position,true);
                            firstRow.remove(position);
                            thirdRow.remove(position);
                            forthRow.remove(position);
                            Store.select[position]=2;
                            rowItem.setSelected("2");
                        }break;
                    case R.id.A3:
                        if (!thirdRow.containsKey(position)){
                            thirdRow.put(position,true);
                            firstRow.remove(position);
                            secondRow.remove(position);
                            forthRow.remove(position);
                            Store.select[position]=3;
                            rowItem.setSelected("3");
                        }break;
                    case R.id.A4:
                        if (!forthRow.containsKey(position)){
                            forthRow.put(position,true);
                            firstRow.remove(position);
                            secondRow.remove(position);
                            thirdRow.remove(position);
                            Store.select[position]=4;
                            rowItem.setSelected("4");
                        }break;


                }


                if (id == finalHolder.Ans) {
                    if (FLAG[0] == 2) {

                        m = m + 1.5F;
                    } else if (FLAG[0] == 0) {

                        m = m + 1.0f;
                    }
                    FLAG[0] = 1;
                    System.out.println("Correct");
                } else {
                    if (FLAG[0] == 1) {

                        m = m - 1.5f;
                    } else if (FLAG[0] == 0) {

                        m = m - 0.5f;
                    }
                    FLAG[0] = 2;
                    System.out.println("Incorrect");
                }
                editor.putFloat("marks", m);
                editor.commit();
                System.out.println(marks.getFloat("marks", 0.0f));


                System.out.print(Store.select);
                Toast.makeText(context, String.valueOf(marks.getFloat("marks",0.0f)), Toast.LENGTH_SHORT).show();
//                  Toast.makeText(context, String.valueOf(R.id.A1), Toast.LENGTH_SHORT).show();

            }

        });
       */ return convertView;


    }

    public View subtitle(int postion){
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View convertView = mInflater.inflate(R.layout.question_listitem,null);
        TextView answer = (TextView) convertView.findViewById(R.id.Answer);
        answer.setText("success");
        return convertView;
    }


}