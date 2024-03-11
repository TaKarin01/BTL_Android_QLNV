package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class userProfile extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profle);
        // Lay dữ liệu ở trang trước
        Intent intent = getIntent();
        String Id = intent.getStringExtra("ID");
        // Mở CSDL
        Database db = new Database(this);
        //Lay thong tin
        ArrayList<String> admin = db.getAdmin(Id);
        // Tim den các plain text thong qua id
        EditText name, id, birth, sex, mail,dept;
        id = findViewById(R.id.idUser);
        name = findViewById(R.id.fullName);
        birth = findViewById(R.id.birthday);
        sex = findViewById(R.id.sex);
        mail = findViewById(R.id.email);
        dept = findViewById(R.id.department);

        //gán các giá trị trong CSDL lên các plain text
        id.setText(admin.get(0));
        name.setText(admin.get(1));
        birth.setText(admin.get(2));
        sex.setText(admin.get(3));
        mail.setText(admin.get(4));
        dept.setText(admin.get(5));
    }
}
