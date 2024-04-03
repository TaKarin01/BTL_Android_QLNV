package com.example.btl.UI;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.Database.Database;
import com.example.btl.R;
import com.example.btl.object.class_employee;
import com.example.btl.Adapter.employeeAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.LocalCacheSettings;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class employee extends AppCompatActivity {
    String str_dept;

    RecyclerView recyclerView;
    employeeAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        ImageView add_person = findViewById(R.id.add);

        Intent intent = getIntent();
        str_dept = intent.getStringExtra("dept");
        // Lay du lieu nhan vien tu sql
        Database db = new Database(this);
        ArrayList<ArrayList<String>> listE = db.getListE(str_dept);

        recyclerView = findViewById(R.id.recycler_view);
        setRecyclerView();



        add_person.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                showDialog(str_dept);
           }
       });
    }

    private void setRecyclerView() {
        List<class_employee> listE = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        adapter = new employeeAdapter(this,listE);
        recyclerView.setAdapter(adapter);


        Database db = new Database(this);
        ArrayList<ArrayList<String>> getAllE = db.getListE(str_dept);

        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        DatabaseReference ref = fdb.getReference();

        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        String m;

        if(month < 10) m = "0" + month;
        else m = month+"";

        for(ArrayList<String> e : getAllE)
        {

            ref.child("TimeKeeping/"+e.get(0)+"/"+ year+ "/" + m).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int count = (int) snapshot.getChildrenCount();
                    listE.add(new class_employee(e.get(0),e.get(1),count));
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }


    private void showDialog(String str_dept)
    {
        Dialog dialog = new Dialog(employee.this);
        dialog.setContentView(R.layout.activity_add_person);

        //Lay thong tin
        EditText id,name,birth, email;
        RadioButton rb_male;
        id = dialog.findViewById(R.id.IDNV);
        name = dialog.findViewById(R.id.name);
        birth = dialog.findViewById(R.id.dob);
        email = dialog.findViewById(R.id.email);

        rb_male = dialog.findViewById(R.id.male);

        Button btn_add = dialog.findViewById(R.id.add);

        Database db = new Database(employee.this);

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
                    Toast.makeText(employee.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(db.checkIDEmployee(str_id))
                    {
                        Toast.makeText(employee.this, "Đã tồn tại mã nhân viên!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if(db.addEmployee(str_id,str_name,str_birth,str_gender,str_dept,str_email))
                        {
                            Toast.makeText(employee.this, "Thêm mới nhân viên vào ban thành công!", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                            finish();
                            startActivity(getIntent());
                        }
                        else
                        {
                            Toast.makeText(employee.this, "Thêm mới nhân viên không thành công!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        dialog.show();
    }
}
