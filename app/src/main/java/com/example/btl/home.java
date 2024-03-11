package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
