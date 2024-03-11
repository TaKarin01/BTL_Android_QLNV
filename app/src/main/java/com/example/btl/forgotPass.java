package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class forgotPass extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        EditText userName = findViewById(R.id.IDNV);
        EditText email = findViewById(R.id.gmail);
        EditText passWord = findViewById(R.id.passWord);
        EditText confirmPassWord = findViewById(R.id.confirmPass);

        Button btn = findViewById(R.id.btn);
        Database db = new Database(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String mail = email.getText().toString();
                String pass = passWord.getText().toString();
                String confPass = confirmPassWord.getText().toString();

                if(name.equals("") || mail.equals("") || pass.equals("") || confPass.equals(""))
                {
                    Toast.makeText(forgotPass.this, "Hãy nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(!db.check_ID(name))
                    {
                        Toast.makeText(forgotPass.this, "Không tồn tại tài khoản!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(!db.checkMail(name,mail))
                        {
                            Toast.makeText(forgotPass.this, "Email không đúng!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if(!pass.equals(confPass))
                            {
                                Toast.makeText(forgotPass.this, "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                if(!db.updatePass(name,pass))
                                {
                                    Toast.makeText(forgotPass.this, "Cập nhật mật khẩu không thành công!", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(forgotPass.this, "Cập nhật mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(forgotPass.this, login.class);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}
