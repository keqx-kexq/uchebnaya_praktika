package com.example.carservice.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "applications")
public class Application {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String number;
    private String carModel;
    private String description;
    private String date;
    private boolean task;
    private String type; // "repair" или "paint"

    public Application(String name, String number, String carModel, String description, String date, boolean task, String type) {
        this.name = name;
        this.number = number;
        this.carModel = carModel;
        this.description = description;
        this.date = date;
        this.task = task;
        this.type = type;
    }

    // Getters и Setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getCarModel() { return carModel; }
    public void setCarModel(String carModel) { this.carModel = carModel; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public boolean isTask() { return task; }
    public void setTask(boolean task) { this.task = task; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
