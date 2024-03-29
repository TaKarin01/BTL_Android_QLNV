package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EmployeeManager extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_manager);

        Intent intent = getIntent();
        String str_dept = intent.getStringExtra("dept");

        FrameLayout f_employee = findViewById(R.id.employee);
        f_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(EmployeeManager.this,employee.class);
                in.putExtra("dept",str_dept);
                startActivity(in);
            }
        });

    }
}
