package com.foxhole.roomdatabasemvvmcrud.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.foxhole.roomdatabasemvvmcrud.Database.PlayerDatabase;
import com.foxhole.roomdatabasemvvmcrud.Model.Player;
import com.foxhole.roomdatabasemvvmcrud.Database.Dao.PlayerDao;

import java.util.List;

public class PlayerRepository {

    private PlayerDao playerDao;
    private LiveData<List<Player>> allPlayers;

    public PlayerRepository(Application application)
    {
        PlayerDatabase playerDatabase = PlayerDatabase.getInstance(application);
        playerDao = playerDatabase.playerDao();

        allPlayers = playerDao.getAllPlayers();
    }

    public void insert(Player player) {
        new InsertPlayerAsyncTask(playerDao).execute(player);
    }

    public void update(Player player){
        new UpdatePlayerAsyncTask(playerDao).execute(player);
    }

    public void delete(Player player){
        new DeletePlayerAsyncTask(playerDao).execute(player);
    }

    public void deleteAllPlayers() {
        new DeleteAllPlayersAsyncTask(playerDao).execute();
    }

    public LiveData<List<Player>> getAllPlayers() {
        return allPlayers;
    }

    //AsyncTask for create new player
    private static class InsertPlayerAsyncTask extends AsyncTask<Player, Void, Void>{

        private PlayerDao playerDao;

        public InsertPlayerAsyncTask(PlayerDao playerDao) {
            this.playerDao = playerDao;
        }


        @Override
        protected Void doInBackground(Player... players) {
            playerDao.insert(players[0]);
            return null;
        }
    }

    //AsyncTask for update existing player
    private static class UpdatePlayerAsyncTask extends AsyncTask<Player, Void, Void>{

        private PlayerDao playerDao;

        public UpdatePlayerAsyncTask(PlayerDao playerDao) {
            this.playerDao = playerDao;
        }

        @Override
        protected Void doInBackground(Player... players) {
            playerDao.update(players[0]);
            return null;
        }
    }

    //AsyncTask for delete existing player
    private static class DeletePlayerAsyncTask extends AsyncTask<Player, Void, Void>{

        private PlayerDao playerDao;

        public DeletePlayerAsyncTask (PlayerDao playerDao){
            this.playerDao = playerDao;
        }

        @Override
        protected Void doInBackground(Player... players) {
            playerDao.delete(players[0]);
            return null;
        }
    }

    //AsyncTask for delete all players
    private static class DeleteAllPlayersAsyncTask extends AsyncTask<Void, Void, Void>{

        private PlayerDao playerDao;

        public DeleteAllPlayersAsyncTask(PlayerDao playerDao){
            this.playerDao = playerDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            playerDao.deleteAllPlayer();
            return null;
        }
    }



}
