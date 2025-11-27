package com.example.carservice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.carservice.data.AppDatabase;
import com.example.carservice.data.Application;
import com.google.android.material.bottomappbar.BottomAppBar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ApplicationAdapter adapter;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = AppDatabase.getInstance(this);

        Button btnRepair = findViewById(R.id.btnRepair);
        Button btnPaint = findViewById(R.id.btnPaint);
        Button btnStats = findViewById(R.id.btnStats);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadApplications();

        btnRepair.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, RepairActivity.class))
        );

        btnPaint.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, PaintActivity.class))
        );

        btnStats.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, StatsActivity.class))
        );

        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadApplications();
    }

    private void loadApplications() {
        List<Application> applications = database.applicationDao().getAllApplications();
        adapter = new ApplicationAdapter(applications, application -> {
            application.setTask(true);
            database.applicationDao().update(application);
            loadApplications();
        });
        recyclerView.setAdapter(adapter);
    }
}
