package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText userName = findViewById(R.id.userName);
        EditText passWord = findViewById(R.id.passWord);
        TextView forgotTV = findViewById(R.id.forgotPass);
        Button btn = findViewById(R.id.button_login);
        Database db = new Database(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String pass = passWord.getText().toString();
                if(user.equals("") || pass.equals(""))
                {
                    Toast.makeText(login.this, "Hãy nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!db.check_ID(user))
                    {
                        Toast.makeText(login.this, "Mã nhân viên không tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(!db.checkLogin(user,pass))
                        {
                            Toast.makeText(login.this, "Sai mật khẩu!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent intent = new Intent(login.this, home.class);
                            intent.putExtra("ID",user);
                            intent.putExtra("Name",db.getAdminName(user));
                            startActivity(intent);
                        }
                    }
                }
            }
        });
        forgotTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, forgotPass.class);
                startActivity(intent);
            }
        });
    }
}

