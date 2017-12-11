package io.mta.apo

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by Michael T. Andemeskel on 12/10/17.
 */
/**
 * A database class, this class is how we interact with the database and get a connection to it.
 *
 * @property pillDao the pill database accessor object so we can query the pill table
 */
@Database(entities = [PillEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pillDao(): PillDao
}