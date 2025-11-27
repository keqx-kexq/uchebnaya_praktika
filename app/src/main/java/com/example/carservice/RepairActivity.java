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

public class RepairActivity extends AppCompatActivity {

    private EditText etName, etNumber, etCarModel, etDescription;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);

        database = AppDatabase.getInstance(this);

        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etCarModel = findViewById(R.id.etCarModel);
        etDescription = findViewById(R.id.etDescription);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> saveApplication());

        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomAppBar.setNavigationOnClickListener(v ->
                startActivity(new Intent(RepairActivity.this, MainActivity.class))
        );
    }

    private void saveApplication() {
        String name = etName.getText().toString().trim();
        String number = etNumber.getText().toString().trim();
        String carModel = etCarModel.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (name.isEmpty() || number.isEmpty() || carModel.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        String currentDate = new SimpleDateFormat("HH:mm-dd.MM.yyyy", Locale.getDefault()).format(new Date());
        Application application = new Application(name, number, carModel, description, currentDate, false, "repair");

        database.applicationDao().insert(application);
        Toast.makeText(this, "Заявка сохранена", Toast.LENGTH_SHORT).show();
        finish();
    }
}
