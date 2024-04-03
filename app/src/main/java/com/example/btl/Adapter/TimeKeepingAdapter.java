package com.example.btl.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.object.class_timeKeeping;

import java.util.ArrayList;

public class TimeKeepingAdapter extends BaseAdapter {
    Context context;
    ArrayList<class_timeKeeping> list;
    public TimeKeepingAdapter(Context context, ArrayList<class_timeKeeping> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = View.inflate(parent.getContext(), R.layout.tbrow_for_time_keeping, null);
        }

        TextView idE = convertView.findViewById(R.id.idE);
        TextView nameE = convertView.findViewById(R.id.nameE);
        TextView check = convertView.findViewById(R.id.check);

        idE.setText(list.get(position).getIdE());
        nameE.setText(list.get(position).getName());

        if(list.get(position).getCheck().equals("checked"))
            check.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checked, 0,0,0);
        else
            check.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0,0,0);

        return convertView;
    }
}
