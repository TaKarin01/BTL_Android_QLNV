package com.example.btl.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.btl.Database.Database;
import com.example.btl.R;
import com.example.btl.object.class_work;

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

        Database db = new Database(context);
        ArrayList<String> e = db.getE(w.get(position).getIdE());

        TextView nameE = convertView.findViewById(R.id.nameE);
        nameE.setText(e.get(0));

        TextView prjName = convertView.findViewById(R.id.projectName);
        prjName.setText("Dự án: " + w.get(position).getPrjName());

        TextView workName = convertView.findViewById(R.id.workName);
        workName.setText("Công việc: " + w.get(position).getWorkName());

        TextView status = convertView.findViewById(R.id.status);
        if(w.get(position).getStatus().equals("Quá hạn")) status.setTextColor(Color.rgb(200,0,0));
        status.setText("Trạng thái: " + w.get(position).getStatus());

        return convertView;
    }
}
