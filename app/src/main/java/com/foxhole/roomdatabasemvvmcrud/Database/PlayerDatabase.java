package com.foxhole.roomdatabasemvvmcrud.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.foxhole.roomdatabasemvvmcrud.Model.Player;
import com.foxhole.roomdatabasemvvmcrud.Database.Dao.PlayerDao;

@Database(entities = {Player.class},version = 1)
public abstract class PlayerDatabase extends RoomDatabase {

    private static PlayerDatabase instance;

    public abstract PlayerDao playerDao();

    public static synchronized PlayerDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            //If instance is null that's mean database is not created and create a new database instance
            instance = Room.databaseBuilder(context.getApplicationContext(),PlayerDatabase.class,"player_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
//            new PopulateDbAsyncTask(instance).execute();
        }
    };

//    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
//
//        private PlayerDao playerDao;
//
//        public PopulateDbAsyncTask(PlayerDatabase db) {
//            this.playerDao = db.playerDao();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            playerDao.insert(new Player("Arther","Midfielder","23"));
//            playerDao.insert(new Player("Suarez","Forward","32"));
//            playerDao.insert(new Player("Griezmann","Forward","28"));
//            return null;
//        }
//    }


}
