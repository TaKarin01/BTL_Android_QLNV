package com.example.btl.UI;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;
import com.example.btl.object.class_employee;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;

public class DetailEvaluate extends AppCompatActivity {
    LocalDate date = LocalDate.now();
    int year = date.getYear();
    int month = date.getMonthValue();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_evaluate);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String idE = bundle.getString("idE");
        String nameE = bundle.getString("nameE");

        TextView id, name, time, dl;

        id = findViewById(R.id.id);
        id.setText("ID: " + idE);

        name = findViewById(R.id.name);
        name.setText("Họ và tên: " + nameE);

        time = findViewById(R.id.timeKeeping);

        dl = findViewById(R.id.lateDL);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();


        String m;
        if(month == 1) {
            m = "12";
            year-=1;
        }
        else
        {
            month-=1;
        }

        if(month < 10) m = "0" + month;
        else m = month+"";

        ref.child("TimeKeeping/"+idE+"/"+ year+ "/" + m).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = (int) snapshot.getChildrenCount();
                time.setText("Số công: " + count);

                if(count < 26) time.setTextColor(Color.RED);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref.child("Evaluate/"+idE+"/LateDl").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int lateDl = snapshot.getValue(Integer.class);
                if(lateDl != 0)
                {
                    dl.setText("Chậm deadline: "+lateDl);
                    dl.setTextColor(Color.RED);
                }
                else
                {
                    dl.setText("Chậm deadline: 0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
