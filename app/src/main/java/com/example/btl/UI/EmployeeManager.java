package com.example.btl.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.R;

public class EmployeeManager extends AppCompatActivity {
    public ImageView user;
    public String ID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_manager);

        Intent intent = getIntent();
        String str_dept = intent.getStringExtra("dept");
        ID = intent.getStringExtra("ID");

        FrameLayout f_employee = findViewById(R.id.employee);
        f_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(EmployeeManager.this,employee.class);
                in.putExtra("dept",str_dept);
                startActivity(in);
            }
        });

        FrameLayout f_timeKeeping = findViewById(R.id.timeKeeping);
        f_timeKeeping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(EmployeeManager.this,TimeKeeping.class);
                in.putExtra("dept",str_dept);
                startActivity(in);
            }
        });

        FrameLayout f_evaluate = findViewById(R.id.evaluate);
        f_evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(EmployeeManager.this, Evaluate.class);
                in.putExtra("dept",str_dept);
                startActivity(in);
            }
        });
        user = findViewById(R.id.user);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu();
            }
        });
    }
    private void showMenu()
    {
        PopupMenu popupMenu = new PopupMenu(this,user);
        popupMenu.getMenuInflater().inflate(R.menu.menu_user,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.signout)
                {
                    Intent intent = new Intent(EmployeeManager.this,startApp.class);
                    startActivity(intent);
                    finish();
                }
                if(item.getItemId() == R.id.profile)
                {
                    Intent intent = new Intent(EmployeeManager.this, userProfile.class);
                    intent.putExtra("ID",ID);
                    startActivity(intent);
                }
                return false;
            }
        });
        popupMenu.show();
    }
}
