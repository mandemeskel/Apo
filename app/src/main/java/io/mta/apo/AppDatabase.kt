package io.mta.apo

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

/**
 * A database class, this class is how we interact with the database and get a connection to it. Added DateConverter as a type converter to allow us to save dates in the database as longs.
 *
 * @property pillDao the pill database accessor object so we can query the pill table
 */
@Database(entities = [PillEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase() {

    /**
     * The pill entity's data access object that allows us to run queries on the app's database.
     */
    abstract fun pillDao(): PillDao

}