package io.mta.apo

import android.arch.persistence.room.*

/**
 * A data access object, this interface which will be implemented by the room service and the doa object it is a part of will be called when making queries on the database.
 *
 * @property insertPill inserts a single pill entity into the database
 * @property insertPills inserts multiple pill entities into the database
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
     * Inserts an array of pills into the database, if a pill already exists replace it...
     * @param pills the array of PillEntities to be inserted into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPills(pills: Array<PillEntity>)

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