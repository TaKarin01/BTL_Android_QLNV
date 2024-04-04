package com.example.btl.UI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.Adapter.evaluateAdapter;
import com.example.btl.Database.Database;
import com.example.btl.R;

import java.util.ArrayList;

public class Evaluate extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);

        Intent intent = getIntent();
        String str_dept = intent.getStringExtra("dept");

        Database db = new Database(this);
        ArrayList<ArrayList<String>> listE = db.getListE(str_dept);

        ListView lv = findViewById(R.id.listEvaluate);

        evaluateAdapter adapter = new evaluateAdapter(this,listE);

        lv.setAdapter(adapter);
    }
}
