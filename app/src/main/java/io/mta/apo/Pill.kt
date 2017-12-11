package io.mta.apo

import android.arch.persistence.room.*

/**
 * Created by Michael T. Andemeskel on 12/5/17.
 */
class Pill (val brand_name: String, val medical_name: String, val img_path: String, val description: String) {

    init {
        // TODO
    }

    /**
     * Saves pill to recent searches list.
     */
    fun save() {
        // TODO
    }

    // companion object to host static functions and properties
    companion object Handler {
        const val TABLE_NAME: String = "pills"

        /**
         * Loads saved pills from recent searches list
         * @return a array of pills searched by the user
         */
        fun loadSavedPills(): Array<Pill> {
            // TODO
            var pills: Array<Pill> = arrayOf()
            return pills
        }

        /**
         * Clear the user's saved pills
         */
        fun clearSavedPills() {
            // TODO
        }
    }

    @Entity(tableName = Pill.TABLE_NAME)
    class PillEntity(val brand_name: String, val medical_name: String, val img_path: String, val description: String) {
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null
        // TODO: should be img_path be a foreign-key, will there be more than one image per pill?

    }

    /**
     * A data access object, this interface which will be implemented by the room service and the doa object it is a part of will be called when making queries on the database.
     *
     * @property insertPill inserts a single pill entity into the database
     * @property getAllPills queries the pills table and returns all the pills in it
     * @property deletePills delete an array pills passed to the function
     * @property deleteAllPills delete all the pills inn the pills table
     */
    @Dao
    interface PillDao {
        /**
         * Insert a single pill into the database, if it already exists replace the old one.
         * @param pill the PillEntity to be inserted into the database
         */
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertPill(pill: PillEntity)

        /**
         * Queries the db for all pills and returns the results.
         * @return an array of pill entities
         */
        @Query("SELECT * FROM ${Pill.TABLE_NAME}")
        fun getAllPills(): Array<PillEntity>

        /**
         * Deletes the provided list of pill entities from pills table.
         * @param pills array of pill entities to delete
         * @return int, the number of pills deleted
         */
        @Delete
        fun deletePills(pills: Array<PillEntity>): Int

        /**
         * Delete all the pills in the pills table.
         */
        @Query("DELETE FROM ${Pill.TABLE_NAME}")
        fun deleteAllPills()
    }
}