package com.foxhole.roomdatabasemvvmcrud.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "players_table")
public class Player {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String position;

    private String age;

    public Player(String name, String position, String age) {
        this.name = name;
        this.position = position;
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getAge() {
        return age;
    }
}
