package com.example.btl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class workAdapter extends BaseAdapter {
    Context context;
    ArrayList<class_work> w;

    public workAdapter(Context context, ArrayList<class_work> w) {
        this.context = context;
        this.w = w;
    }

    @Override
    public int getCount() {
        return w.size();
    }

    @Override
    public Object getItem(int position) {
        return w.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = View.inflate(parent.getContext(), R.layout.work_item, null);
        }

        TextView nameE = convertView.findViewById(R.id.nameE);
        nameE.setText(w.get(position).getNameE());
        return convertView;
    }
}
