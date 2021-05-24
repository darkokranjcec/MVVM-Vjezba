package hr.algebra.mvvm.database

import androidx.room.Database
import androidx.room.RoomDatabase
import hr.algebra.mvvm.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

}