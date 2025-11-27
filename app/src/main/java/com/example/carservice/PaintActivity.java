package com.example.carservice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.carservice.data.AppDatabase;
import com.example.carservice.data.Application;
import com.google.android.material.bottomappbar.BottomAppBar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PaintActivity extends AppCompatActivity {
    private EditText etNumber, etCarModel, etPaint;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);

        database = AppDatabase.getInstance(this);

        etNumber = findViewById(R.id.etNumber);
        etCarModel = findViewById(R.id.etCarModel);
        etPaint = findViewById(R.id.etPaint);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> saveApplication());

        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomAppBar.setNavigationOnClickListener(v ->
                startActivity(new Intent(PaintActivity.this, MainActivity.class))
        );
    }

    private void saveApplication() {
        String number = etNumber.getText().toString().trim();
        String carModel = etCarModel.getText().toString().trim();
        String paint = etPaint.getText().toString().trim();

        if (number.isEmpty() || carModel.isEmpty() || paint.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        String currentDate = new SimpleDateFormat("HH:mm-dd.MM.yyyy", Locale.getDefault()).format(new Date());
        Application application = new Application("", number, carModel, paint, currentDate, false, "paint");

        database.applicationDao().insert(application);
        Toast.makeText(this, "Заявка сохранена", Toast.LENGTH_SHORT).show();
        finish();
    }
}
