package com.example.btl.UI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl.Database.Database;
import com.example.btl.R;
import com.example.btl.object.class_work;
import com.example.btl.Adapter.workAdapter;

import java.util.ArrayList;

public class Work extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        Intent intent = getIntent();
        String str_dept = intent.getStringExtra("dept");

        Database db = new Database(this);
        ArrayList<ArrayList<String>> listE = db.getListE(str_dept);

        ListView lv = findViewById(R.id.listViewWork);

        ArrayList<class_work> listW = new ArrayList<>();

        for(ArrayList<String> e: listE)
        {
            String name = e.get(1);
            class_work w = new class_work(name);
            listW.add(w);
        }
        workAdapter adapter = new workAdapter(this, listW);
        lv.setAdapter(adapter);

    }
}
