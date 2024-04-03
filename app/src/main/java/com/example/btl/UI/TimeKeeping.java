package com.example.btl.UI;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.Adapter.TimeKeepingAdapter;
import com.example.btl.Database.Database;
import com.example.btl.R;
import com.example.btl.object.class_timeKeeping;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class TimeKeeping extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_keeping);

        Intent intent = getIntent();
        String dept = intent.getStringExtra("dept");

        Database db = new Database(this);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        EditText edtCalendar = findViewById(R.id.calendar);


        edtCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate(edtCalendar);
            }
        });

        Button btn = findViewById(R.id.search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] date = edtCalendar.getText().toString().split("/");
                String year = date[2];
                String month = date[1];
                String day = date[0];

                TextView test = findViewById(R.id.test);
                //test.setText(year+"/"+month+"/"+day);

                lv = findViewById(R.id.listView);

                ArrayList<ArrayList<String>> listE = db.getListE(dept);
                ArrayList<class_timeKeeping> list = new ArrayList<>();

                TimeKeepingAdapter adapter = new TimeKeepingAdapter(TimeKeeping.this,list);

                list.clear();

                for(ArrayList<String> e : listE)
                {
                    String idE = e.get(0);
                    String nameE = e.get(1);
                    class_timeKeeping tk = new class_timeKeeping(idE,nameE,"unchecked");
                    ref.child("TimeKeeping/"+idE+"/"+year+"/"+month+"/"+day).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String check;
                            if(snapshot.exists()) check = snapshot.getValue(String.class);
                            else check = "null";
                            class_timeKeeping tk = new class_timeKeeping(idE,nameE,check);
                            list.add(tk);

                            adapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                lv.setAdapter(adapter);
            }
        });

    }


    public void pickDate(EditText c)
    {
        Calendar calendar = Calendar.getInstance();
        int day = (calendar.get(Calendar.DATE));
        int month = (calendar.get(Calendar.MONTH));
        int year = (calendar.get(Calendar.YEAR));
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                c.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year,month,day);
        datePickerDialog.show();
    }

}
