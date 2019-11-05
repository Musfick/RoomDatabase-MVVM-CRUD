package com.foxhole.roomdatabasemvvmcrud.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.foxhole.roomdatabasemvvmcrud.Model.Player;

import java.util.List;

@Dao
public interface PlayerDao {

    //Insert new player in table
    @Insert
    void insert(Player player);

    //Update single player in table
    @Update
    void update(Player player);

    //Delete single player from table
    @Delete
    void delete(Player player);

    //Delete all player from table
    @Query("DELETE FROM players_table")
    void deleteAllPlayer();

    //Get all the list of player from table by descending order
    @Query("SELECT * FROM players_table ")
    LiveData<List<Player>> getAllPlayers();

}
