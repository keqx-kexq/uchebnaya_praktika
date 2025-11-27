package com.example.carservice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.carservice.data.AppDatabase;
import com.google.android.material.bottomappbar.BottomAppBar;

public class StatsActivity extends AppCompatActivity {
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        database = AppDatabase.getInstance(this);

        TextView tvCompleted = findViewById(R.id.tvCompleted);
        TextView tvPending = findViewById(R.id.tvPending);

        int completed = database.applicationDao().getCompletedCount();
        int pending = database.applicationDao().getPendingCount();

        tvCompleted.setText("Выполнено заявок: " + completed);
        tvPending.setText("Не выполнено заявок: " + pending);

        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomAppBar.setNavigationOnClickListener(v ->
                startActivity(new Intent(StatsActivity.this, MainActivity.class))
        );
    }
}
