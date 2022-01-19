package com.platzi.cursoandroidudemy.data.model.remote.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.platzi.cursoandroidudemy.data.model.remote.MovieEntity


@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movie_table"
                ).build()

            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }

}