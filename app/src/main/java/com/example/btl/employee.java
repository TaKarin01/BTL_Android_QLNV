package com.example.btl;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class employee extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

       ImageView add_person = findViewById(R.id.add);

       add_person.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                showDialog();
           }
       });
    }
    private void showDialog()
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_add_person);
        dialog.show();
    }
}
