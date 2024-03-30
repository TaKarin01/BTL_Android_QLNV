package com.example.btl.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.UI.ShowEmployee;
import com.example.btl.object.class_employee;

import java.util.List;

public class employeeAdapter extends RecyclerView.Adapter<employeeAdapter.ViewHolder> {

    Context context;
    List<class_employee> listE;


    public employeeAdapter(Context context,List<class_employee> listE) {
        this.context = context;
        this.listE = listE;
    }


    @NonNull
    @Override
    public employeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull employeeAdapter.ViewHolder holder, int position) {
        if(listE != null && listE.size() > 0)
        {
            class_employee e = listE.get(position);
            holder.name.setText(e.getNameE());
            holder.id.setText(e.getIdE());
            holder.numWork.setText(e.getNumWork()+"/26");

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickGoToDetail(e);
                }
            });
        }else {
            return;
        }
    }

    private void onClickGoToDetail(class_employee e) {
        Intent intent = new Intent(context, ShowEmployee.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("employee",e);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return listE.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView name,id, numWork;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.linear_item);
            name = itemView.findViewById(R.id.nameE);
            id = itemView.findViewById(R.id.idE);
            numWork = itemView.findViewById(R.id.content2);
        }
    }

}
