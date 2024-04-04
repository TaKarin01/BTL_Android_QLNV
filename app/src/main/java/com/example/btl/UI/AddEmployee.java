package com.example.btl.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.Database.Database;
import com.example.btl.R;

public class AddEmployee extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        Intent intent = getIntent();

        String str_dept = intent.getStringExtra("dept");

        EditText id,name,birth, email;
        RadioButton rb_male;
        id = findViewById(R.id.IDNV);
        name = findViewById(R.id.name);
        birth = findViewById(R.id.dob);
        email = findViewById(R.id.email);

        rb_male = findViewById(R.id.male);

        Button btn_add = findViewById(R.id.add);

        Database db = new Database(AddEmployee.this);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_id = id.getText().toString();
                String str_name = name.getText().toString();
                String str_birth = birth.getText().toString();
                String str_email = email.getText().toString();
                String str_gender;

                if(rb_male.isChecked()) str_gender = "Nam";
                else str_gender = "Nữ";

                if(str_id.equals("") || str_name.equals("") || str_birth.equals("") || str_email.equals(""))
                {
                    Toast.makeText(AddEmployee.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(db.checkIDEmployee(str_id))
                    {
                        Toast.makeText(AddEmployee.this, "Đã tồn tại mã nhân viên!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if(db.addEmployee(str_id,str_name,str_birth,str_gender,str_dept,str_email))
                        {
                            Toast.makeText(AddEmployee.this, "Thêm mới nhân viên vào ban thành công!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(AddEmployee.this, "Thêm mới nhân viên không thành công!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}
