package com.example.btl.UI;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.Database.Database;
import com.example.btl.R;
import com.example.btl.object.class_employee;

import java.util.ArrayList;

public class ShowEmployee extends AppCompatActivity {
    Database db = new Database(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_employee);

        TextView id, name, dob, mail;
        RadioButton female;


        Intent inten = getIntent();
        Bundle bundle = inten.getExtras();
        class_employee e =(class_employee) bundle.getSerializable("employee");

        id = findViewById(R.id.IDNV);
        id.setText(e.getIdE());

        ArrayList<String> employee = db.getE(e.getIdE());
        name = findViewById(R.id.name);
        name.setText(employee.get(0));

        dob = findViewById(R.id.dob);
        dob.setText(employee.get(1));

        female = findViewById(R.id.female);
        if(employee.get(2).equals("Nữ"))
        {

            female.setChecked(true);
        }

        mail = findViewById(R.id.email);
        mail.setText(employee.get(3));

        Button change = findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_id = id.getText().toString();
                String str_name = name.getText().toString();
                String str_dob = dob.getText().toString();
                String str_mail = mail.getText().toString();
                String str_gender;
                if(!female.isChecked())
                {
                    str_gender = "Nữ";
                }
                else
                {
                    str_gender = "Nam";
                }
                if(db.changeE(str_id,str_name,str_dob,str_gender,str_mail))
                {
                    Toast.makeText(ShowEmployee.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ShowEmployee.this, "Cập nhật không thành công!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button del = findViewById(R.id.del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_name = name.getText().toString();
                String str_id = id.getText().toString();
                showDialog(str_name,str_id);

            }
        });
    }
    public void showDialog(String name, String id)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowEmployee.this, R.style.AlertDialog);
        View view = LayoutInflater.from(ShowEmployee.this).
                inflate(R.layout.dialog_warring, findViewById(R.id.dialog));

        builder.setView(view);

        TextView content = view.findViewById(R.id.content);
        content.setText("Bạn có chắc muốn xóa thông tin nhân viên " + name +"?");

        AlertDialog alertDialog = builder.create();

        Button btn_no = view.findViewById(R.id.no);
        Button btn_yes = view.findViewById(R.id.yes);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.delE(id))
                {
                    Toast.makeText(ShowEmployee.this, "Xóa nhân viên thành công", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    startActivity(new Intent(ShowEmployee.this,employee.class));
                    finish();
                }
                else
                {
                    Toast.makeText(ShowEmployee.this, "Xóa nhân viên không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(alertDialog.getWindow() != null)
        {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

    }


}
