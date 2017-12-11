package io.mta.apo

import android.arch.persistence.room.*
import android.content.Context

/**
 * Created by Michael T. Andemeskel on 12/5/17.
 */
class Pill (val brand_name: String, val medical_name: String, val img_path: String, val description: String) {
    val entity = PillEntity(brand_name, medical_name, img_path, description)

    init {
        // TODO
    }

    /**
     * Does not actually saves the pill, it just adds it to a list of pills that will be saved en masse.
     */
    fun save() {
        // TODO
    }

    /**
     * Returns the image associated with this pill.
     * @return TODO: IDK, if this should be a path or resource id or whatever
     */
    fun getImage() {
        // TODO
    }

    /**
     * Saves image of the pill onto phone for later use.
     */
    fun saveImage() {
        // TODO
    }

    /**
     * Companion object to host static functions and properties.
     *
     * @property DB_NAME the name of the database
     * @property TABLE_NAME the name of the table that hosts the pill info
     * @property db an AppDatabase object for connecting to the database
     * @property save_entities a list of entities that we will save
     */
    companion object Handler {
        const val DB_NAME: String = "apo_db"
        const val TABLE_NAME: String = "pills"
        private var db: AppDatabase? = null
        private val save_entities: MutableList<PillEntity> = mutableListOf()

        /**
         * Creates a AppDatabase instance if one does not already exist and returns a instance of PillDao for running database queries.
         * @param context the context of the calling activity
         * @return the pill data access object, an instance of PillDao
         */
        fun initDb(context: Context): PillDao {
            if(db == null)
                db = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()

            return db!!.pillDao()
        }

        /**
         * Loads saved pills from recent searches list
         * @return a array of pills searched by the user
         */
        fun loadSavedPills(context: Context): Array<PillEntity> {
            val db = initDb(context)
            return db.getAllPills()
        }

        /**
         * Clear the user's saved pills
         */
        fun clearSavedPills(context: Context) {
            val db = initDb(context)
            db.deleteAllPills()
        }

        /**
         * Save passed pills to the database
         * @param pills an array of pills to save
         * @param context the context of the calling activity
         */
        fun savePills(pills: Array<Pill>, context: Context) {
            val db = initDb(context)
            val entities: MutableList<PillEntity> = mutableListOf()
            pills.map { pill -> entities.add(pill.entity) }
            db.insertPills(entities as Array<PillEntity>)
        }

        /**
         * Saves the pills stored in save_entities i.e. Pill::save() saved pills to the database and clears the save_entities list.
         * @param context the context of the calling activity
         */
        fun savePills(context: Context) {
            val db = initDb(context)
            db.insertPills(save_entities as Array<PillEntity>)
            save_entities.clear()
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
}