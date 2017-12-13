package io.mta.apo

import android.arch.persistence.room.*
import android.content.Context
import android.util.Log

/**
 * Created by Michael T. Andemeskel on 12/5/17.
 */
class Pill (val id: Int, val brand_name: String, val medical_name: String, val img_path: String, val description: String) {
    val entity = PillEntity(id, brand_name, medical_name, img_path, description)
    var saved = false

    init {
        // TODO
    }

    /**
     * Does not actually saves the pill, it just adds it to a list of pills that will be saved en masse.
     */
    fun save() {
        if(saved) return Unit
        saved = true
        Pill.save_entities.add(this.entity)
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
        private val save_entities: MutableSet<PillEntity> = mutableSetOf()

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
            for( pill in pills )
                pill.save()
            savePills(context)
        }

        /**
         * Saves the pills stored in save_entities i.e. Pill::save() saved pills to the database and clears the save_entities list.
         * @param context the context of the calling activity
         */
        fun savePills(context: Context) {
            val db = initDb(context)
            db.insertPills(save_entities.toTypedArray())
            save_entities.clear()
        }

        var loaded_pills: Array<Pill> = emptyArray()
        fun loadPills(context: Context) {
            val thread = Thread(Runnable {
                val db = initDb(context)
                val entities = db.getAllPills()
                loaded_pills = (entities.map { entity -> entityToPill(entity) }).toTypedArray()
                Log.i("Pill", loaded_pills.size.toString())
            } )
            thread.start()
        }

        fun saveAllPills(context: Context) {
            val thread = Thread(Runnable {
                val db = initDb(context)
                db.insertPills(save_entities.toTypedArray())
                save_entities.clear()
            } )
            thread.start()
        }

        fun entityToPill(entity: PillEntity): Pill {
            return Pill(
                    entity.id,
                    entity.brand_name,
                    entity.medical_name,
                    entity.img_path,
                    entity.description
            )
        }

    }


}