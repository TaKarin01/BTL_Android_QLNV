package com.example.btl.UI;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;

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

        EditText id, name, doc, dl, note;
        id = findViewById(R.id.idE);
        name = findViewById(R.id.nameE);
        doc = findViewById(R.id.doc);
        dl = findViewById(R.id.deadline);
        note = findViewById(R.id.note);

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
