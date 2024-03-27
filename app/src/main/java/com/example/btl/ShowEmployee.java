package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;

public class ShowEmployee extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_employee);

        TextView id, name, dob, mail;
        RadioButton female;
        Database db = new Database(this);

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

            }
        });
    }

    public void makeNotification()
    {
        String str = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),str);
        builder.setSmallIcon(R.drawable.notification);
        builder.setContentTitle("Thông báo!");
        builder.setContentText("Bạn có chắc muốn xóa nhân viên này!");
        builder.setAutoCancel(true).setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(getApplicationContext(), Notification.class);
    }
}
