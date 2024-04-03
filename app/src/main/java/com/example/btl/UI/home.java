package com.example.btl.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.Database.Database;
import com.example.btl.R;

import java.util.ArrayList;

public class home extends AppCompatActivity {
    public ImageView user;
    public String ID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        ID = intent.getStringExtra("ID");
        TextView hi = findViewById(R.id.hello);
        hi.setText("Xin chào " + name);

        user = findViewById(R.id.user);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu();
            }
        });

        FrameLayout btn_employee = findViewById(R.id.employee);
        Database db = new Database(this);
        btn_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(home.this,EmployeeManager.class);

                ArrayList<String> admin = db.getAdmin(ID);
                in.putExtra("dept",admin.get(4));

                startActivity(in);
            }
        });

        FrameLayout btn_work = findViewById(R.id.work);
        btn_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(home.this, Work.class);

                ArrayList<String> admin = db.getAdmin(ID);
                in.putExtra("dept",admin.get(4));

                startActivity(in);
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
                    Intent intent = new Intent(home.this,startApp.class);
                    startActivity(intent);
                    finish();
                }
                if(item.getItemId() == R.id.profile)
                {
                    Intent intent = new Intent(home.this, userProfile.class);
                    intent.putExtra("ID",ID);
                    startActivity(intent);
                }
                return false;
            }
        });
        popupMenu.show();
    }
}
