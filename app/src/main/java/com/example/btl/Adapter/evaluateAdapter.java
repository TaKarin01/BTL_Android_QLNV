package com.example.btl.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import com.example.btl.Database.Database;
import com.example.btl.R;
import com.example.btl.UI.AddWork;
import com.example.btl.UI.DetailEvaluate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class evaluateAdapter extends BaseAdapter {
    Context context;
    ArrayList<ArrayList<String>> listE;

    public evaluateAdapter(Context context, ArrayList<ArrayList<String>> listE) {
        this.context = context;
        this.listE = listE;
    }

    @Override
    public int getCount() {
        return listE.size();
    }

    @Override
    public Object getItem(int position) {
        return listE.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = View.inflate(parent.getContext(), R.layout.evaluate_item, null);
        }

        TextView idE = convertView.findViewById(R.id.idE);
        TextView name = convertView.findViewById(R.id.nameE);

        idE.setText(listE.get(position).get(0));
        name.setText(listE.get(position).get(1));

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        View finalConvertView = convertView;
        ref.child("Evaluate").child(listE.get(position).get(0).toString()).child("LateDl").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int lateDl = snapshot.getValue(Integer.class);
                if(lateDl != 0)
                {
                    FrameLayout f = finalConvertView.findViewById(R.id.evaluate);
                    f.setBackgroundColor(Color.rgb(255,10,10));
                    TextView text = finalConvertView.findViewById(R.id.text);
                    text.setText("Chưa tốt");
                    ImageView img = finalConvertView.findViewById(R.id.icon);
                    img.setImageResource(R.drawable.bad);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailEvaluate.class);
                Bundle bundle = new Bundle();

                bundle.putString("idE",listE.get(position).get(0));
                bundle.putString("nameE",listE.get(position).get(1));
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
