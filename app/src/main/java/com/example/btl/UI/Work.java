package com.example.btl.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.Database.Database;
import com.example.btl.R;
import com.example.btl.object.class_work;
import com.example.btl.Adapter.workAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;

public class Work extends AppCompatActivity {

    LocalDate date = LocalDate.now();
    int year = date.getYear();
    int month = date.getMonthValue();
    int day = date.getDayOfMonth();

    public String checkDeadline(String d)
    {
        String[] arr = d.split("-");
        if(Integer.parseInt(arr[2]) < year){
            return "Quá hạn";
        }
        else
        {
            if(Integer.parseInt(arr[1]) < month) return "Quá hạn";
            else
            {
                if(Integer.parseInt(arr[0]) < day) return "Quá hạn";
                else
                {
                    return "Đang tiến hành";
                }
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        Intent intent = getIntent();
        String str_dept = intent.getStringExtra("dept");

        Database db = new Database(this);
        ArrayList<ArrayList<String>> listE = db.getListE(str_dept);

        ListView lv = findViewById(R.id.listViewWork);

        ArrayList<class_work> listW = new ArrayList<>();
        workAdapter adapter = new workAdapter(this, listW);

        for(ArrayList<String> e: listE)
        {
            String idE = e.get(0);

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            ref.child("Work/" + idE).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists())
                    {
                        String[] arr = new String[3];
                        int index = 0;
                        for(DataSnapshot data : snapshot.getChildren())
                        {
                            arr[index] = data.getValue(String.class);
                            index++;
                        }

                        String status = checkDeadline(arr[0]);


                        class_work w = new class_work(idE,arr[1],arr[2],status);
                        listW.add(w);

                    }
                    else
                    {
                        class_work w = new class_work(idE,"Trống","Trống","Trống");
                        listW.add(w);
                    }

                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        lv.setAdapter(adapter);


    }
}
