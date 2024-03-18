package com.example.btl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class employeeAdapter extends RecyclerView.Adapter<employeeAdapter.ViewHolder> {

    Context context;
    List<class_employee> listE;

    public employeeAdapter(Context context, List<class_employee> listE) {
        this.context = context;
        this.listE = listE;
    }

    public void setArraylist(List<class_employee> listE){
        this.listE = listE;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public employeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull employeeAdapter.ViewHolder holder, int position) {
        if(listE != null && listE.size() > 0)
        {
            class_employee e = listE.get(position);
            holder.col1.setText(e.getIdE());
            holder.col2.setText(e.getNameE());
            holder.col3.setText(e.getNumWork()+"");
            holder.col4.setText(e.getMission());
            holder.col5.setText(e.getDeadline());
            holder.col6.setText(e.getStatus());
        }else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return listE.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView col1,col2,col3,col4,col5,col6;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            col1 = itemView.findViewById(R.id.col1);
            col2 = itemView.findViewById(R.id.col2);
            col3 = itemView.findViewById(R.id.col3);
            col4 = itemView.findViewById(R.id.col4);
            col5 = itemView.findViewById(R.id.col5);
            col6 = itemView.findViewById(R.id.col6);

        }
    }
}
