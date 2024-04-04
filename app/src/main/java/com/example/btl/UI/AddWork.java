package com.example.btl.UI;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.Database.Database;
import com.example.btl.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddWork extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);



        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String idE = bundle.getString("idE");
        String nameE = bundle.getString("nameE");

        EditText id, name, doc, dl, note, prj, work;
        id = findViewById(R.id.idE);
        name = findViewById(R.id.nameE);
        doc = findViewById(R.id.doc);
        dl = findViewById(R.id.deadline);
        note = findViewById(R.id.note);
        prj = findViewById(R.id.prj);
        work = findViewById(R.id.workName);

        id.setText(idE);
        name.setText(nameE);

        dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate(dl);
            }
        });

        Button btn = findViewById(R.id.save);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

                ref.child("JobDescribe").child(idE).child("Doc").setValue(doc.getText().toString());
                ref.child("JobDescribe").child(idE).child("Note").setValue(note.getText().toString());

                String [] date = dl.getText().toString().split("/");
                String d = date[0]+"-"+date[1]+"-"+date[2];

                ref.child("Work").child(idE).child("deadline").setValue(d.toString());
                ref.child("Work").child(idE).child("prjName").setValue(prj.getText().toString());
                ref.child("Work").child(idE).child("workName").setValue(work.getText().toString());


                Toast.makeText(AddWork.this, "Đã giao việc cho nhân viên!", Toast.LENGTH_SHORT).show();
                finish();
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
