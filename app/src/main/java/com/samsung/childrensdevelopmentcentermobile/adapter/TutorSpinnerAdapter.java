package com.samsung.childrensdevelopmentcentermobile.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.samsung.childrensdevelopmentcentermobile.R;
import com.samsung.childrensdevelopmentcentermobile.model.Tutor;
import com.samsung.childrensdevelopmentcentermobile.nodb.ChildrensDevelopmentCenterSimpleDB;

import java.util.List;

public class TutorSpinnerAdapter extends ArrayAdapter<Tutor> {

    public TutorSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Tutor> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.spinner_item, null);
        }

        ((TextView) convertView.findViewById(R.id.tv_spinner_item))
                .setText(ChildrensDevelopmentCenterSimpleDB.TUTOR_LIST.get(position).getName());

        return convertView;
    }
}