package com.example.btl;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class employee extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

       ImageView add_person = findViewById(R.id.add);

       add_person.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                showDialog();
           }
       });
    }
    private void showDialog()
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_add_person);

        //Lay thong tin
        EditText id,name,birth, dept, email;
        RadioButton rb_male, rb_female;
        id = dialog.findViewById(R.id.IDNV);
        name = dialog.findViewById(R.id.name);
        birth = dialog.findViewById(R.id.dob);
        dept = dialog.findViewById(R.id.department);
        email = dialog.findViewById(R.id.email);

        rb_male = dialog.findViewById(R.id.male);
        rb_female = dialog.findViewById(R.id.female);

        Button btn_add = dialog.findViewById(R.id.add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_id = id.getText().toString();
                String str_name = name.getText().toString();
                String str_birth = birth.getText().toString();
                String str_dept = dept.getText().toString();
                String str_email = email.getText().toString();
                
                if(str_id.equals("") || str_name.equals("") || str_birth.equals("") || str_dept.equals("") || str_email.equals(""))
                {
                    Toast.makeText(employee.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
                else
                {

                }
            }
        });
        dialog.show();
    }
}
