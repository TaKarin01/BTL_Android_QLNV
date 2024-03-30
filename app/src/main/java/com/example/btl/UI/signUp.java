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

import java.text.SimpleDateFormat;

public class signUp extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText userID, email,userName,birth,dept, pass, confirmPass;
        userName = findViewById(R.id.name);
        birth = findViewById(R.id.ngaySinh);
        dept = findViewById(R.id.phong);
        userID = findViewById(R.id.IDNV);
        email = findViewById(R.id.gmail);
        pass = findViewById(R.id.passWord);
        confirmPass = findViewById(R.id.confirmPass);
        Button btn = findViewById(R.id.btn);

        RadioButton s_m,s_f;
        s_m = findViewById(R.id.male);
        s_f = findViewById(R.id.female);

        Database db = new Database(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String ID = userID.getText().toString();
                String mail = email.getText().toString();
                String passWord = pass.getText().toString();
                String confPass = confirmPass.getText().toString();
                String name = userName.getText().toString();
                String s;
                if(s_m.isChecked())
                    s = "Nam";
                else
                {
                    s = "Nữ";
                }
                String birthday = birth.getText().toString();
                String department = dept.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                if(ID.equals("") || mail.equals("") || passWord.equals("") || confPass.equals("") || name.equals("") || birthday.equals("") || department.equals(""))
                {
                    Toast.makeText(signUp.this, "Xin hãy nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(db.check_ID(ID))
                    {
                        Toast.makeText(signUp.this, "Đã tồn tại tài khoản!", Toast.LENGTH_SHORT).show();
                        userID.setText("");
                    }
                    else {
                        try {
                            sdf.parse(birthday);
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(signUp.this, "Không đúng dạng ngày/tháng/năm sinh!", Toast.LENGTH_SHORT).show();
                        }
                        String emailPattern = "\\w+@\\w+[.]\\w+";
                        Boolean check = mail.matches(emailPattern);
                        if(!check)
                        {
                            Toast.makeText(signUp.this, "Email khong hop le", Toast.LENGTH_SHORT).show();
                            email.setText("");
                        }
                        else
                        {
                            if(!passWord.equals(confPass))
                            {
                                Toast.makeText(signUp.this, "Mật khẩu chưa khớp!", Toast.LENGTH_SHORT).show();
                                pass.setText("");
                                confirmPass.setText("");
                            }
                            else {
                                if(db.insertAdmin(ID,name,birthday+"",s,mail,department,passWord))
                                {
                                    Intent intent = new Intent(signUp.this, SignUpSuccessfully.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(signUp.this, "Đăng kí không thành công!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }
                }
            }
        });
    }
}
